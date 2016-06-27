package com.jzx.config.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jzx.config.example.bean.ExampleBean;
import com.jzx.config.utils.SpringBeanUtils;

public class SpringMain {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext cotext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ExampleBean exampleBean = (ExampleBean) SpringBeanUtils.getBean("exampleBean");
		while (true) {
			System.out.println(exampleBean.getProperties("1"));
			Thread.sleep(5000);
		}
	}
}
