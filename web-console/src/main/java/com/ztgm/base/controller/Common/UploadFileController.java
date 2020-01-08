package com.ztgm.base.controller.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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

import com.ztgm.base.service.SvrFileInfoService;
import com.ztgm.base.util.FileUtil;
import com.ztgm.base.util.sendpost.FormFieldKeyValuePair;
import com.ztgm.base.util.sendpost.HttpPostEmulator;

/**
 * 项目上传调用，可以直接上传，或者转发至文件服务器上传
 * 
 * @author zj
 * @date 2018年5月29日
 *
 */
@Controller
@RequestMapping(value = "/upload")
@PropertySource("classpath:project.properties")
public class UploadFileController {

	@Value("${HTTP_PATH}")
	private String FILE_SVR_HTTP_OUTER_PATH;

	// 文件服务器类型， file/mongodb
	@Value("${IMG_SVR_TYPE}")
	private String IMG_SVR_TYPE;

	@Value("${FILE_SVR_PATH}")
	private String FILE_SVR_PATH;

	@Value("${LOGO_MAXSIZE}")
	private double LOGO_MAXSIZE;

	@Value("${PIC_MAXSIZE}")
	private double PIC_MAXSIZE;

	@Value("${VID_MAXSIZE}")
	private double VID_MAXSIZE;
	@Autowired
	private SvrFileInfoService fileService;

	// 日志记录对象
	private Logger logger = Logger.getLogger(UploadFileController.class);

	@Autowired
	MongoImgSvrCtroller mongoImgSvrCtroller;

