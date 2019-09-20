<?php
include 'connect-db.php';
//error_reporting(E^ALL);
error_reporting(E_ERROR | E_WARNING | E_PARSE);

$server = "localhost";
$user = "root";
$pass = "";
$db = "schoolbustrackingdb";
$conn = mysqli_connect($server, $user, $pass, $db);
if (!$conn) {
    die("connection failed:" . mysqli_connect_error());
} else {
 //  echo "connected successfully";
}
include("EncryptDecrypt.php");


$flag = TRUE;
mysqli_autocommit($conn, FALSE);
//echo "demo";
//echo decryptIt("FylvGt6Yyb6n+zTXcJHwjBawOY/w3QSZxF7rdUJLqhA=");
//if(isset($_POST['name']) || isset($_POST['contactno'])|| isset($_POST['email']) || isset($_POST['password']) || isset($_POST['houseno']) || isset($_POST['societyname']) || isset($_POST['landmark']) || isset($_POST['area']) || isset($_POST['pincode']) || isset($_POST['city']) ) {

if (true) {
    $name = encryptIt($_POST['name']);
//    $name = "aaa";
    $mobile = encryptIt($_POST['contactno']);
//    $mobile ="777";
    $mobileOTP = $_POST['contactno'];
//    $mobileOTP = $mobile;
    $email = $_POST['email'];
//    $email="a@b.c";
    $password = encryptIt($_POST['password']);
//    $password="aaa";
    $houseno = $_POST['houseno'];
//    $houseno="1";
    $societyname = $_POST['societyname'];
//    $societyname="avc";
    $landmark = $_POST['landmark'];
//    $landmark ="landmark";
    $area = $_POST['area'];
//    $area="area";
    $pincode = $_POST['pincode'];
//    $pincode="123455";
    $city = $_POST['city'];
//    $city="aaa";


    $otp = mt_rand(100000, 999999);
    $sel = "SELECT * FROM `parents` WHERE contactno= '" . $mobile . "' AND is_active=1";
//    echo $sel;
    $result = mysqli_query($conn,$sel);
    if (mysqli_num_rows($result) > 0) {
//        echo "Exist";
        $response["success"] = "0";
        $response["msg"] = "Mobile Number Already Exist.";
    } else {
//echo "Not Exist";
        $query = "INSERT INTO `parents`(`name`, `contactno`, `email`, `password`,`houseno`, `societyname`, `landmark`, `area`, `pincode`, `city`) VALUES (?,?,?,?,?,?,?,?,?,?)";

        $stmt = mysqli_prepare($conn, $query);

        if (!$stmt) {
            $flag = FALSE;
            //die('Got server error1' . mysqli_error($conn));
        }


        $bind = mysqli_stmt_bind_param($stmt, "ssssssssss", $name, $mobile, $email, $password, $houseno, $societyname, $landmark, $area, $pincode, $city);

        if (!$bind) {
            $flag = FALSE;
            //die('Got server error2' . mysqli_error($conn));
        }

        $exec = mysqli_stmt_execute($stmt);
        if (!$exec) {
            $flag = FALSE;
            //die('Got server error3' . mysqli_error($conn));
        }

        $parent_id = mysqli_insert_id($conn);

        $query_otp = "INSERT INTO `otp`(`parent_id`,`otp`) VALUES (?,?)";

        $stmt_otp = mysqli_prepare($conn, $query_otp);

        if (!$stmt_otp) {
            $flag = FALSE;
            //die('Got server error' . mysqli_error($conn));
        }

        $bind_otp = mysqli_stmt_bind_param($stmt_otp, "ss", $parent_id, $otp);
        if (!$bind_otp) {
            $flag = FALSE;
            //die('Got server error' . mysqli_error($conn));
        } else {
            
        }

        $exec_otp = mysqli_stmt_execute($stmt_otp);
        if (!$exec_otp) {
            $flag = FALSE;
           // die('Got server error' . mysqli_error($conn));
        }

        if (!$flag) {
            mysqli_rollback($conn);
        } else {
            mysqli_commit($conn);
            $response = array();
            $response["success"] = "1";
            $response['parent_id'] = $parent_id;
            $response["msg"] = "registration Successfully..";
        }
    }
} else {
    $response["success"] = "0";
}
echo json_encode($response);
