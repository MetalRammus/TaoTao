package com.zhiyou100.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.service.ContentCategoryService;
import com.zhiyou100.utils.EUTreeNode;
import com.zhiyou100.utils.TaotaoResult;

@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 跳转内容分类主页
	 * 
	 * @return
	 */
	@RequestMapping("/content-category")
	public String contentCategory() {
		return "content-category";
	}

	/**
	 * 查询分类的 异步树
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EUTreeNode> contentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EUTreeNode> treeList = contentCategoryService.getCategoryList(parentId);
		return treeList;
	}

	/**
	 * 建立异步树的节点，且有孩子的升格为父亲
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name) {
		TaotaoResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}

}
