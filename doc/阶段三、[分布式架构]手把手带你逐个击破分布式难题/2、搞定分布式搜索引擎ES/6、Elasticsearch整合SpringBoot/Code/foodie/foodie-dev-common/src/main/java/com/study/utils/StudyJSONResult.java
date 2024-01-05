package com.study.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @Title: StudyJSONResult.java
 * @Package com.study.utils
 * @Description: 自定义响应数据结构
 * 				本类可提供给 H5/ios/安卓/公众号/小程序 使用
 * 				前端接受此类数据（json object)后，可自行根据业务去实现相关功能
 * 
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 * 				556: 用户qq校验异常
 * @Copyright: Copyright (c) 2020
 * @version V1.0
 */
public class StudyJSONResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    
    @JsonIgnore
    private String ok;	// 不使用

    public static StudyJSONResult build(Integer status, String msg, Object data) {
        return new StudyJSONResult(status, msg, data);
    }

    public static StudyJSONResult build(Integer status, String msg, Object data, String ok) {
        return new StudyJSONResult(status, msg, data, ok);
    }
    
    public static StudyJSONResult ok(Object data) {
        return new StudyJSONResult(data);
    }

    public static StudyJSONResult ok() {
        return new StudyJSONResult(null);
    }
    
    public static StudyJSONResult errorMsg(String msg) {
        return new StudyJSONResult(500, msg, null);
    }
    
    public static StudyJSONResult errorMap(Object data) {
        return new StudyJSONResult(501, "error", data);
    }
    
    public static StudyJSONResult errorTokenMsg(String msg) {
        return new StudyJSONResult(502, msg, null);
    }
    
    public static StudyJSONResult errorException(String msg) {
        return new StudyJSONResult(555, msg, null);
    }
    
    public static StudyJSONResult errorUserQQ(String msg) {
        return new StudyJSONResult(556, msg, null);
    }

    public StudyJSONResult() {

    }

    public StudyJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public StudyJSONResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public StudyJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

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

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
