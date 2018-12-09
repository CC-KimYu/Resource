<%@page import="Bean.LoginForm"%>
<%@page import="java.util.ArrayList,Bean.Fruit"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>shopping</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
 <center>
 	<%
 		LoginForm user=(LoginForm) session.getAttribute("user");
 	%>
 	<h1>欢迎<%=user.getNickname() %>光临</h1>
 	<form action="/ShoppingCart/deal.do" method="post">
 	<table>
 		<tr>
 			<th>商品名</th>
 			<th>商品编号</th>
 			<th>商品价格（元/斤）</th>
 			<th>商品库存</th>
 			<th>购买商品数量（斤）</th>
 		</tr>
 		<%
 			ArrayList<Fruit> fruits=(ArrayList)session.getAttribute("fruits");
 			if(fruits!=null){
 				for(int i=0;i<fruits.size();i++){
 		%>
 		<tr>
 				<td align="center"><%=fruits.get(i).getName() %></td>
 				<td align="center"><%=fruits.get(i).getId() %></td>
 				<td align="center"><%=fruits.get(i).getPrice() %></td>
 				<td align="center"><%=fruits.get(i).getStock() %></td>
 				<td align="center"><input type="text" name="fruit<%=i+1%>" value="0" style="text-align:center" class="form-control" onblur="check(<%=fruits.get(i).getStock() %>,this.value,this)"></td>
 		</tr>
 		<%
 				}
 			}
 		%>
 	</table><br>
 		<input type="submit" value="购买" class="btn btn-lg btn-info">
 		<button type="button" class="btn btn-primary btn-lg" onclick="check1()">退出</button>
 	</form>
 	<script type="text/javascript">
 		function check(a,b,c){
 			if(parseInt(a)<parseInt(b)){ //库存小于选中商品数量
 				alert('选择数量不能大于商品库存数量');
 				c.value="0";
 				c.focus();
 			}
 		}
 		function check1(){
			window.location.href="/ShoppingCart/login.jsp"; 
		}
 	</script>
 </center>
</body>
</html>