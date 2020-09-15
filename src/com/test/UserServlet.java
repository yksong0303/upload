package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/ajax/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		StringBuffer sb = new StringBuffer();
		String str;
		BufferedReader bf = request.getReader();
		while((str=bf.readLine())!=null) {
			sb.append(str);
		}
	      Map<String,Object> user = gson.fromJson(sb.toString(), Map.class);
	      Boolean result = false;
	      if("login".equals(user.get("cmd"))) {
	         if("test".equals(user.get("id"))) {
	            if("test".equals(user.get("pwd"))) {
	               result=true;
	            }
	         }
	      }
	      PrintWriter pw = response.getWriter();
	      pw.print(gson.toJson(result));
	   }

	}