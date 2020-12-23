<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="javax.naming.InitialContext"%>
<jsp:include page="./WebComponent/header.jsp"/>
<jsp:include page="./WebComponent/navbar.jsp"/>


<div>
    <ul>
        <li>Users looged: ${stats.getUsersLogged().size()}</li>
        <li>Books purchased/Selled: ${stats.getPurchasedBook()}</li>
        <li>Carts completed: ${stats.getCartsCompleted()}</li>
    </ul>
    <br>
    
    
    <table class="table-hover">
        <tr>
            <th>User</th>
        </tr>
        <c:forEach var="usuario" items="${stats.getUsersLogged()}">

            <tr> 
                <td>${usuario}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="./WebComponent/footer.jsp"/>