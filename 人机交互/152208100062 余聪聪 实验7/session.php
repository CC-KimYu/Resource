<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />	
	</head>
	<body>
		<center>
			<form action="" method="post">
				�û���: <input type="text" name="username"><br>
				��   ��:  <input type="text" name="password"><br>
				<input type="submit" value="��¼">
				<input type="reset" value="����">
			</form>
		</center>
		<?php
			if(isset($_POST["username"])&&isset($_POST["password"])&&$_POST["username"]!=""&&$_POST["password"]!=""){//����û��� ����������� �Ҳ�Ϊ��
				$username=$_POST["username"];
				$password=$_POST["password"];
				if(strcmp($username,"administrator")==0&&strcmp($password,"123456")==0){//�û��� ����������ȷ
					session_start(); // ��ʼ��session
					$_SESSION["username"]="administrator";
					$_SESSION["password"]="123456";
					echo "<script> window.location.href='session1.php';</script>";  //��ת
				}else{
					echo "<script> alert('��¼��Ϣ����');</script>";
				}
			}
		?>
	</body>
</html>