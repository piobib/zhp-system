<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTopLogin.jsp" %>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="staticElements/menuLeft.jsp" %>

            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <form:form method="POST" action="" modelAttribute="user" class="padding-small text-center">

                        <form:hidden path="id" />
                        <form:errors path="password" cssClass="error"/>
                        <form:password path="password"  class="form-control" placeholder="Wprowadź nowe hasło"/>

                        <form:password path="confirmPassword" class="form-control"  placeholder="Potwierdź nowe hasło"/>

                        <button class="btn btn-color rounded-0 margin-top-30" type="submit">Kliknij, aby zapisać zmiany</button>
                    </form:form>


                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>