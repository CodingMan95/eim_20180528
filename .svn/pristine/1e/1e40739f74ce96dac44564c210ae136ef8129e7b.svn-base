package net.eimarketing.eim_20180528.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.eimarketing.eim_20180528.entity.WechatUser;
import net.eimarketing.eim_20180528.service.WechatService;
@Controller
public class Test {
	@Autowired
	private WechatService wechatService;
	
	@ResponseBody
	@RequestMapping("get.do")
	public WechatUser get(String openId){
		return wechatService.getUserInfo(openId);
	}

}
