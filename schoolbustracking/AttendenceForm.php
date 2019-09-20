
<?php
session_start();
require("dbconfig.php");
//include 'session.php';
	$id=$_SESSION['id'];
	$uername=$_SESSION['username'];

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
<script type="text/javascript">
function formValidation()  
{  


var email=/^[\w\+\'\.-]+@[\w\'\.-]+\.[a-zA-Z]{2,}$/;
var pass=/^[a-zA-Z0-9-_]{6,16}$/;

var ph=/^[0-9]{9,14}$/;



// FOR alphanumeric characters only


	
	 
	
	  if(document.f1.email.value.search(email)==-1)
    {
	 alert("enter correct email address");
	 document.f1.email.focus();
	 return false;
	 }
	 else if(document.f1.password.value.search(pass)==-1)
    {
	 alert("enter the  correct password");
	 document.f1.password.focus();
	 return false;
	 }
	
	  else if(document.f1.phone.value.search(eno)==-1)
    {
	 alert("enter correct phone no");
	 document.f1.phone.focus();
	 return false;
	 }
	 
 
	
	

else  
{  
alert('Form Succesfully Submitted');  
return true;  
}  
}



</script>  
<script>
function showUser(str)
{
if (str=="")
{
document.getElementById("txtHint").innerHTML="";
return;
}

if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}



xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("subcat").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("GET","dd.php?standard="+str,true);
xmlhttp.send();
}
</script>
</head>

<body>
	<?php require("header.php");?>
		
	<?php require("menu.php");?>
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Student Attandance</h1>
			</div>
		</div><!--/.row-->

<?php
error_reporting(0);
$user_name = "root";
 $password = "";
 $host = "localhost";
 $db_name = "schoolbustrackingdb";

 $con = mysqli_connect($host, $user_name, $password, $db_name);
  if(isset($_POST['submit'])) 
  {
$date = date('Y-m-d');
$month=$_POST['month'];
 $standard=$_POST['standard'];
	  $fname=$_POST['fname'];
	  $present=$_POST['present'];
$query=mysqli_query($con,'Insert into attendence (date,month,standard,fname,present)values("'.$date.'","'.$month.'","'.$standard.'","'.$fname.'","'.$present.'")');
if(!$query)
{
	echo mysqli_error();
	}

}




?>	 
	 
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Student Attandance</div>
					<div class="panel-body">
						<div class="col-md-10">
  <form method="post" action="" role="form">
    <div class="col-lg-3">
	  <select name="month">
  <option value=''>Select Month</option>
   <option value='jan'>Jan</option>
     <option value='feb'>Feb</option>
	   <option value='march'>March</option>
   </select>
   
	</div>
     <!-- <div class="container"> -->
     <div class="col-lg-3">
      <select name="standard" onChange="showUser(this.value)">
  <option value=''>Select standard</option>
 <?php
 $user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);
$q=mysqli_query($con,"select * from standard ");
 //$sql = "SELECT * FROM student,standard WHERE  student.standard=standard.standard";
           
while($n=mysqli_fetch_array($q)){
echo "<option value=".$n['standard'].">".$n['standard']."</option>";
}
?>

</select>
      </div> <!--col-lg-4-->
	  <div class="col-lg-3">
      <select name="fname" id="subcat">
 <option value=''>Select Student Name</option>
</select>

      </div> <!--col-lg-4-->
      <input type="radio" name="present" value="1" />Present
      <input type="radio" name="present" value="0" />Absent
      <button type="submit" name="submit" value="Save" class="btn btn-success btn-sm">Save</button>
   
 </form>
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
