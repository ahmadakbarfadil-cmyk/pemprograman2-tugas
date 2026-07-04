package com.hotel.reservasi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity Reservasi - merepresentasikan tabel reservasi di MySQL
 * Tidak menggunakan Lombok agar tidak error di NetBeans tanpa konfigurasi tambahan
 */
@Entity
@Table(name = "reservasi")
public class Reservasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama tamu wajib diisi")
    @Size(min = 3, message = "Nama minimal 3 karakter")
    @Column(name = "nama_tamu", length = 100, nullable = false)
    private String namaTamu;

    @NotBlank(message = "Nomor telepon wajib diisi")
    @Pattern(regexp = "^[0-9]+$", message = "Nomor telepon hanya boleh berisi angka")
    @Size(min = 6, max = 20, message = "Nomor telepon harus antara 6 sampai 20 digit")
    @Column(name = "telepon", length = 20, nullable = false)
    private String telepon;

    @NotBlank(message = "Email wajib diisi")
    @Email(message = "Format email tidak valid")
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotBlank(message = "Tipe kamar wajib diisi")
    @Column(name = "tipe_kamar", length = 50, nullable = false)
    private String tipeKamar;

    @NotNull(message = "Jumlah tamu wajib diisi")
    @Min(value = 1, message = "Jumlah tamu minimal 1 orang")
    @Max(value = 10, message = "Jumlah tamu maksimal 10 orang")
    @Column(name = "jumlah_tamu", nullable = false)
    private Integer jumlahTamu;

    @NotNull(message = "Tanggal check-in wajib diisi")
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @NotNull(message = "Tanggal check-out wajib diisi")
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @NotBlank(message = "Status reservasi wajib diisi")
    @Column(name = "status", length = 30, nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    // =============================================
    // Constructors
    // =============================================
    public Reservasi() {
    }

    // =============================================
    // Getters and Setters
    // =============================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaTamu() {
        return namaTamu;
    }

    public void setNamaTamu(String namaTamu) {
        this.namaTamu = namaTamu;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public Integer getJumlahTamu() {
        return jumlahTamu;
    }

    public void setJumlahTamu(Integer jumlahTamu) {
        this.jumlahTamu = jumlahTamu;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // =============================================
    // Lifecycle Callbacks
    // =============================================

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
