<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: User1
  Date: 20.07.2016
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Calendar ;" %>--%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.Date, java.util.Calendar"
         errorPage="" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
         import="java.util.Date, java.util.Calendar, org.apache.commons.lang.time.DateUtils" errorPage="" %>
<%--<%@ page import="java.util.Date, java.util.Calendar" %>--%>
<c:set var="today" scope="session" value="<%=new java.util.Date()%>"/>
<c:set var="yesterday" scope="session" value="<%=new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000)%>"/>


<%--<%!--%>
<%--Calendar c1 = Calendar.getInstance(); // today--%>
<%--Calendar c2 = Calendar.getInstance();--%>
<%--c2.add(Calendar.DAY_OF_YEAR, -1); // yesterday--%>
<%--c2.setTime(getDateFromLine(line)); // your date--%>

<%--if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)--%>
<%--&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {}--%>
<%--%>--%>


<script src="//code.jquery.com/jquery-3.1.0.min.js">
</script>

<html>
<head>
    <title>Title</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
    <%--     <script src="<c:url value='/static/js/jquery-latest.js' />"></script> --%>
    <script src="<c:url value='/static/js/jquery.tablesorter.js' />"></script>

    <link href="<c:url value='/static/themes/blue/style.css' />" rel="stylesheet" type="text/css"
          media="print, projection, screen"></link>

    <%--<style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>--%>
</head>
<body>

<%--<c:if test="${--%>
                            <%--(theOrder.SAPstatus.id==7&&user.roles[0].id==2)--%>
                 <%--}">--%>
    <%--ChanwfwefgeStatus--%>
<%--</c:if>--%>
<%--<c:if test="true">--%>
    <%--ChangeStatus--%>
<%--</c:if>--%>

<section class="col-md-12 logo-area" style="background-color: #f9f9f9; background-position-x: 10%">
    <a href="<c:url value="/logout"/>" style="float: right; margin: 20px 0">&nbsp;uitloggen </a>
    <p style="float: right; margin: 20px 0">${user.role.name} - </p>
</section>
<section class="subheader col-md-12" style="background-color: white">
    <h2 style="margin-left: 9%">Orderoverzicht</h2>
    <h5 style="color: #29abe2; margin-left: 9%; width: 100px">Alle orders</h5>
