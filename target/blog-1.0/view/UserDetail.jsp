<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="row g-3 mt-5">
    <div class="col-md-6">
        <label for="inputUsername" class="form-label">Username</label>
        <input value="${param.get("username")}" type="text" class="form-control" disabled id="inputUsername">
    </div>
    <div class="col-md-6">
        <label for="inputEmail" class="form-label">Email</label>
        <input value="${param.get("email")}" type="email" class="form-control" id="inputEmail">
    </div>
    <div class="col-md-6">
        <label for="inputPassword" class="form-label">Password</label>
        <input type="password" value="${param.get("password")}" class="form-control" id="inputPassword">
    </div>
    <div class="col-12">
        <label for="inputAddress" class="form-label">Address</label>
        <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
    </div>
    <div class="col-md-6">
        <label for="inputCity" class="form-label">City</label>
        <input type="text" class="form-control" id="inputCity">
    </div>
    <div class="col-md-4">
        <label for="inputState" class="form-label">State</label>
        <select id="inputState" class="form-select">
            <option selected>Choose...</option>
            <option>...</option>
        </select>
    </div>
    <div class="col-md-2">
        <label for="inputZip" class="form-label">Zip</label>
        <input type="text" class="form-control" id="inputZip">
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-outline-danger">SAVE</button>
    </div>
</form>
