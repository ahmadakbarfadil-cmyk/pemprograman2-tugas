<%@page import="com.unpam.model.Nilai"%>
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
        <title>Rekap Nilai - UNPAM</title>
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
                        <a href="MataKuliahController">Mata Kuliah</a>
                    </div>
                    <div class="menu-group">
                        <h4>Transaksi</h4>
                        <a href="index.jsp">Input Nilai</a>
                    </div>
                    <div class="menu-group">
                        <h4>Laporan</h4>
                        <a href="RekapController" class="active">Rekap Nilai</a>
                    </div>
                    <div class="menu-group">
                        <h4>Sistem</h4>
                        <a href="LogoutController" class="logout">Keluar (Logout)</a>
                    </div>
                </div>

                <div class="content">
                    <h3>Data Rekapitulasi Nilai Mahasiswa</h3>
                    
                    <div class="tabel-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>NIM</th>
                                    <th>Nama Mahasiswa</th>
                                    <th>Mata Kuliah</th>
                                    <th>Tugas</th>
                                    <th>UTS</th>
                                    <th>UAS</th>
                                    <th>Nilai Akhir</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Nilai> daftar = (List<Nilai>) request.getAttribute("daftarNilai");
                                    if(daftar != null && !daftar.isEmpty()){
                                        int no = 1;
                                        for(Nilai n : daftar){
                                            float na = (n.getNilaiTugas() * 0.2f) + (n.getNilaiUts() * 0.3f) + (n.getNilaiUas() * 0.5f);
                                %>
                                <tr>
                                    <td><%= no++ %></td>
                                    <td><%= n.getNim() %></td>
                                    <td style="text-align: left;"><%= n.getMahasiswa().getNama() %></td>
                                    <td style="text-align: left;"><%= n.getMataKuliah().getNamaMk() %></td>
                                    <td><%= n.getNilaiTugas() %></td>
                                    <td><%= n.getNilaiUts() %></td>
                                    <td><%= n.getNilaiUas() %></td>
                                    <td style="font-weight: 800; color: #6a4c93;"><%= String.format("%.2f", na) %></td>
                                </tr>
                                <% 
                                        }
                                    } else { 
                                %>
                                <tr>
                                    <td colspan="8" style="padding: 20px; color: #4a4e69;">Belum ada data nilai di database.</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>

                    <div style="margin-top: 25px; text-align: right;">
                        <form action="NilaiController" method="POST" target="_blank">
                            <input type="hidden" name="action" value="Cetak">
                            <button type="submit" class="btn btn-warning">Cetak Semua ke PDF</button>
                        </form>
                    </div>

                </div>
            </div>

            <div class="footer">
                Copyright &copy; 2026 Universitas Pamulang | Tema Glassmorphism
            </div>
        </div>
    </body>
</html>