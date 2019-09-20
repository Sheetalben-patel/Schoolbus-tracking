<?php
error_reporting(E_ALL & ~E_NOTICE);
if($_SERVER['REQUEST_METHOD']=='POST'){
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$dbname = 'schoolbustrackingdb';
$conn = mysqli_connect($dbhost, $dbuser, $dbpass,$dbname);
 
 $phone = $_POST['phone'];
 $busno = $_POST['busno'];
 $locationAddress = $_POST['locationAddress'];
 $date = date('Y-m-d H:i:s');
 
$response = array();


$sql = "INSERT INTO location(phone,busno,locationAddress,date)
VALUES ('$phone','$busno','$locationAddress','$date')";

$retval1 = mysqli_query( $conn,$sql );
$code = "success";
$message = "Your Data Submitted" ;
array_push($response, array("code"=>$code, "message"=>$message));

echo json_encode($response);


}


?>

<html>
<form action="" method="POST">
<input type="text" name="phone">
<input type="text" name="busno">
<input type="text" name="locationAddress">
<input type="submit" name="submit">
</form>
</html>