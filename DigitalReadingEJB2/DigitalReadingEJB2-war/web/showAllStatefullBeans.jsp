<%-- 
    Document   : showAllStatefulBeans
    Created on : 08-oct-2020, 11:14:33
    Author     : Miki
--%>

<jsp:include page="./WebComponent/header.jsp"/>
<jsp:include page="./WebComponent/navbar.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container py-3 px-3">
    <div class="row">


        <table class="table-hover">

            <tr>
                <th>Sessions</th>
            </tr>

            <c:forEach var="usuario" items="${statefulBean.getAllUsers()}">
                <tr>
                    <td>${usuario}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
    <div class="row py-5">


        <table class="table-hover py-3">
            <tr>
                <th>Carts</th>
            </tr>

            <c:forEach var="carritos" items="${statefulBean.getAllCarts().values()}">
                <tr>
                    <td>${carritos}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
<jsp:include page="./WebComponent/footer.jsp"/>

