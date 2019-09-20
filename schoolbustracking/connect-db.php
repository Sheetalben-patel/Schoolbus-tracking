<?php
//error_reporting(E^ALL);
$server = "localhost";
$user = "root";
$pass = "";
$db = "schoolbustrackingdb";
$conn = mysqli_connect($server, $user, $pass, $db);

if (!$conn) {
  
    die("connection failed:" . mysql_connect_error());
} else {
   //echo "connected successfully";
}