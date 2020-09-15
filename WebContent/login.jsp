<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/menu.jsp"></jsp:include>
<form>
  <div class="form-group">
    <label for="id">아이디</label>
    <input type="text" class="form-control" id="id" aria-describedby="emailHelp">
    <small id="emailHelp" class="form-text text-muted">We'll never share your ID with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="pwd">비밀번호</label>
    <input type="password" class="form-control" id="pwd">
  </div>
  <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div>
  <button onclick="login()" type="submit" class="btn btn-primary">Submit</button>
    <a href="/join.jsp"><button type="button" class="btn btn-primary">회원가입하러가깅</button></a>
</form>
<script>
function login(){

	if($('#id').val().trim().length<4){
		alert('아이디는 4글자 이상이어야 합니다.')
		$('#id').focus();
		return;
	}
	if($('#pwd').val().trim().length<4){
		alert('비밀번호는 4글자 이상이어야 합니다.')
		$('#pwd').focus();
		return;
	}
	var params={
		id : $('#id').val(),
		pwd : $('#pwd').val(),
		cmd : 'login'
	}
	console.log(params)
	
$.ajax({
	url : "/ajax/user",
	method : "POST",
	contentType : "application/json",
	data : JSON.stringify(params),
	success:function(res){
		if(res==true){
			alert('로그인 성공'),
			location.href='/';
		}else{
			alert('로그인 실패')
		}
	}	
})
}

</script>
</body>
</html>