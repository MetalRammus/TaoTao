package com.zhiyou100.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.order.pojo.Order;
import com.zhiyou100.order.service.OrderService;
import com.zhiyou100.utils.ExceptionUtil;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 订单接口
 * 
 * @author 19229
 *
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 接口 创建订单
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order) {
		try {
			System.out.println("==========com.zhiyou100.order.controller==OrderController====2===="+order);
			TaotaoResult taotaoResult = orderService.createOrder(order, order.getOrderItems(),
					order.getOrderShipping());
			return taotaoResult;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 改状态
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("/order/changeStatus/{orderNo}")
	@ResponseBody
	public TaotaoResult changeStatus(@PathVariable String orderNo) {
		orderService.changeStatus(orderNo);
		return TaotaoResult.ok();
	}

	
	
}
