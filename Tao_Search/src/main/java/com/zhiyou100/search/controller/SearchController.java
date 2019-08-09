package com.zhiyou100.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.search.pojo.SearchResult;
import com.zhiyou100.search.service.SearchService;
import com.zhiyou100.utils.ExceptionUtil;
import com.zhiyou100.utils.TaotaoResult;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	/**
	 * 检索
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam("q")String queryString, 
			@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="60")Integer rows) {
		//查询条件不能为空
		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		SearchResult searchResult = null;
		try {
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			searchResult = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(searchResult);
		
	}
	
}
