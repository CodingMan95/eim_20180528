package net.eimarketing.eim_20180528.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.eimarketing.eim_20180528.service.FlagService;

@Controller
public class FlagController {
	@Autowired
	private FlagService flagService;
	
	@ResponseBody
	@RequestMapping("selectFlagById.do")
	public void selectVideoByFlag(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		// response.setContentType("text/html;charset=utf-8");

		int video_id = 1265;
		String employee_id = "SD22416401";
		
		int flag = flagService.selectFlag(video_id, employee_id);
		
		System.out.println("flag:"+flag);
		if (flag == 0) {
			System.out.println(0);
		}else{
			System.out.println(1);
		}

	}

}
