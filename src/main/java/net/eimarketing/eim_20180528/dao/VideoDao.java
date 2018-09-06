package net.eimarketing.eim_20180528.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.eimarketing.eim_20180528.entity.TopUser;
import net.eimarketing.eim_20180528.entity.Video;
import net.eimarketing.eim_20180528.entity.VideoTopX;

public interface VideoDao {

	public int addVideo(Video video);// 视频上传

	public int deleteVideo(Integer video_id);// 视频删除

	public List<Video> selectAllVideo();// 查看全部视频
	//public List<Video> selectTop15Video();//查询前15个视频
	public int selectNumById(String employee_id);//通过用户id查找视频数量
	public Video selectTopXVideo(int num);
	public Video selectTopXUrl(int num);

	public List<Video> selectVideoByUser(String employee_id);// 通过用户查看视频
	public List<Video> selectVideoUrlByUser(String employee_id);
	public List<Video> selectVideoByArea(String employee_area);// 通过地区查找视频

	public Video selectVideoById(Integer video_id);// 通过id查看视频
	public Video selectVideoImgById(Integer video_id);// 通过id查看视频缩略图
	public Video selectUserById(Integer video_id);// 通过id查看用户
	
	public List<Video> selectVideosById(Integer video_id);// 通过id查看视频
	
	public int selectFabulous(Integer video_id);// 查看视频点赞数
	
	public int updateFabulous(Integer video_id);// 给视频点赞
	
	public int updateCommentNum(Integer video_id);// 更新评论数
	
	Video getVideoById(int id);
	/**
	 * 分页查询数据
	 * @param begin
	 * @return
	 */
	//List<Video> selectLimit(@Param("employee_id")String employee_id,@Param("begin") Integer begin);
//	List<Video> selectLimit(@Param("testMap") Map<String, Object> testMap);
	List<Video> selectLimit(TopUser top);
	
	public List<Video> selectTopUser();//从复赛人员表中查用户id
	
}
