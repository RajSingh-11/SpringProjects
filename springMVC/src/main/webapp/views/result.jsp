<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- this is using jstl -->
	 <%-- Result is: ${num3} --%>
	
	<!-- Using Model object -->
	<%-- Result is: ${alien} --%>
	
	
	<!-- Using ModelAttribute, the name is same as the model attribute annotation we have assigned in method parameter -->
		Result is: ${a1}
		Welcome Back ${name}
</body>
</html>