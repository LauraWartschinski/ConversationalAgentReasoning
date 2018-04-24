	<html>
	<head>
	<title>Human Reasoning - Thank you</title>
<link rel="stylesheet" type="text/css" href="style.css">
	</head>


	<?php
	session_start();
	
	$id = $_SESSION['id'];
	
	$mail = "";
	$mailwrong = "";
	$no = false;
	
	if(isset($_POST['txtarea'])){
		
		$_SESSION['comment'] = true;
		$comment =  htmlspecialchars($_POST['txtarea']);
		$name = $id . ".6.txt";
		$content =  date("d.m. h:i:s") . " ID " . $_SESSION['id'] . " " . $comment . "\n";
		$f1 = fopen($name, "w") or die("Unable to open file to write!");
		fwrite($f1, $content);
		fclose($f1);
		
		
	}
	
	
	if(isset($_POST['mail']))	 {
		if(filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)){
			$_SESSION['email'] = true;	
			$_SESSION['gaveemail'] = true;
			$mail =  htmlspecialchars($_POST['email']);
			$name = $id . ".5.txt";
			$content =  date("d.m. h:i:s") . " ID " . $_SESSION['id'] . " " . $mail . "\n";
			$f2 = fopen($name, "w") or die("Unable to open file to write!");
			fwrite($f2, $content);
			fclose($f2);
		}
		else {
			$mailwrong = "It seems like you did not enter a valid email.";
		}
	}
	if(isset($_POST['no'])){
		$_SESSION['email'] = true;
		$_SESSION['refusedemail'] = true;
	}
	
	
	?>
	<body>
	

	<div class="content">


	<p><b>Thank you!</b><br><br>You have completed the second test!
	<?php if( $_SESSION['correct2'] >  $_SESSION['correct1']){
		echo "<br>In the first test, you answered " .  $_SESSION['correct1'] . " questions correctly.
		But in the second, you solved " .  $_SESSION['correct2'] ." right!<br>";
		echo "<strong>Congratulations! You did improve your reasoning skills!</strong>";
	}
	else {
		echo "You answered " .  $_SESSION['correct2'] . " questions correctly.";
	}
	?>
	 
	<?php
	
	if(true == $_SESSION['clickworkers']){
		$confirmation = 370000 + $_SESSION['id'];
		echo "<br><br>You confirmation code for clickworker is " . $confirmation . ".<br> Please copy this code and past it into the form for your clickworker job, so you can get paid.";
		
	}
	
	
	if(false == $_SESSION['email'] and false == $_SESSION['clickworkers']){
			?>
		<p><br>
		One last thing:<br>
		You have already answered one test, talked to the bot and answered a second test. The study could be even <em>better</em> if we could ask you again
		in one week to fill out a third test - the same kind you already did twice. Afterwards, you will see all the right and wrong answers to the questions
		to see what you could solve and were you made mistakes, and an explanation for every question. Does that sound interesting to you?<br>
		If so, then we can send you an email with the link to your follow-up test in seven days if you provide your mail address here. We will not use your mail address for
		any other purpose and we will not save it after the study is executed.
		</p>
		<form name="theform" method="post" action="">
		<input  type="text" name="email" maxlength="80" size="30">
		  <input type="submit" name='mail'  value="Notify me in a week!">
		  <input type="submit" name='no' value="No, thank you"><span class="error"><br><?PHP echo $mailwrong;?></span>
		  </form>
		

		<?php
	}
	
	
	if(true == $_SESSION['gaveemail']){
	?>
	<p> Thank you very much! You are <strong>awesome</strong>! You will be notified about the third test in a week.</p>
	
	<?php
	}
	
	if(true == $_SESSION['refusedemail']){
	?>
	<p> We will not notify you about the third part. Thank you for your help!<br><br></p>
	
	<?php
	}
	
	if(false == $_SESSION['comment']){
	
		?>
		<p>If you want to, you can also leave a comment for the creators of this survey.</p>
		<form method="post" action="">
		<textarea name="txtarea" cols="80"></textarea><br><br>
		<input type="submit" value="Submit"/>
 
		<?php
	}
	
	if(true == $_SESSION['comment']){
	
	?>
	
	<p>Your comment will be delivered to the creators immediately.</p>
	<?php
	}
	?>
	<br><br>
	And that's it. We hope you take something with you. Again, thank you!
	
	</div>
	</body>
	</html>
