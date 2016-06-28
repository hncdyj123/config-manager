 ![image](https://github.com/hncdyj123/config-manager/blob/master/images/project.png)
##config-manager是什么？

一个可以时时更新配置的，可以和spring无缝对接。

##config-manager有哪些功能？

* 方便的`程序配置`。
    *  项目中只需要有一个app.properties配置文件即可。
* `毫秒级别`的更新配置。
* `zookeeper`配置管理，支持集群。
* 支持`多点部署`。
* 无缝切换配置。

##使用说明

###环境配置准备

* 搭建一个zookeeper环境。
* git下载源代码。
* 切换目录到config-manager下面执行maven install。

###项目配置

* 在自己项目下pom.xml新增maven依赖。
```xml
<dependency>
	<groupId>com.jzx.config</groupId>
	<artifactId>config-manager-client</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-core</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-beans</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context</artifactId>
</dependency>
```

* 在resources下面新增一个app.properties`(名称可以随意)`
```
// 配置文件组
deploy.group=ams
// 配置文件dataId
deploy.dataId=dev
// zk地址
zk.address=192.168.8.144:2181
```

* 在spring配置中新增如下配置：
```xml
<bean id="propertyConfigurer" class="com.jzx.config.client.ExtendedPropertyPlaceholderConfigurer">
	<property name="locations">
		<list>
			<value>classpath:app.properties</value>
		</list>
	</property>
</bean>
```

##我的博客

* http://blog.csdn.net/hncdyj/article/

##关于作者

```javascript
  var ihubo = {
    nickName  : "杰锅锅",
    site : "http://blog.csdn.net/hncdyj/article/"
  }
```

##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件(hncdyj123#163.com, 把#换成@)


http://localhost:8080/config-manager-web/index/index.html
http://localhost:8080/config-manager-web/index/query.html
http://localhost:8080/config-manager-web/flow/insertConfiguration?groupId=ams&dataId=dev
http://localhost:8080/config-manager-web/flow/queryConfiguration?groupId=ams&dataId=dev