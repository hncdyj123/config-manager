package com.jzx.config.service;

import com.jzx.config.service.domain.Configuration;

/**
 * 配置管理service
 * 
 * @author hncdyj123@163.com
 * @date 2016年6月21日 下午4:34:24
 *
 */
public interface ConfigurationService {
	public boolean insertConfiguration(Configuration configuration);

	public boolean updateConfiguration(Configuration configuration);

	public boolean deleteConfiguration(Configuration configuration);

	public Configuration queryConfiguration(Configuration configuration);
}
