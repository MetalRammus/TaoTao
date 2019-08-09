package com.zhiyou100.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.TbItem;
import com.zhiyou100.model.TbItemCat;
import com.zhiyou100.model.TbItemDesc;
import com.zhiyou100.model.TbItemParam;
import com.zhiyou100.model.TbItemParamItem;
import com.zhiyou100.service.ItemCatService;
import com.zhiyou100.service.ItemParamService;
import com.zhiyou100.service.ItemService;
import com.zhiyou100.utils.EUDataGridResult;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 
 * @author 19229
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCatService itemCatService;
	@Autowired
	private ItemParamService itemParamService;

	/**
	 * 商品查询
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/item-list")
	public String itemList(Model model) {

		return "item-list";
	}

	/**
	 * 返回分页数据
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/itemPage")
	@ResponseBody
	public Map<String, Object> testJson(@RequestParam int page, int rows) {
		// 引入分页查询，使用PageHelper分页功能，在查询之前传入当前页码、每页记录数
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemService.getList();
		// 使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		list = pageInfo.getList();
		// 获取总数
		long count = pageInfo.getTotal();
		// 前台接收到的json，map会自动转为json，可以在F12控制台查看
		Map<String, Object> map = new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		return map;
	}

	/**
	 * 跳转页面添加
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/item-add")
	public String itemAdd(Model model) {

		return "item-add";
	}

	/**
	 * 异步树
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/cat-list")
	@ResponseBody
	public List catList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {

		List catList = new ArrayList();

		List<TbItemCat> list = itemCatService.getItemCatList(parentId);
		for (TbItemCat tbItemCat : list) {
			Map map = new HashMap<>();
			map.put("id", tbItemCat.getId());
			map.put("text", tbItemCat.getName());
			map.put("state", tbItemCat.getIsParent() ? "closed" : "open");
			catList.add(map);
		}
		return catList;
	}

	/**
	 * 添加商品
	 * 
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc, String itemParams) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setParamData(itemParams);
		TaotaoResult result = itemService.addItem(item, itemDesc, itemParamItem);
		return result;
	}

	/**
	 * 更新商品状态
	 */
	@RequestMapping("/rest/item/{method}")
	@ResponseBody
	public TaotaoResult updateItemStatus(@RequestParam(value = "ids") List<Long> ids, @PathVariable String method) {
		TaotaoResult result = itemService.updateItemStatus(ids, method);
		return result;
	}

	/**
	 * 编辑界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/rest/page/item-edit")
	public String itemEdit(Model model) {

		return "item-edit";
	}

	/**
	 * 回显规格参数模板分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult paramList(@RequestParam int page, int rows) {
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}

	/**
	 * 跳转规格参数模板页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/item-param-list")
	public String itemparamlist(Model model) {

		return "item-param-list";
	}

	/**
	 * 跳转添加规格参数页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/item-param-add")
	public String itemparamadd(Model model) {

		return "item-param-add";
	}

	/**
	 * 查询商品分类是否有模板
	 * 
	 * 注：@PathVariable 绑定uri里的参数
	 * 
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping("/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult queryItemCatId(@PathVariable Long itemCatId) {
		TaotaoResult taotaoResult = itemParamService.getItemParamByCatId(itemCatId);
		System.out.println("===============data" + taotaoResult.getData());
		return taotaoResult;
	}

	/**
	 * 添加规格参数模板
	 * 
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}

}
