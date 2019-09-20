<?php
error_reporting(E_ALL & ~E_NOTICE);
include ("connect-db.php");
include("EncryptDecrypt.php");
// array for JSON response
//echo decryptIt('nmJlYI9M3qXXfMBP75rfDTf9c9s0Jq8ZiEdlRCxtmB4=');

$response = array();
if (isset($_POST['username']) || isset($_POST['password'])) {
//if (true) {
  
    $username = encryptIt($_POST['username']);
    $password = encryptIt($_POST['password']);
  
//    echo $username;
    
//    $username = encryptIt("7405396755");
//    $password = encryptIt("aaaaaa");
    $SQL="SELECT * FROM `parents` WHERE contactno= '$username'  and password='$password'";
 
    $result = mysqli_query($conn,$SQL);
 
    if (mysqli_num_rows($result) > 0) {
        if ($row = mysqli_fetch_assoc($result)) {

            if ($row['is_active'] == 1) {

                $response["success"] = "1";
                $response["msg"] = "ok";
                $response["contactno"] = decryptIt($row['contactno']);
                $response["password"] = $row['password'];
                           
                $response["parent_id"] = $row["parent_id"];
                $response["OTP"] = "TRUE";
// echoing JSON response
            } else {
                $response["parent_id"] = $row['parent_id'];
                $response["OTP"] = "FALSE";
                $response["success"] = "2";
                $response["contactno"] = decryptIt($row['contactno']);
                $response["password"] = decryptIt($row['password']);
                $response["OTP"] = "OTP CONFORM FIRST";
            }
        }
    } else {
        $response["success"] = "0";
        $response["msg"] = "0";
        //        $response["std_id"] = "0";
        $response["registration_id"] = "0";
        $response["OTP"] = "false";
    }
}
//$response["success"]="0";
echo json_encode($response);
?>    

