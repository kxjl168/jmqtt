package com.ztgm.base.controller.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用普通图片
 * 
 * @author zj
 * @date 2018年9月7日
 *
 */
@Document(collection = "mongosvr_normal_img")
@Data
public class NormalImg2Mongo implements Serializable {

	@Id
	private String id;//md5

	private String uId;



	/** 图片 */
	private String img_file;
	private String img_file_uptime;
	

}
