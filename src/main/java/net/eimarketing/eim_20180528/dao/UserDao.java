package net.eimarketing.eim_20180528.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.VideoScore;

public interface UserDao {

	//public User userFirstLogin(String employee_id, String defaultpass);

	public User userLogin(String employee_id, String password);
	
	public User selectUser(String employee_id);//通过id查询用户信息

	//public int modifyPassword(String employee_id, String defaultpass, String password);

	public String selectUserById(String employee_id);// 通过id查询用户密码
	
	public int updateSeniorRater1();//预赛高级评委身份变为游客
	public int updateSeniorRater2();//恢复预赛高级评委身份
	public int updatePrimaryRater1();//预赛初级评委身份变为游客
	public int updatePrimaryRater2();//恢复预赛初级评委身份
	
	
	void updateUserInfo(User user);//根据用户id修改openid
	public String selectRaterById(String employee_id);//通过参赛人员的id查对应评委的id 
	
	
	List getRaterByRola(String employee_area);//根据传入的 用户身份 和  地区  查出 评委人数 

	public VideoScore getScoreByUserId(@Param("id") String id);
	
}
