package net.eimarketing.eim_20180528.dao;

import org.apache.ibatis.annotations.Param;

import net.eimarketing.eim_20180528.entity.WechatUser;

public interface WechatDao {
	
	public WechatUser findUser(String openid);	//查找微信用户
	public int updateImg(String img);
	public WechatUser findUserByImg(String headimgurl);//根据头像查找用户昵称和id
	public int addUser(WechatUser user);	//添加微信用户
	
	WechatUser getUserInfo(@Param("openid") String openid);
}