	/**
	 * 根据图片字段中存储的md5数据 访问文件服务器获取文件
	 * 
	 * @param request
	 * @param md5
	 * @param response
	 * @author zj
	 * @date 2019年1月21日
	 */
	@RequestMapping(value = "/file/{md5}")
	public void downFile(HttpServletRequest request, @PathVariable("md5") String md5, HttpServletResponse response) {
		try {

			// 将文件保存到文件服务器
			String responsedata = "";
			// String serverUrl =
			// ConfigReader.getInstance().getProperty("CHANGEHEADUrl");
			String serverUrl = FILE_SVR_PATH// ConfigReader.getInstance().getProperty("FILE_SVR_PATH")
			;// + "FileSvr/downFile.action?m5="+md5;

			if (IMG_SVR_TYPE == null || IMG_SVR_TYPE.equals("file")) {
				serverUrl += "FileSvr/downFile.action?m5=" + md5;
				response.setHeader("Location", serverUrl);
				response.setStatus(302);
			} else if (IMG_SVR_TYPE.equals("mongodb")) {
			
				mongoImgSvrCtroller.downFile(md5,request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/fileUploadError")
	public void fileUploadError(HttpServletRequest request, HttpServletResponse response) {
		// 文件过大
		String uploadUrl = "205";
		response.setCharacterEncoding("utf-8");
		StringBuffer htmlBuffer = new StringBuffer();
		htmlBuffer.append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">         ")
				.append("<html>                                                                                ")
				.append("<head>                                                                                                                        ")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />                                                     ")
				.append("<meta http-equiv=\"pragma\" content=\"no-cache\"/>                                                                            ")
				.append("<meta http-equiv=\"Cache-Control\" content=\"no-cache, must-revalidate\"/>                                                    ")
				.append("<meta http-equiv=\"expires\" content=\"0\"/>                                                                                  ")
				.append("    <title>fileupload</title>                                                                                                 ")
				.append("                                                                                                                              ")
				.append("</head>                                                                                                                       ")
				.append("<body>                                                                                                                        ")
				.append("<input type='text' id='uploadUrl' value='" + uploadUrl
						+ "'  >                                                                 ")
				.append("</body>                                                                                                                       ")
				.append("</html>                                                                                                                       ");
		try {
			response.getWriter().println(htmlBuffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 存储图标文件
	 * 
	 * @param receiveFile
	 * @param request
	 * @param response
	 * @param model
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/logoUploadFile")
	public void uploadfile(@RequestParam(value = "logoUploadURL", required = false) MultipartFile receiveFile,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String curDir = "logo"; // 使用配置的基础目录+ logo目录

		double file_max_size = 0.1; // 100k
		file_max_size = (Long) Math.round(LOGO_MAXSIZE * 1024 * 1024);

		long size = (long) file_max_size;

		String rst = doUploadToDir(receiveFile, curDir, size);

		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(rst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 存储大图标文件，惠民首页大图标
	 * 
	 * @param receiveFile
	 * @param request
	 * @param response
	 * @param model
	 * @date 2017-5-31
	 * @author zj
	 */
	@RequestMapping(value = "/biglogoUploadFile")
	public void biglogoUploadFile(@RequestParam(value = "logoUploadURL", required = false) MultipartFile receiveFile,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String curDir = "logo"; // 使用配置的基础目录+ logo目录

		double file_max_size = 0.5; // 500k
		file_max_size = LOGO_MAXSIZE;// (Long)
										// Math.round(ConfigReader.getInstance().getPropertyDouble("LOGO_MAXSIZE",
										// file_max_size) * 1024 * 1024);

		long size = (long) file_max_size;

		String rst = doUploadToDir(receiveFile, curDir, size);

		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(rst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 存储其他文件文件
	 * 
	 * @param receiveFile
	 * @param request
	 * @param response
	 * @param model
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/UploadCKFile")
	public void UploadCKFile(@RequestParam(value = "upload", required = false) MultipartFile receiveFile,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");

		String curDir = "pic"; // 使用配置的基础目录+ logo目录

		double file_max_size = 10; // 10M
		file_max_size = (Long) Math.round(PIC_MAXSIZE * 1024 * 1024);
		long size = (long) file_max_size;

		String url = doUploadToDirCK(receiveFile, curDir, size);

		String rst = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
				+ ", '" + url + "', 'OK');</script>";
		if (!url.contains("http"))
			rst = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
					+ ", '" + url + "', 'FAIL');</script>";
		try {

			response.setCharacterEncoding("utf-8");
			response.getWriter().println(rst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 存储其他文件文件
	 * 
	 * @param receiveFile
	 * @param request
	 * @param response
	 * @param model
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/UploadFile")
	public @ResponseBody String UploadFile(
			@RequestParam(value = "fileUploadURL", required = false) MultipartFile receiveFile,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String curDir = "apk"; // 使用配置的基础目录+ logo目录

		long file_max_size = 20L; // 20M
		file_max_size = (long) VID_MAXSIZE * 1024 * 1024; // 配置文件为M

		String rst = doUploadToDir(receiveFile, curDir, file_max_size);

		return rst;
		/*
		 * try { response.setCharacterEncoding("utf-8");
		 * response.getWriter().println(rst); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	/**
	 * xhr上传
	 * 
	 * @see com.ztgm.mall.test#testupImg()
	 *
	 * @param receiveFile
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author zj
	 * @date 2018年6月8日
	 */
	@RequestMapping(value = "/UploadFileXhr")
	public @ResponseBody String UploadFileXhr(
			@RequestParam(value = "fileUploadURL", required = false) MultipartFile receiveFile,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String curDir = "apk"; // 使用配置的基础目录+ logo目录

		long file_max_size = 20L; // 20M
		file_max_size = (long) VID_MAXSIZE * 1024 * 1024; // 配置文件为M

		String rst = doUploadToDirAjax(receiveFile, curDir, file_max_size);

		return rst;
		/*
		 * try { response.setCharacterEncoding("utf-8");
		 * response.getWriter().println(rst); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	/**
	 * 上传至指定目录
	 * 
	 * @param receiveFile
	 * @param curDir
	 * @return
	 * @date 2016-6-24
	 * @author zj
	 */
	private String doUploadToDirCK(MultipartFile receiveFile, String curDir, Long MaxFileSize) {
		String fileName = receiveFile.getOriginalFilename();
		String returnCode = "200";
		String returnMsg = "";
		String NewfullName = ""; // 文件名
		String md5 = "";
		String httpURL = ""; // 下载路径
		try {
			if (fileName != null && !"".equals(fileName)) {

				if (MaxFileSize != 0 && receiveFile.getSize() <= MaxFileSize) {
					// 将文件保存到文件服务器
					String responsedata = "";
					// String serverUrl =
					// ConfigReader.getInstance().getProperty("CHANGEHEADUrl");
					String serverUrl = FILE_SVR_PATH// ConfigReader.getInstance().getProperty("FILE_SVR_PATH")
					;

					if (IMG_SVR_TYPE == null || IMG_SVR_TYPE.equals("file")) {
						serverUrl += "FileSvr/UploadFile.action";

						ArrayList<FormFieldKeyValuePair> fds = new ArrayList<FormFieldKeyValuePair>();

						FormFieldKeyValuePair p1 = new FormFieldKeyValuePair("path", curDir);
						fds.add(p1);

						try {
							responsedata = HttpPostEmulator.sendHttpPostRequestByMutiPartFile(serverUrl, fds,
									new MultipartFile[] { receiveFile });
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							// 文件大小超标
							returnCode = "201";
							returnMsg = "上传失败";
						}

					} else if (IMG_SVR_TYPE.equals("mongodb")) {
						serverUrl += "MongoSvr/UploadFile.action";
						responsedata = mongoImgSvrCtroller.upload(new MultipartFile[] { receiveFile }, null, null);

					}

					// 解析responsedate
					JSONObject jsonRes = new JSONObject(responsedata);
					if ((jsonRes.getString("ResponseCode")).equals("200")) {

						// jsonOut.put("ResponseCode", "200");
						// jsonOut.put("ResponseMsg", "OK");
						// jsonOut.put("FileUrl", httpURL);
						// jsonOut.put("relativeURL", relativeURL);
						// jsonOut.put("downURL", downURL);
						// jsonOut.put("httpDownURL", httpDownURL);

						returnCode = "200";
						returnMsg = "OK";
						httpURL = jsonRes.optString("relativeURL");
						md5 = jsonRes.optString("md5");
						NewfullName = jsonRes.optString("newname");

					} else {
						returnCode = "201";
						returnMsg = jsonRes.optString("ResponseMsg");
					}

				} else {
					// 文件大小超标
					returnCode = "205";
					returnMsg = "文件大小超标!最大限制为" + FileUtil.getReadableFileSize(String.valueOf(MaxFileSize));
				}

			} else {
				// 没有选择上传文件
				returnCode = "202";
			}
		} catch (Exception e) {
			logger.error(e.toString());
			// 上传文件失败
			returnCode = "203";

		}

		StringBuffer htmlBuffer = new StringBuffer();
		if (returnCode.equals("200")) {

			String HTTP_PATH = FILE_SVR_HTTP_OUTER_PATH;// ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");

			htmlBuffer.append(HTTP_PATH + httpURL);
		} else {
			htmlBuffer.append(returnMsg);

		}

		return htmlBuffer.toString();

	}

	/**
	 * 上传至指定目录
	 * 
	 * @param receiveFile
	 * @param curDir
	 * @param MaxFileSize
	 *            字节大小
	 * @return
	 * @date 2016-6-24
	 * @author zj
	 */
	private String doUploadToDir(MultipartFile receiveFile, String curDir, Long MaxFileSize) {
		String fileName = receiveFile.getOriginalFilename();
		String returnCode = "200";
		String returnMsg = "";
		String NewfullName = ""; // 文件名
		String md5 = "";
		String httpURL = ""; // 下载路径
		String fileid = "";
		try {
			if (fileName != null && !"".equals(fileName)) {

				if (MaxFileSize != 0 && receiveFile.getSize() <= MaxFileSize) {
					// 将文件保存到文件服务器
					String responsedata = "";
					// String serverUrl =
					// ConfigReader.getInstance().getProperty("CHANGEHEADUrl");
					String serverUrl = FILE_SVR_PATH// ConfigReader.getInstance().getProperty("FILE_SVR_PATH")
					;// + "FileSvr/UploadFile.action";

					
					if (IMG_SVR_TYPE == null || IMG_SVR_TYPE.equals("file")) {
						serverUrl += "FileSvr/UploadFile.action";

						ArrayList<FormFieldKeyValuePair> fds = new ArrayList<FormFieldKeyValuePair>();

						FormFieldKeyValuePair p1 = new FormFieldKeyValuePair("path", curDir);
						fds.add(p1);

						try {

							logger.info("begin to upload to " + serverUrl);
							responsedata = HttpPostEmulator.sendHttpPostRequestByMutiPartFile(serverUrl, fds,
									new MultipartFile[] { receiveFile });

							logger.info("upload success! " + responsedata);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							// 文件大小超标
							returnCode = "201";
							returnMsg = "上传失败";
						}

					} else if (IMG_SVR_TYPE.equals("mongodb")) {
						serverUrl += "MongoSvr/UploadFile.action";
						responsedata = mongoImgSvrCtroller.upload(new MultipartFile[] { receiveFile }, null, null);

					}

					

					// 解析responsedate
					JSONObject jsonRes = new JSONObject(responsedata);
					if ((jsonRes.getString("ResponseCode")).equals("200")) {

						// jsonOut.put("ResponseCode", "200");
						// jsonOut.put("ResponseMsg", "OK");
						// jsonOut.put("FileUrl", httpURL);
						// jsonOut.put("relativeURL", relativeURL);
						// jsonOut.put("downURL", downURL);
						// jsonOut.put("httpDownURL", httpDownURL);

						returnCode = "200";
						returnMsg = "OK";
						httpURL = jsonRes.optString("relativeURL");
						md5 = jsonRes.optString("md5");
						NewfullName = jsonRes.optString("newname");
						fileid = jsonRes.optString("fileid");

					} else {
						returnCode = "201";
						returnMsg = jsonRes.optString("ResponseMsg");
					}

				} else {
					// 文件大小超标
					returnCode = "205";
					returnMsg = "文件大小超标!最大限制为" + FileUtil.getReadableFileSize(String.valueOf(MaxFileSize));
				}

			} else {
				// 没有选择上传文件
				returnCode = "202";
			}
		} catch (Exception e) {
			logger.error(e.toString());
			// 上传文件失败
			returnCode = "203";

		}

		StringBuffer htmlBuffer = new StringBuffer();
		htmlBuffer
				// .append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"
				// \"http://www.w3.org/TR/html4/loose.dtd\"> ")
				.append("<html>                                                                                ")
				.append("<head>                                                                                                                        ")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />                                                     ")
				.append("<meta http-equiv=\"pragma\" content=\"no-cache\"/>                                                                            ")
				.append("<meta http-equiv=\"Cache-Control\" content=\"no-cache, must-revalidate\"/>                                                    ")
				.append("<meta http-equiv=\"expires\" content=\"0\"/>                                                                                  ")
				.append("    <title>fileupload</title>                                                                                                 ")
				.append("                                                                                                                              ")
				.append("</head>                                                                                                                       ")
				.append("<body>                                                                                                                        ");

		if (returnCode.equals("200")) {
			htmlBuffer.append("<input type='text' id='fileid' value='" + fileid + "'>");
			htmlBuffer.append("<input type='text' id='filesize' value='" + receiveFile.getSize()
					+ "'  >                                                                    ");
			htmlBuffer.append("<input type='text' id='oldName' value='" + receiveFile.getOriginalFilename()
					+ "'  >                                                                    ");
			htmlBuffer
					.append("<input type='text' id='uploadUrl' value='" + httpURL
							+ "'  >                                                                    ")
					.append("<input type='text' id='fileMd5' value='" + md5
							+ "'  >                                                                    ")
					.append("<input type='text' id='newFileName' value='" + NewfullName
							+ "'  >                                                                    ");
		} else {
			htmlBuffer.append("<input type='text' id='uploadUrl' value='" + returnCode + "'  >  ");

			htmlBuffer.append("<input type='text' id='returnMsg' value='" + returnMsg + "'  >  ");
		}

		htmlBuffer.append(
				"</body>                                                                                                                       ")
				.append("</html>                                                                                                                       ");

		return htmlBuffer.toString();

	}

	/**
	 * /** 上传至指定目录 ,返回json数据,配合xhr使用
	 * 
	 * @param receiveFile
	 * @param curDir
	 * @param MaxFileSize
	 *            字节大小
	 * @return
	 * @author zj
	 * @date 2018年6月8日
	 */
	private @ResponseBody String doUploadToDirAjax(MultipartFile receiveFile, String curDir, Long MaxFileSize) {
		String fileName = receiveFile.getOriginalFilename();
		String returnCode = "200";
		String returnMsg = "";
		String NewfullName = ""; // 文件名
		String md5 = "";
		String httpURL = ""; // 下载路径
		String fileid = "";
		JSONObject jsonRes = new JSONObject();
		try {
			if (fileName != null && !"".equals(fileName)) {

				if (receiveFile.getSize() == 0) {
					returnCode = "201";
					jsonRes.put("ResponseCode", "201");
					jsonRes.put("ResponseMsg", "不能上传空文件！");
					return jsonRes.toString();
				} else if (MaxFileSize != 0 && receiveFile.getSize() <= MaxFileSize) {
					// 将文件保存到文件服务器
					String responsedata = "";
					// String serverUrl =
					// ConfigReader.getInstance().getProperty("CHANGEHEADUrl");
					String serverUrl = FILE_SVR_PATH// ConfigReader.getInstance().getProperty("FILE_SVR_PATH")
					;// + "FileSvr/UploadFile.action";

					if (IMG_SVR_TYPE == null || IMG_SVR_TYPE.equals("file")) {
						serverUrl += "FileSvr/UploadFile.action";

						ArrayList<FormFieldKeyValuePair> fds = new ArrayList<FormFieldKeyValuePair>();

						FormFieldKeyValuePair p1 = new FormFieldKeyValuePair("path", curDir);
						fds.add(p1);

						try {

							logger.info("begin to upload to " + serverUrl);
							responsedata = HttpPostEmulator.sendHttpPostRequestByMutiPartFile(serverUrl, fds,
									new MultipartFile[] { receiveFile });

							logger.info("upload success! " + responsedata);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							// 文件大小超标
							returnCode = "201";
							returnMsg = "上传失败";
							jsonRes.put("ResponseCode", returnCode);
							jsonRes.put("ResponseMsg", returnMsg);
							return jsonRes.toString();
						}

					} else if (IMG_SVR_TYPE.equals("mongodb")) {
						serverUrl += "MongoSvr/UploadFile.action";
						responsedata = mongoImgSvrCtroller.upload(new MultipartFile[] { receiveFile }, null, null);

					}

					// 解析responsedate
					jsonRes = new JSONObject(responsedata);
					if ((jsonRes.getString("ResponseCode")).equals("200")) {
						jsonRes.put("ResponseCode", "200");
						jsonRes.put("ResponseMsg", "上传成功");
					} else {
						returnCode = "201";
						returnMsg = jsonRes.optString("ResponseMsg");
						jsonRes.put("ResponseCode", "201");
						jsonRes.put("ResponseMsg", returnMsg);
					}

				} else {
					// 文件大小超标
					returnCode = "205";
					returnMsg = "文件大小超标!最大限制为" + FileUtil.getReadableFileSize(String.valueOf(MaxFileSize));
					jsonRes.put("ResponseCode", "205");
					jsonRes.put("ResponseMsg", returnMsg);
				}

			} else {
				// 没有选择上传文件
				returnCode = "202";
				jsonRes.put("ResponseCode", "202");
				jsonRes.put("ResponseMsg", returnMsg);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			// 上传文件失败
			returnCode = "203";
			try {
				jsonRes.put("ResponseCode", "203");
				jsonRes.put("ResponseMsg", e.toString());
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

		return jsonRes.toString();

	}

	/**
	 * 删除文件
	 * 
	 * @param request
	 * @param URL
	 * @return
	 */
	@RequestMapping(value = "/deleteFile")
	public @ResponseBody int deleteFile(HttpServletRequest request, String URL) {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		return deleteFile(URL, realPath);
	}

	public int deleteFile(String URL, String realPath) {
		try {
			URL = new String(URL.getBytes("ISO-8859-1"), "UTF-8");
			logger.info("删除的url：" + URL);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		if ("" == URL || "".equals(URL)) {
			return 0;
		}
		String os = System.getProperty("os.name");
		if (os != null && (os.startsWith("win") || os.startsWith("Win"))) {
			String realPath_tymp = realPath.substring(0, realPath.lastIndexOf("\\", realPath.length()));
			realPath = realPath_tymp.substring(0, realPath_tymp.lastIndexOf("\\", realPath_tymp.length()))
					+ "\\gaga_files\\fileupload\\upgradepackagefile\\";
		} else {
			String realPath_tymp = realPath.substring(0, realPath.lastIndexOf("/", realPath.length()));
			realPath = realPath_tymp.substring(0, realPath_tymp.lastIndexOf("/", realPath_tymp.length()))
					+ "/gaga_files/fileupload/upgradepackagefile/";
		}
		URL = realPath + URL;
		File file = new File(URL);
		int a = -1;
		// 判断当前文件是否存在
		if (file.exists()) {
			// 判断文件删除是否成功
			if (file.delete()) {
				// 文件刪除成功
				a = 1;
				logger.info(URL + "文件删除成功！");
			} else {
				// 文件刪除失敗
				a = 2;
				logger.info(URL + "文件删除失败！");
			}
		} else {
			// 所刪文件不存在
			a = 0;
			logger.info(URL + "文件不存在！");
		}
		return a;
	}
}
