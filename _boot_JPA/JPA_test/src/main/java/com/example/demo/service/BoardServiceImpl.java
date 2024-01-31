package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository repository;

	@Override
	public Long register(BoardDTO bdto) {
		// TODO Auto-generated method stub
		//저장 객체는 Board
		//save : insert 후 저장 객체룰 반환
		return repository.save(convertDtoToEntity(bdto)).getBno();
	}

	@Override
	public List<BoardDTO> getList() {
		//DB에서 리스트로 리턴이 되기 때문에 Board List로 리턴 => DTO 객체로 리턴
		List<Board> list = repository.findAll(Sort.by(Sort.Direction.DESC, "bno"));
		List<BoardDTO> dtoList = new ArrayList<>();
		for(Board board : list) {
			dtoList.add(convertEntityToDto(board));
		}
		return dtoList;
	}
}
