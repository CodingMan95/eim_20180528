package net.eimarketing.eim_20180528.service;

import net.eimarketing.eim_20180528.entity.WechatUser;

public interface WechatService {

	public WechatUser findUser(String openid);	//查找微信用户
	public int updateImg(String img);
	public WechatUser findUserByImg(String headimgurl);//根据头像查找用户昵称和id
	public int addUser(WechatUser user);	//添加微信用户
	
	WechatUser getUserInfo(String openid);
}
