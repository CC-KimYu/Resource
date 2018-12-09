<?php
	if(isset($_POST["username"])&&isset($_POST["password"])&&$_POST["username"]!=""&&$_POST["password"]!=""){//账号 密码均不为空 
		if($_POST["username"]=="administrator"&&$_POST["password"]=="123456"){
			$time=$_POST["time"];
			switch($time)
			{
				case "0": $expire=time()+0 ;break;
				case "1": $expire=time()+60*60*24 ;break;
				case "2": $expire=time()+60*60*24*30 ;break;
				case "3": $expire=time()+60*60*24*30*7 ;break;
			}
			setcookie("password",$_POST["password"],$expire);
			echo "<script> window.location.href='cookie1.php';</script>";  //跳转
		}else{
			echo "<script> alert('登录信息有误');</script>";
		}
	}
?>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	</head>
	<body>
		<center>
			<form action="" method="post">
				用户名: <input type="text" name="username"><br>
				密   码:  <input type="text" name="password"><br>
				Cookie保存时间：
				<select name="time">
					<option value="0">不保存</option>
					<option value ="1">保存1小时</option>
					<option value ="2">保存1天</option>
					<option value="3">保存1星期</option>
				</select><br/>
				<input type="submit" value="登录">
				<input type="reset" value="重置">
			</form>
		</center>
	</body>
</html>