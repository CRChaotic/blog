<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/header.jsp">
        <jsp:param name="title" value="Blog"/>
    </jsp:include>
</head>
<body>
<%@include file="/templates/navigation.jsp" %>
<div class="container mt-5">
    <c:forEach var="article" items="${articles}">
        <div class="list-group col-lg-8 mx-auto">
            <a href="${pageContext.request.contextPath}/blog?article=${article.id}"
               class="list-group-item list-group-item-action" >
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">${article.title}</h5>
                    <small>

                    </small>
                </div>
                <p class="mb-1">Some placeholder content in a paragraph.</p>
                <small> on ${article.createdTime}  by ${article.userID}</small>
            </a>
        </div>
    </c:forEach>
</div>
<jsp:include page="/templates/footer.jsp">
    <jsp:param name="footerText" value="footer"/>
</jsp:include>
</body>
</html>
