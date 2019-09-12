<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTopLogin.jsp" %>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="staticElements/menuLeft.jsp" %>

            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <a href="/account/children/add">Dodaj nowe dziecko</a>

                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>