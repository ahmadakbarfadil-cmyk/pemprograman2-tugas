package com.belajar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HaloServlet", urlPatterns = {"/halo"})
public class HaloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Gate Controller</title>");
            out.println("<style>");
            out.println("body { font-family: 'Poppins', sans-serif; background: #f0f2f5; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            out.println(".gate-card { background: #ffffff; padding: 40px; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); text-align: center; width: 360px; position: relative; overflow: hidden; }");
            out.println(".gate-card::before { content: ''; position: absolute; top: 0; left: 0; right: 0; height: 6px; background: linear-gradient(90deg, #ff007f, #7f00ff); }");
            out.println("h2 { color: #2d3748; margin-top: 0; font-size: 22px; font-weight: 600; }");
            out.println(".badge-active { display: inline-block; padding: 4px 12px; background: #e3fcef; color: #00875a; font-size: 12px; font-weight: bold; border-radius: 6px; margin-bottom: 20px; }");
            out.println("p { color: #718096; font-size: 14px; line-height: 1.5; }");
            out.println(".btn-gate { display: inline-block; width: 100%; padding: 12px; margin-top: 20px; background: linear-gradient(90deg, #7f00ff, #ff007f); color: white; text-decoration: none; border-radius: 10px; font-weight: bold; font-size: 14px; box-shadow: 0 4px 12px rgba(127,0,255,0.3); }");
            out.println("</style>");
            out.println("<head>");
            out.println("<body>");
            
            out.println("<div class='gate-card'>");
            out.println("<h2>Servlet Controller</h2>");
            out.println("<span class='badge-active'>INTERCEPTOR ACTIVE</span>");
            out.println("<p>Request masuk dideteksi pada alur: <br><b>" + request.getContextPath() + "</b></p>");
            out.println("<a href='beranda.jsp' class='btn-gate'>BUKA HALAMAN JSP</a>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }
}