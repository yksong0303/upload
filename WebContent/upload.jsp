<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="upload" enctype="multipart/form-data"><!--enctype 안에 내용은 외워야함/포스트 방식으로 액션태그 안에 내용으로 이동  -->
	사진1 : <input type="file" name="org_file_path1" id="org_file_path1"><br>
	사진2 : <input type="file" name="org_file_path2" id="org_file_path2"><br>
	업로더 : <input type="text" name="up_name" id="up_name"><br>
	<button type="button" onclick="upload()">업로드</button>
<!-- 파일 업로드할땐 !포스트! 방식으로만 보내야함 -->
</form>
<script>
function upload(){
var xhr = new XMLHttpRequest(); // 서블릿이랑 소통하기전에 설정
xhr.open('POST','/upload'); 
xhr.onreadystatechange=function(){
	if(xhr.readyState==4){
		if(xhr.status==200){
			
		}
	}
}
var formData = new FormData();
var f1 = document.querySelector('#org_file_path1');
var f2 = document.querySelector('#org_file_path2');
//console.log(f1.files[0]) formData이전 단위 테스트, 하기전에는 센드하지말것
//console.log(f2.files[0])
formData.append('org_file_path1',f1.files[0]);
formData.append('org_file_path2',f2.files[0]);
formData.append('up_name',document.querySelector('#up_name').value);
xhr.send(formData);
}
</script>
</body>
</html>