package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.BoardVO;
import com.example.demo.repository.BoardMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootMybatisApplication.class)
class BootMybatisApplicationTests {

	@Autowired
	private BoardMapper bm;
	
	@Test
	void contextLoads() {
		for(int i = 0; i<369; i++) {
			BoardVO bvo = BoardVO.builder()
					.title("test"+i)
					.writer("Tester"+i+"@aaa.bbb")
					.content("Test Content"+i)
					.build();
			bm.insert(bvo);
		}
	}

}
