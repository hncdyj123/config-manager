package com.jzx.config.core;

import java.util.List;

import org.I0Itec.zkclient.DataUpdater;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zk连接实体
 * 
 * @author hncdyj123@163.com
 * @date 2016年6月28日 上午9:14:57
 *
 */
public class ZkClientConnect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientConnect.class);
	private ZkClient zkClient;
	public static String ROOT_PATH = "/conf";

	public ZkClientConnect() {

	}

	public ZkClientConnect(String address) {
		this(address, Integer.MAX_VALUE);
	}

	public ZkClientConnect(String address, int timeOut) {
		this.zkClient = new ZkClient(address, timeOut);
		if (!zkClient.exists(ROOT_PATH)) {
			zkClient.createPersistent(ROOT_PATH);
		}
	}

	public ZkClient getZkClient() {
		return zkClient;
	}

	/**
	 * 获取节点数据
	 * 
	 * @param path
	 * @return
	 */
	public Object readData(String path) {
		return zkClient.readData(path);
	}

	/**
	 * 获取节点下的文件列表
	 * 
	 * @param path
	 * @return
	 */
	public List<String> getChildren(String path) {
		return zkClient.getChildren(path);
	}

	/**
	 * 创建节点
	 * 
	 * @param path
	 * @param data
	 */
	public boolean createNote(String path, Object data) {
		if (!zkClient.exists(path)) {
			zkClient.createPersistent(path, data);
			return true;
		} else {
			LOGGER.warn("path = " + path + " exists!");
			return false;
		}
	}

	/**
	 * 修改节点数据
	 * 
	 * @param path
	 * @param data
	 */
	public boolean updateNote(String path, final Object data) {
		if (zkClient.exists(path)) {
			zkClient.updateDataSerialized(path, new DataUpdater<Object>() {
				public Object update(Object currentData) {
					currentData = data;
					return currentData;
				}
			});
			return true;
		} else {
			LOGGER.warn("update path = " + path + " exists!");
			return false;
		}
	}

	/**
	 * 删除节点数据
	 * 
	 * @param path
	 */
	public boolean deleteNote(String path) {
		if (zkClient.exists(path)) {
			zkClient.delete(path);
			return true;
		} else {
			LOGGER.warn("delete path = " + path + " exists!");
			return false;
		}
	}

	/**
	 * 订阅children变化
	 * 
	 * @param zkClient
	 * @param path
	 */
	public void childChangesListener(ZkClient zkClient, final String path) {
		zkClient.subscribeChildChanges(path, new IZkChildListener() {

			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				LOGGER.warn("clildren of path " + parentPath + ":" + currentChilds);
			}

		});
	}

	/**
	 * 订阅节点数据变化
	 * 
	 * @param zkClient
	 * @param path
	 */
	public void dataChangesListener(ZkClient zkClient, final String path) {
		zkClient.subscribeDataChanges(path, new IZkDataListener() {
			public void handleDataChange(String dataPath, Object data) throws Exception {
				LOGGER.warn("Data of " + dataPath + " has changed.");
			}

			public void handleDataDeleted(String dataPath) throws Exception {
				LOGGER.warn("Data of " + dataPath + " has changed.");
			}
		});
	}

	/**
	 * 订阅连接状态的变化
	 * 
	 * @param zkClient
	 */
	public void stateChangesListener(ZkClient zkClient) {
		zkClient.subscribeStateChanges(new IZkStateListener() {
			public void handleStateChanged(KeeperState state) throws Exception {
				LOGGER.warn("handleStateChanged");
			}

			public void handleSessionEstablishmentError(Throwable error) throws Exception {
				LOGGER.warn("handleSessionEstablishmentError");
			}

			public void handleNewSession() throws Exception {
				LOGGER.warn("handleNewSession");
			}
		});
	}

	public static void main(String[] args) {
		ZkClientConnect zkClientConnect = new ZkClientConnect("192.168.1.11:2181");
		// 新增
		zkClientConnect.createNote("/conf/config-manager-web-dev", "1=1\n2=2\n3=3");
		// 修改
		// zkClientConnect.updateNote("/conf/ams-dev", "1=11\n2=2\n3=3\nzk.address=192.168.8.144:2181");
		// // 获取
		// String confStr = (String) zkClientConnect.readData("/myapp");
		// LOGGER.warn(confStr);

		// // 获取节点数据
		// List<String> pathList = zkClientConnect.getChildren("/");
		// for (String path : pathList) {
		// LOGGER.warn(path);
		// }
	}

	public void closeZkClient() {
		try {
			if (zkClient != null) {
				zkClient.close();
			}
		} catch (Exception e) {
			LOGGER.error("closeZkClient()执行失败：{}", e);
		}
	}
}
