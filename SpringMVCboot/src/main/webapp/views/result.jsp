<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	
	
	<!-- Using ModelAttribute, the name is same as the modelattribute annotaion we have assigned in method param -->
		Result is: ${a1}
		 ${name}
		 ${result}
</body>
</html>