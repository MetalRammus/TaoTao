package com.zhiyou100.rest.service;

import com.zhiyou100.utils.TaotaoResult;

/**
 * 
 * @author 19229
 *
 */
public interface ItemService {
	/**
	 * 获取基础信息
	 * 
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemBaseInfo(Long itemId);

	/**
	 * 获取商品描述
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemDesc(Long itemId);
	/**
	 * 获取商品规格参数
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemParam(Long itemId);
}
