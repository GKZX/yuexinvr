package org.yuexin.model.dto;

public class VedioCategoryAppDTO {
	private Integer id;// 分类ID
	private String vedioCategoryName;// 分类名称
	private String logoImgUrl;// 分类LOGO地址

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVedioCategoryName() {
		return vedioCategoryName;
	}

	public void setVedioCategoryName(String vedioCategoryName) {
		this.vedioCategoryName = vedioCategoryName;
	}

	public String getLogoImgUrl() {
		return logoImgUrl;
	}

	public void setLogoImgUrl(String logoImgUrl) {
		this.logoImgUrl = logoImgUrl;
	}

}