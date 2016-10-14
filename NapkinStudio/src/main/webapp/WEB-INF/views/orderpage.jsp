<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
         import="java.util.Date, java.util.Calendar, org.apache.commons.lang.time.DateUtils" errorPage="" %>
<c:set var="today" scope="session" value="<%=new java.util.Date()%>"/>
<c:set var="yesterday" scope="session" value="<%=new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000)%>"/>
<c:set var="thisstage" scope="session" value="true"/>

<script type="text/javascript">
    //    console.log(user);
    //    console.log(userOrder);

</script>
<html>
<head>
    <title>NapkinStudio Order page</title>

    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=PT+Sans+Caption:400,700' rel='stylesheet' type='text/css'>
    <%--TODO: dowload files and set path--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value='/static/js/jquery.ui.widget.js' />"></script>
    <script src="<c:url value='/static/js/jquery.iframe-transport.js' />"></script>
    <script src="<c:url value='/static/js/jquery.fileupload.js' />"></script>
    <%--<nav display="inline">
        <a href="<c:url value="/orders"/>" class="btn btn-primary custom-width"> to orders </a>
        <span style="margin-left: 100px">  order id ${theOrder.orderId}</span>
        &lt;%&ndash;<p display="inline">You are logged in as ${user.roles[0].name}</p>&ndash;%&gt;
        <a href="<c:url value="/logout"/>" class="btn btn-danger custom-width"> Logout </a>
		
		        <c:if test="${prevId.length() > 0 }">
            <a href="<spring:url value="/orders/{prevId}">
                          <spring:param name="prevId" value ="${prevId}"/>
                </spring:url>"
               class="btn btn-primary  custom-width to-orders-btn">Previous</a>
        </c:if>
        <c:if test="${nextId.length() > 0 }">
            <a href="<spring:url value="/orders/{nextId}">
                    <spring:param name="nextId" value ="${nextId}"/>
                </spring:url>"
               class="btn btn-primary  custom-width to-orders-btn">Next</a>
        </c:if>

    </nav>--%>
    <%--<nav display="inline">--%>
    <%--<a href="<c:url value="/orders"/>"  btn btn-primary> to orders </a>--%>
    <%--<span style="margin-left: 100px"> ${theOrder.orderId} order id </span>--%>
    <%--&lt;%&ndash;<p display="inline">You are logged in as ${user.roles[0].name}</p>&ndash;%&gt;--%>
    <%--</nav>%--<a href="<c:url value="/logout"/>"> Logout </a>--%>
</head>

<body style="overflow-y: auto">
<div class="loading-spinner" style="display:none">Loading&#8230;</div>
<section class="col-md-12 logo-area" style="background-color: #f9f9f9; background-position-x: 10%">
    <a href="<c:url value="/logout"/>" style="float: right; margin: 20px 0">&nbsp;uitloggen </a>
    <p style="float: right; margin: 20px 0">${user.role.name} - </p>

</section>
<section class="subheader col-md-12" style="background-color: white">
    <c:if test="${user.role.id!=5}">
        <a href="<c:url value="/orders"/>" class="back-btn"> Overzicht </a>
    </c:if>
    <h2> ${theOrder.orderId} ${theOrder.itemNum} - ${theOrder.printName}</h2>
    <div class="checkout-wrap">
        <div class="progress-bar-line"></div>
        <ul class="checkout-bar">
            <c:if test="${prevId.length() > 0 &&user.role.id!=5}">
                <li class="prev-order-btn" onclick="document.getElementById('prev-btn-id').click()">
                    <a id="prev-btn-id" href="<spring:url value="/orders/{prevId}">
                          <spring:param name="prevId" value ="${prevId}"/>
                </spring:url>">Previous</a>
                </li>
            </c:if>
            <c:forEach items="${barFields}" var="barFields">
                <c:if test="${barFields[1]!=null}">
                    <li class="previous visited">
                        <c:out value="${barFields[0]}"/>
                        <br>
                        <c:choose>
                            <c:when test="${DateUtils.isSameDay(barFields[1], today)}">
                                Today <fmt:formatDate value="${barFields[1]}" pattern=" HH:mm"/>
                            </c:when>
                            <c:when test="${DateUtils.isSameDay(barFields[1], yesterday)}">
                                Yesterday <fmt:formatDate value="${barFields[1]}" pattern=" HH:mm"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${barFields[1]}" pattern=" EEEE d/MM/yyyy HH:mm"/>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:if>
                <c:if test="${barFields[1]==null}">
                    <c:if test="${!thisstage}">
                        <li class="next">
                    </c:if>
                    <c:if test="${thisstage}">
                        <li class="active">
                        <c:set var="thisstage" scope="session" value="false"/>
                    </c:if>
                    <c:out value="${barFields[0]}"/><c:out value="${barFields[1]}"/>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${nextId.length() > 0  &&user.role.id!=5}">
                <li class="next-order-btn" onclick="document.getElementById('next-btn-id').click()"><a id="next-btn-id"
                                                                                                       href="<spring:url value="/orders/{nextId}">
                    <spring:param name="nextId" value ="${nextId}"/>
                </spring:url>">Next</a></li>
            </c:if>
        </ul>
    </div>
