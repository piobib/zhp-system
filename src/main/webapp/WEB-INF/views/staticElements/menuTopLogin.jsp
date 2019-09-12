<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-around">
        <a href="/" class="navbar-brand main-logo">
            <span>e-Harcerz</span>.pl
        </a>
        <form action="<c:url value="/logout"/>" method="post">
            <input class="fa fa-id-badge" type="submit" value="Wyloguj">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </nav>
</header>
</sec:authorize>