<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Minh Duc
  Date: 10/6/2022
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%="FORM CREATE SONG" %>
</h1>
<br/>
<a href="song?action=create">Create Song</a>
<form method="post">
    <input type="text" name="search">
    <button type="submit">Search</button>
</form>
<table border="1" style="width: 100%">
    <tr>
        <th>STT</th>
        <th>NAME</th>
        <th>LISTEN</th>
        <th>IMG</th>
        <th>DELETE</th>
        <th>EDIT</th>
    </tr>

    <c:forEach var="st" items='${requestScope["song"]}'>
        <tr>
            <td><a href="singer?action=detail&id=${st.id}">${st.id}</a></td>
            <td>${st.name}</td>
            <td>${st.listen}</td>
            <td><img style="width: 100px;height: 170px" src="${st.img}" alt=""></td>
            <td>
                <a href="singer?action=edit&id=${st.id}">Edit</a>
            </td>
            <td>
                <a href="singer?action=delete&id=${st.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
