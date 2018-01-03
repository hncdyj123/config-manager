package com.jzx.config.service;

import org.springframework.stereotype.Service;

import com.jzx.config.core.ZkClientConnect;
import com.jzx.config.properties.Properties;
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

	private static String FILEIDF = "/";

	private static String FILEIDF_ = "-";

	public boolean insertConfiguration(Configuration configuration) {
		ZkClientConnect zkClientConnect = new ZkClientConnect(Properties.getString("zk.address"));
		boolean result = zkClientConnect.createNote(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId(), configuration.getConfiguration());
		zkClientConnect.closeZkClient();
		return result;
	}

	public boolean updateConfiguration(Configuration configuration) {
		ZkClientConnect zkClientConnect = new ZkClientConnect(Properties.getString("zk.address"));
		boolean result = zkClientConnect.updateNote(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId(), configuration.getConfiguration());
		zkClientConnect.closeZkClient();
		return result;
	}

	public boolean deleteConfiguration(Configuration configuration) {
		ZkClientConnect zkClientConnect = new ZkClientConnect(Properties.getString("zk.address"));
		boolean result = zkClientConnect.deleteNote(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId());
		zkClientConnect.closeZkClient();
		return result;
	}

	public Configuration queryConfiguration(Configuration configuration) {
		ZkClientConnect zkClientConnect = new ZkClientConnect(Properties.getString("zk.address"));
		String configurationStr = (String) zkClientConnect.readData(zkClientConnect.ROOT_PATH + FILEIDF + configuration.getGroupId() + FILEIDF_ + configuration.getDataId());
		configuration.setConfiguration(configurationStr);
		zkClientConnect.closeZkClient();
		return configuration;
	}

}
