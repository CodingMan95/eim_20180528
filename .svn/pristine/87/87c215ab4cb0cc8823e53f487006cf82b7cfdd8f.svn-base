package net.eimarketing.eim_20180528.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.filter.SensiteWord;
import net.eimarketing.eim_20180528.handler.SystemWebSocketHandler;
import net.eimarketing.eim_20180528.service.UserService;
import net.eimarketing.eim_20180528.util.Succeed;
import net.eimarketing.eim_20180528.util.Util;
import net.sf.json.JSONArray;

@Controller
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("userLogin.do")
	public void userLogin(String employee_id, String password, HttpServletRequest request) throws Exception {

		// response.setContentType("text/html;charset=utf-8");
		
//		SensiteWord sensiteWord=new SensiteWord();
//        Set<String>set=sensiteWord.readSensitivateWord();//读取敏感词库
//        HashMap map=sensiteWord.initSensitivateWord(set);
//        String content = "sb,操你妈";
//
//        String c=sensiteWord.replaceSensitiveWord(content,"*");
//        System.out.println("用户content:"+content);
//        Set<String>set1=sensiteWord.getSensitivateWord(content);
//        System.out.println("用户content中的敏感词:"+set1.size()+"---"+set1);
//        System.out.println("过滤后的content:"+c);
		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		jsonObject.put("a", 123);
		jsonObject.put("b", 456);
		json.put("c", 123);
		json.put("d", 456);
		array.add(jsonObject);
		System.out.println(array.toString());
		
		
//		User u =new User();
//		
//		u.setEmployee_id("001");
//		u.setEmployee_name("张三");
//		SystemWebSocketHandler socketHandler = new SystemWebSocketHandler();
//		String json = socketHandler.toController(u);
//		System.out.println(json);
//		
//		request.getAttribute("id");
//
//		employee_id = "SD22416401";
//		password = "111111";
//
//		String pa = userService.selectUserById(employee_id);
//		User user = userService.userLogin(employee_id, password);
//
//		if (password.equals(pa)) {
//			System.out.println("登陆成功！跳转至相应页面");
//		} else {
//			System.out.println("登录失败！请重新登陆");
//		}
//
//		request.getSession().setAttribute("user", user);
		
		// System.out.println("user:"+JSONObject.toJSONString(user));

		// Util.println(request, response, new Succeed("", user));
	}
	
	@ResponseBody
	@RequestMapping("userInfo.do")
	public void userInfo(HttpServletRequest request) throws Exception {
		
		// response.setContentType("text/html;charset=utf-8");
		SensiteWord sensiteWord=new SensiteWord();
        Set<String>set=sensiteWord.readSensitivateWord();//读取敏感词库
        HashMap map=sensiteWord.initSensitivateWord(set);
        String content = "太多的伤感情怀s b sb sb sb,fucker也许人民只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着,sb,sb,sb。";

        String c=sensiteWord.replaceSensitiveWord(content,"*");
        System.out.println("用户content:"+content);
        Set<String>set1=sensiteWord.getSensitivateWord(content);
        System.out.println("用户content中的敏感词:"+set1.size()+"---"+set1);
        System.out.println("过滤后的content:"+c);
    }
//		User user = new User();
//		
//		request.getSession().setAttribute("user", user);
		
		// System.out.println("user:"+JSONObject.toJSONString(user));

		// Util.println(request, response, new Succeed("", user));
	//}

//	@ResponseBody
//	@RequestMapping("modifyPassword.do")
//	public void modifyPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//		// response.setContentType("text/html;charset=utf-8");
//		//
//		String employee_id = "SD22416401";
//		String defaultpass = "123456";
//		String password = "111111";
//		// String employee_id = request.getParameter("employee_id");
//		// String defaultpass = request.getParameter("defaultpass");
//		// String password = request.getParameter("password");
//		// User user = request.getParameter(user);
//
//		System.out.println("id:" + employee_id);
//		int u = userService.modifyPassword(employee_id, defaultpass, password);
//		System.out.println(":" + u);
//		// System.out.println("user:"+JSONObject.toJSONString(user));
//
//		// Util.println(request, response, new Succeed("", user));
//	}

}
