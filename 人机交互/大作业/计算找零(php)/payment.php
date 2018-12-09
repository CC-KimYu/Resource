<!DOCTYPE html> 
<html> 
	<head>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	</head>
<body> 		
		<form class="form-horizontal" role="form" method="post" id="form">
  			<div class="form-group">
   				<label for="theorypayment" class="col-sm-offset-4 col-sm-1 control-label">应付款</label>
    				<div class="col-sm-2">
      					<input type="text" class="form-control" id="theorypayment"  name="theorypayment" placeholder="请输入应付款金额" value="<?php if(isset($_POST['theorypayment'])){echo $_POST['theorypayment'];}?>">
    				</div>
  			</div>
  			<div class="form-group">
    				<label for="actualpayment" class="col-sm-offset-4 col-sm-1 control-label">实际付款</label>
    				<div class="col-sm-2">
      					<input type="text" class="form-control" id="actualpayment" name="actualpayment" placeholder="请输入实际付款金额" value="<?php if(isset($_POST['actualpayment'])){echo $_POST['actualpayment'];}?>">
    				</div>
  			</div>
  			<div class="form-group">
    				<div class="col-sm-offset-5 col-sm-1">
      					<button type="submit" class="btn btn-success btn-lg">确认</button>
    				</div>
  			</div>
		</form>
	<?php 
		//判断是否获得form表单数据 获得数据即进行下一步操作 否则不做任何操作
		if(isset($_POST["theorypayment"])&&isset($_POST["actualpayment"])){
			$theorypayment=$_POST["theorypayment"];
			$actualpayment=$_POST["actualpayment"];
			$x1=$actualpayment-$theorypayment;
			echo "<br/>";
			echo "<center>";
			echo "<h3>找零".$x1."元</h3s>";
			echo "<h4>具体情况：</h4>";
			if($x1>=50){
				echo "50元 1张"."<br/>";
				$x1=$x1-50;
			}
			if($x1>=40){
				echo "20元 2张"."<br/>";
				$x1=$x1-40;
			}
			if($x1>=20){
				echo "20元 1张"."<br/>";
				$x1=$x1-20;
			}
			if($x1>=10){
				echo "10元 1张"."<br/>";
				$x1=$x1-10;
			}
			if($x1>=5){
				echo "5元 1张"."<br/>";
				$x1=$x1-5;
			}
			if($x1>=1){
				echo "1元 ".$x1."张";
			}
			echo "</center>";
			
		}
	?> 
	<script>
		//js脚本对文本框数值合理性进行判断
		$("#form").on("submit",function(event){
			var theorypayment=document.getElementById("theorypayment");
			var actualpayment=document.getElementById("actualpayment");
			if(parseInt(theorypayment.value)<=0||parseInt(actualpayment.value)<=0){
				alert("输入金额不合法，请重新输入");
				event.preventDefault();   //阻止表单提交
				return;
			}
			if(parseInt(theorypayment.value)>parseInt(actualpayment.value)){
				alert("应付款不能大于实际付款");
				event.preventDefault();   //阻止表单提交
				return;
			}
			event.submit(); // 提交表单
		})
	</script>	
</body> 
</html>
