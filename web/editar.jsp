<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Proyectos"%>
<%
    Proyectos libro = (Proyectos)request.getAttribute("libro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${libro.id == 0}">Nueva Tarea
            </c:if>
            <c:if test="${libro.id != 0}">Editar Tarea
            </c:if>
        </h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="id" value="${libro.id}">
                <tr>
                    <td>Tarea: </td>
                    <td><input type="text" name="tarea" value="${libro.tarea}"></td>
                </tr>
                <tr>
                    <td>Prioridad: </td>
                    <td><input type="text" name="prioridad" value="${libro.prioridad}"></td>
                    <td>
                        1 = Alta
                        <br>
                        2 = Media
                        <br>
                        3 = Baja
                    </td>
                </tr>
                <br>
                <tr>
                    <td>Completado: </td>
                    <td><input type="text" name="completado" value="${libro.completado}"></td>
                    <td>
                        1 = Concluido
                        <br>
                        2 = Pendiente
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
