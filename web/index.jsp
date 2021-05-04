<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Proyectos"%>
<%@page import="java.util.List"%>
<%
    List<Proyectos> lista = (List<Proyectos>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de tareas</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Prioridad</th>
                <th>Completado</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.tarea}</td>
                    <td>
                        <c:if test="${item.prioridad == 1}">Alta
                        </c:if>
                        <c:if test="${item.prioridad == 2}">Media
                        </c:if>
                        <c:if test="${item.prioridad == 3}">Baja
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.completado == 1}">
                            <input type="checkbox" checked="checked">
                        </c:if>
                        <c:if test="${item.completado == 2}">
                            <input type="checkbox">
                        </c:if>
                    </td>
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}">Eliminar</a></td>
                    
                </tr>
            </c:forEach>
            
        </table>
</html>