</section>
<section class="order-wrapper" style="background-color: #f9f9f9">

    <table class="blabla" width="100%" border="0">
        <tr valign="top">
            <td width="10%">
            </td>
            <td width="65%" align="left">
                <%--6.4-5 Order and Article info/////////////////////////////////////////////////--%>
                <table id="maininfotable" width="100%" border="0" style="margin-bottom: 10px; margin-top: 10px"
                       st=${theOrder.SAPstatus.id} ur=${user.role.id}>
                    <tr class="table-title" valign="top">
                        <th width="50%">
                            <b>Order Informatie</b>
                        </th>
                        <th width="50%">
                            <b>Artikel informatie</b>
                        </th>
                    </tr>
                    <tr>
                        <td width="50%" valign="top">
                            <table width="100%" class="inner-info-table">
                                <tr valign="top">
                                    <td width="34%">
                                        debtor number
                                    </td>
                                    <td width="66%">
                                        ${theOrder.debNum}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Debtor Order Number</td>
                                    <td>${theOrder.debNum}</td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td>${theOrder.quantity} stuks</td>
                                </tr>
                                <tr>
                                    <td>Approval by</td>
                                    <td>${theOrder.approvalBy}</td>
                                </tr>
                                <tr>
                                    <td>Debtor contact</td>
                                    <td>${theOrder.debCont}</td>
                                </tr>
                                <tr>
                                    <td>Delivery address</td>
                                    <td>${theOrder.delivAddr}</td>
                                </tr>
                                <tr>
                                    <td>Delivery address contact</td>
                                    <td>${theOrder.delivAddrCont}</td>
                                </tr>
                                <tr>
                                    <td>Unloading times</td>
                                    <td>${theOrder.unloadTimes}</td>
                                </tr>
                                <tr>
                                    <td>Delivery instructions</td>
                                    <td>${theOrder.delivInstruct}</td>
                                </tr>
                            </table>
                        </td>
                        <td width="50%" valign="top">
                            <table width="100%" class="inner-info-table">
                                <tr valign="top">
                                    <td width="34%">
                                        Item number
                                    </td>
                                    <td width="66%">
                                        ${theOrder.itemNum}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Debtor item number</td>
                                    <td>${theOrder.debItemNum}</td>
                                </tr>
                                <tr>
                                    <td>Size</td>
                                    <td>${theOrder.size}</td>
                                </tr>
                                <tr>
                                    <td>Material</td>
                                    <td>${theOrder.material}</td>
                                </tr>
                                <tr>
                                    <td>folding method</td>
                                    <td>${theOrder.foldingMeth}</td>
                                </tr>
                                <tr>
                                    <td>napkin colour</td>
                                    <td>${theOrder.napkinCol}</td>
                                </tr>
                                <c:if test="${(theOrder.SAPstatus.id<=2)}">
                                    <tr>
                                        <td>Print colour 1</td>
                                        <td>${theOrder.color1}</td>
                                    </tr>
                                    <tr>
                                        <td>Print colour 2</td>
                                        <td>${theOrder.color2}</td>
                                    </tr>
                                    <tr>
                                        <td>Print colour 3</td>
                                        <td>${theOrder.color3}</td>
                                    </tr>
                                    <tr>
                                        <td>Print colour 4</td>
                                        <td>${theOrder.color4}</td>
                                    </tr>
                                    <tr>
                                        <td>Print colour 4</td>
                                        <td>${theOrder.color4}</td>
                                    </tr>
                                </c:if>
                            </table>
                        </td>
                    </tr>

                </table>

                <%--6.7 Print proof/////////////////////////////////////////////////--%>
                <%--TODO: Check condition--%>
                <c:if test="${(theOrder.SAPstatus.id==2&&user.role.id==4)||
                                (theOrder.SAPstatus.id>2)}">
                    <%--<div width="100%" style="margin-bottom: 10px">--%>
                    <%--<b>Drukproef</b>--%>
                    <%--</div>--%>
                    <div id="printproof"></div>
                </c:if>
                <%--6.6 Comments/////////////////////////////////////////////////--%>
                <div width="100%" style="margin-bottom: 10px">
                    <b class="opmerkingen-wrapper">Opmerkingen</b>
                    <ul class="coments-table" width="100%" style="list-style-type:none">
                        <li width="100%" class="comments to-deptor-comments">
                            <table width="100%">
                                <td width="17%" style="vertical-align: top">
                                    <b>Voor PVI</b>
                                    <a id="PVI-button" class="add-comment-btn" data-for-role="2">Add comment</a>
                                </td>
                                <%-- <td width="3%" style="vertical-align: top">
                                     <a id="PVI-button" class="add-comment-btn">Add comment</a>
                                 </td>--%>
                                <td>
                                    <ul class="comments-list" style="list-style-type:none">
                                        <c:choose>
                                            <c:when test="${PVIComments.size() > 0 }">
                                                <c:forEach items="${PVIComments}" var="PVIComment">

                                                    <c:if test="${user.role.id == 2  || user.role.id == 4 || user.userId == PVIComment.fromUser.userId }">
                                                        <li>
                                                            <div>
                                                                <div class="comment-container">
                                                                    <p class="commentText"
                                                                       data-unread="${PVIComment.unread}">
                                                                            ${PVIComment.commText}
                                                                    </p>
                                                                    <p>
                                                                            <%--${PVIComment.fromUser.lastModifiedDate}--%>
                                                                        <c:choose>
                                                                            <c:when test="${DateUtils.isSameDay(PVIComment.fromUser.lastModifiedDate, today)}">
                                                                                Today <fmt:formatDate
                                                                                    value="${PVIComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" HH:mm"/>
                                                                            </c:when>
                                                                            <c:when test="${DateUtils.isSameDay(PVIComment.fromUser.lastModifiedDate, yesterday)}">
                                                                                Yesterday <fmt:formatDate
                                                                                    value="${PVIComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" HH:mm"/>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <fmt:formatDate
                                                                                        value="${PVIComment.fromUser.lastModifiedDate}"
                                                                                        pattern=" EEEE d/MM/yyyy HH:mm"/>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                            ${PVIComment.fromUser.firstName} ${PVIComment.fromUser.lastName}
                                                                    </p>
                                                                    <c:if test="${PVIComment.fromUser.userId == user.userId}">
                                                                        <a class="btn edit-comment"
                                                                           commentId="${PVIComment.id}">edit</a>
                                                                        <a class="btn delete-comment"
                                                                           commentId="${PVIComment.id}">delete</a>
                                                                    </c:if>

                                                                </div>
                                                            </div>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="No comments for PVI"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <li id="form-container">

                                            <form:form commandName="comment" method="post"
                                                       action="/NapkinStudio/addComment"
                                                       cssClass="form-horizontal addCommentForm">
                                                <div class="form-group">
                                                    <div class="col-sm-5">
                                                        <form:textarea path="commText"
                                                                       cssClass="form-control"></form:textarea>
                                                        <form:hidden id="for-role" path="forRole.id"
                                                                     value="2"/>
                                                        <form:hidden path="order.orderId"
                                                                     value="${theOrder.orderId}"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-5">
                                                    <input type="submit"
                                                           value="Save"
                                                           class="btn btn-large btn-primary send-comment-btn"
                                                           style="float: left;">
                                                    <a class="btn cancel-button btn-danger "
                                                       style="float:right;">Cancel</a>
                                                </div>
                                            </form:form>
                                        </li>
                                    </ul>


                                </td>
                            </table>
                        </li>
                        <li width="100%" class="comments ">
                            <table width="100%">
                                <td width="17%" style="vertical-align: top">
                                    <b>Voor DTP</b>
                                    <a id="DTP-button" data-for-role="4" class="add-comment-btn">Add comment</a>
                                </td>

                                <td>


                                    <ul class="comments-list" style="list-style-type:none">
                                        <c:choose>
                                            <c:when test="${DTPComments.size() gt 0}">
                                                <c:forEach items="${DTPComments}" var="DTPComment">
                                                    <li>
                                                        <div>
                                                            <div class="comment-container">
                                                                <p class="commentText"
                                                                   data-unread="${DTPComment.unread}">
                                                                        ${DTPComment.commText}
                                                                </p>
                                                                <p>
                                                                        <%--${DTPComment.fromUser.lastModifiedDate} --%>
                                                                    <c:choose>
                                                                        <c:when test="${DateUtils.isSameDay(DTPComment.fromUser.lastModifiedDate, today)}">
                                                                            Today <fmt:formatDate
                                                                                value="${DTPComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:when test="${DateUtils.isSameDay(DTPComment.fromUser.lastModifiedDate, yesterday)}">
                                                                            Yesterday <fmt:formatDate
                                                                                value="${DTPComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <fmt:formatDate
                                                                                    value="${DTPComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" EEEE d/MM/yyyy HH:mm"/>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                        ${DTPComment.fromUser.firstName} ${DTPComment.fromUser.lastName}
                                                                </p>
                                                                <c:if test="${DTPComment.fromUser.userId == user.userId}">
                                                                    <a class="btn edit-comment"
                                                                       commentId="${DTPComment.id}">edit</a>
                                                                    <a class="btn delete-comment"
                                                                       commentId="${DTPComment.id}">delete</a>
                                                                </c:if>

                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="No comments for DTP"/>
                                            </c:otherwise>
                                        </c:choose>

                                    </ul>


                                </td>
                            </table>
                        </li>
                        <li width="100%" class="comments to-deptor-comments to-customer-comments">
                            <table width="100%">
                                <td width="17%" style="vertical-align: top">
                                    <b>Voor debiteur</b>
                                    <a id="debtor-button" class="add-comment-btn" data-for-role="1">Add comment</a>
                                </td>
                                <%--<td width="3%" style="vertical-align: top">
                                    <a class="btn add-comment-btn">Add comment</a>
                                </td>--%>
                                <td>
                                    <ul class="comments-list" style="list-style-type:none">
                                        <c:choose>
                                            <c:when test="${DeptorComments.size() gt 0}">
                                                <c:forEach items="${DeptorComments}" var="DeptorComment">
                                                    <c:if test="${user.role.id != 5 ||  user.userId == DeptorComment.fromUser.userId }">
                                                        <li>
                                                            <div>
                                                                <div class="comment-container">
                                                                    <p class="commentText"
                                                                       data-unread="${DeptorComment.unread}">
                                                                            ${DeptorComment.commText}
                                                                    </p>
                                                                    <p>
                                                                            <%--${DeptorComment.fromUser.lastModifiedDate}--%>
                                                                        <c:choose>
                                                                            <c:when test="${DateUtils.isSameDay(DeptorComment.fromUser.lastModifiedDate, today)}">
                                                                                Today <fmt:formatDate
                                                                                    value="${DeptorComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" HH:mm"/>
                                                                            </c:when>
                                                                            <c:when test="${DateUtils.isSameDay(DeptorComment.fromUser.lastModifiedDate, yesterday)}">
                                                                                Yesterday <fmt:formatDate
                                                                                    value="${DeptorComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" HH:mm"/>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <fmt:formatDate
                                                                                        value="${DeptorComment.fromUser.lastModifiedDate}"
                                                                                        pattern=" EEEE d/MM/yyyy HH:mm"/>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                            ${DeptorComment.fromUser.firstName} ${DeptorComment.fromUser.lastName}
                                                                    </p>
                                                                    <c:if test="${DeptorComment.fromUser.userId == user.userId}">
                                                                        <a class="btn edit-comment"
                                                                           commentId="${DeptorComment.id}">edit</a>
                                                                        <a class="btn delete-comment"
                                                                           commentId="${DeptorComment.id}">delete</a>
                                                                    </c:if>

                                                                </div>
                                                            </div>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="No comments for deptor"/>
                                            </c:otherwise>
                                        </c:choose>

                                    </ul>


                                </td>
                            </table>
                        </li>
                        <li width="100%" class="comments to-deptor-comments to-customer-comments">
                            <table width="100%">
                                <td width="17%" style="vertical-align: top">
                                    <b>Voor eindafnemer</b>
                                    <a class="add-comment-btn" data-for-role="5">Add comment</a>
                                </td>
                                <%--<td width="3%" style="vertical-align: top">
                                    <a class="btn add-comment-btn">Add comment</a>
                                </td>--%>
                                <td>
                                    <ul class="comments-list" style="list-style-type:none">
                                        <c:choose>
                                            <c:when test="${CustomerComments.size() gt 0}">
                                                <c:forEach items="${CustomerComments}" var="CustomerComment">
                                                    <li>
                                                        <div>
                                                            <div class="comment-container">
                                                                <p class="commentText"
                                                                   data-unread="${CustomerComment.unread}">
                                                                        ${CustomerComment.commText}
                                                                </p>
                                                                <p>
                                                                        <%--${CustomerComment.fromUser.lastModifiedDate}--%>
                                                                    <c:choose>
                                                                        <c:when test="${DateUtils.isSameDay(CustomerComment.fromUser.lastModifiedDate, today)}">
                                                                            Today <fmt:formatDate
                                                                                value="${CustomerComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:when test="${DateUtils.isSameDay(CustomerComment.fromUser.lastModifiedDate, yesterday)}">
                                                                            Yesterday <fmt:formatDate
                                                                                value="${CustomerComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <fmt:formatDate
                                                                                    value="${CustomerComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" EEEE d/MM/yyyy HH:mm"/>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                        ${CustomerComment.fromUser.firstName} ${CustomerComment.fromUser.lastName}
                                                                </p>
                                                                <c:if test="${CustomerComment.fromUser.userId == user.userId}">
                                                                    <a class="btn edit-comment"
                                                                       commentId="${CustomerComment.id}">edit</a>
                                                                    <a class="btn delete-comment"
                                                                       commentId="${CustomerComment.id}">delete</a>
                                                                </c:if>

                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="No comments for customer"/>
                                            </c:otherwise>
                                        </c:choose>

                                    </ul>


                                </td>
                            </table>
                        </li>
                        <li width="100%" class="comments">
                            <table width="100%">
                                <td width="17%" style="vertical-align: top">
                                    <b>Voor stamp manufacturer</b>
                                    <a class="add-comment-btn" data-for-role="6">Add comment</a>
                                </td>
                                <%--<td width="3%" style="vertical-align: top">
                                    <a class="btn add-comment-btn">Add comment</a>
                                </td>--%>
                                <td>
                                    <ul class="comments-list" style="list-style-type:none">
                                        <c:choose>
                                            <c:when test="${StampsManufacComments.size() gt 0}">
                                                <c:forEach items="${StampsManufacComments}" var="StampsManufacComment">
                                                    <li>
                                                        <div>
                                                            <div class="comment-container">
                                                                <p class="commentText"
                                                                   data-unread="${StampsManufacComment.unread}">
                                                                        ${StampsManufacComment.commText}
                                                                </p>
                                                                <p>
                                                                        <%--${StampsManufacComment.fromUser.lastModifiedDate} --%>
                                                                    <c:choose>
                                                                        <c:when test="${DateUtils.isSameDay(StampsManufacComment.fromUser.lastModifiedDate, today)}">
                                                                            Today <fmt:formatDate
                                                                                value="${StampsManufacComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:when test="${DateUtils.isSameDay(StampsManufacComment.fromUser.lastModifiedDate, yesterday)}">
                                                                            Yesterday <fmt:formatDate
                                                                                value="${StampsManufacComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <fmt:formatDate
                                                                                    value="${StampsManufacComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" EEEE d/MM/yyyy HH:mm"/>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                        ${StampsManufacComment.fromUser.firstName} ${StampsManufacComment.fromUser.lastName}
                                                                </p>
                                                                <c:if test="${StampsManufacComment.fromUser.userId == user.userId}">
                                                                    <a class="btn edit-comment"
                                                                       commentId="${StampsManufacComment.id}">edit</a>
                                                                    <a class="btn delete-comment"
                                                                       commentId="${StampsManufacComment.id}">delete</a>
                                                                </c:if>

                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="No comments for stamp manufacture "/>
                                            </c:otherwise>
                                        </c:choose>

                                    </ul>


                                </td>
                            </table>
                        </li>
                        <li width="100%" class="comments">
                            <table width="100%">
                                <td width="17%" style="vertical-align: top">
                                    <b>Voor production</b>
                                    <a id="production-button" class="add-comment-btn" data-for-role="7">Add comment</a>
                                </td>
                                <%--<td width="3%" style="vertical-align: top">
                                    <a class="btn add-comment-btn" id="production-button">Add comment</a>
                                </td>--%>
                                <td>
                                    <ul class="comments-list" style="list-style-type:none">
                                        <c:choose>
                                            <c:when test="${ProductionComments.size() gt 0}">
                                                <c:forEach items="${ProductionComments}" var="ProductionComment">
                                                    <li>
                                                        <div>
                                                            <div class="comment-container">
                                                                <p class="commentText"
                                                                   data-unread="${ProductionComment.unread}">
                                                                        ${ProductionComment.commText}
                                                                </p>
                                                                <p>
                                                                    <c:choose>
                                                                        <c:when test="${DateUtils.isSameDay(ProductionComment.fromUser.lastModifiedDate, today)}">
                                                                            Today <fmt:formatDate
                                                                                value="${ProductionComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:when test="${DateUtils.isSameDay(ProductionComment.fromUser.lastModifiedDate, yesterday)}">
                                                                            Yesterday <fmt:formatDate
                                                                                value="${ProductionComment.fromUser.lastModifiedDate}"
                                                                                pattern=" HH:mm"/>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <fmt:formatDate
                                                                                    value="${ProductionComment.fromUser.lastModifiedDate}"
                                                                                    pattern=" EEEE d/MM/yyyy HH:mm"/>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                        ${ProductionComment.fromUser.firstName} ${ProductionComment.fromUser.lastName}
                                                                </p>
                                                                <c:if test="${ProductionComment.fromUser.userId == user.userId}">
                                                                    <a class="btn edit-comment"
                                                                       commentId="${ProductionComment.id}">edit</a>
                                                                    <a class="btn delete-comment"
                                                                       commentId="${ProductionComment.id}">delete</a>
                                                                </c:if>

                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="No comments for production"/>
                                            </c:otherwise>
                                        </c:choose>

                                    </ul>


                                </td>
                            </table>
                        </li>
                    </ul>
                </div>
                <div id="order-attachment" class="order-attachment" orderId="${theOrder.orderId}"></div>

                <div display="block" style="margin-bottom: 10px">

                    <%--Files and comments for StatusChange--%>
                    <c:if test="${
                                    ((theOrder.SAPstatus.id==1)&&user.role.id==2)||
                                    (theOrder.SAPstatus.id==2&&user.role.id==4)||
                                    (theOrder.SAPstatus.id==5&&user.role.id==4&&theOrder.processId==5)||
                                    (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Deptor')||
                                    (theOrder.SAPstatus.id==4&&user.role.id==5&&theOrder.approvalBy=='Customer'&&!theOrder.debCheckScen)||
                                    (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==1)||
                                    (theOrder.SAPstatus.id==4&&user.role.id==5&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==2)||
                                    (theOrder.SAPstatus.id==6&&user.role.id==2)
                         }">
                        <div id="order-new-attachment" style="padding-top: 15px; padding-bottom: 15px"></div>
                        <div>
                            <textarea id="statuscahngecomment" path="commText" placeholder="Enter comment"
                                      style="margin-bottom: 15px; border-radius:5px; "></textarea>
                        </div>
                    </c:if>
                    <%--Appove without comments and files adding--%>
                    <c:if test="${
                                (theOrder.SAPstatus.id==3&&user.role.id==2&&orderPviCheck)||
                                (theOrder.SAPstatus.id==6&&user.role.id==2)||
                                (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Deptor')||
                                (theOrder.SAPstatus.id==4&&user.role.id==5&&theOrder.approvalBy=='Customer'&&!theOrder.debCheckScen)||
                                (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==1)||
                                (theOrder.SAPstatus.id==4&&user.role.id==5&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==2)||
                                (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Customer'&&theOrder.processId==3)||
                                (theOrder.SAPstatus.id==5&&user.role.id==2&&theOrder.processId==4)||
                                (theOrder.SAPstatus.id==7&&user.role.id==2)
                     }">
                        <a href="<c:url value='/changestatus/${theOrder.orderId}/yes' />"
                           class="btn btn-success">Goedkeuren</a>
                    </c:if>
                    <%--Discard without comments and files adding--%>
                    <c:if test="${
                                (theOrder.SAPstatus.id==3&&user.role.id==2&&orderPviCheck)||
                                (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Customer'&&theOrder.processId==3)
                     }">
                        <a href="<c:url value='/changestatus/${theOrder.orderId}/no' />"
                           class="btn  btn-success" style="background-color: grey!important">Annuleren</a>
                    </c:if>
                    <%--Appove with comments and files adding--%>
                    <c:if test="${
                                ((theOrder.SAPstatus.id==1)&&user.role.id==2)||
                                (theOrder.SAPstatus.id==2&&user.role.id==4)||
                                (theOrder.SAPstatus.id==5&&user.role.id==4&&theOrder.processId==5)
                     }">
                        <button type="button" class="approve-btn btn btn-success"
                                foraction="/NapkinStudio/changestatus/${theOrder.orderId}/yes"
                                st=${theOrder.SAPstatus.id} ur=${user.role.id} pi=${theOrder.processId}
                        >Goedkeuren
                        </button>
                    </c:if>
                    <%--Discard with comments and files adding--%>
                    <c:if test="${
                                (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Deptor')||
                                (theOrder.SAPstatus.id==4&&user.role.id==5&&theOrder.approvalBy=='Customer'&&!theOrder.debCheckScen)||
                                (theOrder.SAPstatus.id==4&&user.role.id==1&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==1)||
                                (theOrder.SAPstatus.id==4&&user.role.id==5&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==2)||
                                (theOrder.SAPstatus.id==6&&user.role.id==2)
                     }">
                        <button type="button" class="approve-btn btn "
                                foraction="/NapkinStudio/changestatus/${theOrder.orderId}/no"
                                st=${theOrder.SAPstatus.id} ur=${user.role.id} pi=${theOrder.processId}
                        >Annuleren
                        </button>
                    </c:if>
                </div>


            </td>
            <td width="25%">
            </td>
        </tr>
    </table>
