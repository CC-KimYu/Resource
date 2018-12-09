<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />	
	</head>
	<body>
		<?php
			session_start();
			if(isset($_SESSION["username"])&&isset($_SESSION["password"])){
				echo "<center> 欢迎管理员登录,您的密码为:". $_SESSION["password"]."</center>";
			}else{
				echo "<script> alert('无权限访问');</script>";
				echo "<script> window.location.href='session.php';</script>";  //跳转
			}
		?>
	</body>
</html>