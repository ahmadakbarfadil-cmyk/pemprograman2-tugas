<%@page import="com.unpam.model.MataKuliah"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    if (session.getAttribute("adminLog") == null) { 
        response.sendRedirect("login.jsp"); 
        return; 
    } 
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Daftar Mata Kuliah - UNPAM</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>Informasi Nilai Mahasiswa</h2>
            <h1>UNIVERSITAS PAMULANG</h1>
            <p>Jl. Surya Kencana No. 1 Pamulang, Tangerang Selatan, Banten</p>
        </div>
        <div class="main-content">
            <div class="sidebar">
                <div class="menu-group">
                    <h4>Master Data</h4>
                    <a href="MahasiswaController">Mahasiswa</a>
                    <a href="MataKuliahController" class="active">Mata Kuliah</a>
                </div>
                <div class="menu-group">
                    <h4>Transaksi</h4>
                    <a href="index.jsp">Input Nilai</a>
                </div>
                <div class="menu-group">
                    <h4>Laporan</h4>
                    <a href="RekapController">Rekap Nilai</a>
                </div>
                <div class="menu-group">
                    <h4>Sistem</h4>
                    <a href="LogoutController" class="logout">Keluar (Logout)</a>
                </div>
            </div>
            <div class="content">
                <h3>Daftar Mata Kuliah</h3>
                <div class="tabel-container">
                    <table>
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Kode MK</th>
                                <th>Nama Mata Kuliah</th>
                                <th>Jumlah SKS</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<MataKuliah> list = (List<MataKuliah>) request.getAttribute("listMataKuliah");
                                if (list != null && !list.isEmpty()) {
                                    int no = 1;
                                    for (MataKuliah mk : list) {
                            %>
                            <tr>
                                <td><%= no++ %></td>
                                <td style="font-weight: 700;"><%= mk.getKodeMk() %></td>
                                <td style="text-align: left; font-weight: 600;"><%= mk.getNamaMk() %></td>
                                <td><span style="background: rgba(255,255,255,0.5); padding: 5px 12px; border-radius: 8px;"><%= mk.getSks() %> SKS</span></td>
                            </tr>
                            <% } } else { %>
                            <tr><td colspan="4" style="padding: 20px; color: #4a4e69;">Belum ada data mata kuliah.</td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="footer">
            Copyright &copy; 2026 Universitas Pamulang | Tema Glassmorphism
        </div>
    </div>
</body>
</html>