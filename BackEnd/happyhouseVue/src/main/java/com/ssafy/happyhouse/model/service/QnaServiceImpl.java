package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;



@Service
public class QnaServiceImpl implements QnaService{
	 	@Autowired
		private QnaMapper qnaMapper;

	    @Override
		public List<QnaDto> retrieveQna() {
			return qnaMapper.selectQna();
		}
	    
	  	@Override
		public boolean writeQna(QnaDto qna) {
			return qnaMapper.insertQna(qna) == 1;
		}

		@Override
		public QnaDto detailQna(int articleno) {
			return qnaMapper.selectQnaByNo(articleno);
		}

		@Override
		@Transactional
		public boolean updateQna(QnaDto qna) {
			return qnaMapper.updateQna(qna) == 1;
		}

		@Override
		@Transactional
		public boolean deleteQna(int articleno) {
			return qnaMapper.deleteQna(articleno) == 1;
		}

		@Override
		public List<QnaDto> searchById(String searchId) {
			return qnaMapper.searchById(searchId);
		}

		@Override
		public List<QnaDto> searchByTitle(String serarchTitle) {
			return qnaMapper.searchByTitle(serarchTitle);
		}
		
		@Override
		@Transactional
		public int hitAdd(int articleno) {
			return qnaMapper.hitAdd(articleno);
		}
		
		
}
