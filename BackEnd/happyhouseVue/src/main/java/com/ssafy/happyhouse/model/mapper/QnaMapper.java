package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.QnaDto;



@Mapper
public interface QnaMapper {
	public List<QnaDto> selectQna();
	public List<QnaDto> searchById(String searchId); 
	public List<QnaDto> searchByTitle(String searchTitle);
	public QnaDto selectQnaByNo(int articleno);
	public int insertQna(QnaDto qna);
	public int updateQna(QnaDto qna);
	public int deleteQna(int articleno);
	public int hitAdd(int articleno);
}
