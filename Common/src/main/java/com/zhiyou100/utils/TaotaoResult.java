package com.zhiyou100.utils;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 淘淘商城自定义响应结构 由于每个操作都需要有状态码(status)来表示操作成功与否以及相关信息，因此定义一个TaotaoResult类来专门处理，
 * 该类定义三个属性，分别是状态、消息及数据，由于这个类会被多个工程所使用
 */
public class TaotaoResult implements Serializable {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public static TaotaoResult build(Integer status, String msg, Object data) {
		return new TaotaoResult(status, msg, data);
	}

	//200 ok data  调构造方法
	public static TaotaoResult ok(Object data) {
		return new TaotaoResult(data);
	}

	//200 ok null 调构造方法
	public static TaotaoResult ok() {
		return new TaotaoResult(null);
	}

	public TaotaoResult() {

	}

	public static TaotaoResult build(Integer status, String msg) {
		return new TaotaoResult(status, msg, null);
	}

	public TaotaoResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public TaotaoResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

//    public Boolean isOK() {
//        return this.status == 200;
//    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将json结果集转化为TaotaoResult对象
	 * 
	 * @param jsonData json数据
	 * @param clazz    TaotaoResult中的object类型
	 * @return
	 */
	public static TaotaoResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, TaotaoResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 没有object对象的转化
	 * 
	 * @param json
	 * @return
	 */
	public static TaotaoResult format(String json) {
		try {
			return MAPPER.readValue(json, TaotaoResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object是集合转化
	 * 
	 * @param jsonData json数据
	 * @param clazz    集合中的类型
	 * @return
	 */
	public static TaotaoResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

}