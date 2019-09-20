<?php

require_once 'dbconfig.php';

class USER
{	

	private $conn;
	
	public function __construct()
	{
		$database = new Database();
		$db = $database->dbConnection();
		$this->conn = $db;
    }
	
	public function runQuery($sql)
	{
		$stmt = $this->conn->prepare($sql);
		return $stmt;
	}
	
	public function lasdID()
	{
		$stmt = $this->conn->lastInsertId();
		return $stmt;
	}
	
	public function register($fname,$email,$standard,$password,$parents_name,$phone,$address,$blood_group
	,$driver_name,$driver_number,$bus_timing,$bus_number,$session,$studentId)
	{
		try
		{							
			//$password = md5($upass);
			$stmt = $this->conn->prepare("INSERT INTO student(fname,email,standard,password,parents_name,phone,address,blood_group,driver_name,driver_number,bus_timing,bus_number,session,studentId) 
 VALUES(:fname,:email,:standard,:password,:parents_name,:phone,:address,:blood_group,:driver_name,:driver_number,:bus_timing,:bus_number,:session,:studentId)");
			$stmt->bindparam(":fname",$fname);
			$stmt->bindparam(":email",$email);
			$stmt->bindparam(":standard",$standard);
			$stmt->bindparam(":password",$password);
			$stmt->bindparam(":parents_name",$parents_name);
			$stmt->bindparam(":phone",$phone);
			$stmt->bindparam(":address",$address);
			$stmt->bindparam(":blood_group",$blood_group);
			$stmt->bindparam(":driver_name",$driver_name);
			$stmt->bindparam(":driver_number",$driver_number);
			$stmt->bindparam(":bus_timing",$bus_timing);
			$stmt->bindparam(":bus_number",$bus_number);
				$stmt->bindparam(":session",$session);
			$stmt->bindparam(":studentId",$studentId);
		
			$stmt->execute();	
			return $stmt;
		}
		catch(PDOException $ex)
		{
			echo $ex->getMessage();
		}
	}
	
	
	function send_mail($email,$message,$subject)
	{						
		require_once('mailer/class.phpmailer.php');
		$mail = new PHPMailer();
		$mail->IsSMTP(true); 
		$mail->SMTPDebug  = 1;                     
		$mail->SMTPAuth   = true;                  
		$mail->SMTPSecure = "ssl";                 
		$mail->Host       = "smtp.gmail.com";      
		$mail->Port       = 465;             
		$mail->AddAddress($email);
		$mail->Username="arvinitesh@gmail.com";  
		$mail->Password="niteshvbarot";            
		$mail->SetFrom('arvinitesh@gmail.com','Coding Cage');
		$mail->AddReplyTo("arvinitesh@gmail.com","Coding Cage");
		$mail->Subject    = $subject;
		$mail->MsgHTML($message);
		$mail->Send();
	}	
}
?>