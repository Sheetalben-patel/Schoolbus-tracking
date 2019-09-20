
<?php
session_start();
require("dbconfig.php");
//include 'session.php';
	$id=$_SESSION['id'];
	$uername=$_SESSION['username'];
require_once 'class.user.php';

$reg_user = new USER();




if(isset($_POST['btn-submit']))
{
	$fname = trim($_POST['fname']);
	$email = trim($_POST['email']);
	$standard = trim($_POST['standard']);
	$password = trim($_POST['password']);
$parents_name = trim($_POST['parents_name']);
$phone = trim($_POST['phone']);
	$address = trim($_POST['address']);
	$blood_group = trim($_POST['blood_group']);
	$driver_name = trim($_POST['driver_name']);
	$driver_number = trim($_POST['driver_number']);
	$bus_timing = trim($_POST['bus_timing']);
	$bus_number = trim($_POST['bus_number']);
	$session = trim($_POST['session']);
	//$blood_group = trim($_POST['blood_group']);
	//$code = md5(uniqid(rand()));
	 $studentId=student.rand(100,500);
	
	$stmt = $reg_user->runQuery("SELECT * FROM student WHERE email=:email");
	$stmt->execute(array(":email"=>$email));
	$row = $stmt->fetch(PDO::FETCH_ASSOC);
	
	if($stmt->rowCount() > 0)
	{
		$msg = "
		      <div class='alert alert-error'>
				<button class='close' data-dismiss='alert'>&times;</button>
					<strong>Sorry !</strong>  email allready exists , Please Try another one
			  </div>
			  ";
	}
	else
	{
		if($reg_user->register($fname,$email,$standard,$password,$parents_name,$phone,$address,$blood_group,$driver_name,$driver_number,$bus_timing,$bus_number,$session,$studentId))
		{			
			$id = $reg_user->lasdID();		
			$key = base64_encode($id); 
			$id = $key;
			
			$message = "					
						Hello $fname,<br/>
						Password $password,<br/>
						Username $studentId<br/>
						Driver Name:$driver_name<br/>
						Driver Phone Number:$driver_number<br/>
						Bus Number:$bus_number<br/>
						<br /><br />
						Welcome to Coding Cage!<br/>
						To complete your registration  please , just click following link<br/>
						
						Thanks,";
						
			$subject = "Confirm Registration";
						
			$reg_user->send_mail($email,$message,$subject);	
			$msg = "
					<div class='alert alert-success'>
						<button class='close' data-dismiss='alert'>&times;</button>
						<strong>Success!</strong>  We've sent an email to $email.
                    Please click on the confirmation link in the email to create your account.<br/>
						<font color='red'>Your Pateint ID:$pid</font>
			  		</div>
					";
		}
		else
		{
			echo "sorry , Query could no execute...";
		}		
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
					<div class="panel-heading">Form Elements</div>
					<div class="panel-body">
						<div class="col-md-6">
							<form role="form" method="POST" onSubmit="return formValidation();"  name="f1">
							
								<div class="form-group">
									<label>Student Information</label>
									<input class="form-control" placeholder="Enter Full Name" name="fname" required>
								</div>
								<div class="form-group">
									
									<input class="form-control" onChange="return email()" placeholder="Enter Email" name="email">
								</div>
										<div class="form-group">
									
										<select style="width:300px;height:40px" name="standard" required>
										
<option value="">Select standard</option>

<?php
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);
 $s="select * from standard";
$q=mysqli_query($con,$s) or die($s);
while($rw=mysqli_fetch_array($q))
{ ?>
<option value="<?php echo $rw['standard']; ?>"<?php if($row['standard']==$rw['standard']) echo 'selected="selected"'; ?>><?php echo $rw['standard']; ?></option>
<?php } ?>
</select>
								</div>						
								<div class="form-group">
									
									<input type="password"  name="password"  onChange="return pass()" class="form-control" placeholder="Enter password">
								</div>
								<div class="form-group">
									<label>Parents Information</label>
									<input class="form-control" placeholder="Enter Parents Name" name="parents_name" required>
								</div>
								<div class="form-group">
									
									<input class="form-control" placeholder="Enter phone Number" onChange="return pho()" name="phone" required>
								</div>
								<div class="form-group">
									
									<textarea class="form-control" rows="3"  placeholder="Enter Address" name="address"></textarea>
								</div>
								
								<div class="form-group">
									
									<input class="form-control" placeholder="Enter Blood Group" name="blood_group">
								</div>
								
								
								<div class="form-group">
										<label>Driver Information</label>
									<input class="form-control" placeholder="Enter Driver Name" name="driver_name">
								</div>
									<div class="form-group">
									
									<input class="form-control" placeholder="Enter Driver Number" name="driver_number">
								</div>
									<div class="form-group">
									
									<input class="form-control" placeholder="Enter Bus Timing" name="bus_timing">
								</div>
							<div class="form-group">
									
									<input class="form-control" placeholder="Enter Student Bus Number" name="bus_number">
								</div>								
							
									<div class="form-group">
									<select style="width:200px;height:40px" name="session" required>
										<option>Select Session</option>
									<option value="Morning">Morning</option>
									<option value="evening">evening</option>
									</select>
									
								</div>		
								
								<button type="submit" name="btn-submit" class="btn btn-primary">Submit Button</button>
								<button type="reset" class="btn btn-default">Reset Button</button>
						
								
								
							</div>
						
								
								
								
							
								
									</div>
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
