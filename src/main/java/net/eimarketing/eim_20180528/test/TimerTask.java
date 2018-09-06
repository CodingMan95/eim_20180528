package net.eimarketing.eim_20180528.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.eimarketing.eim_20180528.service.UserService;

@Component
public class TimerTask {
	
	@Autowired
	private UserService userService;
	
  /**
   * 预赛高级评委身份设为游客
   */
 /* @Scheduled(cron = "0 55 10 23 6 *")
  public void updateSeniorRater1()
  {
	  userService.updateSeniorRater1();//预赛高级评委身份设为游客
      System.out.println("定时器：预赛高级评委身份设为游客！");
  } */
  
  /**
   * 恢复预赛高级评委身份，预赛初级评委身份设为游客
   */
  /*@Scheduled(cron = "0 55 10 23 6 *")
  public void updateSeniorRater2()
  {
	  userService.updateSeniorRater2();//预赛高级评委身份设为游客
	  userService.updatePrimaryRater1();//预赛初级评委身份设为游客
      System.out.println("定时器：恢复预赛高级评委身份，预赛初级评委身份设为游客！");
  } */
  
}
