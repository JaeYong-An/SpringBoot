package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

public interface BoardService {

	void register(BoardDTO boardDTO);

	List<BoardVO> list(PagingVO pgvo);

	BoardDTO selectOne(long bno);

	void modify(BoardVO bvo);

	int remove(long bno);

	int getTotalCount(PagingVO pgvo);

}
