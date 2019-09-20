<form method="POST">
<input type="text" name="fname">
  <select name="month">
  <option value=''>Select Month</option>
   <option value='jan'>Jan</option>
     <option value='feb'>Feb</option>
	   <option value='march'>March</option>
   </select>
   <input type="submit" name="submit"/>


</FORM>
<?php
 error_reporting(0);
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);

 
 if(isset($_POST['submit'])) 
  {
  $fname=$_POST['fname'];
  $month=$_POST['month'];
  //echo $fname;
// $sql = "SELECT * FROM hoarding WHERE email = '$email' AND password='$password'";
$sql="SELECT DISTINCT SUM(present),fname FROM  attendence WHERE fname='".$fname."' AND month='".$month."'";
 
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
$sql1="SELECT DISTINCT present,fname FROM  attendence WHERE fname='".$fname."' AND month='".$month."'";
 
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
 }
 ?>
 </table>
 