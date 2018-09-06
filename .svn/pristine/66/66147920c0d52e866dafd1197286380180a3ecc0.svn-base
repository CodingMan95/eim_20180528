package net.eimarketing.eim_20180528.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.service.UserService;
import net.eimarketing.eim_20180528.service.VideoScoreService;
import net.eimarketing.eim_20180528.util.Succeed;
import net.eimarketing.eim_20180528.util.ToJsonUtils;
import net.eimarketing.eim_20180528.util.Util;
import net.eimarketing.eim_20180528.entity.Number;

@Controller
public class VideoScoreController {
	
	@Autowired
	private VideoScoreService videoScoreService;
	@Autowired
	private UserService userService;
	
	// 为视频打分:updateVideoScore
	@RequestMapping("updateVideoScore.do")
	@ResponseBody
	public void updateVideoScore(VideoScore vs, HttpServletRequest request, HttpServletResponse response){
		String id = "TJ11401501";//用户ID
		int score = 0;
		String name = userService.selectUser(id).getEmployee_name();
		String area = userService.selectUser(id).getEmployee_area();
		VideoScore videoScore = new VideoScore();
		videoScore.setEmployee_id(id);
		videoScore.setEmployee_name(name);
		videoScore.setEmployee_area(area);
		videoScore.setScore(score);
//		Date sdf=new Date();
//		sdf.getTime();
//		videoScore.setTime(sdf);
		int msg = videoScoreService.updateVideoScore(videoScore);
		System.out.println(msg);
	}
	
	// 根据地区查询排行榜:selectTopScore
	@RequestMapping("selectTopScore.do")
	@ResponseBody
	public List<VideoScore> selectTopScore(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		String employee_area = "天津大区";
		//request.getParameter("");
		List<VideoScore> scoreList = videoScoreService.selectTopScore(employee_area);
	
		System.out.println(JSONObject.toJSONString(scoreList));
		
		Util.println(request, response, new Succeed("", scoreList));
		return scoreList;
		
	}
	
	//根据裁判的id查询对应人员的分数：selectByRater
	@ResponseBody
	@RequestMapping(value="selectByRater.do",produces = "text/plain;charset=utf-8")
	//@RequestMapping("selectByRater.do")
	public String selectByRater(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=utf-8");
//
//		String employee_id = "TJ11000000";
//
//		//List<VideoScore> list = videoScoreService.selectByRater(employee_id);
//		Number score = videoScoreService.selectNumByRater(employee_id);
//		
//		System.out.println("user:"+JSONObject.toJSONString(score));
//
//		Util.println(request, response, new Succeed("", score));
		String employee_id = "HB11000000";
		int number = videoScoreService.selectNumByRater(employee_id);//已经打过分的人
		int total = videoScoreService.selectTotalByRater(employee_id);
		List<VideoScore> scores = videoScoreService.selectByRater(employee_id);
		VideoScore score = new VideoScore();
		List myList = new ArrayList<>();
		for(int i = 0; i < scores.size(); i++){
			
			score = scores.get(i);
			myList.add(score);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("num", number);//打分的人数
		jsonObject.put("total", total);//总人数
		jsonObject.put("area", userService.selectUser(employee_id).getEmployee_area());
		jsonObject.put("user", myList);
		ToJsonUtils utils = new ToJsonUtils();
		String json = utils.ObjectToJson(jsonObject);
		System.out.println(jsonObject);
		return json;
	}
	
}
