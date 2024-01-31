package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

	//entity : db의 테이블
	//dto : 객체
	
	private Long bno;
	private String title, writer, content;
	private LocalDateTime regAt, modAt;
}
