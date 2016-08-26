<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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



<%--<div>--%>
     <%--<c:out value="All orders (${fn:length(user.orders)})"/>--%>
<%--</div>--%>
<%--<div>--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<th>Order number</th>--%>
            <%--<th>Debtor Order Number</th>--%>
            <%--<th>Item number</th>--%>
            <%--<th>Status</th>--%>
            <%--<th>Print name</th>--%>
            <%--<th>Delivery date</th>--%>
            <%--<th>Last modification</th>--%>
        <%--</tr>--%>
        <%--<c:forEach items="{$user.orders}" var="order">--%>
            <%--<tr>--%>
                <%--<td>--%>
               <%--<c:out value="${order.id}" />--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<c:out value="${order.id}" />--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<c:out value="${order.artical.id}" />--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<c:out value="${order.sapStatus.name}" />--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<c:out value="${order.id}" />--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<c:out value="${order.id}" />--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<c:out value="${order.id}" />--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>

<%--</div>--%>


<%--<ul>--%>
<%--<c:forEach items="${user.roles[0].status}" var="status">--%>
    <%--<li>--%>
     <%--<c:out value="${status.id}" />--%>
    <%--<c:out value="${status.name}" />--%>
    <%--</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
</body>
</html>
