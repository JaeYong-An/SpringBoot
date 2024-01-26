package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

//	private final logger log = LogFactory.getLogger(BoardController.class);
	
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		bsv.register(bvo);
		return "index";
	}
	
	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		List<BoardVO> list = bsv.list(pgvo);
		//totalCount
		int totalCount = bsv.getTotalCount(pgvo);
		//PagingHandler
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list", list);
		//PagingHandler 객체 보내기
		m.addAttribute("ph", ph);
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(@RequestParam("bno")long bno,Model m) {
		BoardVO bvo = bsv.selectOne(bno);
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		bsv.modify(bvo);
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") long bno) {
		int isOK = bsv.remove(bno);
		return "redirect:/board/list";
	}
}
