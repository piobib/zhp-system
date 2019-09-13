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
                    <form:form method="POST" action="/account/children/edit" modelAttribute="children"
                               class="padding-small text-center">

                        <form:hidden path="id"/>

                        <form:input path="firstName" type="text" class="form-control" placeholder="Podaj imię"/>
                        <form:errors path="firstName" cssClass="error"/>
                        <form:input path="lastName" type="text" class="form-control" placeholder="Podaj nazwisko"/>
                        <form:errors path="lastName" cssClass="error"/>
                        <form:input path="phone" type="text" class="form-control" placeholder="Podaj numer telefonu"/>
                        <form:input path="pesel" type="text" class="form-control" placeholder="Podaj PESEL"/>
                        <form:errors path="pesel" cssClass="error"/>
                        <form:input path="evidenceNumber" type="text" class="form-control"
                                    placeholder="Podaj numer ewidencyjny"/>

                        <form:input path="street" type="text" class="form-control" placeholder="Podaj ulicę"/>
                        <form:input path="postCode" type="text" class="form-control" placeholder="Podaj kod pocztowy"/>
                        <form:input path="city" type="text" class="form-control" placeholder="Podaj miasto"/>
                        <br/>
                        <form:select path="active" class="width-100">
                            <form:option value="1" label="Aktywny"/>
                            <form:option value="0" label="Nieaktywny"/>
                        </form:select>

                        <button class="btn btn-color rounded-0 margin-top-30" type="submit">Zapisz zmiany</button>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>