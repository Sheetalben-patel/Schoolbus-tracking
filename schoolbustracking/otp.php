<?php
        include("connect-db.php");
        include("EncryptDecrypt.php");
        
//    if (isset($_POST['register_id']) || isset($_POST['edtop']) ) {
if(true){ 
//    echo 'ggg';
    $parent_id =$_POST['register_id'];
    $otp = $_POST['edtotp'];
//   echo $reg_id;
  //  $reg_id="1";
    //$otp="753311";
  
    $sel = "SELECT * FROM `otp` WHERE parent_id = '" . $parent_id . "' and otp='".$otp."' ";
    $result = mysqli_query($conn, $sel);
//    echo $sel;
    if (mysqli_num_rows($result) > 0) {
//        echo "success=1";
        $response["success"] = "1";
        $response["OTP"]="OTP Conform successfully.";
    } else {
//        echo "success=0";
        $response["success"] = "0";
        $response["OTP"]="Plz enter VALID OTP CODE";
    }
} 
echo json_encode($response);
 ?>

