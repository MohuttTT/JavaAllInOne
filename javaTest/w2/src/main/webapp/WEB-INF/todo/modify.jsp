<%--
  Created by IntelliJ IDEA.
  User: edu12
  Date: 2024-06-19
  Time: 오전 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Modify/Romove </title>
</head>
<body>
<form id="form1" action="/todo/modify" method="post">
  <diV>
    <input type="text" name="tno" value="${dto.tno}" readonly>
  </diV>
  <diV>
    <input type="text" name="title" value="${dto.title}" >
  </diV>
  <diV>
    <input type="date" name="dueDate" value="${dto.dueDate}" >
  </diV>
  <diV>
    <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} >
  </diV>
  <div>
    <button type="submit">Modify</button>
  </div>
</form>

<form id="form2" action="/todo/remove" method="post">
  <input type="hidden" name="tno" value="${dto.tno}" readonly>
  <div>
    <button type="submit">Remove</button>
  </div>
</form>
</body>
</html>
