package org.yuexin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.SysUserMapper;
import org.yuexin.dao.VedioMapper;
import org.yuexin.model.SysUser;
import org.yuexin.model.SysUserExample;
import org.yuexin.model.Vedio;


/**

* @Description: 视频操作

* @author liuqin

* @date 2016-12-12 上午11:15:16

*
 */
@Service
public class VedioService {
	@Autowired
	private VedioMapper vedioMapper;
	
	/**
	 * 新增视频
	 * @param vedioCategoryId
	 * @param isFree
	 * @param money
	 * @param vedioName
	 * @param vedioImgUrl
	 * @param vedioUrl
	 */
	public void addVedio(Integer vedioCategoryId,Short isFree,Integer money,String vedioName,String vedioImgUrl,String vedioUrl){
		Vedio vedio = new Vedio();
		vedio.setVedioCategoryId(vedioCategoryId);
		vedio.setIsFree(isFree);
		vedio.setMoney(money);
		vedio.setVedioName(vedioName);
		vedio.setVedioImgUrl(vedioImgUrl);
		vedio.setVedioUrl(vedioUrl);
		vedio.setVedioStatus(1);// 审核通过
		vedio.setAddTime(new Date());
		vedio.setSysFlag((byte) 1);
		vedioMapper.insertSelective(vedio);
	}
}
