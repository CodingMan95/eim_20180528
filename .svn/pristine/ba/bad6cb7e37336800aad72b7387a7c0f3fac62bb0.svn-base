package net.eimarketing.eim_20180528.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.WechatUser;
import net.eimarketing.eim_20180528.service.UploadService;
import net.eimarketing.eim_20180528.service.WechatService;
import net.eimarketing.eim_20180528.util.ToJsonUtils;

@Controller
public class TestController {
	
	@Autowired
	private WechatService we;
	@Autowired
	private UploadService up;
	
	
	@ResponseBody
	@RequestMapping("test.do")
	public void userLogin(String employee_id, String password, HttpServletRequest request) throws IOException {

		// response.setContentType("text/html;charset=utf-8");

		String openid = "123456";
		WechatUser user = we.findUser(openid);
		ToJsonUtils utils = new ToJsonUtils();
		System.out.println(utils.ObjectToJson(user));
		
		// System.out.println("user:"+JSONObject.toJSONString(user));

		// Util.println(request, response, new Succeed("", user));
	}
	

	@RequestMapping(value="data.do")
	public void userLogin() throws IOException {
		up.name();
	}

}
