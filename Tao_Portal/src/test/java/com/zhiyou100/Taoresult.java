package com.zhiyou100;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zhiyou100.portal.pojo.SearchResult;
import com.zhiyou100.utils.HttpClientUtil;
import com.zhiyou100.utils.TaotaoResult;

public class Taoresult {

	@Test
	public void get() {
		Map<String, String> param = new HashMap<>();
		param.put("q", "手机");
		param.put("page", 2 + "");
		String json = HttpClientUtil.doGet("http://:8083/search/query", param);
		System.out.println("=========json==========="+json);
		// 把字符串转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
		System.out.println("result============名字============"+taotaoResult.getData());
	}
}
