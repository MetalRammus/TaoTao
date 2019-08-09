package com.zhiyou100.rest.service;

import java.util.List;

import com.zhiyou100.rest.pojo.CatResult;
/**
 * 分类服务
 * @author 19229
 *
 */
public interface ItemCatService {

	/**
	 * 获取分类列表
	 * @return
	 */
	CatResult getItemCatList();
	/**
	 * 查询分类列表
	 * @param parentId
	 * @return
	 */
	List<?> getCatList(long parentId);
}