</section>
<section class="main-wrapper">
    <div class="col-md-2 order-status-wrapper">
        <ul class="status-filter-bar">
            <li>
                <a id="all" class="ordersFilter" >All orders (${fn:length(userOrders)})</a>
                <br>
                <ul class="status-filter-bar">
                    <li>
                        <a id="in-progress" class="ordersFilter"  data-filter-column="3"
                           data-filter-text="in progress">In progress</a>
                        <br>
                        <ul class="status-filter-bar">
                            <c:if test="${user.role.id == 2}">
                                <br>
                                <li>
                                    <a class="ordersFilter"  data-filter-column="3"
                                       data-filter-text="1">Proof request set up</a>
                                </li>
                            </c:if>
                            <br>
                            <li>
                                <a class="ordersFilter"  data-filter-column="3"
                                   data-filter-text="2">Proof
                                    requested</a>
                            </li>
                            <c:if test="${user.role.id == 2 || user.role.id == 4}">
                                <br>
                                <li>

                                    <a class="ordersFilter" data-filter-column="3"
                                       data-filter-text="3">Check
                                        by PVI</a>
                                </li>
                            </c:if>


                            <br>
                            <li>

                                <a class="ordersFilter"  data-filter-column="3"
                                   data-filter-text="4">Waiting for approval</a>
                            </li>

                            <br>
                            <li>
                                <a class="ordersFilter"  data-filter-column="3"
                                   data-filter-text="5">Approved</a>

                            </li>
                            <br>
                            <li>
                                <a class="ordersFilter"  data-filter-column="3"
                                   data-filter-text="6">Rejected</a>
                            </li>
                            <c:if test="${user.role.id == 2}">
                                <br>
                                <li>
                                    <a class="ordersFilter"  data-filter-column="3"
                                       data-filter-text="7">Stamps
                                        ordered</a>
                                </li>
                            </c:if>
                        </ul>
                    </li>
                    <c:if test="${user.role.id == 2 || user.role.id == 1}">
                        <br>
                        <li>
                            <a id="planned" class="ordersFilter"  data-filter-column="3"
                               data-filter-text="planned">Planned</a>
                            <br>
                            <ul>
                                <br>
                                <li>
                                    <a class="ordersFilter"  data-filter-column="3"
                                       data-filter-text="8">New
                                        orders</a>
                                </li>
                                <br>
                                <li>
                                    <a class="ordersFilter"  data-filter-column="3"
                                       data-filter-text="11">Existing
                                        art.nr.</a>
                                </li>
                            </ul>
                        </li>
                    </c:if>


                </ul>
            </li>
            <c:if test="${user.role.id == 2 || user.role.id == 1}">
                <%--<br>--%>
                <li>
                    <a id="delivered" class="ordersFilter"  data-filter-column="3"
                       data-filter-text="10">Order
                        delivered</a>
                </li>
            </c:if>
            <c:if test="${user.role.id == 4}">
                <br>
                <li>
                    <a id="stamps-ordered" class="ordersFilter"  data-filter-column="3"
                       data-filter-text="7">Stamps
                        ordered</a>
                </li>
            </c:if>
            <br>
            <li>
                <a id="on-hold" class="ordersFilter"  data-filter-column="3"
                   data-filter-text="9">On
                    hold</a>
            </li>


        </ul>
    </div>
    <div class="col-md-10">
        <table class="tablesorter" id="orderstable">
            <thead>
            <tr>
                <th>Order number</th>
                <%--<th>Unread comments</th>--%>
                <th>Debtor Order number</th>
                <th>Item number</th>
                <th>Status</th>
                <th>Print name</th>
                <th>Delivery date</th>
                <th>Last modification</th>
                <c:if test="${(theOrder.SAPstatus.id==7&&user.roles.id==2)||
							(theOrder.SAPstatus.id==5&&user.roles.id==4&&theOrder.processId==5)}">
                    <th>ChangeStatus</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userOrders}" var="userOrder" varStatus="loop">
                <%--<a href="<spring:url value="/orders/{orderId}">
                                <spring:param name="orderId" value ="${userOrder.order.orderId}"/>
                                </spring:url>">--%>
                <%--<tr href="<spring:url value="/orders/{orderId}">--%>
                <%--<spring:param name="orderId" value ="${userOrder.order.orderId}"/>--%>
                <%--</spring:url>">--%>
                <tr class='clickable-row' data-href="<c:url value='/orders/${userOrder.order.orderId}' />">
                    <td class='clickable-cell orderId'>${userOrder.order.orderId}
                        <c:if test="${userOrder.order.unreadCommentsCount != 0}">
                            <img src="<c:url value='/static/image/new_comments.png' />"  width="15" height="12">
                            <b style="color: #29abe2">
                                    ${userOrder.order.unreadCommentsCount}
                            </b>
                        </c:if>
                    </td>
                    <%--<td class='clickable-cell'>--%>
                        <%--<c:if test="${userOrder.order.unreadCommentsCount != 0}">--%>
                            <%--${userOrder.order.unreadCommentsCount}--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                    <td class='clickable-cell' >${userOrder.order.debItemNum}
                    </td>
                    <td class='clickable-cell'>${userOrder.order.itemNum}</td>
                    <td class="status clickable-cell"
                        data-status="${userOrder.order.SAPstatus.statusSAPStatuseRoles[0].status.id}">
                            ${userOrder.order.SAPstatus.statusSAPStatuseRoles[0].status.name}
                        <c:if test="${(userOrder.order.version>1.0)}">
                            v.${(userOrder.order.version)}
                        </c:if>
                    </td>
                    <td class='clickable-cell'>${userOrder.order.printName}</td>
                    <td class='clickable-cell'>
                        <c:choose>
                            <c:when test="${DateUtils.isSameDay(userOrder.order.deliveryDate, today)}">
                                Today <fmt:formatDate value="${userOrder.order.deliveryDate}" pattern=" HH:mm"/>
                            </c:when>
                            <c:when test="${DateUtils.isSameDay(userOrder.order.deliveryDate, yesterday)}">
                                Yesterday <fmt:formatDate value="${userOrder.order.deliveryDate}" pattern=" HH:mm"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${userOrder.order.deliveryDate}"
                                                pattern=" EEEE d/MM/yyyy HH:mm"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class='clickable-cell'>
                        <c:choose>
                            <c:when test="${DateUtils.isSameDay(userOrder.order.lastModifiedDate, today)}">
                                Today <fmt:formatDate value="${userOrder.order.lastModifiedDate}" pattern=" HH:mm"/>
                            </c:when>
                            <c:when test="${DateUtils.isSameDay(userOrder.order.lastModifiedDate, yesterday)}">
                                Yesterday <fmt:formatDate value="${userOrder.order.lastModifiedDate}" pattern=" HH:mm"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${userOrder.order.lastModifiedDate}"
                                                pattern=" EEEE d/MM/yyyy HH:mm"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${(theOrder.SAPstatus.id==5&&user.roles.id==4&&theOrder.processId==5)}">
                        <td><a href="<spring:url value="/orders/{orderId}/yes">
											<spring:param name="orderId" value ="${userOrder.order.orderId}"/>
										</spring:url>
							" class="btn btn-success  custom-width">Stamps ordered</a></td>
                    </c:if>
                    <c:if test="${(theOrder.SAPstatus.id==7&&user.roles.id==2)}">
                        <td><a href="<spring:url value="/orders/{orderId}/yes">
											<spring:param name="orderId" value ="${userOrder.order.orderId}"/>
										</spring:url>
							" class="btn btn-success  custom-width">Stamps received</a></td>
                    </c:if>
                </tr>
                <%--</a>--%>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>


