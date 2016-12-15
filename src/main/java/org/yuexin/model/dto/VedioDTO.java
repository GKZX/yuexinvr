package org.yuexin.model.dto;

public class VedioDTO {
	private Integer id;
	private Integer vedioCategoryPId;// 视频分类第一级ID
	private Integer vedioCategoryId;// 视频分类第二级ID
	private Short isFree;// 是否收费：1-是否；0-免费
	private Integer money;// 收费金额（单位分）
	private String vedioName;// 视频名称
	private String vedioNotes;// 视频简介
	private String vedioImgUrl;// 视频封面图片地址
	private String vedioUrl;// 视频地址
	private Integer playAmount;// 播放量

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVedioCategoryPId() {
		return vedioCategoryPId;
	}

	public void setVedioCategoryPId(Integer vedioCategoryPId) {
		this.vedioCategoryPId = vedioCategoryPId;
	}

	public Integer getVedioCategoryId() {
		return vedioCategoryId;
	}

	public void setVedioCategoryId(Integer vedioCategoryId) {
		this.vedioCategoryId = vedioCategoryId;
	}

	public Short getIsFree() {
		return isFree;
	}

	public void setIsFree(Short isFree) {
		this.isFree = isFree;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getVedioName() {
		return vedioName;
	}

	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}

	public String getVedioNotes() {
		return vedioNotes;
	}

	public void setVedioNotes(String vedioNotes) {
		this.vedioNotes = vedioNotes;
	}

	public String getVedioImgUrl() {
		return vedioImgUrl;
	}

	public void setVedioImgUrl(String vedioImgUrl) {
		this.vedioImgUrl = vedioImgUrl;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public Integer getPlayAmount() {
		return playAmount;
	}

	public void setPlayAmount(Integer playAmount) {
		this.playAmount = playAmount;
	}
}