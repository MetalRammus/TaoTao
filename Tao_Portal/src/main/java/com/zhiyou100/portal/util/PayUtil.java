package com.zhiyou100.portal.util;

import java.awt.AlphaComposite;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zhiyou100.utils.HttpClientUtil;

@Component
public class PayUtil implements PayInterface {

	private final String APP_ID = "2016100900647828";
	private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCZ30Z3ufmuKMynJwUIoDDryvGzKCSO9Uczns/3XPo84Ep/HzmpVwIVNPxiQ4NKWTxyYpoIV01JZ6iGtRNsSqM4WLuV8r7/OwrTtm0bAtI0k4Ww0kMGChtGK2tQUehF8oGVNR1smwrdCjqJQWdpWhJt58bIpUD9V6y3TWI/PGXC61jR3aaeRaJS2LUnZaNgIBjY9RyNGsvLPdPfKUoQD7tFqev82alD/DzHrGdl6JHisicJRz7Sgkw0ArLzdztfuxG7s8l8UEykef/C78Lx3NBaGoREsr+jE3os5BrYhc2LcxyFD86k0RupBWs4uD1do0B8Ih7n1TsruBNZ2fsV5ENjAgMBAAECggEAYBy+/3oDI24Ip0AQPNsSs7YIU0f3zfQXjX7dmD1TcUMIRW9TrRrUCjsWiqpxD7e9shU08ZDWjctZ0Qq6HyJf4YpLYdZGx1Yx5RxF+mFAu+aJ03ECvHsDnkjwtzLySv63QmPzBSuzFW6WMbXQylWgvVXRLPNMLPLex4BmpeksBxvQnaNlmewBnhMyPznfWRg1ZPvsbrUYeRLGHIl3KF63Mz4eAcEHmV29UFUjPgNuBEjluqgxFNiKgFlGJySEOayqKjGWHSJqVS3IEa/RELX8LyHSH54E356e5xweSdA1Mg+cc2XDDwcabJQwXUEr/ytiR/q0xZsTSEgar8o0TT9yAQKBgQDftwSa2BT9xJ/QLzloOco71RDasRPo91Z8EGcIE0Ct2RC5amU+9Hb5L7FkSYUSxVFTsVAWXZc2AXsvzgixNih4VHWy1gQpnCKvhBMs+8vYLxwBQPH0ZX2NqcM+jMrR3TC/GBHdqzCiwt6/eWEsx9zIvWaGEFyqGGzczbWG1hPSgwKBgQCwE/f+buD5V5wzWad2UNzzsi9+FAzYO6TovzPNG4pDyzh7qmzAmKFJ6NADvddwdTyKrcdbThXdqJ/8aifG9ZYtZasM5uHHIZrrlwOXaKOsveSJ6OLvk508xf+oRzy3IFiWVx2E+11GGKOpyYlWH6jw/r034gcx2T0WFD3X6T11oQKBgHL/CQbvfBrWL7A8Fi5VRndrFrbTv4BJY9NyOwQToXO72LT21ASAdYex1GXQxO7ZLuzugFR+13OTX1c7eYxeYn84Kyg6ivk4oTvHkHPXGvmNfb/HOph+cwOW+B33EWq/YIdaQBTKIr4HnaifJCx4sC0f7suLLZbh51O5Ck69TIMxAoGAdb6UutcIqJSWhpwwaOVAK9J/Ac+Em2CfMWkSydjdx+HwvnnDybMHQDZoS+g6ViB/wLjetEbuRlDJ03Blcx2tNGRlUvZeCtyFDX/pzekScqR1XGeYwd6zaf15rd0dU9RUiQxuWAwDXqmBYDu+FBKN1cJzZBhI+WOs/sPAa060Y6ECgYBAKoc1th14DzYe6yLj6vEOBq6Ga/HZPmKFQ3oFClTyc2cTb4MeHGyA3ZS+IG3+mCpb1lB4QNq5e0Ab2aYWDeAR5ZF/nTIxzzSVDrCE8nIymC9BMvqiC0eUpDunckU5L5C0tB+GRHftw9QjoDVylJgKDKTJnsJ52evlziKgRB+D/Q==";
	private final String CHARSET = "UTF-8";
	private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmd9Gd7n5rijMpycFCKAw68rxsygkjvVHM57P91z6POBKfx85qVcCFTT8YkODSlk8cmKaCFdNSWeohrUTbEqjOFi7lfK+/zsK07ZtGwLSNJOFsNJDBgobRitrUFHoRfKBlTUdbJsK3Qo6iUFnaVoSbefGyKVA/Vest01iPzxlwutY0d2mnkWiUti1J2WjYCAY2PUcjRrLyz3T3ylKEA+7Ranr/NmpQ/w8x6xnZeiR4rInCUc+0oJMNAKy83c7X7sRu7PJfFBMpHn/wu/C8dzQWhqERLK/oxN6LOQa2IXNi3MchQ/OpNEbqQVrOLg9XaNAfCIe59U7K7gTWdn7FeRDYwIDAQAB";
	// 这是沙箱接口路径，正式路径为https://openapi.alipay.com/gateway.do

	private final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

	private final String FORMAT = "JSON";

	// 签名方式

	private final String SIGN_TYPE = "RSA2";

	// 支付宝异步通知路径，付款完毕后会异步调用本项目的方法，必须为公网地址
	// private final String NOTIFY_URL="";

	// 支付宝同步通知路径，也就是当付款完毕后跳转本项目的页面，可以不是公网地址
	private final String RETURN_URL = "http://www.taotao.com/order/success.html";

	@Override
	public void pay(String money, String out_trade_no, HttpServletRequest request, HttpServletResponse httpResponse)
			throws IOException {
		// 商户订单号，商户网站订单系统中唯一订单号，必填

		// 生成随机Id

		Random random = new Random();

		// 实例化客户端，填入所需参数
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET,
				ALIPAY_PUBLIC_KEY, SIGN_TYPE);

		AlipayTradePagePayRequest requests = new AlipayTradePagePayRequest();

		// 在公共参数中设置回跳和通知地址

		requests.setReturnUrl(RETURN_URL);

		// requests.setNotifyUrl(NOTIFY_URL);

		// 付款金额，必填
		String total_amount = money;

		// 订单名称，必填
		String subject = "订单";

		// 商品描述，可空

		String body = "尊敬的会员欢迎您使用淘淘商城购物";

		requests.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		String form = "";
		try {
			form = alipayClient.pageExecute(requests).getBody();// 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	@Override
	public Map<String, String> updateOrder(HttpServletRequest request, HttpServletResponse httpResponse) {
		// 获得支付宝GET过来反馈信息
		Map<String, String> map = new HashMap<String, String>();
		HashMap<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}

			// 乱码解决，这段代码在出现乱码时使用
			try {
				valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			params.put(name, valueStr);
		}
		String orderNo = null;
		try {
			boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE);

			// 商户订单号

			orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			System.out.println("商品订单号=" + orderNo);

			System.out.println("支付宝交易号=" + trade_no);
			System.out.println("付款金额=" + total_amount);

			map.put("orderNo", orderNo);
			map.put("trade_no", trade_no);
			map.put("total_amount", total_amount);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		HttpClientUtil.doGet("http://order.taotao.com/order/changeStatus/" + orderNo);
		return map;
	}

}
