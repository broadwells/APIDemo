<%--
  Created by IntelliJ IDEA.
  User: broadwells
  Date: 5/22/17
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Price</title>
</head>
<body>
<table>
    <tr>
        <td>Name</td>
        <td>Duration</td>
        <td>Estimate</td>
    </tr>
    <c:forEach var="prices" items="${hi}">
        <tr>
            <td>${prices.displayName}</td>
            <td>${prices.duration}</td>
            <td>${prices.estimate}</td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
