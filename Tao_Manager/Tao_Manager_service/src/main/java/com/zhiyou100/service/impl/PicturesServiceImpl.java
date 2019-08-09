package com.zhiyou100.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.service.PictureService;
import com.zhiyou100.utils.ExceptionUtil;
import com.zhiyou100.utils.FtpUtil;
import com.zhiyou100.utils.IDUtils;
import com.zhiyou100.utils.PictureResult;

@Service
public class PicturesServiceImpl implements PictureService{

	
	
	private String FTP_ADDRESS="192.168.16.3";

	private String FTP_USERNAME="ftpuser";
	
	private String FTP_PASSWORD="ftpuser";
	
	private String FTP_BASE_PATH="/home/ftpuser";
	
	private String IMAGE_BASE_URL="http://192.168.16.3";

	
	@Override
	public PictureResult uploadPicture(MultipartFile multipartFile) {
		if(multipartFile==null||multipartFile.isEmpty()) {
			return PictureResult.error("上传图片为空");
		}
		
		String originalFilename=multipartFile.getOriginalFilename();
		//文件名附加 .之前的文件名长度
		String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
		//工具类生成名字 时间+随机数
		String imageName=IDUtils.genImageName();
		//格式化路径
		DateTime dateTime = new DateTime();
		String filePath = dateTime.toString("/yyyy/MM/dd");
		try {
			FtpUtil.uploadFile(FTP_ADDRESS, 21, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH, filePath, imageName + ext, multipartFile.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return PictureResult.error(ExceptionUtil.getStackTrace(e));
		}
		//返回结果，生成一个可以访问到图片的url返回
		System.out.println("=========uploadPicture=========图片url"+IMAGE_BASE_URL + filePath + "/" + imageName + ext);
		return PictureResult.ok(IMAGE_BASE_URL + filePath + "/" + imageName + ext);

	}



}
