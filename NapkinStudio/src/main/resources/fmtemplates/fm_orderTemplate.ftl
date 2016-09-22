<html>
<body>
<h3>Dear ${order.itsUsers[0].user.firstName} </h3>
<div>
    Status of your order number ${order.orderId} was changed to ${order.sapStatus.statusSAPStatuseRoles[0].status.name}
</div>
<div>
    <a href="${link}">To Order</a>
</div>
</body>
</html>