</section>
<script src="<c:url value='/static/js/app.js' />"></script>
</body>
</html>
<script>


    var role = "${user.role.name}";
    var status = "${theOrder.sapStatus.statusSAPStatuseRoles[0].status.name}";
    var statusId = +"${theOrder.sapStatus.statusSAPStatuseRoles[0].status.id}";
    console.log(role + " " + status + " " + statusId);
    $(document).ready(function () {

//         $('#fileupload').fileupload({
//             dataType: 'json',
//             done: function (e, data) {
// //                $('<p/>').text(data.result.fileName+"   "+data.result.fileSize).insertAfter('#progress');
//                 $('<div class="row" name="'+data.result.fileName+'" style="display: inline">'+data.result.fileName+' '+data.result.fileSize+
//                         ' <div class="floatRight">' +
//                         '<input  value="Delete" type="button" class=" del-file-but btn btn-danger btn-sm" >' +
// //                        '</div></div>').insertAfter('#progress');
        <%--                         ' <a href="/remove-file/${theOrder.orderId}/'+data.result.fileName+'" class=" del-file-but  btn btn-danger custom-width">Discard</a> ' + --%>
//                         '</div></div>').appendTo('#foruploadedfiles');
//                 //                $.each(data.files, function (index, file) {
// //                    $('<p/>').text(file.name+"   "+file.size+"Kb  "+file.lastModifiedDate).appendTo(document.body);
// //                });
//                 var orderId=$('#fileupload').attr("orderId");
//                 $('<script>$(".del-file-but").click(function(){'+
//                         'console.log("dqwdqw");'+
//                         'console.log($(this));'+
//                         'var thisElement=$(this);'+
//                         '$.ajax({'+
//                         '    url: 'remove-file/${theOrder.orderId}/"+thisElement.parent().parent().attr("name")','+
//                         'success: function(result){'+
//                         'console.log("dqwdqw"); napkin.buildFileInfoList();'+
//                         'thisElement.parent().parent().remove();'+
//                         '}});'+
//                         '});</s'+'cript>').appendTo(document.body);
//                 napkin.buildFileInfoList();
//             },
//             progressall: function (e, data) {
//                 var progress = parseInt(data.loaded / data.total * 100, 10);
//                 $('#progress .bar').css(
//                         'width',
//                         progress + '%'
//                 );
//             }
//         });


        $(".addCommentForm").hide();
        $(".add-comment-btn").hide();
        $(".editCommentForm").hide();
        $(".edit-comment").hide();
        $(".delete-comment").hide();
        $(".loading-spinner").hide();


        $(".add-comment-btn").click(function () {

            var excist = $(this).parent().next().find("form").length;
            var formContainer = $("#form-container");
            var commentForm = formContainer.find("form");

            if (excist && commentForm.is(":visible"))
                commentForm.hide(350);
            else if (excist && !commentForm.is(":visible"))
                commentForm.show(700);
            else if (!excist) {
                var that = $(this);
                commentForm.hide(350,function(){

                var roleId = that.attr("data-for-role");
                var commentsList = that.parent().next().find("ul");

                formContainer.find("textarea").val("");
                formContainer.find("#for-role").val(roleId);
                commentsList.append(formContainer);
                commentForm.show(700);
                });
            }
//

        });

        $(".send-comment-btn").click(function () {
            $(".loading-spinner").show();
        });


        $(".cancel-button").click(function () {
            var form = $(this).parent().parent();
            form.find("textarea").val("");
            form.hide(350);

        });


        $(".edit-comment").click(function () {
            var commmentId = $(this).attr("commentId");
            console.log(commmentId);
            var innerCommentBlock = $(this).parent();
            var comment = $(this).parent().find(".commentText").text().trim();
            console.log(comment);

            var $editCommentArea = $("<textarea class='edit-comment'/>");
            var $buttonArea = $("<div><a class='btn btn-primary'>Edit</a> <a class='btn btn-danger'>Cancel</a> </div>");
            var commentDiv = $(this).parent().parent();

            innerCommentBlock.hide();

            $editCommentArea.val(comment);
            commentDiv.append($editCommentArea, $buttonArea);

//            $(this).parent().append("<textarea class='edit-comment'/>");
//            var form = $(this).find(".editCommentForm");

//            form.find("#commText").val(comment);
//            form.show();

            commentDiv.find(".btn-danger").click(function () {
                $editCommentArea.hide();
                $buttonArea.hide();

                innerCommentBlock.show();
            });

            commentDiv.find(".btn-primary").click(function () {
                var newCommnet = $editCommentArea.val();
                console.log(newCommnet);

                var commentObj = {
                    "id": commmentId,
                    "commText": newCommnet
                };
                var test = {
                    "id": "11",
                    "commText": "1111111111111"
                };

                console.log(commentObj);
                console.log(test);
                $.ajax({
                    type: "POST",
//                data:{"str":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"},
                    data: JSON.stringify(commentObj),
                    dataType: 'json',
                    url: "../edit-comment",
                    contentType: 'application/json; charset=utf-8',
                    success: function () {
                        console.log("success");
                        window.location.reload();

                    },
                    error: function () {
                        console.log("error");
                    },
                });


            });


        });

        $(".delete-comment").click(function () {

            var commentId = +$(this).attr("commentId");
            console.log(commentId);
//            var comment ={
//                "id":commentId
//            };
//debugger;
            $.ajax({
                type: "POST",
                data: {"commentId": commentId},
                url: "../delete-comment",
                success: function () {
                    console.log("success");
                    window.location.reload();

                },
                error: function () {
                    console.log("error");
                }


            });

        });


        switch (role) {

            case "ROLE_DEPTOR":

                $(".comments").not(".to-deptor-comments").hide();
//debugger;
                var commentsList = $("#PVI-button").parent().next().find("ul");
                var length = commentsList.children().length;
                var PVICOmmentsLength = +"${PVIComments.size()}";
                if (length < 2 && PVICOmmentsLength > 0) {
                    var $p = $("<li><p>No comments for PVI</p></li>")
                    commentsList.prepend($p);
                }

                if (status === "Waiting for approval") {
                    $(".to-deptor-comments").find(".add-comment-btn").not("#PVI-button").show();
                    console.log("waiting for approval>>>");
                }
                break;
            case "ROLE_CUSTOMER":
                $(".comments").not(".to-customer-comments").hide();
//                    debugger;
                var commentsList = $("#debtor-button").parent().next().find("ul");
                var debtorCommentsLength = +"${DeptorComments.size()}";
                var length = commentsList.children().length;
                if (length == 0 && debtorCommentsLength > 0) {

                    var $p = $("<li><p>No comments for debtor</p></li>")
                    commentsList.prepend($p);
                }
                if (status === "Waiting for approval")
                    $(".to-customer-comments").find(".add-comment-btn").not("#debtor-button").show();
                break;
            case "ROLE_PVI":
                $("#DTP-button").show();
                $("#PVI-button").show();

                if (status === "Proof request set up" || status === "Proof requested" || status === "Check by PVI" || status === "Rejected") {
                    $(".add-comment-btn").show();
                    console.log("rejected>>>");
                }

                break;
            case "ROLE_DTP":
                $("#PVI-button").show();
                console.log($("#PVI-button"));
                console.log($("#DTP-button"));
                if (status === "Proof requested" || status === "Approved")
                    $(".add-comment-btn").not("#production-button").show();

                break;
        }

        if (statusId >= 5 && status !== "Rejected")
            $(".add-comment-btn").hide();

        $(".comment-container").hover(function () {

            $(this).find(".edit-comment").show();
            $(this).find(".delete-comment").show();
        }, function () {
            $(this).find(".edit-comment").hide();
            $(this).find(".delete-comment").hide();
        });

        $(".commentText").each(function () {
            var unread = $(this).attr("data-unread");
            console.log(unread);
            if (unread === "true")
                $(this).parent().css("color", "#29abe2");
        });


    });

</script>
