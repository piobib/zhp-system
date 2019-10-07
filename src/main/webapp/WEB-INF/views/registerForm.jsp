<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">


    <%
        String redirectURL = "/account";
        response.sendRedirect(redirectURL);
    %>


</sec:authorize>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<sec:authorize access="isAnonymous()">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTop.jsp" %>
    <section class="dashboard-section">
        <div class="container pt-4 pb-4">
            <div class="border-dashed view-height margin-top-60">
                <div class="container max-width-400">
                    <form:form method="POST" action="" modelAttribute="user" class="padding-small text-center margin-auto">
                        <img src="<%=request.getContextPath()%>/img/logoZHPsmall.png" class="logo-ZHP"/>
                        <h2 class="text-color-darker">Rejestracja</h2>
                        <div class="form-group margin-top-30">
                            <form:errors path="username" cssClass="error"/>
                            <form:input path="username" type="text" class="form-control" id="username" name="username" placeholder="Podaj adres email" />
                        </div>
                        <div class="form-group">
                            <form:errors path="password" cssClass="error" />
                            <form:input path="password" type="password" class="form-control" id="password" name="password"
                                   placeholder="Podaj hasło" />
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control radio-style" id="confirmPassword" name="confirmPassword"
                                   placeholder="Powtórz hasło" />
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</sec:authorize>
