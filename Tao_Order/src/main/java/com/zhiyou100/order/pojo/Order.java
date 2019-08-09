package com.zhiyou100.order.pojo;

import java.io.Serializable;
import java.util.List;

import com.zhiyou100.model.TbOrder;
import com.zhiyou100.model.TbOrderItem;
import com.zhiyou100.model.TbOrderShipping;

public class Order extends TbOrder implements Serializable{

	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

	@Override
	public String toString() {
		return "Order [orderItems=" + orderItems + ", orderShipping=" + orderShipping + ", getOrderId()=" + getOrderId()
				+ ", getPayment()=" + getPayment() + ", getPaymentType()=" + getPaymentType() + ", getPostFee()="
				+ getPostFee() + ", getStatus()=" + getStatus() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getPaymentTime()=" + getPaymentTime()
				+ ", getConsignTime()=" + getConsignTime() + ", getEndTime()=" + getEndTime() + ", getCloseTime()="
				+ getCloseTime() + ", getShippingName()=" + getShippingName() + ", getShippingCode()="
				+ getShippingCode() + ", getUserId()=" + getUserId() + ", getBuyerMessage()=" + getBuyerMessage()
				+ ", getBuyerNick()=" + getBuyerNick() + ", getBuyerRate()=" + getBuyerRate() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
