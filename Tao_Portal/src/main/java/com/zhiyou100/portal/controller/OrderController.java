package com.zhiyou100.portal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.portal.pojo.CartItem;
import com.zhiyou100.portal.pojo.Order;
import com.zhiyou100.portal.service.CartService;
import com.zhiyou100.portal.service.OrderService;
import com.zhiyou100.portal.util.PayInterface;

/**
 * 订单接口
 * 
 * @author 19229
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	PayInterface payInterface;
	
	/**
	 * 点击去结算，跳转订单确认页面
	 * 
	 * @return
	 */
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 商品添加购物车时添加了cookies，现在从cookies取商品
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "order-cart";
	}

	/**
	 * 创建订单
	 * @param order
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/create")
	public String createOrder(Order order, Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {

		String orderId = orderService.createOrder(order);
		payInterface.pay(order.getPayment(), orderId, request, response);

		return "success";
	}
	@RequestMapping("/success")
	public String success(String out_trade_no,String total_amount, Model model,HttpServletRequest request, HttpServletResponse response) {
		payInterface.updateOrder(request, response);
		model.addAttribute("orderId", out_trade_no);
		model.addAttribute("payment", total_amount);
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
		return "success";
	}
	

}
