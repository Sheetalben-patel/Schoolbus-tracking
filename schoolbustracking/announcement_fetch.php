
<?php
	error_reporting(0);
session_start();
require("connect-db.php");
//include 'session.php';
$id=$_SESSION['id'];
	$uername=$_SESSION['username'];

  
    if (isset($_POST['submit'])) {

      $date = $_POST['date'];
      $announcement = $_POST['announcement'];
     

      $sql=("insert into annoucement(date,announcement)values('".$date."','".$announcement."')");
	  $res=mysqli_query($conn,$sql);
	  if(!$res)
	  {
      echo "New entry was created";
      }
      else{
        echo "unable to create new entry.";
      }
    }
     ?> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>School Bus Tracking</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
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
			
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Student</h1>
			</div>
		</div><!--/.row-->
	
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Announcement</div>
					<div class="panel-body">
						<div class="col-md-6">
				<table border="1">
				<tr>
				<td>Date</td>
				<td>Announcement</td>
				</tr>
				<?php

	
	$dbhost="localhost";
	$dbuser="root";
	$dbpass="";
	$dbname="schoolbustrackingdb";
	$conn=mysqli_connect($dbhost,$dbuser,$dbpass,$dbname);

	

$qry="select * from  annoucement";

$res=mysqli_query($conn,$qry);
if (!$res) { // add this check.
    die('Invalid query: ' . mysqli_error());
}
	
while($ans=mysqli_fetch_array($res))
{	
?>		
				<tr>
				<td><?php echo $ans['date'];?></td>
					<td><?php echo $ans['announcement'];?></td>
				</tr>
				<?php
				
				}?>
				</table>
					</div>
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->
		
	</div><!--/.main-->
		
		
		
		</div><!--/.row-->
	</div>	<!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script>
		$('#calendar').datepicker({
		});

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
