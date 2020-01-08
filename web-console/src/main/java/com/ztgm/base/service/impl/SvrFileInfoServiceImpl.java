package com.ztgm.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztgm.base.dao.SvrFileInfoMapper;
import com.ztgm.base.pojo.SvrFileInfo;
import com.ztgm.base.service.SvrFileInfoService;
import com.ztgm.base.util.DateUtil;
import com.ztgm.base.util.UUIDUtil;

@Service
public class SvrFileInfoServiceImpl implements SvrFileInfoService {

	@Autowired
	private SvrFileInfoMapper fileDao;

	@Override
	public int SaveFileInfo(SvrFileInfo info) {

		info.setId(UUIDUtil.getUUID());
		info.setSave_date(DateUtil.getNowStr(""));

		return fileDao.SaveFileInfo(info);
	}

	@Override
	public int DeleteFileInfo(SvrFileInfo info) {
		return fileDao.DeleteFileInfo(info);
	}

	@Override
	public int addFileDonwLoadNums(SvrFileInfo info) {
		return fileDao.addFileDonwLoadNums(info);
	}

	@Override
	public SvrFileInfo getFileInfo(SvrFileInfo info) {
		return fileDao.getFileInfo(info);
	}

}
