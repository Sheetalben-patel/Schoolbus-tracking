<?php
error_reporting(E_ALL ^ E_NOTICE);
	include 'connect-db.php';
	//include 'session.php';
	?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Tables</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/bootstrap-table.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--Icons-->
<script src="js/lumino.glyphs.js"></script>

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<?php require("header.php");?>
		
	<?php require("menu.php");?>
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Parents Data</h1>
			</div>
		</div><!--/.row-->
						


<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
						<div class="col-md-6" style="margin-top:10px">
						<form name="search" method="post" action="">
	<table style=" border:1px solid silver;margin-top:10px;color:brown" cellpadding="5px" align="center" border="0">
	<tr>

	<td><input type="text" name="search" size="30" placeholder="enter the Student Id" /></td>
	<td><input type="submit" value="search" name="sub" /></td>
	</tr>
</table>
</form>
	
<?php
	
if(isset($_POST['sub']))
{
	
	$dbhost="localhost";
	$dbuser="root";
	$dbpass="";
	$dbname="schoolbustrackingdb";
	$conn=mysqli_connect($dbhost,$dbuser,$dbpass,$dbname);

	
$search=$_POST["search"];
$studentId=$_POST['	studentId'];
$qry="select * from  student where 	studentId like '%$search%'";

$res=mysqli_query($conn,$qry);
if (!$res) { // add this check.
    die('Invalid query: ' . mysqli_error());
}
	
while($ans=mysqli_fetch_array($res))
{	
?>
<table style="width:600px;margin:30px auto" border="0">
 
<tr >
 
<td>Parents Name:&nbsp;&nbsp;<?php echo $ans['parents_name'];?></td>
<td>Phone Number:&nbsp;&nbsp;<?php echo $ans['phone'];?></td>	

</tr>
</table>
<?php
}
}

?>
				
				</div>
			</div>
		</div><!--/.row-->	
		


		

		
	</div><!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script>
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>	
</body>

</html>
