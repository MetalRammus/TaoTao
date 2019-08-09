package com.zhiyou100.service;

import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.utils.PictureResult;


public interface PictureService {

	PictureResult uploadPicture(MultipartFile multipartFile);
}
