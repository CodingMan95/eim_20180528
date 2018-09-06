package net.eimarketing.eim_20180528.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.eimarketing.eim_20180528.dao.WechatDao;
import net.eimarketing.eim_20180528.entity.WechatUser;
import net.eimarketing.eim_20180528.service.WechatService;

@Service
@Transactional
public class WechatUserServiceImpl implements WechatService{

	@Autowired
	private WechatDao wechatDao;
	
	@Override
	public WechatUser findUser(String openid) {
		return wechatDao.findUser(openid);
	}

	@Override
	public int addUser(WechatUser user) {
		return wechatDao.addUser(user);
	}

	@Override
	public WechatUser findUserByImg(String headimgurl) {
		// TODO Auto-generated method stub
		return wechatDao.findUserByImg(headimgurl);
	}

	@Override
	public int updateImg(String img) {
		// TODO Auto-generated method stub
		return wechatDao.updateImg(img);
	}

	@Override
	public WechatUser getUserInfo(String openid) {
		// TODO Auto-generated method stub
		return wechatDao.getUserInfo(openid);
	}

}
