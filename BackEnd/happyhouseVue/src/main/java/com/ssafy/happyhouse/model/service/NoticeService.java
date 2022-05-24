package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.NoticeDto;





public interface NoticeService {
	public List<NoticeDto> retrieveNotice();
	public NoticeDto detailNotice(int articleno);
	public boolean writeNotice(NoticeDto notice);
	public boolean updateNotice(NoticeDto notice);
	public boolean deleteNotice(int articleno);
	List<NoticeDto> searchById(String searchid);
	List<NoticeDto> searchByTitle(String searchtitle);
	public int hitAdd(int articleno);
	public List<NoticeDto> selectNoticeComment(int articleno);
	public int insertComment(NoticeDto notice);
	public int deleteComment(int commentno);
}
