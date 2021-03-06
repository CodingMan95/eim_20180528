package net.eimarketing.eim_20180528.service.impl;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.eimarketing.eim_20180528.entity.FileInfo;
import net.eimarketing.eim_20180528.entity.User;
import net.eimarketing.eim_20180528.entity.Video;
import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.entity.WechatUser;
import net.eimarketing.eim_20180528.service.UploadService;
import net.eimarketing.eim_20180528.service.UserService;
import net.eimarketing.eim_20180528.service.VideoScoreService;
import net.eimarketing.eim_20180528.service.VideoService;
import net.eimarketing.eim_20180528.service.WechatService;
import net.eimarketing.eim_20180528.test.Test;
import sun.tools.tree.VarDeclarationStatement;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

	@Autowired
	private VideoService videoService;
	@Autowired
	private WechatService wechatService;
	@Autowired
	private UserService userService;
	@Autowired
	private VideoScoreService videoScoreService;

	@Override
	public boolean uploadFile(HttpServletRequest req, MultipartFile multipartFile[], String video_tag, String id) {

		// long size = multipartFile.getSize();
		// String type = multipartFile.getContentType();
		// 获取文件名
		User user = new User();
		String tag[] = video_tag.split(",");
		for (int a = 0; a < multipartFile.length; a++) {
			String fileName = multipartFile[a].getOriginalFilename();
			// 获取文件后缀，即文件类型
			String houzui = FilenameUtils.getExtension(fileName);
			String newfileName = String.valueOf(new Date().getTime());
			String Name = newfileName + "." + houzui;
			boolean img = true;
			String ip = "http://app.i-mineral.cn/eim_20180528_ziyuan";
			// String ip = "http://app.ei-marketing.net/eim_20180528_ziyuan";
			String imgs = "/images/" + newfileName + ".jpg";
			String videos = "/videos/" + Name;
			// 视频上传路径
			String Url = "/usr/local/tomcat/apache-tomcat-8.0.52/webapps/eim_20180528_ziyuan/videos/" + Name;
			// String Url =
			// "/htdocs/eim/net/ei-marketing/app/eim_20180528_ziyuan/videos/" +
			// Name;
			// 视频截图工具路径
			String utilUrl = req.getSession().getServletContext().getRealPath("/utils/") + "ffmpeg.exe";
			// String utilUrl =
			// "/monchickey/ffmpeg/bin/ffmpeg";//Linux上ffmpeg插件目录
			// 图片上传路径
			String imgUrl = "/usr/local/tomcat/apache-tomcat-8.0.52/webapps/eim_20180528_ziyuan/images/" + newfileName
					+ ".jpg";
			// String imgUrl =
			// "/htdocs/eim/net/ei-marketing/app/eim_20180528_ziyuan/images/" +
			// newfileName + ".jpg";
			for (int b = 0; b < 5; b++) {
				img = Test.fetchFrame(utilUrl, Url, imgUrl);
				if (img) {
					break;
				}
			}

			System.out.println("videourl:" + Url);
			File file = new File(Url);
			if (file.exists()) {
				file.mkdirs();
			}
			try {
				multipartFile[a].transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(Name);

			// User user = (User) req.getSession().getAttribute("user");
			// String employee_id = user.getEmployee_id();
			// String employee_name = user.getEmployee_name();
			// String employee_area = user.getEmployee_area();
			Video video = new Video();
			video.setVideo_url(ip + videos);
			video.setVideo_name(Name);
			if (!img) {
				imgs = "/moren/moren.jpg";
			}
			video.setVideo_img(ip + imgs);
			video.setEmployee_id(id);
			user = userService.selectUser(id);
			String name = null;
			String area = null;
			if (null == user) {
				video.setEmployee_name("无");
				video.setEmployee_area("无");
			} else {
				name = user.getEmployee_name();
				area = user.getEmployee_area();

				video.setEmployee_name(name);
				video.setEmployee_area(area);
			}
			WechatUser sxUser = (WechatUser) req.getSession().getAttribute("user");
			video.setVideo_tag(tag[a]);
			// video.setVideo_tag("讲卫生！");
			Date sdf = new Date();
			sdf.getTime();
			video.setUpload_time(sdf);
			video.setOpenId(sxUser.getOpenid());
			// video.setOpenId("123456");//测试修改

			// video.setImg("454545");//测试修改
			video.setImg(sxUser.getImg());
			int msg = videoService.addVideo(video);
		}
		VideoScore score = new VideoScore();

		score.setEmployee_id(user.getEmployee_id());
		score.setEmployee_area(user.getEmployee_area());
		score.setEmployee_name(user.getEmployee_name());
		videoScoreService.addVideoScore(score);// 直接存储

		VideoScore newScore = userService.getScoreByUserId(id);// 通过用户ID查分数表
																// 获取新的scor对象

		Integer score_id = newScore.getScore_id();

		// score_id 区域
		// 根据身份和区域获取 该区评委人数
		List rater = userService.getRaterByRola(user.getEmployee_area());
		int raterSize = rater.size();
		// 对评委数进行取余
		Integer raternum = score_id % raterSize;

		// 根据余数 分配评委
		switch (raternum) {
		case 0:
			// 取出评委id
			User user0 = (User) rater.get(raternum);
			String employee_id0 = user0.getEmployee_id();
			// 将用户id 和 评委id 存入 分数表
			newScore.setRater_id(employee_id0);// 保存评委id到成绩表中
			videoScoreService.updateRaterId(newScore);
			break;
		case 1:
			// 取出评委id
			User user1 = (User) rater.get(raternum);
			String employee_id1 = user1.getEmployee_id();
			// 将用户id 和 评委id 存入 分数表
			newScore.setRater_id(employee_id1);// 保存评委id到成绩表中
			videoScoreService.updateRaterId(newScore);
			break;
		case 2:
			// 取出评委id
			User user2 = (User) rater.get(raternum);
			String employee_id2 = user2.getEmployee_id();
			// 将用户id 和 评委id 存入 分数表
			newScore.setRater_id(employee_id2);// 保存评委id到成绩表中
			videoScoreService.updateRaterId(newScore);
			break;
		case 3:
			// 取出评委id
			User user3 = (User) rater.get(raternum);
			String employee_id3 = user3.getEmployee_id();
			// 将用户id 和 评委id 存入 分数表
			newScore.setRater_id(employee_id3);// 保存评委id到成绩表中
			videoScoreService.updateRaterId(newScore);

			break;
		case 4:
			// 取出评委id
			User user4 = (User) rater.get(raternum);
			String employee_id4 = user4.getEmployee_id();
			// 将用户id 和 评委id 存入 分数表
			newScore.setRater_id(employee_id4);// 保存评委id到成绩表中
			videoScoreService.updateRaterId(newScore);

			break;
		case 5:
			// 取出评委id
			User user5 = (User) rater.get(raternum);
			String employee_id5 = user5.getEmployee_id();
			// 将用户id 和 评委id 存入 分数表
			newScore.setRater_id(employee_id5);// 保存评委id到成绩表中
			videoScoreService.updateRaterId(newScore);

			break;
		case 6:
			// 取出评委id
			User user6 = (User) rater.get(raternum);
			String employee_id6 = user6.getEmployee_id();
			// 将用户id 和 评委id 存入 分数表
			newScore.setRater_id(employee_id6);// 保存评委id到成绩表中
			videoScoreService.updateRaterId(newScore);

			break;

		default:
			break;
		}

		// req.getSession().setAttribute("vScore", videoScore);
		return true;
	}

	public void name() {
		List<String> pathlist = new ArrayList<String>();
		pathlist.add("天津大区");
		pathlist.add("鲁东地区");
		pathlist.add("鲁中地区");
		pathlist.add("冀东地区");
		pathlist.add("冀西地区");

		for (String path : pathlist) {

			List<VideoScore> vList = videoScoreService.getSocrelist(path);

			for (VideoScore videoScore : vList) {
				Integer score_id = videoScore.getScore_id();

				// score_id 区域
				// 根据身份和区域获取 该区评委人数
				List rater = userService.getRaterByRola(path);
				int raterSize = rater.size();
				// 对评委数进行取余
				Integer raternum = score_id % raterSize;

				// 根据余数 分配评委

				switch (raternum) {
				case 0:
					// 取出评委id
					User user0 = (User) rater.get(raternum);
					String employee_id0 = user0.getEmployee_id();
					// 将用户id 和 评委id 存入 分数表
					videoScore.setRater_id(employee_id0);// 保存评委id到成绩表中
					videoScoreService.updateRaterId(videoScore);
					break;
				case 1:
					// 取出评委id
					User user1 = (User) rater.get(raternum);
					String employee_id1 = user1.getEmployee_id();
					// 将用户id 和 评委id 存入 分数表
					videoScore.setRater_id(employee_id1);// 保存评委id到成绩表中
					videoScoreService.updateRaterId(videoScore);
					break;
				case 2:
					// 取出评委id
					User user2 = (User) rater.get(raternum);
					String employee_id2 = user2.getEmployee_id();
					// 将用户id 和 评委id 存入 分数表
					videoScore.setRater_id(employee_id2);// 保存评委id到成绩表中
					videoScoreService.updateRaterId(videoScore);
					break;
				case 3:
					// 取出评委id
					User user3 = (User) rater.get(raternum);
					String employee_id3 = user3.getEmployee_id();
					// 将用户id 和 评委id 存入 分数表
					videoScore.setRater_id(employee_id3);// 保存评委id到成绩表中
					videoScoreService.updateRaterId(videoScore);

					break;
				case 4:
					// 取出评委id
					User user4 = (User) rater.get(raternum);
					String employee_id4 = user4.getEmployee_id();
					// 将用户id 和 评委id 存入 分数表
					videoScore.setRater_id(employee_id4);// 保存评委id到成绩表中
					videoScoreService.updateRaterId(videoScore);

					break;
				case 5:
					// 取出评委id
					User user5 = (User) rater.get(raternum);
					String employee_id5 = user5.getEmployee_id();
					// 将用户id 和 评委id 存入 分数表
					videoScore.setRater_id(employee_id5);// 保存评委id到成绩表中
					videoScoreService.updateRaterId(videoScore);

					break;
				case 6:
					// 取出评委id
					User user6 = (User) rater.get(raternum);
					String employee_id6 = user6.getEmployee_id();
					// 将用户id 和 评委id 存入 分数表
					videoScore.setRater_id(employee_id6);// 保存评委id到成绩表中
					videoScoreService.updateRaterId(videoScore);

					break;

				default:
					break;
				}

			}

		}

	}

}
