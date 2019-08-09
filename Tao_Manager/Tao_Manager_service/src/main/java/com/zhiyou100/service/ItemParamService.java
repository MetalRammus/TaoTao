package com.zhiyou100.service;

import com.zhiyou100.model.TbItemParam;
import com.zhiyou100.utils.EUDataGridResult;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 
 * @author 19229
 *
 */
public interface ItemParamService {

	public TaotaoResult getItemParamByCatId(Long cid);

	// 增加规格参数模板
	TaotaoResult insertItemParam(TbItemParam itemParam);

	// 增加商品规格参数
	TaotaoResult insertItemParamItem(Long itemId, String itemParam);

	// 分页查规格模板
	EUDataGridResult getItemParamList(int page, int rows);
}
