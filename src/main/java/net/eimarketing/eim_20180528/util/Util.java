package net.eimarketing.eim_20180528.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class Util {

	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

	public static void println(HttpServletRequest request, HttpServletResponse response, Result result) {
		try {
			response.getWriter().println(JSONObject.toJSONString(result));
		} catch (IOException e) {
			LOGGER.debug("get Exception info" + e);
		}
	}
}
