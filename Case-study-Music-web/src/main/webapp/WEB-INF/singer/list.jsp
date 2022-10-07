<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--    <div class="container-fluid">--%>
<%--        <a class="navbar-brand" href="#">Navbar</a>--%>
<%--        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--            <span class="navbar-toggler-icon"></span>--%>
<%--        </button>--%>
<%--        <div class="collapse navbar-collapse" id="navbarNav">--%>
<%--            <ul class="navbar-nav">--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link active" aria-current="page" href="users?action=register">Register</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link active" aria-current="page" href="users?action=login">Login</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Pricing</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>

<%--<a href="singer?action=create">Create Singer</a><br>--%>
<%--<a href="singer?action=edit">Edit Singer</a><br>--%>
<%--<a href="singer?action=delete">Delete Singer</a><br>--%>
<%--<a href="singer?action=detail">Show Singer</a><br>--%>
<%--&lt;%&ndash;<a href="singer?action=create">Show List Singer</a><br>&ndash;%&gt;--%>

<%--</body>--%>
<%--</html>--%>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/10/2022
  Time: 8:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <style>
        h1 {
            text-align: center;
            font-family: Georgia;
            height: 80px;
            box-shadow: white;
            color: white;
        }


        body {
            background-image: url("https://codetheweb.blog/assets/img/posts/css-advanced-background-images/cover.jpg");
            background-size: cover;
            width: 100%;
            height: 100vh;
            background-position: center
        }
    </style>

</head>
<body>

<div><h1 style="text-shadow: -4px 1px 7px #151313; "><%="FORM LIST SINGER" %>
</h1>
</div>

<%--<div>--%>
<%--    <button  type="button"><a href="singer?action=create" style="color: black">Create Singer</a></button>--%>
<%--    <form method="post">--%>
<%--        <input type="text" placeholder="Type here..." name="search">--%>
<%--        <button type="submit">Search</button>--%>
<%--    </form>--%>
<%--</div>--%>


<div class="container">
    <div class="row row-cols-6">
        <table class="table " border="1px" width="80%">
            <thead class="table-dark">
            <form method="post">
                <input type="text" placeholder="Type here..." name="search"><br>
                <button type="submit">Search</button>
            </form>

            <tr>
                    <a href="singer?action=create" style="color: black">
                        <button type="button">Create Singer</button>
                    </a>
            </tr>
            <tr>
                <a href="singer?action=show" style="color:black;">
                    <button type="button">Back to ListSinger</button>
                </a>
            </tr>


            <tr>
                <th scope="col">STT</th>
                <th scope="col">Name</th>
                <th scope="col">Birthday</th>
                <th scope="col">Gender</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="st" items='${requestScope["listSinger"]}'>
                <tr>
                    <td style="color: white">${st.id}</td>
                    <td style="color: white">${st.name}</td>
                    <td style="color: white">${st.birthDay}</td>
                    <td style="color: white">${st.gender}</td>

                    <td>
                        <button class="btn btn-info"><a class="link-light" href="singer?action=edit&id=${st.id}"><i
                                class="bi bi-pencil-square"></i></a></button>
                    </td>
                    <td>
                        <button class="btn btn-danger"><a class="link-light" href="singer?action=delete&id=${st.id}"><i
                                class="bi bi-recycle"></i></a></button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>

        </table>

    </div>


</div>
</body>
</html>
