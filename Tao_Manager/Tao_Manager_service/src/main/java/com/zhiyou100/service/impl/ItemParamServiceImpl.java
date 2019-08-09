package com.zhiyou100.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.EditorAwareTag;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.mapper.TbItemParamItemMapper;
import com.zhiyou100.mapper.TbItemParamMapper;
import com.zhiyou100.model.TbItemParam;
import com.zhiyou100.model.TbItemParamExample;
import com.zhiyou100.model.TbItemParamExample.Criteria;
import com.zhiyou100.model.TbItemParamItem;
import com.zhiyou100.service.ItemParamService;
import com.zhiyou100.utils.EUDataGridResult;
import com.zhiyou100.utils.TaotaoResult;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	/**
	 * 根据catId是否有对应规格参数
	 * 
	 * @param cid
	 * @return
	 */
	public TaotaoResult getItemParamByCatId(Long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			System.out.println("==============   getParamData "+list.get(0).getParamData());
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	/**
	 * 保存规格参数模板
	 */
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		// 补全
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		// 插入到规格参数模板表
		tbItemParamMapper.insert(itemParam);
		return TaotaoResult.ok();

	}

	/**
	 * 保存商品参数
	 */
	@Override
	public TaotaoResult insertItemParamItem(Long itemId, String paramData) {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(paramData);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		tbItemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();


	}

	/**
	 * 分页查规格参数模板
	 */
	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		TbItemParamExample example=new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> list=tbItemParamMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result=new EUDataGridResult();
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		return result;
	}
}
