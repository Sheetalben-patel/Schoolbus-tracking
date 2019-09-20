<?php
 
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 
 
 $sql = "SELECT * FROM student WHERE email = '$email' AND password='$password'";
 
$result = mysqli_query($con,$sql);

 
 if(isset($result)){
 echo 'success';
 }else{
 echo 'failure';
 }
 }
 
 ?>
 
 