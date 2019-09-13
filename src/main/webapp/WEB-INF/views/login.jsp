<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAnonymous()">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTop.jsp" %>
    <section class="dashboard-section">
        <div class="container pt-4 pb-4">
            <div class="border-dashed view-height margin-top-60">
                <div class="container w-25">
                    <form method="POST" class="padding-small text-center">
                        <img src="<%=request.getContextPath()%>/img/logoZHPsmall.png" class="logo-ZHP"/>
                        <h1 class="text-color-darker">Logowanie</h1>
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
