package net.eimarketing.eim_20180528.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.CommentInfo;
import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.Video;
import net.eimarketing.eim_20180528.entity.VideoComment;
import net.eimarketing.eim_20180528.service.VideoCommentService;
import net.eimarketing.eim_20180528.service.VideoService;
import net.eimarketing.eim_20180528.service.WechatService;
import net.eimarketing.eim_20180528.util.Succeed;
import net.eimarketing.eim_20180528.util.Util;
import net.sf.json.JSONArray;

@Controller
public class VideoCommentController {
	
	@Autowired
	private VideoCommentService videoCommentService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private WechatService weChatService;
	
	
	// 查看某一视频的全部评论:selectAllComment
	@RequestMapping("selectAllComment.do")
	@ResponseBody
	public void selectAllComment(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

//		//Integer video_id = Integer.parseInt(request.getParameter("video_id"));
//		Integer video_id = 1;
//		List<VideoComment> comments = videoCommentService.selectAllComment(video_id);
//		System.out.println(JSONObject.toJSONString(comments));
//		Util.println(request, response, new Succeed("", comments));
		
		int video_id = 10;//视频id
		//User user = us.get(cookie);
		String openid = "orLm4wTPuPxGk5ZUVOOuvd-3gRe4";
		
//		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		//dfs.parse(now);
		
		List<VideoComment> comments = videoCommentService.selectAllComment(video_id);//通过id查看全部评论
	
		JSONArray array = new JSONArray();
		
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		VideoComment comment = new VideoComment();
		CommentInfo commentInfo = new CommentInfo();
		List myList = new ArrayList<>();
		for(int i = 0; i < comments.size(); i++){
			
//			String id = comments.get(i).getCommentator_id();//评论者id
//			String name = comments.get(i).getCommentator_name();//评论者name
//			String content = comments.get(i).getContent();//评论内容
			int time = 0;
			Date nowtime = new Date();
			long now = nowtime.getTime();
			Date date = comments.get(i).getTime();
			long commenttime = date.getTime();
			
			time = (int)(now - commenttime)/ (60 * 1000);
			
			//jsonObject.put("comments", comment);
			commentInfo.setCom_id(comments.get(i).getComment_id());
			commentInfo.setContent(comments.get(i).getContent());
			commentInfo.setFab(comments.get(i).getFabulous());
			commentInfo.setImg(weChatService.findUser(openid).getImg());
			commentInfo.setIsfab(0);
			commentInfo.setName(weChatService.findUser(openid).getNickname()+"("+comments.get(i).getCommentator_name()+"("+comments.get(i).getCommentator_id()+")"+")");
			commentInfo.setTime(time);
//			jsonObject.put("img", wechatService.findUser(openid).getImg());
//			jsonObject.put("name", wechatService.findUser(openid).getNickname()+"("+comments.get(i).getCommentator_name()+"("+comments.get(i).getCommentator_id()+")"+")");
//			jsonObject.put("content", comments.get(i).getContent());
//			jsonObject.put("fab", comments.get(i).getFabulous());
//			jsonObject.put("isfab", 0);
//			jsonObject.put("com_id", comments.get(i).getComment_id());
//			jsonObject.put("time", time);
			
			//myList.add(jsonObject);
			myList.add(commentInfo);
			
			//jsonObject2.put("com", array.get(i));
			
			//array.add(jsonObject);
			//json.put("com", array.get(i));
			//json.put("com", array);
		}
		jsonObject2.put("com", myList);
		//jsonObject2.put("com", array);
		//String jString = utils.ObjectToJson(jsonObject2);
		System.out.println(JSONObject.toJSONString(jsonObject2));

	}
	
	// 通过评论id查询某一条评论的点赞数:selectFabulousById
	@RequestMapping("selectFabulousById.do")
	@ResponseBody
	public void selectFabulousById(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		//Integer comment_id = Integer.parseInt(request.getParameter("comment_id"));
		Integer comment_id = 1;
		int msg = videoCommentService.selectFabulousById(comment_id);
		
		System.out.println("fabulous:" + msg);
		Util.println(request, response, new Succeed("", msg));

	}
	
	// 添加评论:addComment
	@RequestMapping("addComment.do")
	@ResponseBody
	public void addComment(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Date time=new Date();
		time.getTime();
		
		int video_id = 1288;
		
		VideoComment comment = new VideoComment();
		comment.setCommentator_id("HB001");
		comment.setCommentator_name("张三");
		comment.setContent("很好看");
		comment.setEmployee_id("SD001");
		comment.setEmployee_name("李四");
		comment.setVideo_id(video_id);
		
		
		comment.setTime(time);
		//comment.setCommentator_id(commentator_id);
		
		int msg = videoCommentService.addComment(comment);
		if (msg == 1) {
			videoService.updateCommentNum(video_id);

		}
		
		System.out.println("fabulous:" + msg);
		Util.println(request, response, new Succeed("", msg));

	}

	// 为某一评论点赞:updateCommentFabulous
	@RequestMapping("updateCommentFabulous.do")
	@ResponseBody
	public void updateCommentFabulous(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		//VideoComment comment = new VideoComment();
		Integer comment_id = 1;
		
		// comment.setCommentator_id(commentator_id);

		videoCommentService.updateCommentFabulous(comment_id);

		//System.out.println("fabulous:" + msg);
		//Util.println(request, response, new Succeed("", msg));

	}

}
