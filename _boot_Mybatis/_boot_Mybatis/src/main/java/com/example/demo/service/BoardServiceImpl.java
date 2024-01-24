package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardVO;
import com.example.demo.repository.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardMapper bm;

	@Override
	public void register(BoardVO bvo) {
		// TODO Auto-generated method stub
		bm.insert(bvo);
	}

	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return bm.list();
	}

	@Override
	public BoardVO selectOne(long bno) {
		// TODO Auto-generated method stub
		return bm.selectOne(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		bm.edit(bvo);
	}

	@Override
	public int remove(long bno) {
		// TODO Auto-generated method stub
		return bm.remove(bno);
	}
}
