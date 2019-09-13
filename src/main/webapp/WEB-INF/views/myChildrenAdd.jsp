<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTopLogin.jsp" %>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="staticElements/menuLeft.jsp" %>

            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">

                    <c:if test="${not empty pesel}">
                        <h3 class="error">Podano nieprawidłowe dane. Proszę spróbować jeszcze raz.</h3>
                    </c:if>

                    <form method="post" action="" accept-charset="UTF-8">

                        <input type="text" name="pesel" class="form-control" value="${pesel}" placeholder="Wprowadź PESEL dziecka"/>
                        <input type="text" name="token" class="form-control" value="${token}" placeholder="Wprowadź otrzymany token"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <input type="submit" class="btn btn-color rounded-0 margin-top-30" value="Kliknij, aby przypisać dziecko"/>

                    </form>

                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>