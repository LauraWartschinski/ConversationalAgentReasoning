<?php
session_start();
if(isset($_SESSION['name'])){
    $text = $_POST['text'];
    $log = $_SESSION['id'];
     
    $fp = fopen($log, 'a');
    fwrite($fp, "<div class='msgln'><b>You</b>: ".stripslashes(htmlspecialchars($text))." <br></div>");
    fclose($fp);
	chmod($log, 0777);
}
?>