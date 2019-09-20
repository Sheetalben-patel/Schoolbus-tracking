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
  $month=feb;
 
 $sql = "SELECT present,fname FROM attendence WHERE fname='".$fname."' AND month='".$month."'";
 
 $r = mysqli_query($con,$sql);
 
while($res = mysqli_fetch_array($r))
{
 
 $result = array();
 
 array_push($result,array(
 "present"=>$res['present'] ) );
 
 //echo json_encode($result);
 
 echo json_encode(array("result"=>$result));
 }
 mysqli_close($con);
 
 }
 ?>
 
 