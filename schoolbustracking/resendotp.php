<?php
include("connect-db.php");
include("EncryptDecrypt.php");
echo "hello";
//if (isset($_POST['register_id'])) {
if (true) {

    $parent_id=$_POST['parent_id'];
//    $parent_id = "1";
    echo $parent_id;
    $sel = "SELECT * FROM `parents` WHERE parent_id= '" . $parent_id . "' AND is_active=0";
//    $mobile = ["contact_no"];
    echo $sel;
    $result = mysqli_query($conn, $sel);
    if (mysqli_num_rows($result) > 0) {
        echo "success";
        $response["success"] = "1";
        $otp = mt_rand(100000, 999999);
        $Resend = "update `otp` SET `otp`='$otp' where `parent_id`='$parent_id'";
        $result_OTP = mysqli_query($conn, $Resend);
        echo "<br/>" . $Resend;
        if (mysqli_affected_rows($conn)) {
            echo "success=1";
//            $response["success"] = "1";
//            $response["OTP id resend!! please wait"];
        } else {
//            echo "success=0";
//            $response["success"] = "0";
//            $response["sorry an occur please try again later"];
        }
    } else {

        $response["success"] = "0";
    }
}

json_encode($response);
?>