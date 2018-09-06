package net.eimarketing.eim_20180528.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.eimarketing.eim_20180528.entity.Number;
import net.eimarketing.eim_20180528.entity.VideoScore;

public interface VideoScoreDao {

	public int updateVideoScore(VideoScore videoScore);// 为视频打分
	public int addVideoScore(VideoScore videoScore);// 为视频打分

	public VideoScore selectScoreByUser(String employee_id);// 查询某一员工的分数
	
	//通过地区查询排行榜
	public List<VideoScore> selectTopScore(String employee_area);
		
	public List<VideoScore> selectByRater(String employee_id);//
	
	public int selectNumByRater(String employee_id);//根据评委id查已经评过分的人数
	public int selectTotalByRater(String employee_id);//根据评委id查需要评分的总人数
	public void updateRaterId(VideoScore videoScore);
	
	
	public List<VideoScore> getSocrelist(@Param("employee_area")String employee_area);

}
