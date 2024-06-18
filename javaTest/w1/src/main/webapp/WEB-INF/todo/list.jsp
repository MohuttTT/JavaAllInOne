<%--
  Created by IntelliJ IDEA.
  User: edu10
  Date: 06-14(금)
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List Page</h1>
    <!--
        for(TodoDto dto (== var) : list (== items) ) {
            System.out.println(dto);
        }
    -->

    <c:forEach var="dto" items="${list}">
        <li>${dto}</li>
    </c:forEach>

    <c:if test="${list.size() % 2 == 0}">
        짝수
    </c:if>
    <c:if test="${list.size() % 2 != 0}">
        홀수
    </c:if>

    <!-- c:choose = switch 구문과 미슷한 역할 -> 위에서 부터 하나씩 조건을 검사 -->
    <!-- c:when : test의 조건에 따라 true면 실행 (if/else if와 동일) -->
    <!-- c:otherwise : 위의 c:when 구문에 true값이 하나도 없다면 실행되는 구문 (else와 동일) -->

    <c:choose>
        <c:when test="${list.size() % 2 == 0}">
            짝수
        </c:when>
        <c:otherwise>
            홀수
        </c:otherwise>
    </c:choose>

    <!-- c:set : 변수 지정 (var : 변수명 / value : 변수 값) -->
    <c:set var="target" value="5"></c:set>
</body>
</html>
