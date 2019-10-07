<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">


    <%
        String redirectURL = "/account";
        response.sendRedirect(redirectURL);
    %>


</sec:authorize>
<sec:authorize access="isAnonymous()">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTop.jsp" %>
    <section class="dashboard-section">
        <div class="container pt-4 pb-4">
            <div class="border-dashed view-height margin-top-60">
                <div class="container max-width-400">
                    <form method="POST" action="/login" class="padding-small text-center margin-auto ">
                        <img src="<%=request.getContextPath()%>/img/logoZHPsmall.png" class="logo-ZHP"/>
                        <h2 class="text-color-darker">Logowanie</h2>
                        <h3 class="error"><c:out value="${ error == true ? 'Błędne dane logowania' : '' }" default="" /></h3>
                        <h3 class="notError"><c:out value="${ activation == true ? 'Aktywacja przebiegła pomyślnie. Możesz się zalogować.' : '' }" default="" /></h3>
                        <div class="form-group margin-top-30">
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="Podaj adres email" autofocus>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Podaj hasło">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
                    </form>
                </div>
            </div>
        </div>
    </section>

</sec:authorize>
