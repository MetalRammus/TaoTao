package com.zhiyou100.portal.service;

import com.zhiyou100.portal.pojo.ItemInfo;

public interface ItemService {

	/**
	 * 获取基础信息
	 * 
	 * @param itemId
	 * @return
	 */
	ItemInfo getItemById(Long itemId);

	/**
	 * 获取描述
	 * 
	 * @param itemId
	 * @return
	 */
	String getItemDescById(Long itemId);

	/**
	 * 规格描述
	 * 
	 * @param itemId
	 * @return
	 */
	String getItemParam(Long itemId);
}
