package org.yuexin.model.dto;

import java.util.List;

public class VedioAppsResultDTO {
	private Integer id;
	private String name;// 视频分类名称
	private List<VedioAppResultDTO> list;// 视频列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VedioAppResultDTO> getList() {
		return list;
	}

	public void setList(List<VedioAppResultDTO> list) {
		this.list = list;
	}

}