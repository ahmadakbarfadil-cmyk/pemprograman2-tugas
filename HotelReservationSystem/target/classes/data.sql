-- Dummy Data Reservasi (menggunakan INSERT IGNORE agar tidak error jika sudah ada data)
INSERT IGNORE INTO reservasi (id, nama_tamu, telepon, email, tipe_kamar, jumlah_tamu, check_in, check_out, status, created_at, updated_at) VALUES 
(1, 'Budi Santoso', '081234567890', 'budi@example.com', 'Deluxe', 2, DATE_ADD(CURDATE(), INTERVAL 1 DAY), DATE_ADD(CURDATE(), INTERVAL 3 DAY), 'CONFIRMED', NOW(), NOW()),
(2, 'Siti Aminah', '085612345678', 'siti@example.com', 'Standard', 1, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 2 DAY), 'CHECKED_IN', NOW(), NOW()),
(3, 'Andi Wijaya', '087812345678', 'andi@example.com', 'Suite', 3, DATE_ADD(CURDATE(), INTERVAL 5 DAY), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'PENDING', NOW(), NOW()),
(4, 'Rina Marlina', '081122334455', 'rina@example.com', 'Standard', 2, DATE_SUB(CURDATE(), INTERVAL 2 DAY), CURDATE(), 'CHECKED_OUT', DATE_SUB(NOW(), INTERVAL 2 DAY), NOW()),
(5, 'Doni Pratama', '089988776655', 'doni@example.com', 'Deluxe', 2, DATE_ADD(CURDATE(), INTERVAL 2 DAY), DATE_ADD(CURDATE(), INTERVAL 4 DAY), 'CONFIRMED', NOW(), NOW());
