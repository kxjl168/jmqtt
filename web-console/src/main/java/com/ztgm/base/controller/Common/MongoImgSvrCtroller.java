package com.ztgm.base.controller.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.ztgm.base.service.impl.SvrFileInfoServiceImpl;
import com.ztgm.base.aopAspect.NoNeedAuthorization;
import com.ztgm.base.controller.mongo.MongoGeoDao;
import com.ztgm.base.controller.mongo.NormalImg2Mongo;
import com.ztgm.base.pojo.SvrFileInfo;
import com.ztgm.base.service.SvrFileInfoService;
import com.ztgm.base.util.DateUtil;
import com.ztgm.base.util.JsonUtil;
import com.ztgm.base.util.StringUtil;
import com.ztgm.base.util.md5.Md5EncryptFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * mongo文件服务器实现
 * 
 * 
 * @date 20190211
 * @author zj
 * 
 */
@Controller
@RequestMapping(value = "/MongoSvr")
@PropertySource("classpath:project.properties")
public class MongoImgSvrCtroller {

	// 日志记录对象
	private Logger logger = Logger.getLogger(MongoImgSvrCtroller.class);

	@Autowired
	private SvrFileInfoServiceImpl fileService;

	@Value("${FILE_SAVE_PATH}")
	private String FILE_SAVE_PATH;

	@Value("${HTTP_PATH}")
	private String HTTP_PATH;

	@Value("${PIC_MAXSIZE}")
	private int PIC_MAXSIZE;

	@Value("${VID_MAXSIZE}")
	private int VID_MAXSIZE;

	@Autowired
	MongoGeoDao mongoGeoDao;

