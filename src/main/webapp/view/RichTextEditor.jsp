<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/quill/quill.snow.css" rel="stylesheet">
<%--    <link href="${pageContext.request.contextPath}/quill/quill.bubble.css" rel="stylesheet">--%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.13.2/dist/katex.min.css"
      integrity="sha384-Cqd8ihRLum0CCg8rz0hYKPoLZ3uw+gES2rXQXycqnL5pgVQIflxAUDS7ZSjITLb5" crossorigin="anonymous">

<form method="post" action="${param.get("")}" onsubmit="getContent()">
    <div class="mb-3">
        <label for="titleInput" class="form-label">Title</label>
        <input name="title" type="text" class="form-control" id="titleInput" placeholder="Enter title here">
    </div>
    <input hidden name="content" type="text" id="content">
    <div class="mb-3">
        <label for="commentableFalse" class="form-label">Commentable</label>
        <br>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="commentable" id="commentableFalse" value="0"
                   checked>
            <label class="form-check-label" for="commentableFalse">false</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="commentable" id="commentableTrue" value="1">
            <label class="form-check-label" for="commentableTrue">true</label>
        </div>
    </div>

    <div id="toolbar-container">
    <span class="ql-formats">
      <select class="ql-font"></select>
      <select class="ql-size"></select>
    </span>
        <span class="ql-formats">
      <button class="ql-bold"></button>
      <button class="ql-italic"></button>
      <button class="ql-underline"></button>
      <button class="ql-strike"></button>
    </span>
        <span class="ql-formats">
      <select class="ql-color"></select>
      <select class="ql-background"></select>
    </span>
        <span class="ql-formats">
      <button class="ql-script" value="sub"></button>
      <button class="ql-script" value="super"></button>
    </span>
        <span class="ql-formats">
      <button class="ql-header" value="1"></button>
      <button class="ql-header" value="2"></button>
      <button class="ql-blockquote"></button>
      <button class="ql-code-block"></button>
    </span>
        <span class="ql-formats">
      <button class="ql-list" value="ordered"></button>
      <button class="ql-list" value="bullet"></button>
      <button class="ql-indent" value="-1"></button>
      <button class="ql-indent" value="+1"></button>
    </span>
        <span class="ql-formats">
      <button class="ql-direction" value="rtl"></button>
      <select class="ql-align"></select>
    </span>
        <span class="ql-formats">
      <button class="ql-link"></button>
      <button class="ql-image"></button>
      <button class="ql-video"></button>
      <button class="ql-formula"></button>
    </span>
        <span class="ql-formats">
      <button class="ql-clean"></button>
    </span>
    </div>
    <div id="editor-container" style="height: 500px;"></div>
    <button type="submit" class="btn btn-outline-primary mt-3">SAVE</button>
</form>

<script src="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"></script>
<script src="${pageContext.request.contextPath}/quill/quill.min.js"></script>
<script>
    const quill = new Quill('#editor-container', {
        modules: {
            formula: true,
            syntax: true,
            toolbar: '#toolbar-container',
        },
        placeholder: 'Enter content here',
        theme: 'snow'
    });

    function getContent() {
        const contentInput = document.getElementById("content");
        contentInput.setAttribute("value", quill.root.innerHTML);
    }
</script>