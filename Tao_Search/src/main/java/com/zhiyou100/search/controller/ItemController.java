package com.zhiyou100.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.search.service.ItemService;
import com.zhiyou100.utils.TaotaoResult;

/**
 * solr的商品item控制器
 * 
 * @author 19229
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 往solr导入所有的数据
	 * 
	 * @return
	 */
	@RequestMapping("/manager/importall")
	@ResponseBody
	public TaotaoResult importAll() {
		TaotaoResult result = itemService.importAllItems();
		return result;
	}
}
