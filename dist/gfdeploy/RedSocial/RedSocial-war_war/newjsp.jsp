<%-- 
    Document   : newjsp
    Created on : 07-mar-2019, 19:01:54
    Author     : Jesus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/RedSocial-war/Login" method="POST">
            <input type="text" name="email"></input>
            <input type="submit"/>
        </form>
        <c:if test="${usuario.nombre != null}">
            <h1>${usuario.nombre}</h1>
        </c:if>    
    </body>
</html>

