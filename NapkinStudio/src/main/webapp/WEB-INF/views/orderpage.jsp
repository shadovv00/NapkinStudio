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
    <%--</nav>%--<a href="<c:url value="/logout"/>"> Logout </a>--%>

</head>
<body style="overflow-y: auto">

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
                    <li width="100%" class="comments">
                        <table width="100%">
                            <td width="17%" style="vertical-align: top">
                                <b>Voor PVI</b>
                            </td>
                            <td  width="3%" style="vertical-align: top">
                            <a id="PVI-button" class="btn add-comment-btn">Add comment</a>
                        </td>
                            <td>
                                <ul style="list-style-type:none">
                                    <c:forEach items="${PVIComments}" var="PVIComment">
                                        <li>
                                            <c:out value="${PVIComment.commText}"/>
                                                <%--<c:out value="${status.name}" />--%>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <%--<c:if test="${param.success eq true }">--%>
                                        <%--<div class="row text-center">--%>
                                        <%--<div class="col-md-offset-4 col-md-5">--%>
                                        <%--<div class="alert alert-success">--%>
                                        <%--Comment was added--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</c:if>--%>
                                        <form:form commandName="comment" method="post" action="/addComment"
                                                   cssClass="form-horizontal addCommentForm">
                                            <div class="form-group">
                                                <div class="col-sm-5">
                                                    <form:textarea path="commText"
                                                                   cssClass="form-control"></form:textarea>
                                                    <form:hidden path="forRole.id"
                                                                 value="2"/>
                                                    <form:hidden path="order.orderId"
                                                                 value="${theOrder.orderId}"/>
                                                    <form:hidden path="fromUser.userId"
                                                                 value="${user.userId}"/>
                                                        <%--<input name="deleted" type="hidden" value="true"/>--%>
                                                </div>
                                            </div>
                                            <%--<div class="modal-footer col-sm-5">--%>
                                            <div class="col-sm-5">
                                                <input type="submit"
                                                       value="Save"
                                                       class="btn btn-large btn-primary" style="float: right;">
                                            </div>
                                            <%--</div>--%>
                                        </form:form>
                                    </li>
                                </ul>


                            </td>
                        </table>
                    </li>
                    <li width="100%" class="comments">
                        <table width="100%">
                            <td width="17%" style="vertical-align: top">
                                <b>Voor DTP</b>
                            </td>
                            <td width="3%" style="vertical-align: top">
                                <a id="DTP-button" class="btn add-comment-btn">Add comment</a>
                            </td>
                            <td>


                                <ul style="list-style-type:none">
                                    <c:forEach items="${DTPComments}" var="DTPComment">
                                        <li>
                                            <c:out value="${DTPComment.commText}"/>
                                                <%--<c:out value="${status.name}" />--%>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <%--<c:if test="${param.success eq true }">--%>
                                        <%--<div class="row text-center">--%>
                                        <%--<div class="col-md-offset-4 col-md-5">--%>
                                        <%--<div class="alert alert-success">--%>
                                        <%--Comment was added--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</c:if>--%>
                                        <form:form commandName="comment" method="post" action="/addComment"
                                                   cssClass="form-horizontal addCommentForm">
                                            <div class="form-group">
                                                <div class="col-sm-5">
                                                    <form:textarea path="commText"
                                                                   cssClass="form-control"></form:textarea>
                                                    <form:hidden path="forRole.id"
                                                                 value="4"/>
                                                    <form:hidden path="order.orderId"
                                                                 value="${theOrder.orderId}"/>
                                                    <form:hidden path="fromUser.userId"
                                                                 value="${user.userId}"/>
                                                        <%--<input name="deleted" type="hidden" value="true"/>--%>
                                                </div>
                                            </div>
                                            <%--<div class="modal-footer col-sm-5">--%>
                                            <div class="col-sm-5">
                                                <input type="submit"
                                                       value="Save"
                                                       class="btn btn-large btn-primary" style="float: right;">
                                            </div>
                                            <%--</div>--%>
                                        </form:form>
                                    </li>
                                </ul>


                            </td>
                        </table>
                    </li>
                    <li width="100%" class="comments to-deptor-comments">
                        <table width="100%">
                            <td width="17%" style="vertical-align: top">
                                <b>Voor debiteur</b>
                            </td>
                            <td width="3%" style="vertical-align: top">
                                <a class="btn add-comment-btn">Add comment</a>
                            </td>
                            <td>
                                <ul style="list-style-type:none">
                                    <c:forEach items="${DeptorComments}" var="DeptorComment">
                                        <li>
                                            <c:out value="${DeptorComment.commText}"/>
                                                <%--<c:out value="${status.name}" />--%>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <%--<c:if test="${param.success eq true }">--%>
                                        <%--<div class="row text-center">--%>
                                        <%--<div class="col-md-offset-4 col-md-5">--%>
                                        <%--<div class="alert alert-success">--%>
                                        <%--Comment was added--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</c:if>--%>
                                        <form:form commandName="comment" method="post" action="/addComment"
                                                   cssClass="form-horizontal addCommentForm">
                                            <div class="form-group">
                                                <div class="col-sm-5">
                                                    <form:textarea path="commText"
                                                                   cssClass="form-control"></form:textarea>
                                                    <form:hidden path="forRole.id"
                                                                 value="1"/>
                                                    <form:hidden path="order.orderId"
                                                                 value="${theOrder.orderId}"/>  <form:hidden path="fromUser.userId"
                                                                                                             value="${user.userId}"/>
                                                        <%--<input name="deleted" type="hidden" value="true"/>--%>
                                                </div>
                                            </div>
                                            <%--<div class="modal-footer col-sm-5">--%>
                                            <div class="col-sm-5">
                                                <input type="submit"
                                                       value="Save"
                                                       class="btn btn-large btn-primary" style="float: right;">
                                            </div>
                                            <%--</div>--%>
                                        </form:form>
                                    </li>
                                </ul>


                            </td>
                        </table>
                    </li>
                    <li width="100%" class="comments to-deptor-comments to-customer-comments">
                        <table width="100%">
                            <td width="17%" style="vertical-align: top">
                                <b>Voor eindafnemer</b>
                            </td>
                            <td width="3%" style="vertical-align: top">
                                <a class="btn add-comment-btn">Add comment</a>
                            </td>
                            <td>
                                <ul style="list-style-type:none">
                                    <c:forEach items="${CustomerComments}" var="CustomerComment">
                                        <li>
                                            <c:out value="${CustomerComment.commText}"/>
                                                <%--<c:out value="${status.name}" />--%>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <%--<c:if test="${param.success eq true }">--%>
                                        <%--<div class="row text-center">--%>
                                        <%--<div class="col-md-offset-4 col-md-5">--%>
                                        <%--<div class="alert alert-success">--%>
                                        <%--Comment was added--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</c:if>--%>
                                        <form:form commandName="comment" method="post" action="/addComment"
                                                   cssClass="form-horizontal addCommentForm">
                                            <div class="form-group">
                                                <div class="col-sm-5">
                                                    <form:textarea path="commText"
                                                                   cssClass="form-control"></form:textarea>
                                                    <form:hidden path="forRole.id"
                                                                 value="5"/>
                                                    <form:hidden path="order.orderId"
                                                                 value="${theOrder.orderId}"/>  <form:hidden path="fromUser.userId"
                                                                                                             value="${user.userId}"/>
                                                        <%--<input name="deleted" type="hidden" value="true"/>--%>
                                                </div>
                                            </div>
                                            <%--<div class="modal-footer col-sm-5">--%>
                                            <div class="col-sm-5">
                                                <input type="submit"
                                                       value="Save"
                                                       class="btn btn-large btn-primary" style="float: right;">
                                            </div>
                                            <%--</div>--%>
                                        </form:form>
                                    </li>
                                </ul>


                            </td>
                        </table>
                    </li>
                    <li width="100%" class="comments">
                        <table width="100%">
                            <td width="17%" style="vertical-align: top">
                                <b>Voor stamp manufacturer</b>
                            </td>
                            <td width="3%" style="vertical-align: top">
                                <a class="btn add-comment-btn">Add comment</a>
                            </td>
                            <td>
                                <ul style="list-style-type:none">
                                    <c:forEach items="${StampsManufacComments}" var="StampsManufacComment">
                                        <li>
                                            <c:out value="${StampsManufacComment.commText}"/>
                                                <%--<c:out value="${status.name}" />--%>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <%--<c:if test="${param.success eq true }">--%>
                                        <%--<div class="row text-center">--%>
                                        <%--<div class="col-md-offset-4 col-md-5">--%>
                                        <%--<div class="alert alert-success">--%>
                                        <%--Comment was added--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</c:if>--%>
                                        <form:form commandName="comment" method="post" action="/addComment"
                                                   cssClass="form-horizontal addCommentForm">
                                            <div class="form-group">
                                                <div class="col-sm-5">
                                                    <form:textarea path="commText"
                                                                   cssClass="form-control"></form:textarea>
                                                    <form:hidden path="forRole.id"
                                                                 value="6"/>
                                                    <form:hidden path="order.orderId"
                                                                 value="${theOrder.orderId}"/>  <form:hidden path="fromUser.userId"
                                                                                                             value="${user.userId}"/>
                                                        <%--<input name="deleted" type="hidden" value="true"/>--%>
                                                </div>
                                            </div>
                                            <%--<div class="modal-footer col-sm-5">--%>
                                            <div class="col-sm-5">
                                                <input type="submit"
                                                       value="Save"
                                                       class="btn btn-large btn-primary" style="float: right;">
                                            </div>
                                            <%--</div>--%>
                                        </form:form>
                                    </li>
                                </ul>


                            </td>
                        </table>
                    </li>
                    <li width="100%" class="comments">
                        <table width="100%">
                            <td width="17%" style="vertical-align: top">
                                <b>Voor production</b>
                            </td>
                            <td width="3%" style="vertical-align: top">
                                <a class="btn add-comment-btn" id="production-button" >Add comment</a>
                            </td>
                            <td>
                                <ul style="list-style-type:none">
                                    <c:forEach items="${ProductionComments}" var="ProductionComment">
                                        <li>
                                            <c:out value="${ProductionComment.commText}"/>
                                                <%--<c:out value="${status.name}" />--%>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <%--<c:if test="${param.success eq true }">--%>
                                        <%--<div class="row text-center">--%>
                                        <%--<div class="col-md-offset-4 col-md-5">--%>
                                        <%--<div class="alert alert-success">--%>
                                        <%--Comment was added--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</c:if>--%>
                                        <form:form commandName="comment" method="post" action="/addComment"
                                                   cssClass="form-horizontal addCommentForm">
                                            <div class="form-group">
                                                <div class="col-sm-5">
                                                    <form:textarea path="commText"
                                                                   cssClass="form-control"></form:textarea>
                                                    <form:hidden path="forRole.id"
                                                                 value="7"/>
                                                    <form:hidden path="order.orderId"
                                                                 value="${theOrder.orderId}"/>  <form:hidden path="fromUser.userId"
                                                                                                                                value="${user.userId}"/>
                                                        <%--<input name="deleted" type="hidden" value="true"/>--%>
                                                </div>
                                            </div>
                                            <%--<div class="modal-footer col-sm-5">--%>
                                            <div class="col-sm-5">
                                                <input type="submit"
                                                       value="Save"
                                                       class="btn btn-large btn-primary" style="float: right;">
                                            </div>
                                            <%--</div>--%>
                                        </form:form>
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
                            (theOrder.SAPstatus.id==6&&user.roles[0].id==2)||

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
                            ((theOrder.SAPstatus.id==1)&&user.roles[0].id==2)||
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
                                    <h4 class="modal-title">Appove?</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Please add some comment and documentation</p>
                                    <div class="form-container">
                                        <form:form action='/changestatus/${theOrder.orderId}/yes' method="POST" modelAttribute="multiFileBucket" enctype="multipart/form-data" class="form-horizontal">
                                            <c:forEach var="v" varStatus="vs" items="${multiFileBucket.files}">
                                                <form:input type="file" path="files[${vs.index}].file" id="files[${vs.index}].file" class="form-control input-sm"/>
                                                <div class="has-error">
                                                    <form:errors path="files[${vs.index}].file" class="help-inline"/>
                                                </div>
                                            </c:forEach>
                                            <br/>
                                            <div class="row">
                                                <div class="form-actions floatRight">
                                                    <input type="submit" value="Approve" class="btn btn-success btn-sm">
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>

                                </div>
                                    <%--<div class="modal-footer">--%>
                                    <%--&lt;%&ndash;<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>&ndash;%&gt;--%>
                                    <%--<a href="<c:url value='/changestatus/${theOrder.orderId}/yes' />"--%>
                                    <%--class="btn btn-success custom-width">Approve</a>--%>
                                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                                    <%--<div class="form-actions floatRight">--%>
                                    <%--<input type="submit" value="Upload" class="btn btn-success btn-sm">--%>
                                    <%--</div>--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                    <%--</div>--%>
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
                                    <h4 class="modal-title">Discard?</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Please add some comment and documentation</p>
                                    <div class="form-container">
                                        <form:form action='/changestatus/${theOrder.orderId}/no' method="POST" modelAttribute="multiFileBucket" enctype="multipart/form-data" class="form-horizontal">
                                            <c:forEach var="v" varStatus="vs" items="${multiFileBucket.files}">
                                                <form:input type="file" path="files[${vs.index}].file" id="files[${vs.index}].file" class="form-control input-sm"/>
                                                <div class="has-error">
                                                    <form:errors path="files[${vs.index}].file" class="help-inline"/>
                                                </div>
                                            </c:forEach>
                                            <br/>
                                            <div class="row">
                                                <div class="form-actions floatRight">
                                                    <input type="submit" value="Discard" class="btn btn-danger btn-sm">
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>

                                </div>
                                    <%--<div class="modal-footer">--%>
                                    <%--&lt;%&ndash;<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<a href="<c:url value='/changestatus/${theOrder.orderId}/no' />"&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;class="btn btn-danger custom-width">Discard</a>&ndash;%&gt;--%>
                                    <%--<div class="form-actions floatRight">--%>
                                    <%--<input type="submit" value="Upload" class="btn btn-danger btn-sm">--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
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
</body>
</html>
<script>
    var role = "${user.roles[0].name}";
    var status = "${theOrder.sapStatus.statusSAPStatuseRoles[0].status.name}";
    var statusId = +"${theOrder.sapStatus.statusSAPStatuseRoles[0].status.id}";
    console.log(role + " " + status +" "+ statusId);
    $(document).ready(function () {
        $(".addCommentForm").hide();
        $(".add-comment-btn").hide();
        $(".add-comment-btn").click(function(){
            console.log("CLick");
            console.log($(this).parent().next().find("form"));
            $(this).parent().next().find("form").show();
        });

        switch (role) {
            case "ROLE_DEPTOR":
                $(".comments").not(".to-deptor-comments").hide();
                    if(status === "Waiting for approval") {
                        $(".comments").not(".to-deptor-comments").find(".add-comment-btn").show();
                        console.log("waiting for approval>>>");
                    }
                break;
            case "ROLE_CUSTOMER":
                $(".comments").not(".to-customer-comments").hide();
                if(status === "Waiting for approval")
                    $(".comments").not(".to-customer-comments").find(".add-comment-btn").hide();
                break;
            case "ROLE_PVI":
                $("#DTP-button").show();
                    if(status === "Proof request set up" || status === "Proof requested" || status === "Check by PVI" || status === "Rejected") {
                        $(".add-comment-btn").show();
                        console.log("rejected>>>");
                    }

                break;
            case "ROLE_DTP":
                $("#PVI-button").show();
                    console.log($("#PVI-button"));
                    console.log($("#DTP-button"));
                    if(status === "Proof requested" || status === "Approved")
                $(".add-comment-btn").not("#production-button").show();

                break;
        }

        if(statusId >= 6)
            $(".add-comment-btn").hide();
    });

</script>
