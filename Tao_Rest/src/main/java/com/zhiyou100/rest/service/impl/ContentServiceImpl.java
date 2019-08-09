package com.zhiyou100.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.TbContentMapper;
import com.zhiyou100.model.TbContent;
import com.zhiyou100.model.TbContentExample;
import com.zhiyou100.model.TbContentExample.Criteria;
import com.zhiyou100.rest.service.ContentService;
/**
 * 
 * @author 19229
 *
 */

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	/**
	 * 类别id获取  89 大公告
	 */
	@Cacheable(value = "getContentList")
	@Override
	public List<TbContent> getContentList(long contentCategoryId) {
		// 根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCategoryId);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;

	}

}
