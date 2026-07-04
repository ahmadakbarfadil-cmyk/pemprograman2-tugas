package com.hotel.reservasi.service.impl;

import com.hotel.reservasi.entity.Reservasi;
import com.hotel.reservasi.exception.ResourceNotFoundException;
import com.hotel.reservasi.repository.ReservasiRepository;
import com.hotel.reservasi.service.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Implementasi business logic untuk ReservasiService
 */
@Service
@Transactional
public class ReservasiServiceImpl implements ReservasiService {

    private final ReservasiRepository reservasiRepository;

    @Autowired
    public ReservasiServiceImpl(ReservasiRepository reservasiRepository) {
        this.reservasiRepository = reservasiRepository;
    }

    // =============================================
    // CRUD Operations
    // =============================================

    @Override
    @Transactional(readOnly = true)
    public List<Reservasi> getAllReservasi() {
        return reservasiRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservasi> getReservasiById(Long id) {
        return reservasiRepository.findById(id);
    }

    @Override
    public Reservasi saveReservasi(Reservasi reservasi) {
        // Validasi bisnis: check out tidak boleh sebelum check in
        if (reservasi.getCheckOut() != null && reservasi.getCheckIn() != null) {
            if (reservasi.getCheckOut().isBefore(reservasi.getCheckIn())) {
                throw new IllegalArgumentException("Tanggal Check Out tidak boleh lebih kecil dari Check In");
            }
            if (reservasi.getCheckOut().isEqual(reservasi.getCheckIn())) {
                throw new IllegalArgumentException("Tanggal Check Out tidak boleh sama dengan Check In");
            }
        }
        return reservasiRepository.save(reservasi);
    }

    @Override
    public void deleteReservasiById(Long id) {
        if (!reservasiRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservasi dengan ID " + id + " tidak ditemukan");
        }
        reservasiRepository.deleteById(id);
    }

    // =============================================
    // Search Operations
    // =============================================

    @Override
    @Transactional(readOnly = true)
    public List<Reservasi> searchByNamaTamu(String namaTamu) {
        if (namaTamu == null || namaTamu.trim().isEmpty()) {
            return reservasiRepository.findAll();
        }
        return reservasiRepository.findByNamaTamuContainingIgnoreCase(namaTamu.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservasi> searchByTelepon(String telepon) {
        if (telepon == null || telepon.trim().isEmpty()) {
            return reservasiRepository.findAll();
        }
        return reservasiRepository.findByTeleponContaining(telepon.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservasi> searchByTipeKamar(String tipeKamar) {
        if (tipeKamar == null || tipeKamar.trim().isEmpty()) {
            return reservasiRepository.findAll();
        }
        return reservasiRepository.findByTipeKamarIgnoreCase(tipeKamar.trim());
    }

    // =============================================
    // Dashboard Operations
    // =============================================

    @Override
    @Transactional(readOnly = true)
    public long getTotalReservasi() {
        return reservasiRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getJumlahPerTipeKamar() {
        List<Object[]> results = reservasiRepository.countByTipeKamar();
        Map<String, Long> map = new LinkedHashMap<>();
        for (Object[] row : results) {
            map.put((String) row[0], (Long) row[1]);
        }
        // Pastikan semua tipe kamar ada, meski jumlahnya 0
        String[] tipeKamars = {"Standard", "Deluxe", "Suite", "Family"};
        for (String tipe : tipeKamars) {
            map.putIfAbsent(tipe, 0L);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservasi> getReservasiTerbaru() {
        return reservasiRepository.findTop5ByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getJumlahPerStatus() {
        Map<String, Long> map = new LinkedHashMap<>();
        String[] statusList = {"Booked", "Check In", "Check Out", "Cancelled"};
        for (String st : statusList) {
            map.put(st, reservasiRepository.countByStatus(st));
        }
        return map;
    }
}
