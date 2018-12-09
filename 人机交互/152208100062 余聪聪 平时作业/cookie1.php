<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	</head>
	<body>
		<?php
			if(isset($_COOKIE["password"])){
				echo "<center> 欢迎管理员登录,您的密码为:". $_COOKIE["password"]."</center>";
			}else{
				echo "<script> alert('无权限访问');</script>";
				echo "<script> window.location.href='cookie.php';</script>";  //跳转
			}
		?>
	</body>
</html>