<%@page import="com.unpam.model.Mahasiswa"%>
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
    <title>Daftar Mahasiswa - UNPAM</title>
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
                    <a href="MahasiswaController" class="active">Mahasiswa</a>
                    <a href="MataKuliahController">Mata Kuliah</a>
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
                <h3>Daftar Mahasiswa Terdaftar</h3>
                <div class="tabel-container">
                    <table>
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>NIM</th>
                                <th>Nama Lengkap</th>
                                <th>Semester</th>
                                <th>Kelas</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Mahasiswa> list = (List<Mahasiswa>) request.getAttribute("listMahasiswa");
                                if (list != null && !list.isEmpty()) {
                                    int no = 1;
                                    for (Mahasiswa m : list) {
                            %>
                            <tr>
                                <td><%= no++ %></td>
                                <td style="font-weight: 700;"><%= m.getNim() %></td>
                                <td style="text-align: left; font-weight: 600;"><%= m.getNama() %></td>
                                <td><%= m.getSemester() %></td>
                                <td><%= m.getKelas() %></td>
                            </tr>
                            <% } } else { %>
                            <tr><td colspan="5" style="padding: 20px; color: #4a4e69;">Belum ada data mahasiswa.</td></tr>
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