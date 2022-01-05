<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/header.jsp">
        <jsp:param name="title" value="Space"/>
    </jsp:include>
    <link href="${pageContext.request.contextPath}/quill/quill.snow.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.13.2/dist/katex.min.css"
          integrity="sha384-Cqd8ihRLum0CCg8rz0hYKPoLZ3uw+gES2rXQXycqnL5pgVQIflxAUDS7ZSjITLb5" crossorigin="anonymous">
</head>
<body>
<%@include file="/templates/navigation.jsp" %>
<div class="container-fluid">

    <div class="d-flex align-items-start">
        <div class="nav flex-column nav-pills col-md-2" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <button class="nav-link active" id="v-pills-home-tab" data-bs-toggle="pill" data-bs-target="#v-pills-post"
                    type="button" role="tab" aria-controls="v-pills-post" aria-selected="true">Post
            </button>
            <button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile"
                    type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">Profile
            </button>
            <button class="nav-link" id="v-pills-math-tab" data-bs-toggle="pill" data-bs-target="#v-pills-math"
                    type="button" role="tab" aria-controls="v-pills-math" aria-selected="false">Math
            </button>
            <button class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill" data-bs-target="#v-pills-settings"
                    type="button" role="tab" aria-controls="v-pills-settings" aria-selected="false">Settings
            </button>
        </div>

        <div class="tab-content col-md-10" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-post" role="tabpanel" aria-labelledby="v-pills-post-tab">
                <div class="col-lg-9 mx-auto">
                    <form method="post" action="${pageContext.request.contextPath}/space" id="quillForm">
                        <div class="mb-3">
                            <label for="titleInput" class="form-label">Title</label>
                            <input name="title" type="text" class="form-control" id="titleInput"
                                   placeholder="Enter title here">
                        </div>

                        <input hidden name="content" type="text" id="content">
                        <div id="toolbar-container"></div>
                        <div id="editor-container" style="height: 500px;"></div>

                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="commentable" value="0"
                                   id="flexRadioDefault1">
                            <label class="form-check-label" for="flexRadioDefault1">
                                Not commentable
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="commentable" value="1"
                                   id="flexRadioDefault2" checked>
                            <label class="form-check-label" for="flexRadioDefault2">
                                Commentable
                            </label>
                        </div>
                        <button type="submit" class="btn btn-outline-primary mt-3">SAVE</button>
                    </form>
                </div>
            </div>

            <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                <div class="col-lg-9 mx-auto">
                    <jsp:include page="/view/UserDetail.jsp">
                        <jsp:param name="username" value="${sessionScope.get('user').username}"/>
                        <jsp:param name="email" value="${sessionScope.get('user').email}"/>
                    </jsp:include>
                </div>
            </div>
            <div class="tab-pane fade" id="v-pills-math" role="tabpanel" aria-labelledby="v-pills-math-tab">
                <div class="col-lg-9 mx-auto mt-5">
                    <%@include file="/view/MathManage.jsp"%>
                </div>
            </div>
            <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                ...
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"></script>
<script src="${pageContext.request.contextPath}/quill/quill.min.js"></script>
<script src="${pageContext.request.contextPath}/js/space.js"></script>
</body>
</html>
