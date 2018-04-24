<html>
 <head>
  <title>PHP-Test</title>

<link rel="stylesheet" type="text/css" href="style.css">
 </head>
 <body>

<div class="header">
</div>
<div class="content">

 <?php	$id = $_GET["id"];
	session_start();
	$_SESSION['ID'] = $id;

?>
	<h1>Basic demographic stuff</h1>
	<p>Please provide some basic information about you.<br><br></p>

	<p>Please pick your age from the following list:</p>
	<form method="post" action="next.php">
	<input type="radio"  name="age" value="12" />younger than 12<br>
        <input type="radio"  name="age" value="14" />12-17<br>
        <input type="radio"  name="age" value="21" />18-24<br>
        <input type="radio"  name="age" value="30" />25-34<br>
        <input type="radio"  name="age" value="40" />35-44<br>
        <input type="radio"  name="age" value="50" />45-54<br>
        <input type="radio"  name="age" value="60" />55-64<br>
        <input type="radio"  name="age" value="70" />65-74<br>
        <input type="radio"  name="age" value="80" />older than 74<br>

	<p>What is the highest degree or level of school you have completed?</p>

        <input type="radio" name="degree" value="n" />No schooling completed<br>
        <input type="radio" name="degree" value="" />High school without graduation<br>
        <input type="radio" name="degree" value="" />Graduated from high school<br>
        <input type="radio" name="degree" value="" />....?....<br>
        <input type="radio" name="degree" value="" />bachelor's degree or equivalent<br>
        <input type="radio" name="degree" value="" />master's degree or equivalent<br>
	<input type="radio" name="degree" value="" />Doctorate degree or higher<br>

	<p>Please specificy your gender.</p>
	<input type="radio" name="gender" value="f" />female
	<input type="radio" name="gender" value="m" />male
	<input type="radio" name="gender" value="n" />none/other/not applicable

	<p>Level of English</p>

        <input type="radio" name="english" value="b" />Beginner
        <input type="radio" name="english" value="i" />Intermediate
        <input type="radio" name="english" value="f" />Fluent
	<input type="radio" name="english" value="n" />Native Speaker

	<p>If any of the following options apply for you, please check them:</p>
	<input type="checkbox" name="psychology" value="p" />Took psychology or neuroscience courses<br>
	<input type="checkbox" name="thinking" value="t" />Have read "Thinking, fast and slow" by Daniel Kahnemann<br>
	<input type="checkbox" name="lesswrong" value="lw" />Member of the LessWrong community<br>
	<input type="checkbox" name="biases" value="b" />Already familiar with heuristics and biases (e.g. if you what an anchor heuristic or the gambler's fallacy is)<br>
	<input type="checkbox" name="crt" value="c" />Recently took a Cognitive Reflection Test (CRT)<br>

	<p><br></p>


	<input type="submit" value="Submit" />



	</form>

<?php
	$f = fopen("user.txt",w);
	fwrite($f, $id); 
	fclose($f);

?>


</div>
 </body>
</html>
