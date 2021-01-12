<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">

	<div class="m-2">
		<form class="form-inline d-flex justify-content-end" action="/blog/board">
			<input type="hidden" name="cmd" value="search" />
			<input type="hidden" name="page" value="0" />
		</form>
	</div>

		<c:forEach var="users" items="${users}">
		<div class="card col-md-12 m-2">
			<div class="card-body">
				<h5 class="card-title">ID: ${users.id}</h5>
				<h5 class="card-title">USERNAME: ${users.username}</h5>				
				<h6 class="card-title">EMAIL: ${users.email}</h6>				
				<h6 class="card-title">ROLE: ${users.role}</h6>				
					<c:if test="${sessionScope.principal.id == users.id}">
						<a href="user?cmd=delete&id=${users.id}" class="btn btn-warning">계정삭제</a>
					</c:if >
					<c:if test="${sessionScope.principal.role == 'ADMIN'}">
						<a href="user?cmd=deleteOfAdmin&id=${users.id}" class="btn btn-danger">관리자권한 계정삭제</a>						
					</c:if>	
			</div>
		</div>
		</c:forEach>
	<br />
	
	
	<!-- disabled -->
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${param.page == 0}">
				<li class="page-item disabled"><a class="page-link" href="user?cmd=list&page=${param.page-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="user?cmd=list&page=${param.page-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${lastPage == param.page}">
				<li class="page-item disabled"><a class="page-link" href="user?cmd=list&page=${param.page+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="user?cmd=list&page=${param.page+1}">Next</a></li>
			</c:otherwise>
		</c:choose>
	
		
	</ul>
</div>


  
</body>
</html>