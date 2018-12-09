<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.LoginForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
	<form action="/ShoppingCart/update.do" method="post">
	<center>
		<%
			LoginForm user = (LoginForm) session.getAttribute("user");
			ArrayList<HashMap<String, String>> data=(ArrayList<HashMap<String, String>>)session.getAttribute("data");
		%>
		<h1>尊敬的<%=user.getNickname()%>,这是您的购物车清单</h1>
		<table>
			<tr>
				<th align="center">商品编号</th>
				<th align="center">商品名称</th>
				<th align="center">商品单价</th>
				<th align="center">商品数量</th>
				<th align="center">小计</th>
				<th align="center">修改数量</th>
			</tr>
			<%
				double result=0;
				if (data != null) {
					for (int i = 0; i < data.size(); i++) {
						result+=Double.parseDouble(data.get(i).get("xiaoji"));
			%>
			<tr>
				<td align="center"><%=data.get(i).get("fruit_id") %></td>
				<td align="center"><%=data.get(i).get("fruit_name") %></td>
				<td align="center"><%=data.get(i).get("fruit_price")%></td>
				<td align="center"><%=data.get(i).get("fruit_number")%></td>
				<td align="center"><%=data.get(i).get("xiaoji")%></td>
				<td align="center"><input style="text-align:center" class="form-control" type="text" name="fruit_number<%=i%>" value="<%=data.get(i).get("fruit_number")%>"></td>
			</tr>
			<%				
					}
				}
			%>
			<tr><td colspan="4" align="center"><h3 align="center">共<%=result %>（元）</h3></td></tr>
		</table><br>
		<a href="/ShoppingCart/shopping.jsp" class="btn btn-default btn-lg" role="button">返回购物页面</a>
		<button type="button" class="btn btn-primary btn-lg" onclick="check1()">退出</button>
		<input type="submit" value="确认修改" class="btn btn-lg btn-info">
	</center>
	</form>
	<script type="text/javascript">
		function check1(){
			window.location.href="/ShoppingCart/login.jsp"; 
		}
	</script>
</body>
</html>