</body>
</html>
<script>
    var statuses = [];
    var obj = {};
    <c:forEach items="${userOrders}" var="userOrder">
    statuses.push('${userOrder.order.SAPstatus.statusSAPStatuseRoles[0].status.id}');
    </c:forEach>

    for (var i = 0, j = statuses.length; i < j; i++) {
        if (obj[statuses[i]]) {
            obj[statuses[i]]++;
        }
        else {
            obj[statuses[i]] = 1;
        }
    }

    $(".ordersFilter").each(function () {

        if (obj[$(this).attr("data-filter-text")] > 0) {
            console.log($(this).attr("data-filter-text"));
            $(this).append(" (" + obj[$(this).attr("data-filter-text")] + ")");
        }
    });

    $(".ordersFilter").click(function () {

        var that = $(this);
        var selection;
        var result;
        var roleId = "${user.role.id}";

        $(".clickable-row").show();
//        if ($(this).attr('id') != "all") {
//            debugger;
//
//            if ($(this).attr('id') === "in-progress") {
//
//                selection = "[data-status='1'], [data-status='2'], [data-status='3'], [data-status='4'], [data-status='5'], [data-status='6'], [data-status='7']";
//                console.log($(".clickable-row .status").not(selection));
//
//
//            } else if ($(this).attr('id') === "planned") {
//                selection = "[data-status='8'], [data-status='11']";
//            }
//            else if ($(this).attr('id') === "delivered") {
//                selection = "[data-status='10']";
//
//            } else if ($(this).attr('id') === "on-hold") {
//                selection = "[data-status='9']";
//            }
////            var str = $(this).text();
////            var filterStatus = str.slice(0, str.indexOf("(")).trim();
//////            console.log(filterStatus);
////            $(".status").each(function () {
////                var status = $(this).text().trim();
//////                console.log(filterStatus+" "+status);
////                if (!status.includes(filterStatus)) {
////                    $(this).parent().hide();
////                }
////
////     });
//
//        }
//        debugger;

            switch ($(this).attr('id')) {

                case "all":{
                    selection =null;
                    break;
                }

                case "in-progress":
                    if (roleId === "1" || roleId === "2")
                        selection = "[data-status='1'], [data-status='2'], [data-status='3'], [data-status='4'], [data-status='5'], [data-status='6'], [data-status='7']";
                    else if (roleId === "4")
                        selection = "[data-status='2'], [data-status='3'], [data-status='4'], [data-status='5'], [data-status='6']";
                    break;
                case "planned":
                    selection = "[data-status='8'], [data-status='11']";
                    break;
                case "delivered" :
                    selection = "[data-status='10']";
                    break;
                case "on-hold":
                    selection = "[data-status='9']";
                    break;
                case "stamps-ordered":
                    selection = "[data-status='7'], [data-status='8'], [data-status='10']";
                    break;

                default:
                    console.log($(this));
                    var filterStatus = $(this).attr("data-filter-text");
                    console.log(filterStatus);
                    $(".status").each(function () {
                        var status = $(this).attr("data-status");
                        console.log(filterStatus + " " + status);
                        if (status !== filterStatus) {
                            $(this).parent().hide();
                        }
                    });


            }

                if (selection != undefined || selection != null) {
                    result = $(".clickable-row .status").not(selection);
                    result.each(function () {
                        $(this).parent().hide();
                    });
                }




    });

    $("#orderstable").tablesorter({sortList: [[0, 1]]});

    $(document).ready(function () {


//		$("#orderstable .clickable-row").click(function() {
//			window.location = $(this).data("href");
//		});
        $("#orderstable .clickable-row .clickable-cell").click(function () {
            var that = $(this);
            var IDList = [];
            $("tr:visible").children(".orderId").each(function () {
                var orderId = $(this).text().trim();
                console.log(orderId);
                IDList.push(orderId);

            });
            var result = IDList.toString(IDList);
            console.log(result);


            $.ajax({
                type: "POST",
                data: {"IDList": IDList},
//            contentType:'application/json',
                url: "../NapkinStudio/order-ids",
                success: function () {

                    console.log("success");

                },
                error: function () {

                    console.log("error");
                },
                complete: function () {

                    window.location = that.parent().data("href");
                }

            });


        });
    });


    // $(".to-orders-btn").click(function () {
    //
    //	 var orderId = $(this).parent().parent().children(".orderId").text().trim();
    //	 console.log("Order " + orderId);
    //	 var IDList = [];
    //	 $("tr:visible").children(".orderId").each(function () {
    //		 var orderId = $(this).text().trim();
    //		 console.log(orderId);
    //		 IDList.push(orderId);
    //
    //	 });
    //	 var result = IDList.toString(IDList);
    //	 console.log(result);
    //
    //
    //	 $.ajax({
    //		 type: "POST",
    //		 data: {"IDList": IDList},
    ////            contentType:'application/json',
    //		 url: "../order-ids",
    //		 success: function () {
    //			 console.log("success");
    //
    //		 },
    //		 error: function () {
    //			 console.log("error");
    //		 },
    //		 complete: function () {
    //
    //			 window.location.href = "orders/" + orderId;
    //		 }
    //
    //	 });
    ////        var list = {testArray:["a", "b", "c"]};
    ////        $.ajax({
    ////            url : '../order-ids',
    ////            data : list,
    ////            type : 'POST', //<== not 'GET',
    //////            contentType : "application/json; charset=utf-8",
    //////            dataType : 'json',
    ////            error : function() {
    ////                console.log("error");
    ////            },
    ////            success : function(arr) {
    ////                console.log(arr.testArray);
    ////                var testArray = arr.testArray;
    ////                $.each(function(i,e) {
    ////                    document.writeln(e);
    ////                });
    ////            }
    ////        });
    //
    //
    //
    // });

</script>
