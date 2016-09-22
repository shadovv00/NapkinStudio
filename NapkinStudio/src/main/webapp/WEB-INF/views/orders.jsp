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
<script src="//code.jquery.com/jquery-3.1.0.min.js">
</script>

<html>
<head>
    <title>Title</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>

    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<a href="<c:url value="/logout"/>"> Logout </a>
<p>You are logged in as ${user.role.name}</p>
<div>
    <section>
        <a id="all" class="ordersFilter" style="margin-left: 15px">All orders (${fn:length(userOrders)})</a>
        <a class="ordersFilter" style="margin-left: 15px">Proof request set up</a>
        <a class="ordersFilter" style="margin-left: 15px">Proof requested</a>
        <a class="ordersFilter" style="margin-left: 15px" >Check by PVI</a>
        <a class="ordersFilter" style="margin-left: 15px">Waiting for approval</a>
        <a class="ordersFilter" style="margin-left: 15px">Approved</a>
        <a class="ordersFilter" style="margin-left: 15px">Rejected</a>
        <a class="ordersFilter" style="margin-left: 15px">Stamps ordered</a>
        <a class="ordersFilter" style="margin-left: 15px">New orders</a>
        <a class="ordersFilter" style="margin-left: 15px">On hold</a>
        <a class="ordersFilter" style="margin-left: 15px">Order delivered</a>
        <a class="ordersFilter" style="margin-left: 15px">Existing art.nr.</a>
    </section>
    <aside>
        <table>
            <tr>
                <th>Order number</th>
                <th>Debtor Order number</th>
                <th>Item number</th>
                <th>Status</th>
                <th>Print name</th>
                <th>Delivery date
                </td>
                <th>Last modification</th>
                <th>Go to orers</th>
                <th>Send email</th>
            </tr>
            <c:forEach items="${userOrders}" var="userOrder"  varStatus="loop">
                <tr>

                    <td>
                            ${userOrder.order.orderId}
                    </td>
                    <td>
                            ${userOrder.order.debItemNum}
                    </td>
                    <td>
                            ${userOrder.order.itemNum}"
                    </td>
                    <td class="status">
                            ${userOrder.order.SAPstatus.statusSAPStatuseRoles[0].status.name}
                    </td>
                    <td>
                            ${userOrder.order.printName}
                    </td>
                    <td>
                            ${userOrder.order.deliveryDate}
                    </td>
                    <td>
                            ${userOrder.order.lastModifiedDate}
                    </td>
                    <td>
                            <%--<c:set var="orderId" value=""></c:set>--%>
                        <a href="<spring:url value="/orders/{orderId}">
                    <spring:param name="orderId" value ="${userOrder.order.orderId}"/>
                </spring:url>

" class="btn btn-primary  custom-width">GO</a>
                    </td>

                    <td>
                            <%--<c:set var="orderId" value=""></c:set>--%>
                 <a href="<spring:url value="orders/sendEmail/{index}">
                    <spring:param name="index" value ="${loop.index}"/>
                </spring:url>

" class="btn btn-primary  custom-width">GO</a>
                    </td>
                </tr>
            </c:forEach>


        </table>
    </aside>
</div>
<a></a>

</body>
</html>
<script>
var statuses=[];
var obj={};
<c:forEach items="${userOrders}" var="userOrder">
    statuses.push('${userOrder.order.SAPstatus.statusSAPStatuseRoles[0].status.name}');
</c:forEach>

for (var i = 0, j = statuses.length; i < j; i++) {
    if (obj[statuses[i]]) {
        obj[statuses[i]]++;
    }
    else {
        obj[statuses[i]] = 1;
    }
}



    $(".ordersFilter").each(function(){

       if(obj[$(this).text()] > 0) {
            console.log($(this).text());
            $(this).append(" (" + obj[$(this).text()] + ")");
        }
    });

    $(".ordersFilter").click(function () {

        $("tr").show();
        console.log(">>>>>>"+$(this).attr('id'));

        if($(this).attr('id') != "all"){
            var str = $(this).text();
            var filterStatus =str.slice(0, str.indexOf("(")).trim();
            console.log(filterStatus);
            $(".status").each(function () {
                var status = $(this).text().trim();
                console.log(filterStatus+" "+status);
                if (filterStatus !== status) {

                    $(this).parent().hide();
                }
            });
        }

    });
</script>
