<?php
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "schoolbustrackingdb";

$con = mysqli_connect($host, $user_name, $password, $db_name);
$str=$_GET['standard'];


 //$sql = "SELECT * FROM student,standard WHERE  student.standard=standard.standard and standard ='".$str."'";
           
$sql="SELECT * FROM student WHERE 	standard ='".$str."'";

$result = mysqli_query($con,$sql);

 


while($row = mysqli_fetch_array($result))
{
echo "<option>" . $row['email'] . "</option>";
 
}

//echo "</select>";
?>

