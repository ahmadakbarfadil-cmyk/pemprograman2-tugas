<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // SISTEM KEAMANAN
    if (session.getAttribute("adminLog") == null) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Administrasi Nilai - UNPAM</title>
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
                        <a href="index.jsp" class="active">Input Nilai</a>
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
                    <h3>Form Input & Transaksi Nilai</h3>
                    
                    <form action="NilaiController" method="POST">
                        
                        <div class="form-group">
                            <label>NIM Mahasiswa</label>
                            <input type="text" name="nim" value="${mhs != null ? mhs.nim : ''}" placeholder="Masukkan NIM...">
                            <input type="submit" name="action" value="Cari NIM" class="btn btn-primary" style="margin-left: 10px;">
                        </div>
                        <div class="form-group">
                            <label>Nama Lengkap</label>
                            <input type="text" name="nama" value="${mhs != null ? mhs.nama : ''}" readonly>
                        </div>
                        <div class="form-group">
                            <label>Semester</label>
                            <input type="text" name="semester" value="${mhs != null ? mhs.semester : ''}" class="short" readonly>
                        </div>
                        <div class="form-group">
                            <label>Kelas</label>
                            <input type="text" name="kelas" value="${mhs != null ? mhs.kelas : ''}" class="short" readonly>
                        </div>

                        <hr style="margin: 25px 0; border: 0; border-top: 1px dashed rgba(255,255,255,0.5);">

                        <div class="form-group">
                            <label>Kode Mata Kuliah</label>
                            <input type="text" name="kodeMk" value="${mk != null ? mk.kodeMk : ''}" placeholder="Masukkan Kode...">
                            <input type="submit" name="action" value="Cari MK" class="btn btn-primary" style="margin-left: 10px;">
                        </div>
                        <div class="form-group">
                            <label>Nama Mata Kuliah</label>
                            <input type="text" name="namaMk" value="${mk != null ? mk.namaMk : ''}" readonly>
                        </div>
                        <div class="form-group">
                            <label>Jumlah SKS</label>
                            <input type="text" name="sks" value="${mk != null ? mk.sks : ''}" class="short" readonly>
                        </div>

                        <hr style="margin: 25px 0; border: 0; border-top: 1px dashed rgba(255,255,255,0.5);">

                        <div class="form-group">
                            <label>Nilai Tugas</label>
                            <input type="text" name="nilaiTugas" value="${nilai != null ? nilai.nilaiTugas : ''}" class="short" placeholder="0">
                        </div>
                        <div class="form-group">
                            <label>Nilai UTS</label>
                            <input type="text" name="nilaiUts" value="${nilai != null ? nilai.nilaiUts : ''}" class="short" placeholder="0">
                        </div>
                        <div class="form-group">
                            <label>Nilai UAS</label>
                            <input type="text" name="nilaiUas" value="${nilai != null ? nilai.nilaiUas : ''}" class="short" placeholder="0">
                        </div>

                        <div class="btn-group">
                            <input type="submit" name="action" value="Simpan" class="btn btn-success">
                            <input type="submit" name="action" value="Hapus" class="btn btn-danger" style="margin-left: 5px;">
                            <input type="submit" name="action" value="Cetak" class="btn btn-warning" style="margin-left: 5px;">
                        </div>
                        
                        <div style="text-align: center; margin-top: 25px; padding: 12px; border-radius: 12px; 
                             background: ${pesan.contains('Berhasil') || pesan.contains('ditemukan') ? 'rgba(255, 255, 255, 0.5)' : (pesan != null && pesan != '' ? 'rgba(255, 255, 255, 0.5)' : 'transparent')}; 
                             box-shadow: ${pesan != null && pesan != '' ? '0 4px 15px rgba(0,0,0,0.05)' : 'none'};">
                            <span style="color: ${pesan.contains('Berhasil') || pesan.contains('ditemukan') ? '#11998e' : '#d90429'}; font-weight: 700; letter-spacing: 0.5px;">
                                ${pesan}
                            </span>
                        </div>
                    </form>
                </div>
            </div>

            <div class="footer">
                Copyright &copy; 2026 Universitas Pamulang | Tema Glassmorphism
            </div>
        </div>
    </body>
</html>