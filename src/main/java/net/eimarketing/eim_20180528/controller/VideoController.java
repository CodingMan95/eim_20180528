package net.eimarketing.eim_20180528.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.Flags;
import net.eimarketing.eim_20180528.entity.TopUser;
import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.Video;
import net.eimarketing.eim_20180528.service.FlagService;
import net.eimarketing.eim_20180528.service.UserService;
import net.eimarketing.eim_20180528.service.VideoService;
import net.eimarketing.eim_20180528.util.Succeed;
import net.eimarketing.eim_20180528.util.ToJsonUtils;
import net.eimarketing.eim_20180528.util.Util;
import net.sf.json.JSONArray;

@Controller
public class VideoController {

	@Autowired
	private VideoService videoService;
	@Autowired
	private FlagService flagService;
	@Autowired
	private UserService userService;

	ToJsonUtils utils = new ToJsonUtils();

	// 视频删除:deleteVideo
	/*
	 * @RequestMapping("deleteVideo.do")
	 * 
	 * @ResponseBody public void deleteVideo(HttpServletResponse response,
	 * HttpServletRequest request) throws IOException {
	 * 
	 * response.setContentType("text/html;charset=utf-8"); // Integer video_id =
	 * Integer.parseInt(request.getParameter("video_id")); int video_id = 1;
	 * 
	 * System.out.println(video_id); int msg =
	 * videoService.deleteVideo(video_id); Util.println(request, response, new
	 * Succeed("", msg)); }
	 */


	// 查看全部视频:selectAllVideo
	@RequestMapping("selectAllVideo.do")
	@ResponseBody
	public void selectAllVideo(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		int id = 10;
		
		List<Video> videos = videoService.selectAllVideo();
		Video video = new Video();
		JSONObject jsonObject = new JSONObject();
		if (id <= videos.size()) {
			//video = videoService.selectTopXVideo(id-1);
			int video_id = videos.get(id-1).getVideo_id();// 根据传过来的参数确定查询哪个视频缩略图
			String video_img = videos.get(id-1).getVideo_img();
			int fabulous = videos.get(id-1).getFabulous();
			video.setVideo_id(video_id);
			video.setVideo_img(video_img);
			video.setFabulous(fabulous);
		
			jsonObject.put("video", video);
			String json = utils.ObjectToJson(video);
			System.out.println(json);
			Util.println(request, response, new Succeed("", video));

		} 
	}
	// 查看某一用户视频：selectVideoByUser
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("selectVideoByUser.do")
	@ResponseBody
	public void selectVideoByUser(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		// String employee_id = request.getParameter("employee_id");
		// User user = (User) request.getSession().getAttribute("user");
		String employee_id = "HB11420102";

		List<Video> list = videoService.selectVideoByUser(employee_id);

		List<List<Video>> biglist = new ArrayList<>();

		for (Video video : list) {
			List myList = new ArrayList<>();
			myList.add(video);
			biglist.add(myList);
		}

		// String json = utils.VideoListToJson(list);

		JSONArray array = JSONArray.fromObject(biglist); // 首先把字符串转成 JSONArray
															// 对象
		System.out.println(array.toString());

		// object1.put("video", object);

		Util.println(request, response, new Succeed("", array));

	}

	// 查看某一用户视频：selectTopXVideo
	@RequestMapping("selectTopXVideo.do")
	@ResponseBody
	public void selectTopXVideo(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		// String employee_id = request.getParameter("employee_id");
		// User user = (User) request.getSession().getAttribute("user");
		int num = 1;
		ToJsonUtils utils = new ToJsonUtils();
		Video topX = videoService.selectTopXVideo(num);// 根据传过来的参数确定查询哪个视频

		// JSONObject jsonObject = new JSONObject();
		// jsonObject.put("top", num);
		// jsonObject.put("video", topX);

		String json = utils.ObjectToJson(topX);
		// String jString = utils.ObjectToJson(object);
		System.out.println("json:" + json);

		// System.out.println(JSONObject.toJSONString(json));
		// Util.println(request, response, new Succeed("", json));

	}

	// 通过地区查看视频：selectVideoByArea
	@RequestMapping("selectVideoByArea.do")
	@ResponseBody
	public void selectVideoByArea(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		// String employee_area = request.getParameter("employee_area");
		String employee_area = "河北大区";

		List<Video> list = videoService.selectVideoByArea(employee_area);
		System.out.println(utils.VideoListToJson(list));
		Util.println(request, response, new Succeed("", list));

	}

	// 查看某一视频：selectVideoById
	@RequestMapping("selectVideoById.do")
	@ResponseBody
	public void selectVideoById(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		// Integer video_id =
		// Integer.parseInt(request.getParameter("video_id"));
		Integer video_id = 1240;

		Video video = videoService.selectVideoById(video_id);
		System.out.println(utils.ObjectToJson(video));
		// System.out.println(JSONObject.toJSONString(video));
		// Util.println(request, response, new Succeed("", video));

	}

	@RequestMapping("selectVideoByFlag.do")
	@ResponseBody
	public String selectVideoByFlag(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		
		String employee_id = "HB11420102";
		
		User user = userService.selectUser(employee_id);
		if (user == null) {
			return "user is null";
		}else{
			String userid = user.getEmployee_id();
			String name = user.getEmployee_name();
			
			Video video = new Video();
			List<Video> listvideo = new ArrayList<>();
			
			List<Flags> video_id = flagService.selectVideoByFlag(employee_id);
			for(int i = 0; i < video_id.size(); i++){
				Flags flag = video_id.get(i);
				int id = flag.getVideo_id();
				video = videoService.selectVideoById(id);
				listvideo.add(video);
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("employee_id", userid);
			jsonObject.put("employee_name", name);
			List<Video> list = videoService.selectVideoByUser(employee_id);
			if (null == list || null == listvideo) {
				video.setVideo_id(0);
				list.add(video);
				listvideo.add(video);
			}else{
				jsonObject.put("myvideo", list);
				jsonObject.put("othervideo", listvideo);
			}
			
			String json = utils.ObjectToJson(jsonObject);
			System.out.println(json);
			return "OK!" ;
		}
	}

	// 给视频点赞：updateFabulous
	@RequestMapping("updateFabulous.do")
	@ResponseBody
	public void updateFabulous(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		// Integer video_id =
		// Integer.parseInt(request.getParameter("video_id"));
		Flags flag = new Flags();
		int i = 0;
	
		Integer video_id = 1287;
		int msg = 0;
		
		if (i <= 0) {
			msg = videoService.updateFabulous(video_id);
			
			flag.setEmployee_id("HB001");
			// flag.setIsflag(1);
			flag.setVideo_id(video_id);

			flagService.addFlag(flag);
			i++;
		}

		System.out.println("点赞+1 !");
		Util.println(request, response, new Succeed("", msg));

	}
	
	@RequestMapping("selectUser.do")
	@ResponseBody
	public void selectUser(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		// String employee_id = request.getParameter("employee_id");
		// User user = (User) request.getSession().getAttribute("user");
//		String employee_id = null;
//		Map<String, Object> testMap = new HashMap<String, Object>();
		
		int id = 0;
		//List<Video> videos = videoService.selectAllVideo();// 通过点赞查询全部视频排行
		
		List<Video> u = videoService.selectTopUser();
//		
//		testMap.put("id", id);
//		testMap.put("user", u);
		TopUser top = new TopUser();
		top.setBegin(id);
		top.setVi(u);
		List<Video> videos = videoService.selectLimit(top);
		
		
		System.out.println(JSONObject.toJSONString(videos));

	}

}
