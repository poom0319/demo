<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	*{margin: 0;padding: 0;}
	#login{margin: auto auto;}
	table tr{height: 35px;}
	table{margin:10px auto;border-right: 2px solid #bebbb8;border-bottom: 2px solid #bebbb8;border-collapse: collapse;}
	table th{padding:5px;border-left: 2px solid #bebbb8;border-top:2px solid #bebbb8;}
	table td{padding:5px;border-left: 2px solid #bebbb8;border-top:2px solid #bebbb8;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript">

	$(function(){ 
		$("#submit").click(function(){
			var user=$("#user").val();
			var pwd=$("#password").val();
			$.ajax({
				url:"DoLogin?opr=in&user="+user+"&password="+pwd,
				success:function(data){
					if(data==""){
						location.href="ShowBooks";
					}else{
						$("#mess").html(data);
					}
				}
			})
		});
	});
</script>

</head>
<body>
	<div id="login">
	
			<table>
				<tr><th colspan="2">登录图书管理系统</th></tr>
				<tr><td>用户名:</td><td><input type="text" id="user"/></td></tr>
				<tr><td>　密码:</td><td><input type="password" id="password"/></td></tr>
				<tr><th colspan="2"><input type="button" value="登录" id="submit"/></th></tr>
				<tr><td colspan="2" id="mess" style="color: red;"></td></tr>
			</table>
	</div>
</body>
</html>