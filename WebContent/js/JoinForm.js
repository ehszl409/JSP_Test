var isChecking = false;
	
	function valid() {
		
		if (isChecking == false) {
			alert("아이디 중복체크를 해주세요");
		}
		return isChecking;
	}
	
function usernameCheck(){
	 var username = $("#username").val();

	$.ajax({
		type: "post",
		url: "user?cmd=usernameCheck",
		data: username,
		contentType: "text/plain; charset=utf-8",
		dataType: "text"
	})
	.done(function(data){
		if (data === 'ok') { 
				isChecking = false;
				alert('유저네임이 중복되었습니다.')
			} else {
				isChecking = true;
				$("#username").attr("readonly", "readonly");
				alert("해당 유저네임을 사용할 수 있습니다.")
			}
	});
	
}