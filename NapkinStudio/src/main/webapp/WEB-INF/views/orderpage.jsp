<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
//    console.log(user);
//    console.log(userOrder);

</script>
<html>
<head>
    <title>NapkinStudio</title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />

    <nav display="inline">
        <a href="<c:url value="/orders"/>"   class="btn btn-primary custom-width"> to orders </a>
        <span style="margin-left: 100px">  order id ${theOrder.orderId}</span>
        <%--<p display="inline">You are logged in as ${user.roles[0].name}</p>--%>
        <a href="<c:url value="/logout"/>" class="btn btn-danger custom-width"> Logout </a>
    </nav>
    <%--<nav display="inline">--%>
        <%--<a href="<c:url value="/orders"/>"  btn btn-primary> to orders </a>--%>
        <%--<span style="margin-left: 100px"> ${theOrder.orderId} order id </span>--%>
        <%--&lt;%&ndash;<p display="inline">You are logged in as ${user.roles[0].name}</p>&ndash;%&gt;--%>
        <%--<a href="<c:url value="/logout"/>"> Logout </a>--%>
    <%--</nav>--%>
</head>
<body>
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
                <th >
                    <b>Status</b>
                </th>
                <th >
                    <b>Date</b>
                </th>

            </tr>

        <c:forEach items="${barFields}" var="barFields">
            <tr>
                <td>
                    <c:out value="${barFields[0]}" />
                </td>
                <td>
                    <c:out value="${barFields[1]}" />
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
                <tr > <td >Debtor Order Number</td><td >${theOrder.debNum}</td>         <td >Debtor item number</td><td >${theOrder.debItemNum}</td></tr>
                <tr > <td >Quantity</td><td >${theOrder.quantity}  stuks</td>           <td >Size</td><td >${theOrder.size}</td></tr>
                <tr > <td >Approval by</td><td >${theOrder.approvalBy}</td>             <td >Material</td><td >${theOrder.material}</td></tr>
                <tr > <td >Debtor contact</td><td >${theOrder.debCont}</td>             <td >folding method</td><td >${theOrder.foldingMeth}</td></tr>
                <tr > <td >Delivery address</td><td >${theOrder.delivAddr}</td>         <td >napkin colour</td><td >${theOrder.napkinCol}</td></tr>
                <tr > <td >Delivery address contact</td><td >${theOrder.delivAddrCont}</td><td >Print colour 1</td><td >${theOrder.color1}</td></tr>
                <tr > <td >Unloading times</td><td >${theOrder.unloadTimes}</td>        <td >Print colour 2</td><td >${theOrder.color2}</td></tr>
                <tr > <td >Delivery instructions</td><td >${theOrder.delivInstruct}</td><td >Print colour 3</td><td >${theOrder.color3}</td></tr>
                <tr > <td >                     </td><td >                  </td>       <td >Print colour 4</td><td >${theOrder.color4}</td></tr>
                <tr > <td >                     </td><td >                  </td>       <td >Print colour 4</td><td >${theOrder.color4}</td></tr>
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

                <%--TODO: Change&check conditions--%>
                    <c:if test="${(theOrder.SAPstatus.id==3&&user.roles[0].id==4&&theOrder.pVIcheckScen)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Deptor')||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.debCheckScen)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.toDeptor)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&!theOrder.debCheckScen)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&!theOrder.toDeptor)
                }">
                    <a href="<c:url value='/changestatus/${theOrder.orderId}/yes' />"  class="btn btn-success custom-width">Approve</a>

               </c:if>
                    <%--${theOrder.SAPstatus.id}--%>
                    <%--${user.roles[0].id}--%>
                <%--<c:if test="${(theOrder.SAPstatus.id==3&&theOrder.pVIcheckScen&&user.roles[0].id==4)}">--%>
                <c:if test="${(theOrder.SAPstatus.id==3&&user.roles[0].id==4&&theOrder.pVIcheckScen)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.approvalBy=='Deptor')||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.debCheckScen)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==1&&theOrder.toDeptor)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&!theOrder.debCheckScen)||
                              (theOrder.SAPstatus.id==4&&user.roles[0].id==5&&theOrder.approvalBy=='Customer'&&theOrder.debCheckScen&&!theOrder.toDeptor)
                }">
                    <a href="<c:url value='/changestatus/${theOrder.orderId}/no' />"  class="btn btn-danger custom-width">Discard</a>
                </c:if>

                Testing:
                <a href="<c:url value='/changestatus/${theOrder.orderId}/yes' />"  class="btn btn-success custom-width">Approve</a>
                <a href="<c:url value='/changestatus/${theOrder.orderId}/no' />"  class="btn btn-danger custom-width">Discard</a>
                <%--<c:url value='/changestatus/' var="statuschange1">--%>
                   <%--<c:param name="orderId" value="${theOrder.orderId}"/>--%>
                   <%--<c:param name="answer" value="yes"/>--%>
                <%--</c:url>--%>
                <%--<c:import url='${statuschange1}'/>--%>
                <%--<c:out value="<c:import url='${statuschange1}'/>" />--%>

                <%--<a href="<c:import url='${statuschange1}'/>" class="btn btn-success custom-width">Approve</a>--%>

                <%--<a href="<c:url value='/changestatus/' var="statuschange1">--%>
                           <%--<c:param name="orderId" value="${theOrder.orderId}"/>--%>
                           <%--<c:param name="answer" value="yes"/>--%>
                         <%--</c:url>" class="btn btn-success custom-width">Approve</a>--%>
                <%--<a href="<c:url value='/changestatus/' var="statuschange0">--%>
                           <%--<c:param name="orderId" value="${theOrder.orderId}"/>--%>
                           <%--<c:param name="answer" value="no"/>--%>
                         <%--</c:url>" class="btn btn-danger custom-width">Discard</a>--%>



            </div>


        </td>
        <td width="25%">
        </td>
    </tr>
</table>
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
