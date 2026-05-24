package com.belajar;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Core Controller</title>");
            out.println("<style>");
            out.println("body { font-family: 'Inter', system-ui, sans-serif; background-color: #fafaf9; color: #292524; margin: 0; padding: 40px; display: flex; justify-content: center; }");
            out.println(".card { background: #ffffff; border: 1px solid #e7e5e4; padding: 35px; border-radius: 12px; width: 380px; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.05); }");
            out.println("h2 { color: #15803d; margin-top: 0; font-size: 22px; font-weight: 600; letter-spacing: -0.5px; }");
            out.println(".desc { font-size: 14px; line-height: 1.6; color: #57534e; }");
            out.println(".path-box { background: #f5f5f4; padding: 10px 14px; border-radius: 8px; font-family: monospace; font-size: 13px; color: #44403c; margin: 16px 0; border: 1px solid #e7e5e4; }");
            out.println(".btn-link { display: inline-block; background-color: #15803d; color: white; padding: 10px 20px; text-decoration: none; border-radius: 8px; font-size: 14px; font-weight: 500; margin-top: 8px; transition: background 0.2s; }");
            out.println(".btn-link:hover { background-color: #166534; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class='card'>");
            out.println("<h2>Servlet Engine</h2>");
            out.println("<p class='desc'>Sistem backend berhasil membaca request. Servlet ini bertugas menjembatani logika Java sebelum dilempar ke client.</p>");
            out.println("<div class='path-box'>Path: " + request.getContextPath() + "</div>");
            out.println("<a href='beranda.jsp' class='btn-link'>Lihat Render JSP &rarr;</a>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}