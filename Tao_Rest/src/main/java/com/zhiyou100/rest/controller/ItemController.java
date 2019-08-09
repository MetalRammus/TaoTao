package com.zhiyou100.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 服务 商品
 * @author 19229
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.rest.service.ItemService;
import com.zhiyou100.utils.TaotaoResult;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 返回商品基础信息
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}

	/**
	 * 返回商品描述
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {

		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}
	/**
	 * 返回规格参数
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemParam(itemId);
		return result;
	}

}
