<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10/4/2022
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Change pass</h1>

<c:if test="${requestScope['message'] != null}">
    <p style="color: red">${requestScope['message']}</p>
</c:if>
<form method="post">
    Old password <br>
    <input type="password" name="old-pass"><br>
    New password<br>
    <input type="password" name="new-pass"><br>
    Repeat password<br>
    <input type="password" name="repeat-pass"><br>
    <button>Submit</button>
</form>
<a href="/">Home</a>
</body>
</html>
