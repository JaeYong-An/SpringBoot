package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.repository.BoardMapper;
import com.example.demo.repository.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

	private final CommentMapper cm;

	private final BoardMapper bm;

	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cm.insert(cvo);
	}

	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// TODO Auto-generated method stub
		//controller에서 해도 되지만 처리 속도가 더 빨라짐
		int totalCount = cm.selectOneBnoTotalCount(bno);
		List<CommentVO> list = cm.getList(bno,pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}

//	@Override
//	public List<CommentVO> getList(long bno) {
//		// TODO Auto-generated method stub
//		return cm.getList(bno);
//	}

	@Override
	public int edit(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cm.edit(cvo);
	}

	@Override
	public int remove(long cno) {
		// TODO Auto-generated method stub
		return cm.delete(cno);
	}
}