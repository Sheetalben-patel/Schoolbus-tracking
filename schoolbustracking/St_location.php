<?php
 error_reporting(0);
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);

 
 if($_SERVER['REQUEST_METHOD']=='GET') 
  {
  $phone=$_GET['phone'];
  
 
// $sql = "SELECT locationAddress,date FROM location WHERE phone='".$phone."'  ORDER BY `date` DESC";\
$sql="select locationAddress,date,busno from location WHERE phone='".$phone."' ORDER BY `date` DESC";
 
 $r = mysqli_query($con,$sql);
 
 while($res = mysqli_fetch_array($r))
 {
 
 $response = array();
 
 array_push($response,array("busno"=>$res['busno'],
 "locationAddress"=>$res['locationAddress'],"date"=>$res['date'] ) );
 $re['data']=$response;	

 
 //echo json_encode($result);
 
echo json_encode($re);

 
 mysqli_close($con);
 }
 }
 ?>