<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 필터를 걸어 주고 디스패처로 이동해야 한다 
	RequestDispatcher dis = 
	request.getRequestDispatcher("user?cmd=list&page=0");
	dis.forward(request, response);
%>