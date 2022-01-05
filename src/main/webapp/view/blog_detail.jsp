<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/header.jsp">
        <jsp:param name="title" value="${article.title}"/>
    </jsp:include>
    <link href="${pageContext.request.contextPath}/quill/quill.snow.css" rel="stylesheet">
</head>
<body>
<%@include file="/templates/navigation.jsp"%>
    <div class="container">
        <div class="row mt-5">
            <h1 class="text-center">
                ${article.title}
            </h1>
            <figcaption class="blockquote-footer text-center">${article.createdTime}
                BY <cite title="Source Title">${article.userID}</cite>
            </figcaption>
            <div class="col-lg-8 col-12 mx-auto" id="quill-container">
                ${article.content}
            </div>
            <c:if test="${sessionScope.user != null}">
                <div class="col-lg-8 col-12 mx-auto">
                    <button id="deletingArticleBtn" class="btn btn-danger" type="button">Delete</button>
                </div>
            </c:if>
        </div>
    </div>

<%@include file="/templates/footer.jsp"%>
<script src="${pageContext.request.contextPath}/quill/quill.min.js"></script>
<script>
    var quill = new Quill('#quill-container', {
        readOnly: true,
    });
    const btn = document.querySelector("#deletingArticleBtn");
    if(btn != null){
        btn.addEventListener("click",deleteArticle);
    }
    function deleteArticle(){
        const ajax = new XMLHttpRequest();
        ajax.onload = function (){
            window.location = "${pageContext.request.contextPath}/blog";
        }
        ajax.open("delete","${pageContext.request.contextPath}/article?id=${article.id}");
        ajax.send();
    }

</script>
</body>
</html>
