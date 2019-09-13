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
                    <h4>Składki prosimy przelewać na następujące dane:</h4>
                    <table class="margin-top-30">
                        <tr>
                            <td>Numer konta:</td>
                            <td>45 3453 3453 3453 2342 1234 2323</td>
                        </tr>
                        <tr>
                            <td>Tytuł przelewu:</td>
                            <td><c:out value="${transferData}" default="Brak danych"/></td>
                        </tr>
                    </table>
                    <h5 class="margin-top-30 error">Prosimy o dokładne skopiowanie tytułu przelewu i nie dokonywanie żadnych zmian</h5>



                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>