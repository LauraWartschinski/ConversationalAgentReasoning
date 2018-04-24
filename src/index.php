<html>
 <head>
  <title>PHP-Test</title>
 </head>
 <body>
<p>The page loaded correctly, again.</p>

<form method="post" action="index.php">
    <input type="text" name="userinput">
    <input type="submit" value="click" name="submit"> 
</form>




<?php
function display()
{
 	$input = $_POST["userinput"];
	
			
	$PORT = 20222; //the port on which we are connecting to the "remote" machine
	$HOST = "localhost"; //the ip of the remote machine (in this case it's the same machine)

	$sock = socket_create(AF_INET, SOCK_STREAM, 0) //Creating a TCP socket
			or die("error: could not create socket\n");

	$succ = socket_connect($sock, $HOST, $PORT) //Connecting to to server using that socket
			or die("error: could not connect to host\n");

	
	sendToJava($input, $sock);
	#receiveFromJava($sock);
}
if(isset($_POST['submit']))
{
   display();
} 


?>


<?php

function sendToJava($input, $sock) {

socket_write($sock, $input . "\n", strlen($input) + 1) //Writing the text to the socket
		or die("error: failed to write to socket\n");
		
} 

function receiveFromJava($sock){
	
$reply = socket_read($sock, 10000, PHP_NORMAL_READ) //Reading the reply from socket
or die("error: failed to read from socket\n");

echo $reply;
}





?>
 </body>
</html>
