package com.jzx.config.web;

import javax.annotation.Resource;

import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzx.config.service.ConfigurationService;
import com.jzx.config.service.domain.Configuration;
import com.jzx.config.web.result.JsonMessage;

@Controller
@RequestMapping("/flow")
public class FlowController {
	private static Logger logger = LoggerFactory.getLogger(FlowController.class);
	@Resource
	private ConfigurationService configurationService;

	@RequestMapping("/insertConfiguration")
	@ResponseBody
	public JsonMessage insertConfiguration(Configuration configuration) {
		JsonMessage jsonMessage = new JsonMessage();
		try {
			boolean result = configurationService.insertConfiguration(configuration);
			if (!result) {
				jsonMessage.setCode(500);
				jsonMessage.setMessage("节点文件为：" + configuration.getGroupId() + "-" + configuration.getDataId() + "已经存在!");
			}
		} catch (Exception e) {
			logger.error("error: " + e.getMessage());
			jsonMessage.setMessage("服务器内部异常：请联系管理员！");
			jsonMessage.setCode(500);
		}
		return jsonMessage;
	}

	@RequestMapping("/updateConfiguration")
	@ResponseBody
	public JsonMessage updateConfiguration(Configuration configuration) {
		JsonMessage jsonMessage = new JsonMessage();
		try {
			boolean result = configurationService.updateConfiguration(configuration);
			if (!result) {
				jsonMessage.setCode(500);
				jsonMessage.setMessage("节点文件为：" + configuration.getGroupId() + "-" + configuration.getDataId() + "不存在,修改失败!");
			}
		} catch (Exception e) {
			logger.error("error: " + e.getMessage());
			jsonMessage.setMessage("服务器内部异常：请联系管理员！");
			jsonMessage.setCode(500);
		}
		return jsonMessage;
	}

	@RequestMapping("/deleteConfiguration")
	@ResponseBody
	public JsonMessage deleteConfiguration(Configuration configuration) {
		JsonMessage jsonMessage = new JsonMessage();
		try {
			boolean result = configurationService.deleteConfiguration(configuration);
			if (!result) {
				jsonMessage.setCode(500);
				jsonMessage.setMessage("节点文件为：" + configuration.getGroupId() + "-" + configuration.getDataId() + "不存在,删除失败!");
			}
		} catch (Exception e) {
			logger.error("error: " + e.getMessage());
			jsonMessage.setMessage("服务器内部异常：请联系管理员！");
			jsonMessage.setCode(500);
		}
		return jsonMessage;
	}

	@RequestMapping("/queryConfiguration")
	@ResponseBody
	public JsonMessage queryConfiguration(Configuration configuration) {
		JsonMessage jsonMessage = new JsonMessage();
		try {
			Configuration configurationResult = configurationService.queryConfiguration(configuration);
			jsonMessage.setResult(configurationResult);
		} catch (ZkNoNodeException zkEx) {
			logger.error("error: ", zkEx);
			jsonMessage.setMessage("获取节点配置异常：节点文件为：" + configuration.getGroupId() + "-" + configuration.getDataId()+"不存在!");
			jsonMessage.setCode(500);
		} catch (Exception e) {
			logger.error("error: " + e.getMessage());
			jsonMessage.setMessage("服务器内部异常：请联系管理员！");
			jsonMessage.setCode(500);
		}
		return jsonMessage;
	}
}
