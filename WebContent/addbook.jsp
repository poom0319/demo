<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
	*{margin: 0;padding: 0;}
	table tr{height: 30px;}
	a{text-decoration: none;margin-left: 5px;background-color: #bebbb8;color:black;padding: 2px;width:40px;height: 25px;font-size:16px;}
	input{outline:none;width:150px;height: 20px;border:none;}
	textarea{outline:none;border:none;}
	table{margin:20px 0 0 20px;border-right: 2px solid #bebbb8;border-bottom: 2px solid #bebbb8;border-collapse: collapse;}
	table th{padding:5px;border-left: 2px solid #bebbb8;border-top:2px solid #bebbb8;}
	table td{padding:5px;border-left: 2px solid #bebbb8;border-top:2px solid #bebbb8;}
	#submit{margin-left: 115px;width:40px;height: 25px;font-size:16px; background-color: #bebbb8;color:black;padding: 2px;}
	span{color:red;}
	h2{color:black;margin: 10px 20px;}
	h2 span{color:red;}
</style>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script language="javascript">
	function check(){
		var bookName = document.getElementById("bookName");
		var author = document.getElementById("author");
		var publish = document.getElementById("publish");
		var publishDate = document.getElementById("publishDate");
		
		if(bookName.value == ""){
			alert("书名不能为空！请重新填入！");
			bookName.focus();	
			return false;
		}else if(author.value == ""){
			alert("作者不能为空！请重新填入！");
			author.focus();
			return false;
		}else if(publish.value == ""){
			alert("出版社不能为空！请重新填入！");
			publish.focus();
			return false;
		}else if(publishDate.value == ""){
			alert("出版日期不能为空！请重新填入！");
			publishDate.focus();
			return false;
		}
		return true;
	}
</script>
<title>Insert title here</title>
</head>
<body>
<%
	Object object=session.getAttribute("login");
	String name="";
	if(object==null){
		response.sendRedirect("login.jsp");
	}else{
		name=object.toString();
	}
%>
<h2>管理员<span><%=name %></span></h2>
	<table border=1>
	<form action="AddBook" method="post" onsubmit="return check()">
		<tr><th colspan="2" style="background-color: orange;">增加图书</th></tr>
		<tr><td>书名(<span>*</span>)</td><td><input name="bookName" type="text" id="bookName"/></td></tr>
		<tr><td>作者(<span>*</span>)</td><td><input name="author" type="text" id="author"/></td></tr>
		<tr><td>出版社(<span>*</span>)</td><td><input name="publish" type="text" id="publish"/></td></tr>
		<tr><td>出版日期(<span>*</span>)</td><td><input name="publishDate" type="text" id="publishDate"/>(yyyy-MM-dd)</td></tr>
		<tr><td>页数</td><td><input name="page" type="text"/></td></tr>
		<tr><td>价格</td><td><input name="price" type="text"/></td></tr>
		<tr><td>内容摘要</td><td><textarea name="content" cols="25" rows="5"></textarea></td></tr>
		<tr><td colspan="2"><input type="submit" value='提交' id='submit'/><a href="books.jsp">返回</a></td></tr>
	</form>
	</table>
</body>
</html>