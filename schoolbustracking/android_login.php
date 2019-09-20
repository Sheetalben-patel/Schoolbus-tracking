<?php
 error_reporting(E_ALL & ~E_NOTICE);
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 
 
 $sql = "SELECT * FROM `parents` WHERE email = '$email' AND password='$password'";
 
$result = mysqli_query($con,$sql)or die(mysqli_error($con));
 
$check = mysqli_fetch_array($result);
 
 if(isset($check)){
 echo 'success';
 }else{
 echo 'failure';
 }
 }
 
 ?>
 
  
 <html>
 
 <body>
 <form action="" method="POST">
 
 <input type="text" name="email" >
 <input type="password" name="password">
 <input type="submit" name="submit">
 </form>
 
 </body>
 
 </html>