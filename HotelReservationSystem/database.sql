-- =============================================
-- Hotel Reservation System - Database Script
-- UAS Pemrograman II
-- =============================================

-- Buat Database
CREATE DATABASE IF NOT EXISTS hotel_reservasi
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Gunakan Database
USE hotel_reservasi;

-- =============================================
-- Tabel Reservasi
-- =============================================
CREATE TABLE IF NOT EXISTS reservasi (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    nama_tamu   VARCHAR(100)    NOT NULL            COMMENT 'Nama lengkap tamu',
    telepon     VARCHAR(20)     NOT NULL            COMMENT 'Nomor telepon tamu',
    email       VARCHAR(100)    NOT NULL            COMMENT 'Alamat email tamu',
    tipe_kamar  VARCHAR(50)     NOT NULL            COMMENT 'Tipe kamar (Standard/Deluxe/Suite/Family)',
    jumlah_tamu INT             NOT NULL DEFAULT 1  COMMENT 'Jumlah tamu',
    check_in    DATE            NOT NULL            COMMENT 'Tanggal check-in',
    check_out   DATE            NOT NULL            COMMENT 'Tanggal check-out',
    status      VARCHAR(30)     NOT NULL            COMMENT 'Status reservasi',
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Data Sample
-- =============================================
INSERT INTO reservasi (nama_tamu, telepon, email, tipe_kamar, jumlah_tamu, check_in, check_out, status)
VALUES
    ('Budi Santoso',     '081234567890', 'budi@email.com',     'Standard', 2, '2025-07-10', '2025-07-13', 'Booked'),
    ('Siti Rahayu',      '082345678901', 'siti@email.com',     'Deluxe',   3, '2025-07-11', '2025-07-15', 'Check In'),
    ('Ahmad Fauzi',      '083456789012', 'ahmad@email.com',    'Suite',    1, '2025-07-05', '2025-07-08', 'Check Out'),
    ('Dewi Lestari',     '084567890123', 'dewi@email.com',     'Family',   4, '2025-07-20', '2025-07-25', 'Booked'),
    ('Eko Prasetyo',     '085678901234', 'eko@email.com',      'Standard', 2, '2025-07-12', '2025-07-14', 'Cancelled'),
    ('Fitri Handayani',  '086789012345', 'fitri@email.com',    'Deluxe',   2, '2025-07-18', '2025-07-22', 'Booked'),
    ('Gunawan Wibowo',   '087890123456', 'gunawan@email.com',  'Suite',    2, '2025-07-09', '2025-07-11', 'Check Out'),
    ('Heni Kurniawati',  '088901234567', 'heni@email.com',     'Standard', 1, '2025-07-15', '2025-07-17', 'Booked');
