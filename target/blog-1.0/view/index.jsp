<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="/templates/header.jsp">
        <jsp:param name="title" value="Home"/>
    </jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <body>
    <jsp:include page="/templates/navigation.jsp">
        <jsp:param name="navbarStyle" value="dark"/>
    </jsp:include>
    <canvas id="canvas"></canvas>
    <script src="${pageContext.request.contextPath}/js/canvas.js"></script>
    </body>
</html>
