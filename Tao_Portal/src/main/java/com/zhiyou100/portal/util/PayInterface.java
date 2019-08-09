package com.zhiyou100.portal.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayInterface {
public void pay(String money,String out_trade_no,HttpServletRequest request,HttpServletResponse response)throws IOException;
public Map<String, String> updateOrder(HttpServletRequest request,HttpServletResponse httpResponse);
}
