<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/test/user?cmd=join" method="post" onsubmit="return valid()">

		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="usernameCheck()">중복체크</button>
		</div>
		<input type="text" name="username" id="username" class="form-control"
				placeholder="Enter Username" required /></br>

		<div class="form-group">
			<input type="password" name="password" class="form-control"
				placeholder="Enter Password" required />
		</div>

		<div class="form-group">
			<input type="email" name="email" class="form-control"
				placeholder="Enter Email" required />
		</div>
		
		
		<button type="submit" class="btn btn-primary">회원가입완료</button>
	</form>
</div>

<script src="/test/js/JoinForm.js">
</script>



</body>
</html>