package net.eimarketing.eim_20180528.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import net.eimarketing.eim_20180528.entity.Video;
import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.entity.VideoTopX;

public class ToJsonUtils {
	
	public String  VideoListToJson(List<Video> list) {
		String json = JSONObject.toJSONString(list);
		return json;
	}
	public String  ObjectToJson(Object object) {
		String json = JSONObject.toJSONString(object);
		return json;
	}
	public String  VideoScoreListToJson(List<VideoScore> list) {
		String json = JSONObject.toJSONString(list);
		return json;
	}

	public JSONObject IntPutToJson(String json, int i){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(json, i);
		return jsonObject;
	}
}
