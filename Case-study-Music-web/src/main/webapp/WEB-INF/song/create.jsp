<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Minh Duc
  Date: 10/6/2022
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
</head>
<body>
<h1>FORM CREATE SONG</h1>
<c:if test='${requestScope["message"]!=null}'>
    <span style="color: blue">${requestScope["message"]}</span>
</c:if>
<form  method="post">
    <label>NAME</label><br>
    <input type="text" name="name"><br>
    <label>LISTEN</label>
    <br>
    <input type="text" name="listen">
    <br>
    <jsp:include page='../upload/upload_audio.jsp'>
        <jsp:param name="articleId" value=""/>
    </jsp:include>

    <br>
    <select id="category" name="category">
        <c:forEach items='${requestScope["category"]}' var="st">
            <option value="${st.id}"> ${st.name}</option>
        </c:forEach>
            </select>
    <select multiple id="singer" name="singer">
        <c:forEach items='${requestScope["singer"]}' var="sg">
            <option value="${sg.id}"> ${sg.name}</option>
        </c:forEach>
    </select>
    <select multiple id="band" name="band">
        <c:forEach items='${requestScope["band"]}' var="bd">
            <option value="${bd.id}">${bd.name}</option>
        </c:forEach>
    </select>
    <button type="submit">Create</button>
    <a href="/song">Back Menu</a>
</form>
</body>
</html>
