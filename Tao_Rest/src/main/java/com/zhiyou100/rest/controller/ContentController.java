package com.zhiyou100.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.model.TbContent;
import com.zhiyou100.rest.service.ContentService;
import com.zhiyou100.utils.ExceptionUtil;
import com.zhiyou100.utils.TaotaoResult;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	/**
	 * 大广告
	 * @param contentCategoryId
	 * @return
	 */
	@RequestMapping("/content/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<TbContent> list = contentService.getContentList(contentCategoryId);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
