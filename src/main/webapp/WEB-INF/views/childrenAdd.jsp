<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTopLogin.jsp" %>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="staticElements/menuLeft.jsp" %>

            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <form:form method="POST" action="" modelAttribute="children" class="padding-small text-center">
                        <h1 class="text-color-darker">Dodaj nowe dziecko</h1>
                        <div class="form-group">
                            <form:errors path="firstName" cssClass="error"/>
                            <form:input path="firstName" type="text" class="form-control" id="firstName" name="firstName" placeholder="Podaj imię dziecka" />
                            <form:errors path="lastName" cssClass="error"/>
                            <form:input path="lastName" type="text" class="form-control" id="lastName" name="lastName" placeholder="Podaj nazwisko dziecka" />
                            <form:errors path="pesel" cssClass="error"/>
                            <form:input path="pesel" type="text" class="form-control" id="pesel" name="pesel" placeholder="Podaj PESEL dziecka" />

                        </div>
                        <button class="btn btn-color rounded-0" type="submit">Dodaj nowe dziecko</button>
                    </form:form>

                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>