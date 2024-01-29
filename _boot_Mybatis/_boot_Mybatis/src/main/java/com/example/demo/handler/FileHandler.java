package com.example.demo.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class FileHandler {

	private final String UP_DIR = "D:\\_myProject\\_java\\_fileUpload\\";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		LocalDate date = LocalDate.now();
		String today = date.toString();
		//오늘 날짜를 파일이 경로 모양으로 변경하여 투데이에 저장
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		//실제 폴더를 생성하는 명령어 mkdir(1개)/mkdirs(여러 폴더 한번에 생성)
		if(!folders.exists()) {
			folders.mkdirs();
		}
		//fileVO 생성
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			//----------------fvo 설정 마무리
			
			//디스크에 저장할 파일 생성
			String fullFileName = uuid.toString()+"_"+onlyFileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					fvo.setFileType(1);
					File thumbnail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75,75).toFile(thumbnail);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.info("파일 저장 오류");
			}
			flist.add(fvo);
		}
		
		return flist;
	}
	private boolean isImageFile(File file) throws IOException{
		String mimeType = new Tika().detect(file);
		return mimeType.startsWith("image")? true:false;
	}
}
