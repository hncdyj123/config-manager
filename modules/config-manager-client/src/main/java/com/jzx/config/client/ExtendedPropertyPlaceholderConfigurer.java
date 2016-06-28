package com.jzx.config.client;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.I0Itec.zkclient.IZkDataListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.jzx.config.core.ZkClientConnect;
/**
 * spring配置扩展
 * @author hncdyj123@163.com
 * @date 2016年6月28日 上午9:14:02
 *
 */
public class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static Logger logger = LoggerFactory.getLogger(ExtendedPropertyPlaceholderConfigurer.class);

	private Properties props;

	private boolean loadConfCenter = true;

	public void setLoadConfCenter(boolean loadConfCenter) {
		this.loadConfCenter = loadConfCenter;
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		if (!loadConfCenter) {
			super.processProperties(beanFactory, props);
			this.props = props;
			PropertyConfigurer.load(props);
			return;
		}

		String group = props.getProperty("deploy.group");
		String dataId = props.getProperty("deploy.dataId");
		logger.warn("配置项：group=" + group);
		logger.warn("配置项：dataId=" + dataId);

		String configInfo = "";
		if (StringUtils.isNotEmpty(group) && StringUtils.isNotEmpty(dataId)) {
			ZkClientConnect zkClientConnect = new ZkClientConnect(props.getProperty("zk.address"));
			final String confPath = ZkClientConnect.ROOT_PATH + "/" + group + "-" + dataId;
			configInfo = (String) zkClientConnect.readData(confPath);
			if (StringUtils.isEmpty(configInfo)) {
				logger.warn("配置中心没有找到配置：" + confPath);
				return;
			}

			try {
				logger.warn("配置项内容: \n" + configInfo);
				StringReader reader = new StringReader(configInfo);
				props.load(reader);
				PropertyConfigurer.load(props);
			} catch (IOException e) {
				logger.error("配置合并异常" + e);
			}

			zkClientConnect.getZkClient().subscribeDataChanges(confPath, new IZkDataListener() {
				public void handleDataChange(String dataPath, Object data) throws Exception {
					if (StringUtils.equalsIgnoreCase(confPath, dataPath)) {
						logger.warn("Data of " + dataPath + " has changed.");
						// 客户端处理数据的逻辑
						logger.warn("已改动的配置：\n" + (String) data);
						StringReader reader = new StringReader((String) data);
						try {
							PropertyConfigurer.props.load(reader);
						} catch (IOException e) {
							logger.error("配置合并异常" + e);
						}

					}
				}

				public void handleDataDeleted(String dataPath) throws Exception {
					logger.warn("Data of " + dataPath + " has changed.");
				}
			});

		} else {
			PropertyConfigurer.load(props);
		}
		super.processProperties(beanFactory, props);
		this.props = props;
	}

	public Object getProperty(String key) {
		return props.get(key);
	}
}