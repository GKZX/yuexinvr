package org.yuexin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.SysUserMapper;
import org.yuexin.dao.VedioCustomMapper;
import org.yuexin.dao.VedioLogMapper;
import org.yuexin.dao.VedioMapper;
import org.yuexin.model.SysUser;
import org.yuexin.model.SysUserExample;
import org.yuexin.model.Vedio;
import org.yuexin.model.VedioLog;

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
	@Autowired
	private VedioLogMapper vedioLogMapper;

	/**
	 * 新增视频
	 * 
	 * @param sysUser
	 *            管理员信息
	 * @param type
	 *            操作类型: 0-上传;1-编辑;2-删除
	 * @param vedioId
	 *            视频ID
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
	@Transactional
	public void addOrUpdateVedio(SysUser sysUser, Integer type, Integer vedioId, Integer vedioCategoryPId, Integer vedioCategoryId, Short isFree, Integer money,
			String vedioName, String vedioNotes, String vedioImgUrl, String vedioUrl) {
		if (sysUser == null || vedioCategoryPId == null || vedioCategoryPId == 0 || isFree == null || StringUtils.isBlank(vedioName)
				|| StringUtils.isBlank(vedioImgUrl)) {
			return;
		}
		Integer categoryId = (vedioCategoryId != null && vedioCategoryId != 0) ? vedioCategoryId : vedioCategoryPId;// 分类存当前类别的ID
		
		Vedio vedio = new Vedio();
		if(type == 1 && vedioId != null){// 编辑
			vedio = selectVedioById(vedioId);
			vedio.setUpdateTime(new Date());
		}else {
			vedio.setAddTime(new Date());
		}
		vedio.setVedioCategoryId(categoryId);
		vedio.setIsFree(isFree);
		vedio.setMoney(money);
		vedio.setVedioName(vedioName);
		vedio.setVedioNotes(vedioNotes);
		vedio.setVedioImgUrl(vedioImgUrl);
		vedio.setVedioUrl(vedioUrl);
		vedio.setVedioStatus(1);// 暂时默认审核通过
		vedio.setSysFlag((byte) 1);
		if(type == 1 && vedioId != null){// 编辑
			vedioMapper.updateByPrimaryKeySelective(vedio);
		}else {
			vedioMapper.insertSelective(vedio);
		}
		
		addVedioLog(vedio.getId(), sysUser.getId(), type);
	}
	
	/**
	 * 根据ID获取视频
	 * @param vedioId
	 * @return
	 */
     public Vedio selectVedioById(Integer vedioId){
    	 if(vedioId == null){
    		 return null;
    	 }
    	 return vedioMapper.selectByPrimaryKey(vedioId);
     }

	/**
	 * 视频上传记录
	 * 
	 * @param vedioId
	 *            视频ID
	 * @param sysUserId
	 *            视频发布者ID
	 * @param type
	 *            操作类型：0-上传视频；1-编辑；2-删除
	 */
	public void addVedioLog(Integer vedioId, Integer sysUserId, Integer type) {
		VedioLog vedioLog = new VedioLog();
		vedioLog.setVedioId(vedioId);
		vedioLog.setSysUserId(sysUserId);
		vedioLog.setType(type);
		vedioLog.setAddTime(new Date());
		vedioLog.setSysFlag((byte) 1);
		vedioLogMapper.insertSelective(vedioLog);
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

	/**
	 * 条件查询视频统计
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
	public int selectVedioSize(Integer vedioCategoryId, String searchCriteria) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("vedioCategoryId", vedioCategoryId);
		map.put("searchCriteria", searchCriteria);
		return vedioCustomMapper.countVedios(map);
	}

	public void deleteVedios(String[] vedioIds) {
		if (vedioIds == null) {
			return;
		}
	}
}
