package com.zhiyou100.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.mapper.TbContentMapper;
import com.zhiyou100.model.TbContent;
import com.zhiyou100.model.TbContentCategory;
import com.zhiyou100.model.TbContentExample;
import com.zhiyou100.service.ContentService;
import com.zhiyou100.utils.EUDataGridResult;
import com.zhiyou100.utils.HttpClientUtil;
import com.zhiyou100.utils.TaotaoResult;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;

	/**
	 * 分页查询
	 */
	@Override
	public EUDataGridResult getContentList(int page, int rows) {
		TbContentExample example = new TbContentExample();
		PageHelper.startPage(page, rows);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 增加
	 */
	@CacheEvict(value= {"addContent"},allEntries=true)
	@Override
	public TaotaoResult addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		tbContentMapper.insert(content);
		return TaotaoResult.ok();
	}

}
