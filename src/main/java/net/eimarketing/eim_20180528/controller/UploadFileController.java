package net.eimarketing.eim_20180528.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.eimarketing.eim_20180528.entity.FileInfo;
import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.service.UploadService;
import net.eimarketing.eim_20180528.service.VideoScoreService;
import net.eimarketing.eim_20180528.service.VideoService;

/**
 * 文件上传相关接口
 * 
 */
@Controller
public class UploadFileController {

	@Autowired
	private UploadService uploadService;
	@Autowired
	private VideoScoreService videoScoreService;
	@Autowired
	private VideoService videoService;

	/**
	 * 视频上传
	 * 
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fileUpload.do")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("file") MultipartFile[] multipartFile/*,@RequestParam("tag") String video_tag*/) throws Exception {
		// 设置响应数据的类型json
		Map<String, Object> map=new HashedMap();
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setContentType("application/json; charset=utf-8");
		String tag = req.getParameter("tag");
		String id = req.getParameter("userId");
//		int userId = Integer.parseInt(id);
		
		System.out.println("tags:" + req.getParameter("tag"));
		// 判断file数组不能为空并且长度大于0
		/*if (multipartFile != null && multipartFile.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < multipartFile.length; i++) {
				MultipartFile file = multipartFile[i];
				String t = tags[i];
				//String t="...";
				//String tag = video_tag[i];
				// 保存文件
				
				fileInfo = uploadService.uploadFile(req, file, t,id);
			}
			
		}*/
		boolean is = uploadService.uploadFile(req, multipartFile, tag,id);
		
		// FileInfo fileInfo = uploadService.uploadFile(req,multipartFile);
		int st=404;
		if(is){
			map.put("status", 200);
		}else{
			map.put("status", 404);
		}
		return map;
	}

}
