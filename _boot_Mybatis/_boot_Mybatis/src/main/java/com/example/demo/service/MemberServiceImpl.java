package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberMapper;
import com.example.demo.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper mm;

	@Override
	public int insert(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOK = mm.insert(mvo);
		return mm.insertAuthinit(mvo.getEmail());
	}
}
