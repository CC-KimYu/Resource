<!DOCTYPE html> 
<html> 
	<head>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	</head>
<body> 		
		<form class="form-horizontal" role="form" method="post" id="form">
  			<div class="form-group">
   				<label for="theorypayment" class="col-sm-offset-4 col-sm-1 control-label">Ӧ����</label>
    				<div class="col-sm-2">
      					<input type="text" class="form-control" id="theorypayment"  name="theorypayment" placeholder="������Ӧ������" value="<?php if(isset($_POST['theorypayment'])){echo $_POST['theorypayment'];}?>">
    				</div>
  			</div>
  			<div class="form-group">
    				<label for="actualpayment" class="col-sm-offset-4 col-sm-1 control-label">ʵ�ʸ���</label>
    				<div class="col-sm-2">
      					<input type="text" class="form-control" id="actualpayment" name="actualpayment" placeholder="������ʵ�ʸ�����" value="<?php if(isset($_POST['actualpayment'])){echo $_POST['actualpayment'];}?>">
    				</div>
  			</div>
  			<div class="form-group">
    				<div class="col-sm-offset-5 col-sm-1">
      					<button type="submit" class="btn btn-success btn-lg">ȷ��</button>
    				</div>
  			</div>
		</form>
	<?php 
		//�ж��Ƿ���form������ ������ݼ�������һ������ �������κβ���
		if(isset($_POST["theorypayment"])&&isset($_POST["actualpayment"])){
			$theorypayment=$_POST["theorypayment"];
			$actualpayment=$_POST["actualpayment"];
			$x1=$actualpayment-$theorypayment;
			echo "<br/>";
			echo "<center>";
			echo "<h3>����".$x1."Ԫ</h3s>";
			echo "<h4>���������</h4>";
			if($x1>=50){
				echo "50Ԫ 1��"."<br/>";
				$x1=$x1-50;
			}
			if($x1>=40){
				echo "20Ԫ 2��"."<br/>";
				$x1=$x1-40;
			}
			if($x1>=20){
				echo "20Ԫ 1��"."<br/>";
				$x1=$x1-20;
			}
			if($x1>=10){
				echo "10Ԫ 1��"."<br/>";
				$x1=$x1-10;
			}
			if($x1>=5){
				echo "5Ԫ 1��"."<br/>";
				$x1=$x1-5;
			}
			if($x1>=1){
				echo "1Ԫ ".$x1."��";
			}
			echo "</center>";
			
		}
	?> 
	<script>
		//js�ű����ı�����ֵ�����Խ����ж�
		$("#form").on("submit",function(event){
			var theorypayment=document.getElementById("theorypayment");
			var actualpayment=document.getElementById("actualpayment");
			if(parseInt(theorypayment.value)<=0||parseInt(actualpayment.value)<=0){
				alert("������Ϸ�������������");
				event.preventDefault();   //��ֹ���ύ
				return;
			}
			if(parseInt(theorypayment.value)>parseInt(actualpayment.value)){
				alert("Ӧ����ܴ���ʵ�ʸ���");
				event.preventDefault();   //��ֹ���ύ
				return;
			}
			event.submit(); // �ύ��
		})
	</script>	
</body> 
</html>
