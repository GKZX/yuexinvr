package org.yuexin.model.dto;

public class BannerAppDTO {
	private Integer id;
	private String url;// 链接地址
	private String imgUrl;// 图片地址
	private Integer type;// banner类型:1-视频;2-链接
	private Integer vedioId;// 视频ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVedioId() {
		return vedioId;
	}

	public void setVedioId(Integer vedioId) {
		this.vedioId = vedioId;
	}
	
}