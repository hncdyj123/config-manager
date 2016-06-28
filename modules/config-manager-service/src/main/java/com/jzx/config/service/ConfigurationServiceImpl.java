package com.jzx.config.service;

import org.springframework.stereotype.Service;

import com.jzx.config.client.PropertyConfigurer;
import com.jzx.config.core.ZkClientConnect;
import com.jzx.config.service.domain.Configuration;

/**
 * 配置管理service实现类
 * 
 * @author hncdyj123@163.com
 * @date 2016年6月28日 上午9:16:04
 *
 */
@SuppressWarnings("static-access")
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private ZkClientConnect zkClientConnect = new ZkClientConnect(PropertyConfigurer.getString("zk.address"));

	private static String FILEIDF = "/";

	private static String FILEIDF_ = "-";

	public boolean insertConfiguration(Configuration configuration) {
		return zkClientConnect.createNote(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId(), configuration.getConfiguration());
	}

	public boolean updateConfiguration(Configuration configuration) {
		return zkClientConnect.updateNote(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId(), configuration.getConfiguration());
	}

	public boolean deleteConfiguration(Configuration configuration) {
		return zkClientConnect.deleteNote(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId());
	}

	public Configuration queryConfiguration(Configuration configuration) {
		String configurationStr = (String) zkClientConnect.readData(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId());
		configuration.setConfiguration(configurationStr);
		return configuration;
	}

}
