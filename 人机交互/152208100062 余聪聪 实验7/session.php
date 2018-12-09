<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />	
	</head>
	<body>
		<center>
			<form action="" method="post">
				用户名: <input type="text" name="username"><br>
				密   码:  <input type="text" name="password"><br>
				<input type="submit" value="登录">
				<input type="reset" value="重置">
			</form>
		</center>
		<?php
			if(isset($_POST["username"])&&isset($_POST["password"])&&$_POST["username"]!=""&&$_POST["password"]!=""){//检测用户名 、密码均存在 且不为空
				$username=$_POST["username"];
				$password=$_POST["password"];
				if(strcmp($username,"administrator")==0&&strcmp($password,"123456")==0){//用户名 密码输入正确
					session_start(); // 初始化session
					$_SESSION["username"]="administrator";
					$_SESSION["password"]="123456";
					echo "<script> window.location.href='session1.php';</script>";  //跳转
				}else{
					echo "<script> alert('登录信息有误');</script>";
				}
			}
		?>
	</body>
</html>