package net.eimarketing.eim_20180528.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.eimarketing.eim_20180528.dao.UserDao;
import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User userLogin(String employee_id, String password) {
		return userDao.userLogin(employee_id, password);
	}

	@Override
	public String selectUserById(String employee_id) {
		return userDao.selectUserById(employee_id);
	}

	@Override
	public int updateSeniorRater1() {
		return userDao.updateSeniorRater1();
	}

	@Override
	public int updateSeniorRater2() {
		return userDao.updateSeniorRater2();
	}

	@Override
	public int updatePrimaryRater1() {
		return userDao.updatePrimaryRater1();
	}

	@Override
	public int updatePrimaryRater2() {
		return userDao.updatePrimaryRater2();
	}

	@Override
	public User selectUser(String employee_id) {
		return userDao.selectUser(employee_id);
	}

	@Override
	public void updateUserInfo(User user) {
		// TODO Auto-generated method stub
		userDao.updateUserInfo(user);
	}

	@Override
	public String selectRaterById(String employee_id) {
		// TODO Auto-generated method stub
		return userDao.selectRaterById(employee_id);
	}

	@Override
	public List getRaterByRola(String employee_area) {
		return userDao.getRaterByRola(employee_area);
	}

	@Override
	public VideoScore getScoreByUserId(String id) {
		return userDao.getScoreByUserId(id);
	}

}
