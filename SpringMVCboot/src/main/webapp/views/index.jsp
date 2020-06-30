<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Welcome <br>
	
	<!-- This is for adding two numbers -->
	<!-- <form action="add">
		Enter 1st number :  <input type="text"  name="num1"><br>
		Enter 2nd number :  <input type="text"  name="num2"><br>
		       <input type="submit">
		       
		       
		Enter your Id :  <input type="text"  name="aid"><br>
		Enter your Name :  <input type="text"  name="aname"><br>
		       <input type="submit">
	</form> -->
	
	
	<!-- This is for taking two inputs in a modal because java is all about objects. -->
	SAVE RECORD:
	<form action="addAlien" method="post">
		Enter your Id  : <input type="text"  name="aid"><br>
		Enter your Name: <input type="text"  name="aname"><br>
		       <input type="submit">
	</form>
	<hr>
	FETCH SINGLE RECORD:
 	<form action="getAlien" method="get">
 		Enter your id : <input type="text" name="aid"><br>
 		<input type="submit">
 	</form>

	<hr>
	DELETE SINGLE RECORD:
 	<form action="deleteAlien" method="get">
 		Enter your id : <input type="text" name="aid"><br>
 		<input type="submit">
 	</form>
 	
 	<hr>
	FETCH SINGLE RECORD BY NAME:
 	<form action="getAlienByName" method="get">
 		Enter your Name : <input type="text" name="aname"><br>
 		<input type="submit">
 	</form>
 	
</body>
</html>