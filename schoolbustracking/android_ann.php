<?php
 error_reporting(0);
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);

 
 if($_SERVER['REQUEST_METHOD']=='GET') 
  {
 
 
 $sql = "SELECT * FROM annoucement";
 
 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array("id"=>$res['id'],"date"=>$res['date'],"announcement"=>$res['announcement'] ) );
  $re['data']=$result;	
 
 //echo json_encode($result);
 
 echo json_encode($re);
 
 mysqli_close($con);
 
 }
 ?>
 
 