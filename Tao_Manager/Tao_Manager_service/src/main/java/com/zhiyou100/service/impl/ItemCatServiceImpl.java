package com.zhiyou100.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.TbItemCatMapper;
import com.zhiyou100.model.TbItemCat;
import com.zhiyou100.model.TbItemCatExample;
import com.zhiyou100.model.TbItemCatExample.Criteria;
import com.zhiyou100.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	/**
	 * 获取类目
	 */
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		return tbItemCatMapper.selectByExample(example);
	}
}
