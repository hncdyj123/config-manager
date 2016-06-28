package com.jzx.config.example.bean;

import com.jzx.config.client.PropertyConfigurer;


public class ExampleBean {
	public String getProperties(String key) {
		return PropertyConfigurer.getString(key);
	}
}
