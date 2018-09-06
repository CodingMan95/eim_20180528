package net.eimarketing.eim_20180528.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.eimarketing.eim_20180528.entity.Flags;
import net.eimarketing.eim_20180528.dao.FlagDao;
import net.eimarketing.eim_20180528.service.FlagService;

@Service
@Transactional
public class FlagServiceImpl implements FlagService{

	@Autowired
	private FlagDao flagDao;



	@Override
	public int addFlag(Flags flag) {
		// TODO Auto-generated method stub
		return flagDao.addFlag(flag);
	}



	@Override
	public List<Flags> selectVideoByFlag(String employee_id) {
		// TODO Auto-generated method stub
		return flagDao.selectVideoByFlag(employee_id);
	}



	@Override
	public int selectFlagById(int video_id) {
		// TODO Auto-generated method stub
		return flagDao.selectFlagById(video_id);
	}



	@Override
	public int selectFlag(int video_id, String employee_id) {
		// TODO Auto-generated method stub
		return flagDao.selectFlag(video_id, employee_id);
	}

}
