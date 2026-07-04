package com.hotel.reservasi.service;

import com.hotel.reservasi.entity.Reservasi;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface Service untuk business logic Reservasi
 */
public interface ReservasiService {

    // CRUD
    List<Reservasi> getAllReservasi();
    Optional<Reservasi> getReservasiById(Long id);
    Reservasi saveReservasi(Reservasi reservasi);
    void deleteReservasiById(Long id);

    // Search
    List<Reservasi> searchByNamaTamu(String namaTamu);
    List<Reservasi> searchByTelepon(String telepon);
    List<Reservasi> searchByTipeKamar(String tipeKamar);

    // Dashboard
    long getTotalReservasi();
    Map<String, Long> getJumlahPerTipeKamar();
    List<Reservasi> getReservasiTerbaru();
    Map<String, Long> getJumlahPerStatus();
}
