<?php
 require_once('dbConnect.php');
 
 $total = mysqli_num_rows(mysqli_query($con, "SELECT id from codetr "));

 $sql = "SELECT * from codetr ";

 $result = mysqli_query($con,$sql);
 $res = array();

 while($row = mysqli_fetch_array($result)){
 array_push($res, array(
 "name"=>$row['name'],
 "publisher"=>$row['publisher'],
 "image"=>$row['image'])
 );
 }
 echo json_encode(array('result'=>$res));
?>
