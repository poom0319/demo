<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
	*{margin: 0;padding: 0;}
	table tr{height: 30px;}
	a{text-decoration: none;}
	a:HOVER{color:red;text-decoration: underline;}
	#bot{margin: 10px; 20px;}
	#bot input{width: 50px;}
	#bot a{margin-left: 10px;}
	#go{display:inline-block;  width:30px;height:20px; background:#bebbb8;line-height: 20px; text-align: center;}
	table{margin:20px 0 0 20px;border-right: 2px solid #bebbb8;border-bottom: 2px solid #bebbb8;border-collapse: collapse;}
	table th{padding:5px;border-left: 2px solid #bebbb8;border-top:2px solid #bebbb8;}
	table td{padding:5px;border-left: 2px solid #bebbb8;border-top:2px solid #bebbb8;}
	h2{color:black;margin: 10px 20px;}
	h2 span{color:red;}
</style>
<head>

<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript">
		function _go(){
			var pageSize=$("#pageSize").val();
			var pageIndex=$("#pageIndex").val();
			location="ShowBooks?pageIndex="+pageIndex+"&pageSize="+pageSize;
		}
		function del() {
			var msg = "您真的确定要删除吗？";
			if (confirm(msg)==true){
				return true;
			}else{
				return false;
			}
		}
</script>
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
<h2>欢迎<span><%=name %></span>登录图书管理系统&nbsp;<a href="DoLogin?opr=out">退出登录</a></h2>
	<table>
		<tr id="a" style="background-color: #f6d7c3"><th colspan="8">图书详细信息列表</th></tr>
		<tr><td>书名</td><td>作者</td><td>出版社</td><td>出版日期</td><td>页数</td><td>价格</td><td>内容摘要</td><td>操作</td></tr>
		<c:forEach items="${page.bookList }" var="book" varStatus="i">
		
		<tr <c:if test="${i.count%2==1}">style="background-color:#f4e5ed;"</c:if>>
			<td>${book.bookName}</td>
			<td>${book.author}</td>
			<td>${book.publish}</td>
			<td>${book.publishDate}</td>
			<td>${book.page}</td>
			<td>${book.price}</td>
			<td>${book.content}</td>
			<td><a href="DoUpdate?opr=read&id=${book.id}">修改</a>&nbsp;<a href="DoUpdate?opr=del&id=${book.id}" onclick="javascript:return del();">删除</a></td>
			
		</tr>
		</c:forEach>
	</table>
	<div id="bot">
		<a href="addbook.jsp">新增图书</a>&nbsp;共${page.count }条数据 
		每页<input type="text" id="pageSize" value="${page.pageSize}"/>条&nbsp;&nbsp;
		第${page.pageIndex }页/共${page.totalPage}页
		<a href="ShowBooks?pageIndex=1" id="first">首页</a>
		<a href="ShowBooks?pageIndex=${page.pageIndex-1<1?1:page.pageIndex-1}" id="up">上一页</a>
		<a href="ShowBooks?pageIndex=${page.pageIndex+1>page.totalPage?page.totalPage:page.pageIndex+1}" id="next">下一页</a>
		<a href="ShowBooks?pageIndex=${page.totalPage}" id="end">尾页</a>&nbsp;
		跳转到<input type="text" id="pageIndex"/>页<a href="javascript:_go();" id="go">go</a>
	</div>
</body>
</html>