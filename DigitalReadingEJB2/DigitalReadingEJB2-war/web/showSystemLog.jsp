<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="EJBs.Log"%>
<%@page import="javax.naming.InitialContext"%>
<jsp:include page="./WebComponent/header.jsp"/>
<jsp:include page="./WebComponent/navbar.jsp"/>


<div container>
    <div class="row py-3 px-3">

        <div class="col-md-8 order-md-1">
            <table class="table-hover">
                <tr>
                    <th>log</th>
                </tr>
                <c:forEach var="log" items="${logs}">
                    <tr> 
                        <td>${log}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-4 order-md-2">
            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#addTimer">
                new Timer
            </button>
            <a href="FrontController?command=deleteSystemLogs" class="btn btn-warning" onclick="return confirm('Do you want clear all System Log?');">
                clear
            </a>
            
        </div>
    </div>
</div>
<jsp:include page="./WebComponent/modals.jsp"/>
<jsp:include page="./WebComponent/footer.jsp"/>