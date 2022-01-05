<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="${pageContext.request.contextPath}/math" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input class="form-control" id="title" name="title"><br>
    </div>
    <div class="mb-3">
        <label for="subtitle" class="form-label">Subtitle</label>
        <input class="form-control" id="subtitle" name="subtitle"><br>
    </div>
    <div class="mb-3">
        <label for="mathFile" class="form-label">File</label>
        <input class="form-control" name="file" type="file" id="mathFile" accept="text/html">
    </div>
    <input hidden name="operation" value="save">
    <button type="submit" class="btn btn-outline-primary">Save</button><br>
</form>

<form method="post" action="${pageContext.request.contextPath}/math">
    <label for="mathFileDelete" class="form-label">Delete MathFile By ID</label>
    <input name="id" class="form-control" id="mathFileDelete" >
    <input hidden name="operation" value="delete">
    <button type="submit" class="btn btn-outline-danger">Delete</button>
</form>

