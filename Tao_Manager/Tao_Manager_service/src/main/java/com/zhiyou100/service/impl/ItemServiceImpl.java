package com.zhiyou100.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.TbItemCatMapper;
import com.zhiyou100.mapper.TbItemDescMapper;
import com.zhiyou100.mapper.TbItemMapper;
import com.zhiyou100.mapper.TbItemParamItemMapper;
import com.zhiyou100.model.TbItem;
import com.zhiyou100.model.TbItemDesc;
import com.zhiyou100.model.TbItemExample;
import com.zhiyou100.model.TbItemExample.Criteria;
import com.zhiyou100.model.TbItemParamExample;
import com.zhiyou100.model.TbItemParamItem;
import com.zhiyou100.service.ItemService;
import com.zhiyou100.utils.IDUtils;
/**
 * 
 * @author 19229
 *
 */
import com.zhiyou100.utils.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	/**
	 * 获取全部商品
	 */
	@Override
	public List<TbItem> getList() {

		return tbItemMapper.getList();
	}

	

	/**
	 * 添加商品 以及商品描述
	 */
	@Override
	public TaotaoResult addItem(TbItem item, TbItemDesc itemDesc,TbItemParamItem itemParamItem) {

		Long itemId = IDUtils.genItemId();
		//补全item
		item.setId(itemId);
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		tbItemMapper.insert(item);
		//补全desc
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		tbItemDescMapper.insert(itemDesc);
		
		//补全商品规格参数
        itemParamItem.setItemId(itemId);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //插入商品规格到商品规格表
        tbItemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}

	/**
	 * 更新状态 1 正常 2 下架  3 删除
	 */
	@Override
	public TaotaoResult updateItemStatus(List<Long> ids, String method) {
		TbItem item = new TbItem();
		if (method.equals("reshelf")) {
			// 正常，更新status=1即可
			item.setStatus((byte) 1);
		} else if (method.equals("instock")) {
			// 下架，更新status=2即可
			item.setStatus((byte) 2);
		} else if (method.equals("delete")) {
			// 删除，更新status=3即可
			item.setStatus((byte) 3);
		}
		
		for (Long id : ids) {
			// 创建查询条件，根据id更新
			TbItemExample tbItemExample = new TbItemExample();
			Criteria criteria = tbItemExample.createCriteria();
			criteria.andIdEqualTo(id);
			// 第一个参数 是要修改的部分值组成的对象，其中有些属性为null则表示该项不修改。
			// 第二个参数 是一个对应的查询条件的类,可以直接主键id
			tbItemMapper.updateByExampleSelective(item, tbItemExample);
		}
		return TaotaoResult.ok();
	}

}
