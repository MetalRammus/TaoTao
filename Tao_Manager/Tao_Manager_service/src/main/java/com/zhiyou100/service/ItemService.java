package com.zhiyou100.service;

import java.util.List;

import com.zhiyou100.model.TbItem;
import com.zhiyou100.model.TbItemCat;
import com.zhiyou100.model.TbItemDesc;
import com.zhiyou100.model.TbItemParamItem;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 后台的商品操作
 * 
 * @author 19229
 *
 */
public interface ItemService {

	/**
	 * 获取全部商品
	 * 
	 * @return
	 */
	List<TbItem> getList();



	/**
	 * 添加商品 以及商品描述
	 * 
	 * @param item
	 * @param itemDesc
	 * @return
	 */
	TaotaoResult addItem(TbItem item, TbItemDesc itemDesc,TbItemParamItem itemParamItem);
	/**
	 * 更新状态 1 正常 2 下架  3 删除
	 * @param ids
	 * @param method
	 * @return
	 */
	TaotaoResult updateItemStatus(List<Long> ids,String method);
}
