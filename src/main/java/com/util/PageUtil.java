package com.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 分页的辅助类
 * @author wjx
 *
 * @param <T>
 */
public class PageUtil<T> {

	private int code;
	private String msg;
	private int count ;
	private T data;
	private String jsonData;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		JSONArray array = JSONArray.fromObject(data);
		this.data = (T) array;
	}
	
	public String getJsonData() {
		return jsonData;
	}
	
	public void setJsonData(PageUtil pageUtil) {
		JSONObject jsonObject=JSONObject.fromObject(pageUtil);
		this.jsonData = jsonObject.toString();
	}
	
	
	
}
