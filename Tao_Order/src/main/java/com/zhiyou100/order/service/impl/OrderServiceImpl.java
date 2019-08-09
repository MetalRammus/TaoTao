package com.zhiyou100.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.TbOrderItemMapper;
import com.zhiyou100.mapper.TbOrderMapper;
import com.zhiyou100.mapper.TbOrderShippingMapper;
import com.zhiyou100.model.TbOrder;
import com.zhiyou100.model.TbOrderExample;
import com.zhiyou100.model.TbOrderExample.Criteria;
import com.zhiyou100.model.TbOrderItem;
import com.zhiyou100.model.TbOrderShipping;
import com.zhiyou100.order.service.OrderService;
import com.zhiyou100.utils.IDUtils;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 订单
 * @author 19229
 *
 */
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;
	
	/**
	 * 创建订单 订单 item 物流
	 */
	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		//redis取订单号
		String string=(String) redisTemplate.opsForValue().get(ORDER_GEN_KEY);
		//校验 如果为空，初始化 
		if (StringUtils.isBlank(string)) {
		redisTemplate.opsForValue().set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		//如果不为空，订单号自增长，返回
		long orderId=IDUtils.genItemId();
		//补全 并插入
		order.setOrderId(orderId+"");
		////状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		//0：未评价 1：已评价
		order.setBuyerRate(0);
		orderMapper.insert(order);
		//补全明细 并插入
		for (TbOrderItem tbOrderItem : itemList) {
			long orderDetailId=IDUtils.genItemId();
			tbOrderItem.setId(orderDetailId+"");
			tbOrderItem.setOrderId(orderId+"");
			orderItemMapper.insert(tbOrderItem);
		}
		//补全物流表 并插入
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}
	/**
	 * 改状态
	 */
	@Override
	public TaotaoResult changeStatus(String orderId) {
		TbOrder order = new TbOrder();
		order.setStatus(2);
		order.setPaymentTime(new Date());
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		orderMapper.updateByExampleSelective(order, example);
		return TaotaoResult.ok();

	}

}
