package com.zhiyou100.rest.service.impl;

import java.util.List;

import org.apache.http.impl.NoConnectionReuseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.TbItemDescMapper;
import com.zhiyou100.mapper.TbItemMapper;
import com.zhiyou100.mapper.TbItemParamItemMapper;
import com.zhiyou100.model.TbItem;
import com.zhiyou100.model.TbItemDesc;
import com.zhiyou100.model.TbItemParam;
import com.zhiyou100.model.TbItemParamItem;
import com.zhiyou100.model.TbItemParamItemExample;
import com.zhiyou100.model.TbItemParamItemExample.Criteria;
import com.zhiyou100.rest.service.ItemService;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 
 * @author 19229
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	
	public String keyDesc(Long itemId) {
		String string = "REDIS_ITEM_KEY:" + itemId + ":desc";
		return string;
	}
	
	public String keyParam(Long itemId) {
		String string = "REDIS_ITEM_KEY:" + itemId + ":param";
		return string;
	}

	/**
	 * 获取基础信息
	 */
	@Cacheable(value="getItemBaseInfo")
	@Override
	public TaotaoResult getItemBaseInfo(Long itemId) {
		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		return TaotaoResult.ok(item);
	}

	/**
	 * 获取商品描述
	 */
	@Cacheable(value="getItemDesc", key="#root.target.keyDesc(#p0)")
	@Override
	public TaotaoResult getItemDesc(Long itemId) {
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		return TaotaoResult.ok(itemDesc);
	}

	/**
	 * 获取商品规格参数
	 */
	@Cacheable(value="getItemParam", key="#root.target.keyParam(#p0)")
	@Override
	public TaotaoResult getItemParam(Long itemId) {
		//根据itemId搜索出list，因为可能没有
		TbItemParamItemExample example=new TbItemParamItemExample();
		Criteria criteria=example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list=tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0) {
			TbItemParamItem tbItemParamItem=list.get(0);
			return TaotaoResult.ok(tbItemParamItem);
		}
		
		return TaotaoResult.build(400, "无此商品规格");
	}

}
