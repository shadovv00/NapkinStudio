<html>
    <body>
        <h3>Dear ${order.itsUsers[0].user.firstName} </h3>
        <div>
            Your order id is ${order.orderId}
        </div>
        <span>Status: ${order.sapStatus.statusSAPStatuseRoles[0].status.name} </span>
        <div>
        <a href="${link}">Approve</a>

        </div>
    </body>
</html>