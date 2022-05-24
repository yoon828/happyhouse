package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDto;



@Mapper
public interface NoticeMapper {
	public List<NoticeDto> selectNotice();
	public List<NoticeDto> searchById(String searchId); 
	public List<NoticeDto> searchByTitle(String searchTitle);
	public NoticeDto selectNoticeByNo(int articleno);
	public int insertNotice(NoticeDto notice);
	public int updateNotice(NoticeDto notice);
	public int deleteNotice(int articleno);
	public int hitAdd(int articleno);
	public List<NoticeDto> selectNoticeComment(int articleno);
	public int insertComment(NoticeDto notice);
	public int deleteComment(int commentno);
}
