package com.zhiyou100.order.service;

import java.util.List;

import com.zhiyou100.model.TbOrder;
import com.zhiyou100.model.TbOrderItem;
import com.zhiyou100.model.TbOrderShipping;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 订单接口
 * 
 * @author 19229
 *
 */
public interface OrderService {
	/**
	 * 创建订单 订单 item 物流
	 * 
	 * @param order
	 * @param itemList
	 * @param orderShipping
	 * @return
	 */
	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
	/**
	 * 改状态
	 * @param orderId
	 * @return
	 */
	TaotaoResult changeStatus(String orderId);
}