	/**
	 * mongodb获取 下载文件，计算下载数+1
	 * 
	 * @param request
	 * @param response
	 * @date 20190211
	 * @author zj
	 */
	@NoNeedAuthorization
	@RequestMapping(value = "/downFile")
	public void downFile(String m5, HttpServletRequest request, HttpServletResponse response) {
		try {

			String md5 = request.getParameter("m5");
			if (md5 == null || md5.equals(""))
				md5 = m5;

			SvrFileInfo query = new SvrFileInfo();
			query.setFile_md5(md5);

			SvrFileInfo sInfo = fileService.getFileInfo(query);
			if (sInfo != null) {
				//File file = new File(sInfo.getFull_path());

				//if (file.exists()) {

					//if (file != null) {

						try {

							NormalImg2Mongo Imgs = mongoGeoDao.getNormalImg(md5);

							byte[] byteimg = new byte[] {};
							try {

								byteimg = new BASE64Decoder().decodeBuffer(Imgs.getImg_file());

							} catch (Exception e) {

							}

							response.setCharacterEncoding("UTF-8");
							response.setContentType("application/octet-stream");
							response.setHeader("Content-Disposition", "attachment;filename=head.png");// mod
							ServletOutputStream out = response.getOutputStream();
							out.write(byteimg);
							out.flush();
							out.close();

						} catch (Exception e) {
							logger.error("error", e);
						}

						fileService.addFileDonwLoadNums(sInfo);
				//	}
				//}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件服务器接收上传文件
	 * 
	 * @param receiveFiles
	 * @param request
	 *            path属性指定文件存储的第二级目录，不指定则默认存放至tmp目录
	 * @param response
	 * @date 2016-7-12
	 * @author zj
	 */
	@RequestMapping(value = "/UploadFile")
	public @ResponseBody String upload(@RequestParam(value = "file", required = false) MultipartFile[] receiveFiles,
			HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonOut = new JSONObject();

		String curDir = "tmp"; // 服务器文件存储目录-默认 tmp

		/*
		 * String path = request.getParameter("path"); if (!StringUtil.isNull(path))
		 * curDir = path;
		 */

		// 设置图片类型
		String extension = ".png";
		// 获取 服务器上文件存储绝对路径
		String uploadPath = FILE_SAVE_PATH;// ConfigReader.getInstance().getProperty("FILE_SAVE_PATH");

		// web访问相对路径
		String http_path = HTTP_PATH;// ConfigReader.getInstance().getProperty("HTTP_PATH");

		String relativeURL = ""; // 文件相对路径
		String httpURL = ""; // 文件http完整路径

		String downURL = "";
		String httpDownURL = "";
		String md5 = "";
		String fileName = "";
		String os = System.getProperty("os.name");
		try {

			// 增加月份分级
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String formattedDate = formatter.format(new Date());

			String relativePath = curDir + "/" + formattedDate + "/";
			logger.info("uploadPath:  " + uploadPath + relativePath);

			File dir = new File(uploadPath + relativePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			for (int i = 0; i < receiveFiles.length; i++) {

				MultipartFile mfile = receiveFiles[i];

				// 文件
				// itemFile = item;

				// String name =mfile.getOriginalFilename();//
				// item.getFieldName();

				String value = mfile.getOriginalFilename();// .getName();//
															// item.getName();

				extension = value.substring(value.lastIndexOf("."), value.length());

				int picMax = PIC_MAXSIZE;// ConfigReader.getInstance().getIntProperty("PIC_MAXSIZE", 10);
				int vidMax = VID_MAXSIZE;// ConfigReader.getInstance().getIntProperty("VID_MAXSIZE", 50);

				if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")
						|| extension.equalsIgnoreCase("bmp") || extension.equalsIgnoreCase("jpeg")) {
					if (mfile.getSize() > picMax * 1024 * 1024) {
						jsonOut.put("ResponseCode", "201");
						jsonOut.put("ResponseMsg", "FAILED:文件超出" + picMax + "M大小！");
						// JsonUtil.responseOutWithJson(response, jsonOut.toString());
						return jsonOut.toString();
					}
				} else // 非图片文件，最大50M
				{
					if (mfile.getSize() > vidMax * 1024 * 1024) {
						jsonOut.put("ResponseCode", "201");
						jsonOut.put("ResponseMsg", "FAILED:文件超出" + picMax + "M大小！");
						// JsonUtil.responseOutWithJson(response, jsonOut.toString());
						return jsonOut.toString();
					}
				}

				UUID uuid = UUID.randomUUID();
				fileName = uuid.toString() + extension; // 随机生成的唯一文件名

				md5 = new Md5EncryptFile().getMD5(mfile);
				boolean isExsit = false;

				SvrFileInfo query = new SvrFileInfo();
				query.setFile_md5(md5);
				SvrFileInfo efile = fileService.getFileInfo(query);

				String absoluteURL = "mongodb" + mongoGeoDao.getDbInfo() + " id:" + md5; // 文件存放的绝对路径
				// logger.info("name:" + name);

				if (efile != null)
					isExsit = true;

				// 计算md5,查看本地文件库中是否有相同的文件
				if (!isExsit) {

					// 没有，则上传
					// 存储mongodb

					NormalImg2Mongo mimg = new NormalImg2Mongo();
					mimg.setId(md5);
					mimg.setUId(value);

					String strBase64 = new BASE64Encoder().encode(mfile.getBytes());

					mimg.setImg_file(strBase64);
					mimg.setImg_file_uptime(DateUtil.getNowStr(""));

					mongoGeoDao.saveOrUpdateNormalImg(mimg);

					logger.info("文件存放的绝对路径:" + absoluteURL);

					relativeURL = "/MongoSvr/downFile.action?m5=" + md5; // 文件相对路径
					httpURL = "";

					downURL = "/MongoSvr/downFile.action?m5=" + md5;
					httpDownURL = http_path + downURL;

					// 存储信息
					SvrFileInfo finfo = new SvrFileInfo();
					finfo.setOld_name(value);
					finfo.setSave_name(fileName);
					finfo.setFull_path(absoluteURL);
					finfo.setHttp_relative_path(relativeURL);
					finfo.setHttp_down_url(downURL);
					finfo.setFile_md5(md5);
					finfo.setFile_size(mfile.getSize());
					finfo.setDown_nums(0);

					fileService.SaveFileInfo(finfo);

					efile = finfo;

				} else {

					absoluteURL = efile.getFull_path();// 之前上传的文件路径
					logger.info("md5已存在，使用原有文件存放的绝对路径:" + absoluteURL);

					relativeURL = efile.getHttp_relative_path();// curDir + "/"
																// + fileName;
																// // 文件相对路径
					httpURL = http_path + efile.getHttp_relative_path();// curDir
																		// + "/"
																		// +
																		// fileName;
					downURL = efile.getHttp_down_url();
					httpDownURL = http_path + downURL;
				}

				if (relativeURL != null && !relativeURL.equals("")) {
					try {
						jsonOut.put("ResponseCode", "200");
						jsonOut.put("ResponseMsg", "OK");
						jsonOut.put("FileUrl", httpURL);
						jsonOut.put("relativeURL", relativeURL);
						jsonOut.put("downURL", downURL);
						jsonOut.put("httpDownURL", httpDownURL);
						jsonOut.put("md5", md5);
						jsonOut.put("newname", fileName);
						jsonOut.put("oldname", efile.getOld_name());
						jsonOut.put("fileid", efile.getId());

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					try {
						jsonOut.put("ResponseCode", "201");
						jsonOut.put("ResponseMsg", "上传失败!");
						jsonOut.put("FileUrl", "");
						jsonOut.put("relativeURL", "");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				break;// 只处理一个文件一张图片

			}

		} catch (Exception e) {
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "FAILED:" + e.getMessage());
				e.printStackTrace();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return jsonOut.toString();
		// JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}
}
