<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />	
	</head>
	<body>
		<?php
			session_start();
			if(isset($_SESSION["username"])&&isset($_SESSION["password"])){
				echo "<center> ��ӭ����Ա��¼,��������Ϊ:". $_SESSION["password"]."</center>";
			}else{
				echo "<script> alert('��Ȩ�޷���');</script>";
				echo "<script> window.location.href='session.php';</script>";  //��ת
			}
		?>
	</body>
</html>