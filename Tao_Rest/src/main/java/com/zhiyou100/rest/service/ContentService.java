package com.zhiyou100.rest.service;

import java.util.List;

import com.zhiyou100.model.TbContent;

public interface ContentService {

	/**
	 * 类别id获取  89 大公告
	 * @param contentCategoryId
	 * @return
	 */
	List<TbContent> getContentList(long contentCategoryId);
}
