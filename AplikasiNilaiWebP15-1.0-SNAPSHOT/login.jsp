<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Administrasi Nilai UNPAM</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                /* Background gradasi sudah otomatis ditarik dari style.css */
            }
            .login-box {
                background: rgba(255, 255, 255, 0.3);
                backdrop-filter: blur(15px);
                -webkit-backdrop-filter: blur(15px);
                padding: 40px;
                border-radius: 20px;
                border: 1px solid rgba(255, 255, 255, 0.5);
                box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.15);
                width: 100%;
                max-width: 350px;
                text-align: center;
            }
            .login-box h2 {
                color: #22223b;
                margin-top: 0;
                margin-bottom: 5px;
                font-weight: 700;
            }
            .login-box p {
                color: #4a4e69;
                font-size: 14px;
                margin-bottom: 25px;
                font-weight: 600;
            }
            .input-group {
                margin-bottom: 20px;
                text-align: left;
            }
            .input-group input {
                width: 90%;
            }
            .pesan-error {
                color: #d90429;
                font-size: 13px;
                margin-top: 15px;
                font-weight: 700;
                /* Munculkan kotak putih transparan hanya jika ada error */
                background: ${pesanError != null && pesanError != '' ? 'rgba(255, 255, 255, 0.5)' : 'transparent'};
                padding: ${pesanError != null && pesanError != '' ? '10px' : '0'};
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <div class="login-box">
            <h2>Portal Admin</h2>
            <p>Sistem Administrasi Akademik</p>
            
            <form action="LoginController" method="POST">
                <div class="input-group">
                    <input type="text" name="username" placeholder="Username" required autocomplete="off">
                </div>
                <div class="input-group">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="btn-login" style="width: 100%; margin-top: 10px; background: linear-gradient(45deg, #8ec5fc, #e0c3fc); color: #4a4e69;">MASUK</button>
            </form>
            
            <div class="pesan-error">
                ${pesanError}
            </div>
        </div>
    </body>
</html>