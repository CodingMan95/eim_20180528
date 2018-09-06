package net.eimarketing.eim_20180528.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.WechatUser;
import net.eimarketing.eim_20180528.helper.ApplicationPathHelper;
import net.eimarketing.eim_20180528.service.UploadService;
import net.eimarketing.eim_20180528.service.WechatService;
import net.eimarketing.eim_20180528.test.Upload;
import net.eimarketing.eim_20180528.util.Base64Utils;

@Controller
public class WeChatController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);
	
	// 用户和cookie之间的关系
	private static final Map<String, WechatUser> us = new HashMap<>();

	@Autowired
	private WechatService wechatService;
	@Autowired
	private UploadService uploadService;

	//微信授权登录接口
	@RequestMapping("admin.do")
	public void getWeChatUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println(request.getSession().getId());
		String userInfo = request.getParameter("userInfo");
		if (StringUtils.isBlank(userInfo)) {
			response.getWriter().print("userInfo is null");
			return;
		}
		LOGGER.info("userInfo:" + userInfo);

		String jsonData = URLDecoder.decode(Base64Utils.decode(userInfo), "UTF-8");

		if (StringUtils.isBlank(jsonData)) {
			response.getWriter().print("jsonData is null");
			return;
		}

		LOGGER.info("jsonData:" + jsonData);

		WechatUser user = JSONObject.parseObject(jsonData, WechatUser.class);

		
		WechatUser u = wechatService.findUser(user.getOpenid());//通过openid查找微信用户
		
		if (null==u) {
			try {
				String path="/usr/local/tomcat/apache-tomcat-8.0.52/webapps/eim_20180528_ziyuan/wechatImg";
				//String path="/htdocs/eim/net/ei-marketing/app/eim_20180528_ziyuan/wechatImg";
				String imgUrl = Upload.download(user.getHeadimgurl(), user.getOpenid() + ".jpg",path);
				user.setImg(imgUrl);
				wechatService.addUser(user);//添加微信用户
				request.getSession().setAttribute("user", user);
				//wechatService.updateImg(imgUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			request.getSession().setAttribute("user", u);
		}

		response.sendRedirect(ApplicationPathHelper.get(request) + "web-mobile/index.html");//跳转页面
	}


}
