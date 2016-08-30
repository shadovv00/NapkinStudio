<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: User1
  Date: 20.07.2016
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>

</script>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<a href="<c:url value="/logout"/>"> Logout </a>
<p>You are logged in as ${user.roles[0].name}</p>
<div>

<table>
    <tr>
        <c:out value="All orders (${fn:length(userOrders)})"/>
    </tr>
    <tr>
        <th>Order number</th>
        <th>Debtor  Order number</th>
        <th>Item  number</th>
        <th>Status</th>
        <th>Print name</th>
        <th>Delivery date</td>
        <th>Last modification</th>

    </tr>
    <c:forEach items="${userOrders}" var="userOrder">
        <tr>
            <c:set var="orderId" value="${userOrder.order.orderId}"></c:set>
            <a href="<spring:url value="/orders/${orderId}"/>"/>
            <td>
            <c:out value="${userOrder.order.orderId}" />
            </td>
            <td>
                <c:out value="${userOrder.order.debItemNum}" />
            </td>
            <td>
                <c:out value="${userOrder.order.itemNum}" />
            </td>
            <td>
                <c:out value="${userOrder.order.SAPstatus.statusSAPStatuseRoles[0].status.name}" />
            </td>
            <td>
                <c:out value="${userOrder.order.printName}" />
            </td>
            <td>
                <c:out value="${userOrder.order.deliveryDate}" />
            </td>
            <td>
                <c:out value="${userOrder.order.lastModifiedDate}" />
            </td>
        </tr>
    </c:forEach>


</table>
</div>



</body>
</html>
