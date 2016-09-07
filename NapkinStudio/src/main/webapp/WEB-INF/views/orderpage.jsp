<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    //    console.log(user);
    //    console.log(userOrder);

</script>
<html>
<head>

    <title>NapkinStudio</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
    <%--TODO: dowload files and set path--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <nav display="inline">
        <a href="<c:url value="/orders"/>" class="btn btn-primary custom-width"> to orders </a>
        <span style="margin-left: 100px">  order id ${theOrder.orderId}</span>
        <%--<p display="inline">You are logged in as ${user.roles[0].name}</p>--%>
        <a href="<c:url value="/logout"/>" class="btn btn-danger custom-width"> Logout </a>
    </nav>
    <%--<nav display="inline">--%>
    <%--<a href="<c:url value="/orders"/>"  btn btn-primary> to orders </a>--%>
    <%--<span style="margin-left: 100px"> ${theOrder.orderId} order id </span>--%>
    <%--&lt;%&ndash;<p display="inline">You are logged in as ${user.roles[0].name}</p>&ndash;%&gt;--%>
    <%--</nav>--%>%--<a href="<c:url value="/logout"/>"> Logout </a>--%>
    <
</head>
<body>

<div>

    <ul style="list-style-type:none">
        <c:forEach items="${PVIComments}" var="PVIComment">
            <li>
                <c:out value="${PVIComment.commText}"/>
                    <%--<c:out value="${status.name}" />--%>
            </li>
        </c:forEach>
    </ul>

</div>

<div>

    <ul style="list-style-type:none">
        <c:forEach items="${DeptorComments}" var="DeptorComment">
            <li>
                <c:out value="${DeptorComment.commText}"/>
                    <%--<c:out value="${status.name}" />--%>
            </li>
        </c:forEach>
    </ul>

</div>
<div>

    <ul style="list-style-type:none">
        <c:forEach items="${DTPComments}" var="DTPComment">
            <li>
                <c:out value="${DTPComment.commText}"/>
                    <%--<c:out value="${status.name}" />--%>
            </li>
        </c:forEach>
    </ul>

</div>
<div>

    <ul style="list-style-type:none">
        <c:forEach items="${CustomerComments}" var="CustomerComment">
            <li>
                <c:out value="${CustomerComment.commText}"/>
                    <%--<c:out value="${status.name}" />--%>
            </li>
        </c:forEach>
    </ul>

</div>
<div>

    <ul style="list-style-type:none">
        <c:forEach items="${StampsManufacComments}" var="StampsManufacComment">
            <li>
                <c:out value="${StampsManufacComment.commText}"/>
                    <%--<c:out value="${status.name}" />--%>
            </li>
        </c:forEach>
    </ul>

</div>
<div>

    <ul style="list-style-type:none">
        <c:forEach items="${ProductionComments}" var="ProductionComment">
            <li>
                <c:out value="${ProductionComment.commText}"/>
                    <%--<c:out value="${status.name}" />--%>
            </li>
        </c:forEach>
    </ul>
</div>


