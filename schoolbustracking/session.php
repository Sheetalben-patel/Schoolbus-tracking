<?php
session_start();

if(!isset($_SESSION['username']) || (trim($_SESSION['username']) == '')){
	header('location:index.php');
	exit();
}

$session_id = $_SESSION['id']; 
$session_id = $_SESSION['username']; 

?>