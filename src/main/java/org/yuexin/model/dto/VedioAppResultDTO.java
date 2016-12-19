package org.yuexin.model.dto;

import java.util.List;

public class VedioAppResultDTO {
	private Integer id;
	private Integer name;// 视频分类名称
	private List<VedioAppDTO> list;// 视频列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public List<VedioAppDTO> getList() {
		return list;
	}

	public void setList(List<VedioAppDTO> list) {
		this.list = list;
	}

}