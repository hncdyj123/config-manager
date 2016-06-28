package com.jzx.config.service.domain;
/**
 * 配置解析实体类
 * @author hncdyj123@163.com
 * @date 2016年6月28日 上午9:15:49
 *
 */
public class Configuration {
	// groupId
	private String groupId;
	// dataId
	private String dataId;
	// 配置项
	private String configuration;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

}
