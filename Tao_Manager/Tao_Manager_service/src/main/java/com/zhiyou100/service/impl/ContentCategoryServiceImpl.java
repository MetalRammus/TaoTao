package com.zhiyou100.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.TbContentCategoryMapper;
import com.zhiyou100.model.TbContentCategory;
import com.zhiyou100.model.TbContentCategoryExample;
import com.zhiyou100.model.TbContentCategoryExample.Criteria;
import com.zhiyou100.service.ContentCategoryService;
import com.zhiyou100.utils.EUTreeNode;
import com.zhiyou100.utils.TaotaoResult;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//根据父找到子节点 们
		List<TbContentCategory> list=tbContentCategoryMapper.selectByExample(example);
		List<EUTreeNode> treeList=new ArrayList<EUTreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node=new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			node.setText(tbContentCategory.getName());
			treeList.add(node);
		}
		
		return treeList;
	}

	/**
	 * 添加  主键自增长
	 */
	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		TbContentCategory contentCategory=new TbContentCategory();
		//补全
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//状态 1 正常 2 删除
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		tbContentCategoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true，如果不是true改成true    一个节点有孩子，会升格为父亲
		TbContentCategory parentCat = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			tbContentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);
	}

}
