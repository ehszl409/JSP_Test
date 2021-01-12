package com.cos.test.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.test.domain.user.User;
import com.cos.test.domain.user.dto.JoinReqDto;
import com.cos.test.domain.user.dto.LoginReqDto;
import com.cos.test.domain.user.dto.UsernameCheckReqDto;
import com.cos.test.service.UserService;
import com.cos.test.utill.Script;
import com.google.gson.Gson;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		
		
		if(cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));
			List<User> users = userService.유저목록보기(page);
			
			int userCount = userService.유저수();
			int lastPage = (userCount-1)/5;
			
			request.setAttribute("users", users);
			request.setAttribute("lastPage", lastPage);
			
			RequestDispatcher dis = 
			request.getRequestDispatcher("user/userList.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("joinForm")) {
			RequestDispatcher dis = 
			request.getRequestDispatcher("user/joinForm.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			System.out.println("username:" + username);
			System.out.println("password: " + password);
			System.out.println("email: "+ email);
			
			JoinReqDto dto = new JoinReqDto().builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
			System.out.println("dto: " + dto);
			
			int result = userService.회원가입(dto);
			if(result == 1) {
				
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "회원 가입에 실패 했습니다.");
			}
			
		} else if(cmd.equals("loginForm")) {
			RequestDispatcher dis = 
			request.getRequestDispatcher("user/loginForm.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			LoginReqDto dto = new LoginReqDto().builder()
					.username(username)
					.password(password)
					.build();
			
			User userEntity = userService.로그인(dto);
			if(userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "로그인에 실패 했습니다.");
			}
		} else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		} else if(cmd.equals("delete")) {
			int userId = Integer.parseInt(request.getParameter("id"));
			System.out.println("userId: " + userId);
			
			int result = userService.유저삭제(userId);
			if(result == 1) {
				RequestDispatcher dis =
				request.getRequestDispatcher("user?cmd=logout");
				dis.forward(request, response);
			} else {
				Script.back(response, "삭제에 실패 했습니다.");
			}
		} else if(cmd.equals("deleteOfAdmin")) {
			int userId = Integer.parseInt(request.getParameter("id"));
			System.out.println("userId: " + userId);
			
			int result = userService.유저삭제(userId);
			if(result == 1) {
				RequestDispatcher dis =
				request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "삭제에 실패 했습니다.");
			}
		} else if(cmd.equals("usernameCheck")) {
			BufferedReader br = request.getReader();
			String username = br.readLine();
			System.out.println(username);
			
			int result = userService.유저네임중복체크(username);
			PrintWriter out = response.getWriter();
			if (result == 1) {
				out.print("ok");
			} else {
				out.print("fail");
			}
			out.flush();
		}
		
	}

}
