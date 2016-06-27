package com.jzx.config.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件读取
 * 
 * @author hncdyj123@163.com
 * @date 2016年6月20日 上午9:53:43
 *
 */
public class Properties {
	private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);
	private static final String BUNDLE_NAME = "app";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	private static ResourceBundle EXIT_RESOURCE_BUNDLE = null;

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			LOGGER.error("load properties for key = " + key + "not found!");
			return null;
		}
	}

	public static String getByKey(String key) {
		try {
			if (EXIT_RESOURCE_BUNDLE == null) {
				LOGGER.warn("外部配置初始化为空");
				return null;
			}
			return EXIT_RESOURCE_BUNDLE.getString(key);
		} catch (Exception e) {
			LOGGER.error("load properties for key = " + key + "not found!");
			return null;
		}
	}

	public static void initProperties(String filePath) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		EXIT_RESOURCE_BUNDLE = new PropertyResourceBundle(in);
	}
}
