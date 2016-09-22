<html>
<body>
<h3>Dear ${comment.toUser.firstName} </h3>
<div>
Order number ${comment.order.orderId} was changed to status ${comment.order.SAPstatus.statusSAPStatuseRoles[0].status.name} by ${comment.fromUser.firstName} ${comment.fromUser.lastName}  role(${comment.fromUser.roles[0].name})

</div>
<span> ${comment.commText}</span>

<div>
    <a href="${link}">To Order</a>
</div>
</body>
</html>