package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@Table(name="") 이름이 다를 때만 사용
public class Board extends TimeBased{

	//entity : db의 테이블
	//dto : 객체
	
	//자주 쓰는 코드들 : base class로 별도 관리
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	
	//@ColumnDefault("aaaaa") (columnDefinition = "String default writer")
	@Column(length = 100, nullable = false)
	private String title, writer;
	@Column(length = 2000, nullable = false)
	private String content;
}
