package org.yuexin.model.dto;

import java.util.Date;

/**
 * 
 * @Description: 返回给移动端的视频信息对象
 * 
 * @author liuqin
 * 
 * @date 2016-12-21 下午5:13:07
 * 
 */
public class VedioAppResultDTO {
	private Integer id;// 视频ID
	private String vedioName;// 视频名称
	private String vedioNotes;// 视频简介
	private String vedioImgUrl;// 视频封面图片地址
	private String vedioUrl;// 视频地址
	private Integer playAmount;// 播放量
	private Date addTime;// 视频上传时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}