<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="staticElements/header.jsp" %>
    <%@include file="staticElements/menuTopLogin.jsp" %>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="staticElements/menuLeft.jsp" %>

            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <a href="/account/children/newToken/${childrenId}">Utwórz nowy token</a>
                    <table><th>Token</th><th>Wykorzystany</th><th>Data utworzenia</th>
                        <c:forEach items="${childrenTokenList}" var="childrenTokenList">
                            <tr>
                                <td><c:out value="${childrenTokenList.token}" default="Brak danych"/></td>
                                <td><c:out value="${ childrenTokenList.active == 1 ? 'Nie' : 'Tak' }" default="Brak danych"/></td>
                                <td><c:out value="${childrenTokenList.created}" default="Brak danych"/></td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </div>
    </section>
    <%@include file="staticElements/footer.jsp" %>
</sec:authorize>