<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar sticky-top navbar-expand-lg navbar-${param.get("navbarStyle")==null?"light bg-light":param.get("navbarStyle")}">
    <div class="container-fluid col-md-10">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">HOME</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav ">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/blog">news</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/math">math</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contact">contact</a>
                </li>

                <c:choose>
                    <c:when test="${sessionScope.containsKey('user')}">
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link" href="${pageContext.request.contextPath}/user/blog">space</a>--%>
<%--                        </li>--%>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/space">space</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/logout">logout</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">login</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <form class="d-flex" method="get" action="${pageContext.request.contextPath}/blog">
                    <input name="title" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <input hidden name="article" value="-2">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </ul>
        </div>
    </div>
</nav>
