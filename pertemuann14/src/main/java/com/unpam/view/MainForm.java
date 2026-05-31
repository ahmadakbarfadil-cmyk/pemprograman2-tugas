package com.unpam.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainForm", urlPatterns = {"/MainForm"})
public class MainForm extends HttpServlet {

    public void tampilkan(String konten, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        // 1. Menu Sidebar Default
        String menu = """
            <br><b>Master Data</b>
            <a href="MahasiswaController">Mahasiswa</a>
            <a href="MataKuliahController">Mata Kuliah</a>
            
            <b>Transaksi</b>
            <a href="NilaiController">Nilai</a>
            
            <b>Laporan</b>
            <a href="LaporanController">Nilai</a>
            
            <a href="LoginController" style="color:#00e5ff; font-weight:bold; margin-top:20px; text-shadow: 0 0 8px rgba(0,229,255,0.4);">Login</a>
            """;
        
        // 2. Menu Atas Default
        String topMenu = """
            <nav>
                <ul>
                    <li><a href="MainForm">Home</a></li>
                    <li><a href="MahasiswaController">Mahasiswa</a></li>
                    <li><a href="MataKuliahController">Mata Kuliah</a></li>
                    <li><a href="NilaiController">Transaksi</a></li>
                    <li><a href="LaporanController">Laporan</a></li>
                </ul>
            </nav>
            """;
        
        // 3. Logika Sesi
        String userName = "";
        if (!session.isNew()) {
            try {
                if (session.getAttribute("userName") != null) {
                    userName = session.getAttribute("userName").toString();
                }
            } catch (Exception ex) {}
            
            if (userName != null && !userName.trim().isEmpty()) {
                // Tampilan Welcome Screen khusus Dark Mode / Cyber Theme
                if (konten == null || konten.trim().isEmpty()) {
                    konten = "<div style='padding:60px 20px; text-align:center;'>"
                           + "<h1 style='font-size:52px; color:#00e5ff; margin-bottom:10px; text-shadow: 0 0 20px rgba(0,229,255,0.4); text-transform:uppercase;'>System Online</h1>"
                           + "<h2 style='font-weight:400; color:#e2e8f0; font-size:24px; letter-spacing:2px;'>Authorized Personnel: " + userName + "</h2>"
                           + "<p style='margin-top:20px; color:#94a3b8;'>Secure connection established. Awaiting command input.</p>"
                           + "</div>";
                }
                
                try {
                    if (session.getAttribute("menu") != null) {
                        menu = session.getAttribute("menu").toString();
                    }
                } catch (Exception ex) {}
            }
        }
        
        // 4. Render HTML
        try (PrintWriter out = response.getWriter()) {
            out.println("""
                <!DOCTYPE html>
                <html lang="id">
                <head>
                    <meta charset="UTF-8">
                    <link href="style.css" rel="stylesheet" type="text/css" />
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;800&display=swap" rel="stylesheet">
                    <title>SIAKAD - Cyber Command Center</title>
                </head>
                <body>
                <center>
                    <table cellspacing="0" cellpadding="0" border="0">
                        <tr>
                            <td colspan="2" bgcolor="#dddddd">
                                <h2>Sistem Informasi Akademik</h2>
                                <h1>UNIVERSITAS PAMULANG</h1>
                                <h4>Data Management Protocol</h4>
                            </td>
                        </tr>
                        
                        <tr>
                            <td valign="top" bgcolor="#eeffee">
                                <div id="menu">
                                    """ + menu + """
                                </div>
                            </td>
                            
                            <td valign="top" bgcolor="#ffffff">
                                """ + topMenu + """
                                <div id="konten_utama">
                                    """ + konten + """
                                </div>
                            </td>
                        </tr>
                        
                        <tr>
                            <td colspan="2" align="center" bgcolor="#eeeeff">
                                <small>
                                    <strong>SIAKAD UNPAM</strong> &bull; Secure Encrypted Network<br>
                                    Copyright &copy; 2026 Universitas Pamulang. All rights reserved.
                                </small>
                            </td>
                        </tr>
                    </table>
                </center>
                </body>
                </html>
                """);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tampilkan("", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tampilkan("", request, response);
    }
}