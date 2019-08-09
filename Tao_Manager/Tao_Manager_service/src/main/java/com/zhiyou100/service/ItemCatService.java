package com.zhiyou100.service;

import java.util.List;

import com.zhiyou100.model.TbItemCat;

public interface ItemCatService {
	/**
	 * 获取类目
	 * @param parentId
	 * @return
	 */
	List<TbItemCat> getItemCatList(Long parentId);
}
