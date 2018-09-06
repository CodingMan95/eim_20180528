package net.eimarketing.eim_20180528.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.Video;
import net.eimarketing.eim_20180528.entity.VideoComment;
import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.entity.WechatUser;
import net.eimarketing.eim_20180528.filter.SensiteWord;
import net.eimarketing.eim_20180528.service.FlagService;
import net.eimarketing.eim_20180528.service.UserService;
import net.eimarketing.eim_20180528.service.VideoCommentService;
import net.eimarketing.eim_20180528.service.VideoScoreService;
import net.eimarketing.eim_20180528.service.VideoService;
import net.eimarketing.eim_20180528.service.WechatService;
import net.eimarketing.eim_20180528.util.ToJsonUtils;
import net.sf.json.JSONArray;
import net.eimarketing.eim_20180528.entity.Flags;
import net.eimarketing.eim_20180528.entity.Number;
import net.eimarketing.eim_20180528.entity.TopUser;

public class SystemWebSocketHandler implements WebSocketHandler {
	// cookie和WebSocketSession之间的关系
	private static final Map<String, Object> ses = new HashMap<>();
	// 用户和cookie之间的关系
	private static final Map<String, User> us = new HashMap<>();
	// 微信用户与cookie之间的关系
	private static final Map<String, WechatUser> wei = new HashMap<>();
	
