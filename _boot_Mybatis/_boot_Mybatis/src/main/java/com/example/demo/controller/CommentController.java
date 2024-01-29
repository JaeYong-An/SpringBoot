package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment/*")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

	private final CommentService csv;
	
	@PostMapping("/post")
	@ResponseBody
	public String post(@RequestBody CommentVO cvo) {
		log.info(">>>> CommentVO >>>{} "+cvo);
		int isOK = csv.post(cvo);
		return isOK>0 ? "1":"0";
	}
	
	@GetMapping("/{bno}/{page}")
	@ResponseBody
	public PagingHandler list(@PathVariable("bno")long bno, @PathVariable("page")int page) {
		log.info(">>>> bno >> " +bno+"/ page >>" + page);
		//List<CommentVO> / PagingHandler
		//비동기 => 한 객제만 전송 가능
		PagingVO pgvo = new PagingVO(page, 5);
		PagingHandler ph = csv.getList(bno, pgvo);
		return ph;
	}
	
	@PutMapping("/edit")
	@ResponseBody
	public String edit(@RequestBody CommentVO cvo) {
		log.info(">>>> cvo >>>{}" + cvo);
		int isOK = csv.edit(cvo);
		return isOK>0? "1":"0";
	}
	
	@DeleteMapping("/{cno}")
	@ResponseBody
	public String remove(@PathVariable("cno")long cno) {
		log.info(">>> cno >>"+ cno);
		int isOK = csv.remove(cno);
		return isOK>0? "1":"0";
	}
	
//	@PostMapping(value="/post", consumes="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
//		
//		log.info(">>>> cvo >> {}" + cvo);
//		int isOK = csv.post(cvo);
//		return isOK > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
//	@GetMapping(value="/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PagingHandler> list(@PathVariable("bno")long bno, @PathVariable("page")int page){
//
//		log.info(">>>> bno >> " +bno+">>>> page >> "+page);
//		PagingVO pgvo = new PagingVO(page, 5);
//		PagingHandler ph = csv.getList(bno, pgvo);
//		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
//	}
}
