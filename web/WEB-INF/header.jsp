<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : header
    Created on : 06-may-2014, 13:18:33
    Author     : FO-Mañana


<% if (request.getSession().getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
    } else {
        if (!request.getSession().getAttribute("usuario").equals("admin")) {
            response.sendRedirect("/VisualizaDatosServlet");
        }
    }
%> 
--%>

<%-- ${} obtiene sesion con nombre que digamos--%>

<c:choose>
    <c:when test="${usuario==null}">
        <jsp:forward page="./login.jsp" />
    </c:when>
    <c:when test="${usuario!='admin'}">
        <jsp:forward page="../VisualizaDatosServlet" />
    </c:when>
</c:choose>

<a href="logout" >Logout</a>

