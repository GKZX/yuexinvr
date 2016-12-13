package org.yuexin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.SysUserMapper;
import org.yuexin.dao.VedioCustomMapper;
import org.yuexin.dao.VedioMapper;
import org.yuexin.model.SysUser;
import org.yuexin.model.SysUserExample;
import org.yuexin.model.Vedio;

/**
 * 
 * @Description: 视频操作
 * 
 * @author liuqin
 * 
 * @date 2016-12-12 上午11:15:16
 * 
 * 
 */
@Service
public class VedioService {
	@Autowired
	private VedioMapper vedioMapper;
	@Autowired
	private VedioCustomMapper vedioCustomMapper;

	/**
	 * 新增视频
	 * 
	 * @param vedioCategoryPId
	 *            视频分类第一级ID
	 * @param vedioCategoryId
	 *            视频分类第二级ID
	 * @param isFree
	 *            是否收费：0-免费；1-收费；
	 * @param money
	 *            收费金额；
	 * @param vedioName
	 *            视频名称
	 * @param vedioNotes
	 *            视频简介
	 * @param vedioImgUrl
	 *            视频封面地址
	 * @param vedioUrl
	 *            视频地址
	 */
	public void addVedio(Integer vedioCategoryPId, Integer vedioCategoryId, Short isFree, Integer money, String vedioName,
			String vedioNotes, String vedioImgUrl, String vedioUrl) {
		if (vedioCategoryPId == null || vedioCategoryPId == 0 || isFree == null || StringUtils.isBlank(vedioName)
				|| StringUtils.isBlank(vedioImgUrl)) {
			return;
		}
		Integer categoryId = (vedioCategoryId != null && vedioCategoryId != 0) ? vedioCategoryId : vedioCategoryPId;// 分类存当前类别的ID
		Vedio vedio = new Vedio();
		vedio.setVedioCategoryId(categoryId);
		vedio.setIsFree(isFree);
		vedio.setMoney(money);
		vedio.setVedioName(vedioName);
		vedio.setVedioNotes(vedioNotes);
		vedio.setVedioImgUrl(vedioImgUrl);
		vedio.setVedioUrl(vedioUrl);
		vedio.setVedioStatus(1);// 审核通过
		vedio.setAddTime(new Date());
		vedio.setSysFlag((byte) 1);
		vedioMapper.insertSelective(vedio);
	}

	/**
	 * 条件分页查询视频
	 * 
	 * @param vedioCategoryId
	 *            分类ID
	 * @param searchCriteria
	 *            搜索条件
	 * @param indexPage
	 *            第几页
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	public List<Vedio> selectVedioList(Integer vedioCategoryId, String searchCriteria, Integer indexPage, Integer pageSize) {
		Integer startIndex = (indexPage - 1) * pageSize;
		Integer endIndex = indexPage * pageSize;
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("vedioCategoryId", vedioCategoryId);
		map.put("searchCriteria", searchCriteria);
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
		return vedioCustomMapper.selectVedios(map);
	}

	public void deleteVedios(String[] vedioIds) {
		if (vedioIds == null) {
			return;
		}
	}
}
