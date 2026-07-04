package com.hotel.reservasi.repository;

import com.hotel.reservasi.entity.Reservasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Repository untuk akses data Reservasi menggunakan Spring Data JPA
 */
@Repository
public interface ReservasiRepository extends JpaRepository<Reservasi, Long> {

    // Cari berdasarkan nama tamu (case-insensitive, partial match)
    List<Reservasi> findByNamaTamuContainingIgnoreCase(String namaTamu);

    // Cari berdasarkan nomor telepon (partial match)
    List<Reservasi> findByTeleponContaining(String telepon);

    // Cari berdasarkan tipe kamar (exact match, case-insensitive)
    List<Reservasi> findByTipeKamarIgnoreCase(String tipeKamar);

    // Hitung jumlah reservasi per tipe kamar
    @Query("SELECT r.tipeKamar, COUNT(r) FROM Reservasi r GROUP BY r.tipeKamar")
    List<Object[]> countByTipeKamar();

    // Ambil 5 reservasi terbaru
    List<Reservasi> findTop5ByOrderByCreatedAtDesc();

    // Hitung berdasarkan status
    Long countByStatus(String status);
}
