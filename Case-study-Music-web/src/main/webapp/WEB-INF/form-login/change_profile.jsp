<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10/6/2022
  Time: 5:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <p style="color: red">${requestScope['message']}</p>
</c:if>
<form method="post">
    Old name <br>
    <input type="text" name="oldName"><br>
    New name<br>
    <input type="text" name="newName"><br>
    Old email <br>
    <input type="text" name="oldEmail"><br>
    New email<br>
    <input type="text" name="newEmail"><br>
    <button>Submit</button>
</form>
</body>
</html>
