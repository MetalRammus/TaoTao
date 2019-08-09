package com.zhiyou100.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.model.TbContent;
import com.zhiyou100.service.ContentService;
import com.zhiyou100.utils.EUDataGridResult;
import com.zhiyou100.utils.TaotaoResult;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;

	/**
	 * 跳转内容主页
	 * @return
	 */
	@RequestMapping("/content")
	public String content() {
		return "content";
	}

	/**
	 * 分页查内容
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EUDataGridResult contentList(@RequestParam int page, int rows) {
		EUDataGridResult result = contentService.getContentList(page, rows);
		return result;
	}
	
	/**
	 * 跳转添加内容主页
	 * @return
	 */
	@RequestMapping("/content-add")
	public String contentAdd() {
		return "content-add";
	}
	/**
	 * 增加，返回
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.addContent(content);
		return result;
	}

	
}
