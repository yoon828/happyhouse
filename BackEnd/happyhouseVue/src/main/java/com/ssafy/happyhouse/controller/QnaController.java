package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.service.QnaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaService qnaService;

    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnaDto>> retrieveQna() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<QnaDto>>(qnaService.retrieveQna(), HttpStatus.OK);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = QnaDto.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<QnaDto> detailQna(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<QnaDto>(qnaService.detailQna(articleno), HttpStatus.OK);
	}
    
    

    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQna(@RequestBody QnaDto qna) {
		logger.debug("writeBoard - 호출");
		if (qnaService.writeQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateQna(@RequestBody QnaDto qna) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + qna);
		
		if (qnaService.updateQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "글번호에 해당하는 게시글의 조회수를 1 더한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/hitadd/{articleno}")
	public ResponseEntity<String> hitAdd(@PathVariable int articleno) {
		logger.debug("hitAdd - 호출");	
		if (qnaService.hitAdd(articleno) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteQna(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (qnaService.deleteQna(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "검색한 작성자에 글들을 반환한다. ")
    @GetMapping("/searchid/{searchId}")
    public ResponseEntity<List<QnaDto>> searchById(@PathVariable String searchId){
    	logger.debug(searchId);
    	return new ResponseEntity<List<QnaDto>>(qnaService.searchById(searchId), HttpStatus.OK); 
    }
    
    @ApiOperation(value = "검색한 제목이 들어간 글들을 반환한다. ")
    @GetMapping("/searchtitle/{searchTitle}")
    public ResponseEntity<List<QnaDto>> searchByTitle(@PathVariable String searchTitle){
    	logger.debug(searchTitle);
    	return new ResponseEntity<List<QnaDto>>(qnaService.searchByTitle(searchTitle), HttpStatus.OK); 
    }
    
    @ApiOperation(value = "글 번호로 검색한 게시글의 정보를 반환한다.", response = QnaDto.class)    
   	@GetMapping("/searchno/{articleno}")
   	public ResponseEntity<QnaDto> searchByNo(@PathVariable int articleno) {
   		logger.debug("detailBoard - 호출");
   		return new ResponseEntity<QnaDto>(qnaService.detailQna(articleno), HttpStatus.OK);
   	}
  
}