	@Autowired
	private UserService userService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private VideoCommentService videoCommentService;
	@Autowired
	private VideoScoreService videoScoreService;
	@Autowired
	private FlagService flagService;
	@Autowired
	private WechatService wechatService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId());
		Map<String, Object> map = session.getHandshakeAttributes();

		String cookie = (String) map.get("cookie");
		WechatUser uWechatUser = (WechatUser)map.get("userInfo");
		ses.put(cookie, session);
		wei.put(cookie, uWechatUser);
		System.out.println("cookie:" + cookie);

		session.sendMessage(new TextMessage("Server:connected OK!"));
	}

	@Override
	public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {

		// TextMessage returnMessage = new TextMessage(wsm.getPayload() + "
		// received at server");

		String utl = "wsm:"+wsm.getPayload().toString();

		Map<String, Object> map = wss.getHandshakeAttributes();
		String cookie = (String) map.get("cookie");
		// 授权注释
//		WechatUser uWechatUser = wei.get(cookie);
//		System.out.println("cookie:" + cookie);
		// System.out.println("cookie:" + cookie);
		
		JSONObject jsStr = null;
		int type = 0 ;
		if(utl.contains("type")){
			 jsStr = JSONObject.parseObject(wsm.getPayload().toString());
			 type = jsStr.getInteger("type");
		}
		ToJsonUtils utils = new ToJsonUtils();// 转json工具类
		

	
		// 用户登录
		if (type == 1) {
			String employee_id = jsStr.getString("id");
			// String employee_id = "TJ11000000";
			// String password = "682061";

			String password = jsStr.getString("password");
			//
			String pass = userService.selectUserById(employee_id);

			User user = userService.userLogin(employee_id, password);
			JSONObject jsonObject = new JSONObject();
			
			if (null == user) {

				sendMessageToUser(cookie, new TextMessage("0"));
			} else if (password.equals(pass)) {
				us.put(cookie, user);
				/**
				 * 每次登录成功修改该用户对应的openid
				 */
				//user.setOpenId(uWechatUser.getOpenid());
				user.setOpenId("001");
				userService.updateUserInfo(user);
				
				
				String identity = user.getEmployee_identity();
				int num = videoService.selectNumById(employee_id);
				if (identity.equals("参赛人员")) {
					jsonObject.put("id", 1);
					if (num >= 4) {
						jsonObject.put("userId", 0);
					} else {
						jsonObject.put("userId", user.getEmployee_id());
					}

					String json = utils.ObjectToJson(jsonObject);
					sendMessageToUser(cookie, new TextMessage(json));
				} else if (identity.equals("预赛初级评委")) {
					jsonObject.put("id", 2);
					jsonObject.put("userId", 0);
					String json = utils.ObjectToJson(jsonObject);
					sendMessageToUser(cookie, new TextMessage(json));
				} else {
					jsonObject.put("id", 3);
					jsonObject.put("userId", 0);
					String json = utils.ObjectToJson(jsonObject);
					sendMessageToUser(cookie, new TextMessage(json));
				}
			} else if (!password.equals(pass)) {
				sendMessageToUser(cookie, new TextMessage("0"));
			}
			// 查看视频列表缩略图
		} else if (type == 2) {
			int id = jsStr.getInteger("id");
			//List<Video> videos = videoService.selectAllVideo();// 通过点赞查询全部视频排行
			List<Video> u = videoService.selectTopUser();//从复赛表查询所有用户id

			TopUser top = new TopUser();
			top.setBegin(id);
			top.setVi(u);
			List<Video> videos = videoService.selectLimit(top);

			Video video = new Video();
			JSONObject jsonObject = new JSONObject();
			/*if (id <= videos.size()) {*/
				// video = videoService.selectTopXVideo(id-1);
				/*int video_id = videos.get(id - 1).getVideo_id();// 根据传过来的参数确定查询哪个视频缩略图
				String video_img = videos.get(id - 1).getVideo_img();
				String video_url = videos.get(id - 1).getVideo_url();
				int fabulous = videos.get(id - 1).getFabulous();
				video.setVideo_id(video_id);
				video.setVideo_img(video_img);
				video.setVideo_url(video_url);
				video.setFabulous(fabulous);*/
				
				jsonObject.put("video", video);
				String json = utils.VideoListToJson(videos);
				System.out.println(json);
				sendMessageToUser(cookie, new TextMessage(json));
			/*} else {
				video.setVideo_id(0);
				video.setFabulous(0);
				video.setVideo_img("img");
				String json = utils.ObjectToJson(video);
				System.out.println(json);
				sendMessageToUser(cookie, new TextMessage(json));
			}*/

			// 查看某一指定视频
		} else if (type == 3) {
			// String openid = "";
			// 授权注释
			//String openid = uWechatUser.getOpenid();
			//String img = wechatService.findUser(openid).getHeadimgurl();
			User user = us.get(cookie);
			String employee_id = null;
			if (null == user) {
				// 授权注释
				//employee_id = uWechatUser.getOpenid();
				employee_id="okgGxt--qQxgSGrAbS2fj9mmpCZc";
			} else {
				employee_id = user.getEmployee_id();
			}
			//视频id
			int id = jsStr.getInteger("id");
			
			if (id < 0) {
				sendMessageToUser(cookie, new TextMessage("0"));
			} else {
				Video video = new Video();
				video = videoService.selectVideoById(id);
				if (null == video) {
					video.setVideo_id(0);
					video.setEmployee_id("0");
					video.setEmployee_name("null");
					video.setVideo_url("null");
					video.setVideo_tag("null");
					video.setComment_num(0);
					video.setFabulous(0);
					String json = utils.ObjectToJson(video);
					sendMessageToUser(cookie, new TextMessage(json));
				} else {
					// 本地注释
					//String img = "http://app.i-mineral.cn/eim_20180528/wechat_imgs/132.jpg";
					List<VideoComment> list = videoCommentService.selectAllComment(id);
					// String json_video = utils.ObjectToJson(video);
					WechatUser userx=wechatService.findUser(video.getOpenId());
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("img",video.getImg());
					jsonObject.put("userid", video.getEmployee_id());
					jsonObject.put("video_id", id);
					jsonObject.put("comment_num", list.size());
					jsonObject.put("fabulous", video.getFabulous());
					jsonObject.put("tag", video.getVideo_tag());
					// jsonObject.put("flag", 0);
					// int flag = flagService.selectFlagById(id);
					int flag = flagService.selectFlag(id, employee_id);
					if (flag == 0) {
						jsonObject.put("flag", 0);
					} else {
						jsonObject.put("flag", 1);
					}

					String json = utils.ObjectToJson(jsonObject);
					System.out.println(json);
					sendMessageToUser(cookie, new TextMessage(json));
				}
			}
			// 根据id查视频列表和点过赞的视频   根据他人视频获取他人发布视频的详情信息   没问题
		} else if (type == 4) {
			User u = us.get(cookie);
			JSONObject jsonObject = new JSONObject();
			User user = new User();
			
			String employee_id = jsStr.getString("id");
			// String employee_id = u.getEmployee_id();
			// String employee_id = "HB11420102";
			System.out.println(employee_id);
			if (employee_id.equals("0")) {
				user = u;
				//授权注释
				//jsonObject.put("img", uWechatUser.getImg());
				System.out.println(user);
			}else {
				user = userService.selectUser(employee_id);
				WechatUser wx=wechatService.getUserInfo(user.getOpenId());
				if(null==wx||null==wx.getImg()||""==wx.getImg()){
					jsonObject.put("img", "http://app.i-mineral.cn/eim_20180528_ziyuan/moren/wei.jpg");
				}else{
					jsonObject.put("img", wx.getImg());
				}
				
			}
		
			String userid = user.getEmployee_id();
			String name = user.getEmployee_name();
			String department = user.getEmployee_department();

			Video video = new Video();
			List<Video> listvideo = new ArrayList<>();//点赞视频

			List<Flags> video_id = flagService.selectVideoByFlag(employee_id);
			for (int i = 0; i < video_id.size(); i++) {
				Flags flag = video_id.get(i);
				int id = flag.getVideo_id();
				video = videoService.selectVideoImgById(id);
				if (video != null) {
					listvideo.add(video);
				}
			}
			
			jsonObject.put("userid", userid);
			jsonObject.put("name", name);
			
			jsonObject.put("department", department);
			List<Video> list = videoService.selectVideoByUser(userid);
//			if(!employee_id.equals("0")){
//				jsonObject.put("img", list.get(0).getImg());
//			}
			

			jsonObject.put("myvideo", list);
			jsonObject.put("othervideo", listvideo);

			String json = utils.ObjectToJson(jsonObject);
			System.out.println(json);

			sendMessageToUser(cookie, new TextMessage(json));
			
			// 给视频点赞
		} else if (type == 5) {

			User user = us.get(cookie);
			String employee_id = null;
			Flags flag = new Flags();
			// 授权注释
			//String openid = uWechatUser.getOpenid();
			if (null == user) {
				// 授权注释
				//employee_id = openid;//微信id
				// 本地注释
				employee_id = "okgGxt--qQxgSGrAbS2fj9mmpCZc";// 获取微信用户
			} else {
				employee_id = user.getEmployee_id();
			}

			int video_id = jsStr.getInteger("id");// 视频id

			int num = flagService.selectFlag(video_id, employee_id);
			// 视频未被点赞
			if (num == 0) {
				int msg = videoService.updateFabulous(video_id);// 给视频点赞

				// 点赞成功：将点赞者的id，被点赞视频id，保存到表中方便查询
				if (msg == 1) {
					flag.setEmployee_id(employee_id);
					// flag.setIsflag(1);
					flag.setVideo_id(video_id);
					flagService.addFlag(flag);
					// System.out.println(videoService.);
					System.out.println("点赞+1 !");

					// sendMessageToUser(cookie, new TextMessage("1"));

				}
			}

			// 给视频添加评论
		} else if (type == 6) {

			// 授权注释

//			 String we_name = uWechatUser.getNickname();//微信昵称
//			 String openid = uWechatUser.getOpenid();//微信id

			User u = us.get(cookie);
			// String employee_id = "HB001";
			// 本地注释
			//String openid = "okgGxt0m9lsJVi6d56nQVuJIbELY";

			int video_id = jsStr.getInteger("id");// 视频id
			String id = videoService.selectVideoById(video_id).getEmployee_id();
			String name = videoService.selectVideoById(video_id).getEmployee_area();
			String content = jsStr.getString("com");// 评论内容
			if (null == content) {
				content = "评论为空！";
			}
//			 SensiteWord sensiteWord=new SensiteWord();
//			 Set<String> set=sensiteWord.readSensitivateWord();//读取敏感词库
//			 //HashMap map=sensiteWord.initSensitivateWord(set);
//			 //String content = "sb,操你妈";
//			
//			 String c=sensiteWord.replaceSensitiveWord(content,"*");//将敏感字用*代替

			Video video = videoService.selectUserById(video_id);

			String employee_id = video.getEmployee_id();// 被评论者id
			String employee_name = video.getEmployee_area();// 被评论者name

			Date time = new Date();
			time.getTime();
			List<VideoComment> comments = videoCommentService.selectAllComment(video_id);

			VideoComment comment = new VideoComment();
			comment.setCommentator_id(id);
			comment.setCommentator_name(name);
			comment.setContent(content);
			comment.setEmployee_id(employee_id);
			comment.setEmployee_name(employee_name);
			comment.setVideo_id(video_id);
			comment.setTime(time);
			//comment.setOpenId(uWechatUser.getOpenid());
			comment.setOpenId("okgGxt--qQxgSGrAbS2fj9mmpCZc");

			int msg = videoCommentService.addComment(comment);// 添加评论

			// 评论添加成功
			if (msg == 1) {
				int com_id = videoCommentService.selectIdByUser(content);
				String img = "xxx";
				//String img = wechatService.findUser(openid).getHeadimgurl();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("com_id", com_id);
				jsonObject.put("name", comment.getCommentator_name());
				//jsonObject.put("img", uWechatUser.getImg());
				jsonObject.put("content", content);
				String json = utils.ObjectToJson(jsonObject);
				sendMessageToUser(cookie, new TextMessage(json));
			} else {
				sendMessageToUser(cookie, new TextMessage("0"));
			}

			// 通过视频id查看全部评论
		} else if (type == 7) {
			int video_id = jsStr.getInteger("id");// 视频id
			User user = us.get(cookie);
			// 授权注释
			//String openid = uWechatUser.getOpenid();
			// 本地注释
			String openid = "okgGxt--qQxgSGrAbS2fj9mmpCZc";

			List<VideoComment> comments = videoCommentService.selectAllComment(video_id);// 通过id查看全部评论

			JSONObject jsonObject2 = new JSONObject();

			List myList = new ArrayList<>();
			for (int i = 0; i < comments.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				// String id = comments.get(i).getCommentator_id();//评论者id
				// String name = comments.get(i).getCommentator_name();//评论者name
				// String content = comments.get(i).getContent();//评论内容
				int time = 0;
				Date nowtime = new Date();
				long now = nowtime.getTime();
				Date date = comments.get(i).getTime();
				long commenttime = date.getTime();

				time = (int) (now - commenttime) / (60 * 1000);

				// jsonObject.put("comments", comment);
				
				String openId=comments.get(i).getOpenId();
				WechatUser wx=wechatService.getUserInfo(openId);
				if(null==wx||null==wx.getImg()|| ""==wx.getImg()){
					jsonObject.put("img", "http://app.i-mineral.cn/eim_20180528_ziyuan/moren/wei.jpg");
				}else{
					jsonObject.put("img", wx.getImg());
				}
				
				//jsonObject.put("img", wx.getImg());
				
				WechatUser user2=wechatService.findUser(openId);
				if(null==user2){
					user2=new WechatUser();
					user2.setNickname("游客");
				}
				String na=comments.get(i).getCommentator_name();
				String tor=comments.get(i).getCommentator_id();
				jsonObject.put("name",
						user2.getNickname() + "(" + na + "("
								+ tor + ")" + ")");
				jsonObject.put("content", comments.get(i).getContent());
				jsonObject.put("fab", comments.get(i).getFabulous());
				jsonObject.put("isfab", 0);
				jsonObject.put("com_id", comments.get(i).getComment_id());
				jsonObject.put("time", time);
				myList.add(jsonObject);

			}
			jsonObject2.put("com", myList);
			// jsonObject2.put("com", array);
			String jString = utils.ObjectToJson(jsonObject2);
			System.out.println(jString);
			sendMessageToUser(cookie, new TextMessage(jString));
			// 给评论点赞
		} else if (type == 8) {

			int comment_id = jsStr.getInteger("id");
			int msg = videoCommentService.updateCommentFabulous(comment_id);
			if (msg == 1) {
				System.out.println("点赞成功！");

			} else {
				System.out.println("已经点过赞！");
			}

			// 评委打分
		} else if (type == 9) {
			User user = us.get(cookie);
			String employee_id = user.getEmployee_id();
			// String employee_id = "TJ11000000";
			int number = videoScoreService.selectNumByRater(employee_id);// 已经打过分的人数
			int total = videoScoreService.selectTotalByRater(employee_id);// 需要打分的总人数

			List<VideoScore> scores = videoScoreService.selectByRater(employee_id);

			VideoScore score = new VideoScore();
			List myList = new ArrayList<>();
		
			for (VideoScore sc : scores) {
				myList.add(sc);
			}
			JSONObject jsonObject = new JSONObject();
	
			jsonObject.put("num", number);// 已经打分的人数
			jsonObject.put("total", total);// 总人数
			jsonObject.put("area", user.getEmployee_area());// 所在分区
			jsonObject.put("user", myList);
			String json = utils.ObjectToJson(jsonObject);
			sendMessageToUser(cookie, new TextMessage(json));
			System.out.println(json);

			// 通过用户id查看是否存在存在该用户的视频信息
		} else if (type == 10) {

			User u = us.get(cookie);
			//
			String id = jsStr.getString("id");

			JSONObject jsonObject = new JSONObject();
			// String id = "TJ11400102";
			// 本地注释
			String openid = "okgGxt--qQxgSGrAbS2fj9mmpCZc";
			// 授权注释
			//String openid = uWechatUser.getOpenid();
			String img = wechatService.findUser(openid).getHeadimgurl();//微信头像

			// 本地注释
			//String img = "abc.jpg";

			String name = userService.selectUser(id).getEmployee_name();// 用户名
			String uid = userService.selectUser(id).getEmployee_id();// 用户ID
			String depart = userService.selectUser(id).getEmployee_department();// 用户所在部门
			
			jsonObject.put("name", name);
			jsonObject.put("uid", uid);
			jsonObject.put("depart", depart);
			List<Video> videos2 = videoService.selectVideoUrlByUser(id);
			Video video = new Video();
			JSONObject js = new JSONObject();
			List myList = new ArrayList<>();
			for (int i = 0; i < videos2.size(); i++) {
				// int video_id = videos2.get(i).getVideo_id();
				// String video_url = videos2.get(i).getVideo_url();
				video = videos2.get(i);
				// video.setVideo_url(video_url);
				myList.add(video.getVideo_url());
			}
			jsonObject.put("img", videos2.get(0).getImg());
			jsonObject.put("url", myList);
			int num = videoService.selectNumById(id);
			if (num == 5) {
				jsonObject.put("iden", 0);
				// sendMessageToUser(cookie, new TextMessage("0"));//加油员
			} else if (num == 4) {
				jsonObject.put("iden", 1);
				// sendMessageToUser(cookie, new TextMessage("1"));//收银员
			} else {
				jsonObject.put("iden", 0);
			}
			String jString = utils.ObjectToJson(jsonObject);
			System.out.println(jString);
			sendMessageToUser(cookie, new TextMessage(jString));
			// 给用户添加分数
		} else if (type == 11) {
			String id = jsStr.getString("id");
			int score = jsStr.getInteger("num");
			String name = userService.selectUser(id).getEmployee_name();
			String area = userService.selectUser(id).getEmployee_area();
			VideoScore videoScore = new VideoScore();
			videoScore.setEmployee_id(id);
			videoScore.setEmployee_name(name);
			videoScore.setEmployee_area(area);
			videoScore.setScore(score);
			Date sdf=new Date();
			sdf.getTime();
			videoScore.setTime(sdf);
			int msg = videoScoreService.updateVideoScore(videoScore);
			if (msg == 1) {
				sendMessageToUser(cookie, new TextMessage("1"));
			} else {
				sendMessageToUser(cookie, new TextMessage("0"));
			}
		}
	}

	@Override
	public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
		if (wss.isOpen()) {
			wss.close();
		}
		Map<String, Object> map = wss.getHandshakeAttributes();
		String cookie = (String) map.get("cookie");
		ses.remove(cookie);
		us.remove(cookie);
		wei.remove(cookie);
		System.out.println("websocket connection closed......");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
		System.out.println("websocket connection closed......");
		Map<String, Object> map = wss.getHandshakeAttributes();
		String cookie = (String) map.get("cookie");
		ses.remove(cookie);
		us.remove(cookie);
		wei.remove(cookie);

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 发送消息给用户
	 */
	public void sendMessageToUser(String cookie, TextMessage message) {

		WebSocketSession socket = (WebSocketSession) ses.get(cookie);
		try {
			// isOpen()在线就发送
			if (socket.isOpen()) {
				socket.sendMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
