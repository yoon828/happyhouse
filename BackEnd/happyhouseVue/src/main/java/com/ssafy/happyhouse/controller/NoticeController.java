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

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService;
	//댓글 조회
	@ApiOperation(value = "aritcleno를 받아 그 글의 댓글들을 반환한다.", response = List.class)
	@GetMapping("/comment/{articleno}")
	public ResponseEntity<?> commentList(@PathVariable int articleno) throws Exception{
		logger.debug("commentList 호출");
		return new ResponseEntity<List<NoticeDto>>(noticeService.selectNoticeComment(articleno), HttpStatus.OK);
	}
	//댓글 입력
	@ApiOperation(value = "댓글을 받아서 입력한다. 성공하면 'success' 실패하면 'fail'을 반환한다.")
	@PostMapping("/comment")
	public ResponseEntity<String> insertComment(@RequestBody NoticeDto notice) throws Exception {
		logger.debug("insertComment 호출");
		if(noticeService.insertComment(notice) == 1){
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
 	//댓글 삭제 
	@ApiOperation(value = "댓글을 받아서 입력한다. 성공하면 'success' 실패하면 'fail'을 반환한다.")
	@DeleteMapping("/comment/{commentno}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentno) throws Exception {
		logger.debug("deleteComment 호출");
		if(noticeService.deleteComment(commentno) == 1){
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeDto>> retrieveQna() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<NoticeDto>>(noticeService.retrieveNotice(), HttpStatus.OK);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = NoticeDto.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<NoticeDto> detailQna(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<NoticeDto>(noticeService.detailNotice(articleno), HttpStatus.OK);
	}
    
    

    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQna(@RequestBody NoticeDto notice) {
		logger.debug("writeBoard - 호출");
		if (noticeService.writeNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateQna(@RequestBody NoticeDto notice) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + notice);
		
		if (noticeService.updateNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "글번호에 해당하는 게시글의 조회수를 1 더한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/hitadd/{articleno}")
	public ResponseEntity<String> hitAdd(@PathVariable int articleno) {
		logger.debug("hitAdd - 호출");	
		if (noticeService.hitAdd(articleno) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteQna(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (noticeService.deleteNotice(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "검색한 작성자에 글들을 반환한다. ")
    @GetMapping("/searchid/{searchId}")
    public ResponseEntity<List<NoticeDto>> searchById(@PathVariable String searchId){
    	logger.debug(searchId);
    	return new ResponseEntity<List<NoticeDto>>(noticeService.searchById(searchId), HttpStatus.OK); 
    }
    
    @ApiOperation(value = "검색한 제목이 들어간 글들을 반환한다. ")
    @GetMapping("/searchtitle/{searchTitle}")
    public ResponseEntity<List<NoticeDto>> searchByTitle(@PathVariable String searchTitle){
    	logger.debug(searchTitle);
    	return new ResponseEntity<List<NoticeDto>>(noticeService.searchByTitle(searchTitle), HttpStatus.OK); 
    }
    
    @ApiOperation(value = "글 번호로 검색한 게시글의 정보를 반환한다.", response = NoticeDto.class)    
   	@GetMapping("/searchno/{articleno}")
   	public ResponseEntity<NoticeDto> searchByNo(@PathVariable int articleno) {
   		logger.debug("detailBoard - 호출");
   		return new ResponseEntity<NoticeDto>(noticeService.detailNotice(articleno), HttpStatus.OK);
   	}
  
}