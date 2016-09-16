<html>
<body>
<h3>Dear ${comment.toUser.firstName} </h3>
<div>
Order number ${comment.order.orderId} was changet to status ${comment.order.SAPstatus.statusSAPStatuseRoles[0].status.name} by ${comment.fromUser.firstName} ${comment.fromUser.lastName}  role(${comment.forRole.name}) with following comment:
</div>
<span> ${comment.commText}</span>
</body>
</html>