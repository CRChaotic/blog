<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/header.jsp">
        <jsp:param name="title" value="Login"/>
    </jsp:include>
</head>
<body>
<%@include file="/templates/navigation.jsp"%>
<div class="container">
    <form class="col-sm-6 mt-5 mx-auto" method="post" action="${pageContext.request.contextPath}/login">
        <h1 class="mb-5">LOGIN</h1>
        <p class="text-danger">${sessionScope.get("message")}</p>
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Username</label>
            <input name="username" type="text" class="form-control" id="exampleInputEmail1">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input name="password" type="password" class="form-control" id="exampleInputPassword1">
        </div>
        <button type="submit" class="btn btn-outline-primary">Login</button>
    </form>
</div>
<%@include file="/templates/footer.jsp"%>
</body>
</html>
