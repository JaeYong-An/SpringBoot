package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

@Mapper
public interface BoardMapper {

	int insert(BoardVO bvo);

	List<BoardVO> list(PagingVO pgvo);

	BoardVO selectOne(long bno);

	int edit(BoardVO bvo);

	int remove(long bno);

	int getTotalCount(PagingVO pgvo);

	long getBno();

}