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
                    <form:form method="POST" action="/account/users/edit" modelAttribute="user"
                               class="padding-small text-center">

                        <form:hidden path="id"/>
                        <form:errors path="username" cssClass="error"/>
                        <form:input path="username" type="text" class="form-control" placeholder="Podaj adres email"/>

                        <form:input path="firstName" type="text" class="form-control" placeholder="Podaj imię"/>

                        <form:input path="lastName" type="text" class="form-control" placeholder="Podaj nazwisko"/>
                        <br/>
                        <form:select path="enabled" class="width-100">
                            <form:option value="1" label="Aktywny"/>
                            <form:option value="0" label="Nieaktywny"/>
                        </form:select>
                        <br/>
                        <div class="margin-top-30 width-100 align-left">
                        <c:forEach items="${roles}" var="roles">
                            <form:radiobutton path="roles" label="${roles.name}" value="${roles.id}"
                                              checked="${ roles.id == userRole ? 'checked' : '' }"/>
                            <br/>
                        </c:forEach>
                        </div>
                        <br/>
                        <button class="btn btn-color rounded-0" type="submit">Zapisz zmiany</button>
                    </form:form>


                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>