<table width="100%" border="0">
    <tr valign="top">
        <td width="10%">
        </td>
        <td width="65%" align="left">
            <div style="margin-bottom: 10px">
                <%--stautus                 <c:out value="${theOrder.SAPstatus.statusSAPStatuseRoles[0].status.name}" />--%>


            </div>

            <%--6.3 Progressbar/////////////////////////////////////////////////--%>
            <table border="0" style="margin-bottom: 10px">
                <tr valign="top">
                    <th>
                        <b>Status</b>
                    </th>
                    <th>
                        <b>Date</b>
                    </th>

                </tr>

                <c:forEach items="${barFields}" var="barFields">
                    <tr>
                        <td>
                            <c:out value="${barFields[0]}"/>
                        </td>
                        <td>
                            <c:out value="${barFields[1]}"/>
                        </td>
                            <%--<td>--%>
                            <%--<c:out value="${barFields[2]}" />--%>
                            <%--</td>--%>
                    </tr>
                </c:forEach>
            </table>

            <%--6.4-5 Order and Article info/////////////////////////////////////////////////--%>
            <table width="100%" border="0" style="margin-bottom: 10px">
                <tr valign="top">
                    <th width="50%" colspan="2">
                        <b>Order Informatie</b>
                    </th>
                    <th width="50%" colspan="2">
                        <b>Artikel informatie</b>
                    </th>
                </tr>
                <tr valign="top">
                    <td width="17%">
                        debtor number
                    </td>
                    <td width="33%">
                        ${theOrder.debNum}
                    </td>
                    <td width="17%">
                        Item number
                    </td>
                    <td width="33%">
                        ${theOrder.itemNum}
                    </td>
                </tr>
                <tr>
                    <td>Debtor Order Number</td>
                    <td>${theOrder.debNum}</td>
                    <td>Debtor item number</td>
                    <td>${theOrder.debItemNum}</td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td>${theOrder.quantity} stuks</td>
                    <td>Size</td>
                    <td>${theOrder.size}</td>
                </tr>
                <tr>
                    <td>Approval by</td>
                    <td>${theOrder.approvalBy}</td>
                    <td>Material</td>
                    <td>${theOrder.material}</td>
                </tr>
                <tr>
                    <td>Debtor contact</td>
                    <td>${theOrder.debCont}</td>
                    <td>folding method</td>
                    <td>${theOrder.foldingMeth}</td>
                </tr>
                <tr>
                    <td>Delivery address</td>
                    <td>${theOrder.delivAddr}</td>
                    <td>napkin colour</td>
                    <td>${theOrder.napkinCol}</td>
                </tr>
                <tr>
                    <td>Delivery address contact</td>
                    <td>${theOrder.delivAddrCont}</td>
                    <td>Print colour 1</td>
                    <td>${theOrder.color1}</td>
                </tr>
                <tr>
                    <td>Unloading times</td>
                    <td>${theOrder.unloadTimes}</td>
                    <td>Print colour 2</td>
                    <td>${theOrder.color2}</td>
                </tr>
                <tr>
                    <td>Delivery instructions</td>
                    <td>${theOrder.delivInstruct}</td>
                    <td>Print colour 3</td>
                    <td>${theOrder.color3}</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>Print colour 4</td>
                    <td>${theOrder.color4}</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>Print colour 4</td>
                    <td>${theOrder.color4}</td>
                </tr>
            </table>

            <%--6.7 Print proof/////////////////////////////////////////////////--%>
            <%--TODO: Change condition--%>
            <c:if test="${theOrder.debNum==1}">
                <div width="100%" style="margin-bottom: 10px">
                    <b>Drukproef</b>
                </div>
            </c:if>

            <%--6.6 Comments/////////////////////////////////////////////////--%>
            <div width="100%" style="margin-bottom: 10px">
                <b>Opmerkingen</b>
                <ul width="100%" style="list-style-type:none">
                    <li width="100%">
                        <table width="100%">
                            <td width="17%">
                                <b>Voor PVI</b>
                            </td>
                            <td>
                                <ul style="list-style-type:none">
                                    <li>
                                        <span>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ut nisl neque. Pellentesque sollicitudin feugiat porttitor. Duis hendrerit, neque sed mattis placerat, velit massa ultrices mi, ac fermentum metus eros a quam.
                                        </span>
                                        <div>Bunzl - 14 maart 9:10</div>
                                    </li>
                                </ul>
                            </td>
                        </table>
                    </li>
                    <li width="100%">
                        <table width="100%">
                            <td width="17%">
                                <b>Voor DTP</b>
                            </td>
                            <td>
                                <ul style="list-style-type:none">
                                    <li>
                                        <div>Nog geen opmerking voor DTP’er</div>
                                    </li>
                                </ul>
                            </td>
                        </table>
                    </li>
                </ul>
            </div>
            <div width="100%" style="margin-bottom: 10px">
                <b>Opmerkingen</b>
                <ul>
                    <li>some files</li>
                </ul>
            </div>
            <input type="checkbox" name="check0" value="a2">Drukproef eerst door PVI laten controleren.<Br>
            <div display="inline" style="margin-bottom: 10px">

                <%--Appove without comments and files adding--%>
                <c:if test="${
                            (theOrder.SAPstatus.id==3&&user.roles[0].id==2&&orderPviCheck)||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Deptor')||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&!theOrder.debCheckScen)||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==1)||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==2)||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Customer'&&theOrder.processId==3)||
                            (theOrder.SAPstatus.id==5&&user.roles[0].id==2&&theOrder.processId==4)
                 }">
                    <a href="<c:url value='/changestatus/${theOrder.orderId}/yes' />"
                       class="btn btn-success custom-width">Approve</a>
                </c:if>
                <%--Discard without comments and files adding--%>
                <c:if test="${
                            (theOrder.SAPstatus.id==3&&user.roles[0].id==2&&orderPviCheck)||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Customer'&&theOrder.processId==3)
                 }">
                    <a href="<c:url value='/changestatus/${theOrder.orderId}/no' />"
                       class="btn btn-danger custom-width">Discard</a>
                </c:if>
                <%--Appove with comments and files adding--%>
                <c:if test="${
                            ((theOrder.SAPstatus.id==1||theOrder.SAPstatus.id==6)&&user.roles[0].id==2)||
                            (theOrder.SAPstatus.id==2&&user.roles[0].id==4)||
                            (theOrder.SAPstatus.id==5&&user.roles[0].id==4&&theOrder.processId==5)
                 }">
                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal"
                            data-target="#approveOrder">Approve
                    </button>
                    <div class="modal fade" id="approveOrder" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Appove</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Some text in the modal.</p>
                                </div>
                                <div class="modal-footer">
                                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                                    <a href="<c:url value='/changestatus/${theOrder.orderId}/yes' />"
                                       class="btn btn-success custom-width" data-dismiss="modal">Approve</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:if>
                <%--Discard with comments and files adding--%>
                <c:if test="${
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Deptor')||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&!theOrder.debCheckScen)||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==1)||
                            (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&theOrder.processId==2)||
                            (theOrder.SAPstatus.id==6&&user.roles[0].id==2)
                 }">
                    <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#discardModal">
                        Discard
                    </button>
                    <div class="modal fade" id="discardModal" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Discard</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Some text in the modal.</p>
                                </div>
                                <div class="modal-footer">
                                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                                    <a href="<c:url value='/changestatus/${theOrder.orderId}/no' />"
                                       class="btn btn-danger custom-width" data-dismiss="modal">Discard</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:if>


            </div>


        </td>
        <td width="25%">
        </td>
    </tr>
</table>


<%--</div>--%>
<%--<div>--%>
<%--${user.roles[0].name}--%>
<%--${theOrder.sapStatus.name}--%>

<%--</div>--%>
<%--<ul style="list-style-type:none">--%>
<%--<c:forEach items="${userOrders}" var="userOrder">--%>
<%--<li>--%>
<%--<c:out value="${userOrder.order.SAPstatus.statusSAPStatuseRoles[0].status.name}" />--%>
<%--&lt;%&ndash;<c:out value="${status.name}" />&ndash;%&gt;--%>
<%--</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
</body>
</html>
