<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
//    console.log(user);
//    console.log(userOrder);

</script>
<html>
<head>
    <title>NapkinStudio</title>
    <nav display="inline">
        <a href="<c:url value="/orders"/>" style="width: 70%"> back </a>
        <a > ${theOrder.orderId} order id </a>
        <%--<p display="inline">You are logged in as ${user.roles[0].name}</p>--%>
        <a href="<c:url value="/logout"/>"> Logout </a>
    </nav>
</head>
<body>
<table width="100%" border="0">
    <tr valign="top">
        <td width="10%">
        </td>
        <td width="65%" align="left">

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
            <%--TODO: Change condition--%>
            <c:if test="${theOrder.debNum==1}">
                <div width="100%" style="margin-bottom: 10px">
                    <b>Drukproef</b>
                </div>
            </c:if>

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
                <button type="submit" style="color:white; background : blue; border-radius:  5px;">Drukproef aanvragen</button>
                <button type="submit" style="color:white; background : grey; border-radius:  5px;">Annuleren</button>
                <input type="submit" name="approve" value="approve" />

            </div>


        </td>
        <td width="25%">
        </td>
    </tr>
</table>
<%--<div>--%>
    <%--${user.roles[0].id}--%>

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
