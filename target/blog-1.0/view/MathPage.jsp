<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="/templates/header.jsp">
    <jsp:param name="title" value="Math"/>
</jsp:include>
<body>
<%@include file="/templates/navigation.jsp" %>
<div class="container mt-5">
    <c:forEach var="article" items="${articles}">
        <div class="list-group col-lg-8 mx-auto">
            <a href="${pageContext.request.contextPath}/math?id=${article.id}"
               class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">${article.title}</h5>
                    <small>

                    </small>
                </div>
                <p class="mb-1">${article.subtitle}</p>
                <small> on ${article.createdTime} by ${article.owner}</small>
            </a>
        </div>
    </c:forEach>
</div>
<%@include file="/templates/footer.jsp" %>
</body>
</html>
