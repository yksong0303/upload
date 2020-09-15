<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/menu.jsp"></jsp:include>

  <div class="form-group">
    <label for="id">아이디</label>
    <input type="text" class="form-control" id="id" aria-describedby="emailHelp">
  </div>
    <div class="form-group">
    <label for="pwd">비밀번호</label>
    <input type="password" class="form-control" id="pwd" aria-describedby="emailHelp">
  </div>
    <div class="form-group">
    <label for="name">이름</label>
    <input type="text" class="form-control" id="name" aria-describedby="emailHelp">
  </div>

 <button onclick="dosignup()" class="btn btn-primary">회원가입</button>
 
 <script>
 function dosignup(){
	 if($('#id').val().trim().length<4){
		 alert('4글자 이상 쓰시오');
		 $('#id').focus();
		 return;
	 }
	 if($('#pwd').val().trim().length<4){
		 alert('4글자 이상 쓰시오');
		 $('#pwd').focus();
		 return;
	 }
	 if(!$('#pwd').val().match(/[!@#$%^&*()]/)){
		 alert('비밀번호에 특수문자를 추가해 주세요');
		 $('#pwd').focus();
		 return;
	 }
	 
 }
 </script>
  
</body>
</html>