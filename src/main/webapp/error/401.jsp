<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/header.jsp">
        <jsp:param name="title" value="Unauthorized"/>
    </jsp:include>
</head>
<body>
<%@include file="/templates/navigation.jsp" %>
<h1 class="display-1 text-center mt-5">401 UNAUTHORIZED</h1>
<%@include file="/templates/footer.jsp" %>
</body>
</html>