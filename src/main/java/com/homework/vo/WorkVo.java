package com.homework.vo;

import com.homework.pojo.TbTwork;

public class WorkVo extends TbTwork{
	private String status;

	private String over="N";
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}
	
}
