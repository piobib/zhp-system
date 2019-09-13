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
                        Podano nieprawidłowe dane. Proszę spróbować jeszcze raz.
                    </c:if>

                    <form method="post" action="" accept-charset="UTF-8">

                        <input type="text" name="pesel" value="${pesel}" placeholder="Wprowadź PESEL dziecka"/>
                        <input type="text" name="token" value="${token}" placeholder="Wprowadź otrzymany token"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <input type="submit"/>

                    </form>

                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>