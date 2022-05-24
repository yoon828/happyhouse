package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.QnaDto;



public interface QnaService {
	public List<QnaDto> retrieveQna();
	public QnaDto detailQna(int articleno);
	public boolean writeQna(QnaDto qna);
	public boolean updateQna(QnaDto qna);
	public boolean deleteQna(int articleno);
	List<QnaDto> searchById(String searchid);
	List<QnaDto> searchByTitle(String searchtitle);
	public int hitAdd(int articleno);
	public List<QnaDto> selectQnaComment(int articleno);
	public int insertComment(QnaDto qna);
	public int deleteComment(int articleno, int commentno);
}
