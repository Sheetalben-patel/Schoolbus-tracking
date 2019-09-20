<?php
 error_reporting(0);
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);

 
 if($_SERVER['REQUEST_METHOD']=='GET') 
  {
  $fname=$_GET['fname'];
  $month=$_GET['month'];
  //echo $fname;
// $sql = "SELECT * FROM hoarding WHERE email = '$email' AND password='$password'";
$sql="SELECT DISTINCT SUM(present),fname FROM  attendence WHERE fname='".$fname."' AND month='".$month."'";
 
$result = mysqli_query($con,$sql);
$response=array();
while($row=mysqli_fetch_array($result))
{
$present=$row['SUM(present)'];
$fname=$row['fname'];


// $sql = "SELECT * FROM hoarding WHERE email = '$email' AND password='$password'";
$sql1="SELECT DISTINCT present,fname FROM  attendence WHERE fname='".$fname."' AND month='".$month."'";
 
$result1 = mysqli_query($con,$sql1);

while($row1=mysqli_fetch_array($result1))
{
$present1=$row1['present'];
$fname=$row1['fname'];

 array_push($response,array("present"=>$present,"fname"=>$fname))
 ?>

 <?php
 }
 }
 echo json_encode($response);
 }
 ?>
 
 