package com.zhiyou100.service;

import com.zhiyou100.model.TbContent;
import com.zhiyou100.utils.EUDataGridResult;
import com.zhiyou100.utils.TaotaoResult;

public interface ContentService {

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getContentList(int page, int rows);

	/**
	 * 增加
	 * 
	 * @param content
	 * @return
	 */
	TaotaoResult addContent(TbContent content);
}
