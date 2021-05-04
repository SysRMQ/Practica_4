
package com.emergentes.controlller;

import com.emergentes.ConexionBD;
import com.emergentes.modelo.Proyectos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op"):"list";
        
        ArrayList<Proyectos> lista = new ArrayList<Proyectos>();
        ConexionBD canal = new ConexionBD();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (op.equals("list")){
            try {
                String sql = "select * from tareas";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()){
                    Proyectos lib = new Proyectos();
                    lib.setId(rs.getInt("id"));
                    lib.setTarea(rs.getString("tarea"));
                    lib.setPrioridad(rs.getInt("prioridad"));
                    lib.setCompletado(rs.getInt("completado"));
                    lista.add(lib);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error de Sql "+ex.getMessage());
            }
        }
        if(op.equals("nuevo")){
            Proyectos l = new Proyectos();
            request.setAttribute("libro", l);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("editar")){
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Proyectos l = new Proyectos();
                String sql = "  select * from tareas where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if(rs.next())
                {
                    l.setId(rs.getInt("id"));
                    l.setTarea(rs.getString("tarea"));
                    l.setPrioridad(rs.getInt("prioridad"));
                    l.setCompletado(rs.getInt("completado"));
                }
                request.setAttribute("libro", l);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error de Sql "+ex.getMessage());
            }
        }
        if(op.equals("eliminar")){
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "delete from tareas where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            } catch (SQLException ex) {
                System.out.println("Error de Sql "+ex.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tarea = request.getParameter("tarea");
        int prioridad = Integer.parseInt(request.getParameter("prioridad"));
        int completado = Integer.parseInt(request.getParameter("completado"));
        Proyectos l = new Proyectos();
        
        l.setId(id);
        l.setTarea(tarea);
        l.setPrioridad(prioridad);
        l.setCompletado(completado);
        ConexionBD canal = new ConexionBD();
        Connection conn = canal.conectar();
        PreparedStatement ps = null;
        ResultSet rs;
        if (id == 0){
            try {
                String sql = "insert into tareas (tarea,prioridad,completado) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, l.getTarea());
                ps.setInt(2, l.getPrioridad());
                ps.setInt(3, l.getCompletado());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error de Sql "+ex.getMessage());
            }
                response.sendRedirect("MainController");
        }else{
            try {
                String sql = "update tareas set tarea= ?, prioridad = ?, completado = ? where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, l.getTarea());
                ps.setInt(2, l.getPrioridad());
                ps.setInt(3, l.getCompletado());
                ps.setInt(4, id);
                ps.executeUpdate();
            }catch (Exception ex) {
                System.out.println("error de update "+ex.getMessage());;
            }
                response.sendRedirect("MainController");
        }
    }


}
