package com.zhiyo100.test;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.zhiyou100.utils.CookieUtils;
import com.zhiyou100.utils.HttpClientUtil;

public class Cookies {

	@Test
	public void cookies(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 创建Httpclient对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		CloseableHttpResponse closeableHttpResponse = null;
		String url="http://sso.taotao.com/page/login";
		
		URIBuilder builder = new URIBuilder(url);
		Map<String, String> param=new HashMap<String, String>();
		param.put("username", "soraka");
		param.put("password", "123456");
		
		if (param != null) {
			for (String key : param.keySet()) {
				builder.addParameter(key, param.get(key));
			}
		}
		URI uri = builder.build();
		// 创建http GET请求
		HttpGet httpGet = new HttpGet(uri);
		// 执行请求
		closeableHttpResponse = httpClient.execute(httpGet);
		Header encode = (Header) closeableHttpResponse.getFirstHeader("Content-Type");  //请求头，要返回content-type，以便前台知道如何处理
        response.setHeader(((org.apache.http.Header) encode).getName(), ((org.apache.http.Header) encode).getValue());

		String token = UUID.randomUUID().toString();
		System.out.println("=======token=============="+token);
		CookieUtils.setCookie(null, response, "TT_TOKEN", token);
		String token1 = CookieUtils.getCookieValue(request, "TT_TOKEN");
		System.out.println("===============token1============="+token1);
	}
}
