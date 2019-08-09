package com.zhiyou100.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.service.PictureService;
import com.zhiyou100.utils.PictureResult;

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;

	/**
	 * 上传图片
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile) {
		PictureResult result = pictureService.uploadPicture(uploadFile);
		return result;
	}


}
