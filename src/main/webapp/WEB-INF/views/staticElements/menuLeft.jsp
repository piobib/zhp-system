<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <ul class="nav flex-column long-bg">

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="/account/users">
                    <span>Użytkownicy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_SUBTEACHER'})">
            <li class="nav-item">
                <a class="nav-link" href="/account/children">
                    <span>Dzieci</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_SUBTEACHER', 'ROLE_PARENT'})">
            <li class="nav-item">
                <a class="nav-link" href="/account/my-children/add">
                    <span>Przypisz dziecko</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_SUBTEACHER', 'ROLE_PARENT'})">
            <li class="nav-item">
                <a class="nav-link" href="/account/my-children/list">
                    <span>Moje dzieci</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </sec:authorize>
        <li class="nav-item">
            <a class="nav-link disabled" href="/account/users/change-password">
                <span>Zmień hasło</span>
                <i class="fas fa-angle-right"></i>
            </a>
        </li>
    </ul>
</sec:authorize>