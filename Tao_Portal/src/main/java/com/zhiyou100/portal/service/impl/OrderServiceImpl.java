package com.zhiyou100.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhiyou100.portal.pojo.Order;
import com.zhiyou100.portal.service.OrderService;
import com.zhiyou100.utils.HttpClientUtil;
import com.zhiyou100.utils.JsonUtils;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 调用8085的接口
 * 
 * @author 19229
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;

	@Override
	public String createOrder(Order order) {
		System.out.println("==========JsonUtils.objectToJson(order)=======1=======" + JsonUtils.objectToJson(order));
		// 调用taotao-order的服务提交订单。  /create
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		System.out.println("========com.zhiyou100.portal.service.impl===OrderServiceImpl====2==" + json);
		// 把json转换成taotaoResult
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if (taotaoResult.getStatus() == 200) {
			Long orderId = (Long) taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}

}
