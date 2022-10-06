<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/4/2022
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page='../bootstrap/bootstrap.jsp'>
    <jsp:param name="articleId" value=""/>
</jsp:include>
<html>
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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<head>
    <title>Title</title>
</head>
<body>


<section class="vh-100" style="background-color: #f4f5f7;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-lg-6 mb-4 mb-lg-0">
                <div class="card mb-3" style="border-radius: .5rem;">
                    <div class="row g-0">
                        <div class="col-md-4 gradient-custom text-center text-black"
                             style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem; padding: 240px 0; border: 3px solid ; text-align: center; ">
                            <c:if test='${requestScope["avatar"]==null}'>
                                    <a href="users?action=change_avatar"><button type="button" class="btn btn-primary">Change avatar</button></a>
                            </c:if>

                            <c:if test='${requestScope["avatar"]!=null}'>

                                <img width="120px" height="120px" style="border-radius: 50%"
                                     src="${requestScope["avatar"]}" alt="">

                            </c:if>
                            <c:if test="${user.name!=null}">
                                <h4> ${user.name}</h4>
                            </c:if>
                            <p>Web Designer</p>
                            <i class="far fa-edit mb-5"></i>

                            <%--                            <c:if test="${sessionScope['role'] == 'ADMIN' }">--%>
                            <%--                                menu admin--%>
                            <%--                            </c:if>--%>
                        </div>
                        <div class="col-md-8" style="padding: 70px 0; border: 3px solid ; text-align: center; ">
                            <div class="card-body p-4">
                                <h6><i class="bi bi-info-circle-fill"></i> Information</h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6><i class="bi bi-envelope-fill"></i> Email</h6>
                                        <c:if test="${user.email!=null}">
                                            <p >${user.email}</p>
                                        </c:if>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6>Phone</h6>
                                        <p class="text-muted">123 456 789</p>
                                    </div>
                                </div>
                                <h6><i class="bi bi-kanban"></i> Projects : Music web</h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6>Recent</h6>
                                        <p class="text-muted">Lorem ipsum</p>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6>Most Viewed</h6>
                                        <p class="text-muted">Dolor sit amet</p>
                                    </div>
                                </div>
                                <%--                                <div class="d-flex justify-content-start">--%>
                                <%--                                    <a href="#!"><i class="fab fa-facebook-f fa-lg me-3"></i></a>--%>
                                <%--                                    <a href="#!"><i class="fab fa-twitter fa-lg me-3"></i></a>--%>
                                <%--                                    <a href="#!"><i class="fab fa-instagram fa-lg"></i></a>--%>
                                <%--                                </div>--%>
                                <div style="padding: 10px">
                                    <c:if test='${requestScope["success"] != null}'>
                                        <p style="color: lightseagreen">${requestScope['success']}</p>
                                    </c:if>
                                    <a href="users?action=change_pass">
                                        <button type="button" class="btn btn-primary">Change password</button>
                                    </a>

                                    <a href="users?action=change_profile">
                                        <button type="button" class="btn btn-primary">Change profile</button>
                                    </a>
                                </div>

                                <div>
                                    <a href="users?action=logout">
                                        <button type="button" class="btn btn-primary">Log out</button>
                                    </a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
