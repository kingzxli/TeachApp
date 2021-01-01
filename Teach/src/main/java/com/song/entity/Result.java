package com.song.entity;

import java.io.Serializable;

public class Result<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	static final String SUCCESS_MESSAGE = "操作成功";
	static final String FAILURE_MESSAGE = "操作失败";
	static final Integer OK = 200;
	static final Integer FAILTURE = 400;
	/**
	 * 状态码
	 */
	private Integer status = OK;
	
	private T data;
	
	private Integer total = 0;

	private String message;
	
	public static final Result<?> SUCCESS = success();
	
	public static final Result<?> FAILURE = failure();
	
	private static Result<?> success() {
		return new Result<>(OK, SUCCESS_MESSAGE);
	}
	
	private static Result<?> failure() {
		return new Result<>(FAILTURE, FAILURE_MESSAGE);
	}
	
	public static Result<?> create(String message) {
		return new Result<>(message);
	}
	
	public static Result<?> create(Integer status, String message) {
		return new Result<>(status, message);
	}
	
	public Result() {
		this.status = OK;
		this.message = SUCCESS_MESSAGE;
	}

	public Result(T t) {
		this.status = OK;
		this.message = SUCCESS_MESSAGE;
		this.data = t;
	}

	public Result(Integer status, T data) {
		this.status = status;
		this.data = data;
	}

	public Result(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Result(Integer status, T data, Integer total) {
		this.status = status;
		this.data = data;
	}

	public Result(Integer status, String message, Integer total) {
		this.status = status;
		this.message = message;
	}
	
	public Integer getstatus() {
		return status;
	}

	public void setstatus(Integer status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result<T> status(Integer status){
		this.status = status;
		return this;
	}
	
	public Result<T> msg(String message){
		this.message = message;
		return this;
	}
	
	public Result<T> data(T data){
		this.data = data;
		return this;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Result<T> total(Integer total) {
		this.total = total;
		return this;
	}
	
	public Result<T> total(Long total) {
		this.total = total == null ? 0 : total.intValue();
		return this;
	}

	public String getMsg() {
		return message;
	}

	public void setMsg(String msg) {
		this.message = msg;
	}
	
}
