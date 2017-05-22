<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: broadwells
  Date: 5/8/17
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring Demo</title>
</head>
<body>
<table>
    <tr>
        <td>Name</td>
        <td>No. of Riders</td>
        <td>Description</td>
    </tr>
    <c:forEach var="results" items="${product}">
        <tr>
            <td>${results.displayName}</td>
            <td>${results.capacity}</td>
            <td>${results.description}</td>
        </tr>
    </c:forEach>
    <br><br>
    <tr>
        <td>Name</td>
        <td>Duration</td>
        <td>Price</td>
    </tr>
    <c:forEach var="prices" items="${price}">
        <tr>
            <td>${prices.displayName}</td>
            <td>${prices.duration}</td>
            <td>${prices.estimate}</td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
