<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){

$name = $_POST['name'];
$pbsher = $_POST['publisher'];
 $image = $_POST['image'];

 require_once('dbConnect.php');

 $sql ="SELECT id FROM codetr ORDER BY id ASC";

 $res = mysqli_query($con,$sql);

 $id = 0;

 while($row = mysqli_fetch_array($res)){
 $id = $row['id'];
 }

 $path = "uploads/$id.png";

 $actualpath = "http://192.168.30.23/web1/webservice/codetr/$path";

 $sql = "INSERT INTO codetr (name,publisher,image) VALUES ('$name','$pbsher','$actualpath')";

 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "Succses Add...";
 }

 mysqli_close($con);
 }else{
 echo "Error";
 }

?>
