package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardVO;

public interface BoardService {

	void register(BoardVO bvo);

	List<BoardVO> list();

	BoardVO selectOne(long bno);

	void modify(BoardVO bvo);

	int remove(long bno);

}
