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
                <div class="container padding-small text-center max-width-400">

                        <img src="<%=request.getContextPath()%>/img/logoZHPsmall.png" class="logo-ZHP"/>
                        <h2 class="text-color-darker">Aktywacja konta</h2>

                        <h3 class="notError">Aktywacja przebiegła pomyślnie. Możesz się zalogować.</h3>

                        <a href='login' class="btn btn-color rounded-0">Przejdź do strony logowania</a>

                </div>
            </div>
        </div>
    </section>

</sec:authorize>
