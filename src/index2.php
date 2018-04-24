<?php
session_start ();


$id = $_GET["id"];

function loginForm() {
	echo '
    <div id="loginform">
    <form action="index2.php" method="post">
        <p>Please enter your name to continue:</p>
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" />
        <input type="submit" name="enter" id="enter" value="Enter" />
    </form>
    </div>
    ';
}

if (isset ( $_POST ['enter'] )) {
	if ($_POST ['name'] != "") {
		$_SESSION ['name'] = stripslashes ( htmlspecialchars ( $_POST ['name'] ) );
		$fp = fopen ( $log, 'a' );
		fwrite ( $fp, "<div class='msgln'><i>User " . $_SESSION ['name'] . " has joined the chat session.</i><br></div>" );
		fclose ( $fp );
		chmod($log, 0777);
	} else {
		echo '<span class="error">Please type in a name</span>';
	}
}

if (isset ( $_GET ['logout'] )) {
	
	// Simple exit message
	$fp = fopen ( $log, 'a' );
	fwrite ( $fp, "<div class='msgln'><i>User " . $_SESSION ['name'] . " has left the chat session.</i><br></div>" );
	fclose ( $fp );
	
	session_destroy ();
	header ( "Location: index2.php" ); // Redirect the user
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="chatstyle.css">
 </head>
<title>Chat with Liza E.</title>
</head>
<body>
	<?php


		$_SESSION['name'] = $id;
		$log = $id . ".3.html";
		$_SESSION['id'] = $log;
		
		`java Test2`;
	
	if (! isset ( $_SESSION ['name'] )) {

		
//		loginForm ();
	} else {
		?>
<div id="wrapper">
		<div id="menu">
			<div style="float: left; height: 70px; width: 70px; background-color: #bbbbbb; border: 1px solid black;">
			<p style="font-size: 26px; font-family: 'Courier New', Courier, monospace; padding-top: 18px; font-weight: bold;">:-)</p>
			</div>	
			<div style="float: right; height: 70px; width: 470px; padding-left: 10px;">
			<p class="welcome">
				Welcome to the chat. You are talking to Liza E., a software that was programmed to teach reasoning skills to humans. Enjoy your time!</b>
			</p>
			</div>
		<!--
			<p class="logout">
				<a id="exit" href="#">Exit Chat</a>
			</p>-->
			<div style="clear: both"></div>
		</div>
		<div id="chatbox"><?php
		if (file_exists ( $log ) && filesize ( $log ) > 0) {
			$handle = fopen ( $log, "r" );
			$contents = fread ( $handle, filesize ( $log ) );
			fclose ( $handle );
			echo $contents;
		}
		?></div>

		<form name="message" action="">
			<input name="usermsg" type="text" id="usermsg" size="63" /> <input
				name="submitmsg" type="submit" id="submitmsg" value="Say it" />
		</form>
	</div>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
	<script type="text/javascript">
// jQuery Document
$(document).ready(function(){
});

//jQuery Document
$(document).ready(function(){
	//If user wants to end session
	$("#exit").click(function(){
		var exit = confirm("Are you sure you want to end the session?");
		if(exit==true){window.location = 'index2.php?logout=true';}		
	});
});

//If user submits the form
$("#submitmsg").click(function(){
		var clientmsg = $("#usermsg").val();
		$.post("post.php", {text: clientmsg});	
		$("#usermsg").attr("value", "");
		loadLog;
	return false;
});

function loadLog(){		
	var oldscrollHeight = $("#chatbox").attr("scrollHeight") - 20; //Scroll height before the request
		var logging =  "<?php echo $log; ?>" ;
	$.ajax({
		url: logging,
		cache: false,
		success: function(html){		
			$("#chatbox").html(html); //Insert chat log into the #chatbox div	
			
			//Auto-scroll			
			var newscrollHeight = $("#chatbox").attr("scrollHeight") - 20; //Scroll height after the request
			if(newscrollHeight > oldscrollHeight){
				$("#chatbox").animate({ scrollTop: newscrollHeight }, 'normal'); //Autoscroll to bottom of div
			}				
	  	},
	});
}

setInterval (loadLog, 100);
</script>
<?php
	}
	?>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
	<script type="text/javascript">
</script>
</body>
</html>