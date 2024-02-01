package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

//	@Override
//	public List<BoardDTO> getList() {
//		//DB에서 리스트로 리턴이 되기 때문에 Board List로 리턴 => DTO 객체로 리턴
//		List<Board> list = repository.findAll(Sort.by(Sort.Direction.DESC, "bno"));
//		List<BoardDTO> dtoList = new ArrayList<>();
//		for(Board board : list) {
//			dtoList.add(convertEntityToDto(board));
//		}
//		return dtoList;
//	}
	
	@Override
	public Page<BoardDTO> getList(int page) {
		//DB에서 리스트로 리턴이 되기 때문에 Board List로 리턴 => DTO 객체로 리턴
		//1 => limit 0,10 / 
		//pageNo = 0부터 시작
		Pageable pageable = PageRequest.of(page, 10, Sort.by("bno").descending());
		
		Page<Board> list = repository.findAll(pageable);
		Page<BoardDTO> dtoList = list.map(b->convertEntityToDto(b));
		return dtoList;
	}
	
	@Override
	public BoardDTO getDetail(Long bno) {
		//findById : 아이디(pk)를 주고 해당하는 객체를 리턴
		//findById의 리턴 타입 Optional<Board>타입으로 리턴
		//Optional<T> : nullPointException가 발생하지 않도록 도와줌
		//Optional.isEmpty() : null인 경우 확인 가능(true/false)
		//Optional.isPresent : 값이 있는지 확인(true/false)
		//option.get() : 값을 가져오기
		Optional<Board> option = repository.findById(bno);
		return option.isPresent()? convertEntityToDto(option.get()) : null;		
	}

	@Override
	public Long modify(BoardDTO bdto) {
		//update : jpa는 업데이트가 없음
		//기존의 객체를 가져와서 변경한 후 다시 저장
		Optional<Board> option = repository.findById(bdto.getBno());
		
		if(option.isPresent()) {
			Board entity = option.get();
			//변경내용 set
			entity.setTitle(bdto.getTitle());
			entity.setContent(bdto.getContent());
			//다시 저장
			return repository.save(entity).getBno();
		}
		return null;
	}

	@Override
	public void remove(Long bno) {
		// deleteById(id)
		repository.deleteById(bno);
	}
}
