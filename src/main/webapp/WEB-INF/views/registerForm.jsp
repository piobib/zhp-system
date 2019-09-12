<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<sec:authorize access="isAnonymous()">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTop.jsp" %>
    <section class="dashboard-section">
        <div class="container pt-4 pb-4">
            <div class="border-dashed view-height">
                <div class="container w-25">
                    <form:form method="POST" action="" modelAttribute="user" class="padding-small text-center">
                        <h1 class="text-color-darker">Rejestracja</h1>
                        <div class="form-group">
                            <form:errors path="username" cssClass="error"/>
                            <form:input path="username" type="text" class="form-control" id="username" name="username" placeholder="Podaj adres email" />
                        </div>
                        <div class="form-group">
                            <form:errors path="password" cssClass="error" />
                            <form:input path="password" type="password" class="form-control" id="password" name="password"
                                   placeholder="Podaj hasło" />
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                                   placeholder="Powtórz hasło" />
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>
