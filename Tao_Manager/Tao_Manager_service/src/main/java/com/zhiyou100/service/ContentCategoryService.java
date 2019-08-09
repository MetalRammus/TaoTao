package com.zhiyou100.service;

import java.util.List;

import com.zhiyou100.utils.EUTreeNode;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 网站内容管理
 * 
 * @author 19229
 *
 */
public interface ContentCategoryService {

	/**
	 * 获取内容list
	 * 
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getCategoryList(long parentId);
	/**
	 * 添加 主键自增长
	 * @param parentId
	 * @param name
	 * @return
	 */
	TaotaoResult addContentCategory(long parentId, String name);
}
