<?php
 error_reporting(0);
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);

 
 
 
// $sql = "SELECT * FROM hoarding WHERE email = '$email' AND password='$password'";
$sql="SELECT DISTINCT SUM(present),fname FROM  attendence WHERE present = 1 AND date = '2017-03-19'";
 
$result = mysqli_query($con,$sql);

while($row=mysqli_fetch_array($result))
{
$present=$row['SUM(present)'];
$fname=$row['fname'];

 
 ?>
 <table>
 <tr>
 <td>Name</td>
 <td>Presend</td>
 </tr>
 <?php 
// $sql = "SELECT * FROM hoarding WHERE email = '$email' AND password='$password'";
$sql1="SELECT DISTINCT present,fname FROM  attendence WHERE present = 1 AND date = '2017-03-19'";
 
$result1 = mysqli_query($con,$sql1);

while($row1=mysqli_fetch_array($result1))
{
$present1=$row1['present'];
$fname=$row1['fname'];

 
 ?>
 <tr>
 <td><?php echo $present?></td>
<td><?php echo $fname?></td>
 
 
 </tr>
 <?php
 }
 }
 ?>
 </table>
 