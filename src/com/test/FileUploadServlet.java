package com.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static int ramSize=100*1024; //100kb, 1024=1kb 서버가 감당 가능한 메모리 사이즈 
       private static int maxTotalSize = 10*1024*1024; //10mb 최대로 올릴 수 있는 사이즈
       private static int maxFileSize = 2*1024*1024; //2mb 파일 하나당 올릴 수 있는 사이즈
       private UploadDAO upDAO = new UploadDAOImpl();
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType =  request.getContentType();
//		System.out.println(contentType); DT
		DiskFileItemFactory dfiFactory = new DiskFileItemFactory();//파일을 업로드 받을때 메모리를 생성해서 얘로 가져옴?
		dfiFactory.setSizeThreshold(ramSize);
		
		ServletFileUpload sfu = new ServletFileUpload(dfiFactory); // 파일 꺼내옴
		sfu.setFileSizeMax(maxFileSize); //파일 사이즈max
		sfu.setSizeMax(maxTotalSize); //총 사이즈 max
		try {
			Map<String, String> upload = new HashMap<String, String>(); // map형태로 바꿔야 편함.
			
			List<FileItem> fiList = sfu.parseRequest(new ServletRequestContext(request)); //try catch jsp에서 받아온 내용을 분석함
			for(FileItem fi : fiList) {
//				System.out.println(fi.isFormField());//TD
				if(fi.isFormField()) { //form field는 파일로 저장하지않고 jsp상의 form태그를 말하는거
					upload.put(fi.getFieldName(), fi.getString("UTF-8"));
					
//					System.out.println(fi.getFieldName()); //form 태그 안의 name값 가져옴, KEY
//					System.out.println(fi.getString("UTF-8"));//String encoding, 한글 깨짐 , VALUE
				}else {
					System.out.println(fi.getName());//브라우저에 업로드하는 파일 이름
		               String fileName= fi.getName();
		               String extendName = fileName.substring(fileName.lastIndexOf("."));
		               String path = "C:\\upload\\"+System.nanoTime()+extendName; //중복방지로 시스템에 나노타임으로 분리
		               File f = new File(path); // 경로앞에 \\ 하나씩 더 붙이기** 마지막에도 붙여야 들어감
		               //사진을 올리면 사진이름과 올린사람이름을 DB에 저장해야 하는데
		               //고유번호를 지정해줘서 사진에대한 식별을 할 수 있게 해준다
		               //파일저장이름과 원래 사용자가 올린 이름으로 두가지를 저장한다
		               //DB에 저장시에는 원래의 파일 경로까지 알아야 한다
		               fi.write(f);
		               upload.put(fi.getFieldName(),fileName);
		         //    upload.put(fi.getFieldName().substring(4),"C:\\upload\\"+fi.getName());이상태에서 value를 exetendName으로 변경
		               upload.put(fi.getFieldName().substring(4),path);
				}
			}
			upDAO.insertUpload(upload);
		} catch (Exception e) { //FileUploadException > Exception으로 바꿔주기
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		doGet(request, response);
	}

}

/*
 * 게시판 개발시 유의점
 * 이름, 내용, 파일
 * 1. 파일 저장
 * 2. 데이터베이스 저장
 * 
*/