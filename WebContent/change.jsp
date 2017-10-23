<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form action="DoUpdate?opr=change&id=${book.id }" method="post">
		<tr><th colspan="2" style="background-color: orange;">修改图书</th></tr>
		<tr><td>书名(<span>*</span>)</td><td><input name="bookName" type="text" value='${book.bookName}'/></td></tr>
		<tr><td>作者(<span>*</span>)</td><td><input name="author" type="text" value='${book.author}'/></td></tr>
		<tr><td>出版社(<span>*</span>)</td><td><input name="publish" type="text" value='${book.publish}'/></td></tr>
		<tr><td>出版日期(<span>*</span>)</td><td><input name="publishDate" type="text" value='${book.publishDate}'/>(yyyy-MM-dd)</td></tr>
		<tr><td>页数</td><td><input name="page" type="text" value='${book.page}'/></td></tr>
		<tr><td>价格</td><td><input name="price" type="text" value='${book.price}'/></td></tr>
		<tr><td>内容摘要</td><td><textarea name="content" cols="25" rows="5">${book.content}</textarea></td></tr>
		<tr><td colspan="2"><input type="submit" value='提交' id='submit'/><a href="books.jsp">返回</a></td></tr>
	</form>
	</table>
</body>
</html>