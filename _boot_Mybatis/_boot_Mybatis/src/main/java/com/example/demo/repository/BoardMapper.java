package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardVO;

@Mapper
public interface BoardMapper {

	void insert(BoardVO bvo);

	List<BoardVO> list();

	BoardVO selectOne(long bno);

	void edit(BoardVO bvo);

	int remove(long bno);

}