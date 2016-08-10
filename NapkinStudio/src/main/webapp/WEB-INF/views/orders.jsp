<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User1
  Date: 20.07.2016
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/logout"/>"> Logout </a>
<p>You are logged in as ${user.roles[0].name}</p>

<ul>
<c:forEach items="${user.roles[0].status}" var="status">
    <li>
     <c:out value="${status.id}" />
    <c:out value="${status.name}" />
    </li>
</c:forEach>
</ul>
</body>
</html>
