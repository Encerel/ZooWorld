<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
    <%=request.getAttribute("name")%>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<c:forEach var="user" items="${users}">
    <p>${user}</p>
</c:forEach>
</body>
</html>