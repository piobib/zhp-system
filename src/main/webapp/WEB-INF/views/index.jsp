<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">


    <%
        String redirectURL = "/account";
        response.sendRedirect(redirectURL);
    %>


</sec:authorize>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="staticElements/header.jsp" %>
<%@include file="staticElements/menuTop.jsp" %>
<%@include file="staticElements/mainPage.jsp" %>
<%@include file="staticElements/footer.jsp" %>