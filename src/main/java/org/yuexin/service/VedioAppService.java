package org.yuexin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuexin.dao.VedioCustomMapper;
import org.yuexin.dao.VedioLogMapper;
import org.yuexin.dao.VedioMapper;
import org.yuexin.model.SysUser;
import org.yuexin.model.Vedio;
import org.yuexin.model.VedioLog;
import org.yuexin.model.dto.VedioDTO;

/**

* @Description: app视频查询

* @author liuqin

* @date 2016-12-19 下午2:34:49

*
 */
@Service
public class VedioAppService {
	@Autowired
	private VedioMapper vedioMapper;
	@Autowired
	private VedioCustomMapper vedioCustomMapper;
	@Autowired
	private VedioLogMapper vedioLogMapper;

	
}
