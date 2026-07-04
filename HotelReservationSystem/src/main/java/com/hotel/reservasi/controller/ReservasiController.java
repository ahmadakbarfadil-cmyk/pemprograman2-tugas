package com.hotel.reservasi.controller;

import com.hotel.reservasi.entity.Reservasi;
import com.hotel.reservasi.exception.ResourceNotFoundException;
import com.hotel.reservasi.service.ReservasiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller untuk manajemen Reservasi
 * Routes:
 *   GET  /reservasi              -> daftar reservasi
 *   GET  /reservasi/create       -> form tambah
 *   POST /reservasi/save         -> simpan baru
 *   GET  /reservasi/edit/{id}    -> form edit
 *   POST /reservasi/update/{id}  -> update data
 *   GET  /reservasi/delete/{id}  -> hapus data
 *   GET  /reservasi/detail/{id}  -> detail data
 */
@Controller
@RequestMapping("/reservasi")
public class ReservasiController {

    private final ReservasiService reservasiService;

    @Autowired
    public ReservasiController(ReservasiService reservasiService) {
        this.reservasiService = reservasiService;
    }

    // =============================================
    // LIST - Daftar Reservasi dengan Search
    // =============================================
    @GetMapping
    public String listReservasi(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "telepon", required = false, defaultValue = "") String telepon,
            @RequestParam(value = "tipeKamar", required = false, defaultValue = "") String tipeKamar,
            Model model) {

        List<Reservasi> listReservasi;

        if (!search.isEmpty()) {
            listReservasi = reservasiService.searchByNamaTamu(search);
        } else if (!telepon.isEmpty()) {
            listReservasi = reservasiService.searchByTelepon(telepon);
        } else if (!tipeKamar.isEmpty()) {
            listReservasi = reservasiService.searchByTipeKamar(tipeKamar);
        } else {
            listReservasi = reservasiService.getAllReservasi();
        }

        model.addAttribute("listReservasi", listReservasi);
        model.addAttribute("totalData", listReservasi.size());
        model.addAttribute("searchNama", search);
        model.addAttribute("searchTelepon", telepon);
        model.addAttribute("searchTipeKamar", tipeKamar);
        return "reservasi-list";
    }

    // =============================================
    // CREATE - Form Tambah Reservasi
    // =============================================
    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("reservasi", new Reservasi());
        model.addAttribute("isEdit", false);
        return "reservasi-form";
    }

    // =============================================
    // SAVE - Simpan Reservasi Baru
    // =============================================
    @PostMapping("/save")
    public String saveReservasi(
            @Valid @ModelAttribute("reservasi") Reservasi reservasi,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Validasi check out vs check in
        if (reservasi.getCheckIn() != null && reservasi.getCheckOut() != null) {
            if (reservasi.getCheckOut().isBefore(reservasi.getCheckIn())) {
                bindingResult.rejectValue("checkOut", "error.reservasi",
                        "Check Out tidak boleh lebih kecil dari Check In");
            } else if (reservasi.getCheckOut().isEqual(reservasi.getCheckIn())) {
                bindingResult.rejectValue("checkOut", "error.reservasi",
                        "Check Out tidak boleh sama dengan Check In");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "reservasi-form";
        }

        try {
            reservasiService.saveReservasi(reservasi);
            redirectAttributes.addFlashAttribute("successMessage", "Reservasi berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("isEdit", false);
            return "reservasi-form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Gagal menyimpan reservasi: " + e.getMessage());
            model.addAttribute("isEdit", false);
            return "reservasi-form";
        }

        return "redirect:/reservasi";
    }

    // =============================================
    // EDIT - Form Edit Reservasi
    // =============================================
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable("id") Long id, Model model) {
        Reservasi reservasi = reservasiService.getReservasiById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservasi", "id", id));
        model.addAttribute("reservasi", reservasi);
        model.addAttribute("isEdit", true);
        return "reservasi-form";
    }

    // =============================================
    // UPDATE - Update Reservasi
    // =============================================
    @PostMapping("/update/{id}")
    public String updateReservasi(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("reservasi") Reservasi reservasi,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Validasi check out vs check in
        if (reservasi.getCheckIn() != null && reservasi.getCheckOut() != null) {
            if (reservasi.getCheckOut().isBefore(reservasi.getCheckIn())) {
                bindingResult.rejectValue("checkOut", "error.reservasi",
                        "Check Out tidak boleh lebih kecil dari Check In");
            } else if (reservasi.getCheckOut().isEqual(reservasi.getCheckIn())) {
                bindingResult.rejectValue("checkOut", "error.reservasi",
                        "Check Out tidak boleh sama dengan Check In");
            }
        }

        if (bindingResult.hasErrors()) {
            reservasi.setId(id);
            model.addAttribute("isEdit", true);
            return "reservasi-form";
        }

        try {
            reservasi.setId(id);
            reservasiService.saveReservasi(reservasi);
            redirectAttributes.addFlashAttribute("successMessage", "Reservasi berhasil diperbarui!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            reservasi.setId(id);
            model.addAttribute("isEdit", true);
            return "reservasi-form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Gagal memperbarui reservasi: " + e.getMessage());
            reservasi.setId(id);
            model.addAttribute("isEdit", true);
            return "reservasi-form";
        }

        return "redirect:/reservasi";
    }

    // =============================================
    // DELETE - Hapus Reservasi
    // =============================================
    @GetMapping("/delete/{id}")
    public String deleteReservasi(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            reservasiService.deleteReservasiById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Reservasi berhasil dihapus!");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal menghapus reservasi: " + e.getMessage());
        }
        return "redirect:/reservasi";
    }

    // =============================================
    // DETAIL - Detail Reservasi
    // =============================================
    @GetMapping("/detail/{id}")
    public String detailReservasi(@PathVariable("id") Long id, Model model) {
        Reservasi reservasi = reservasiService.getReservasiById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservasi", "id", id));
        model.addAttribute("reservasi", reservasi);
        return "reservasi-detail";
    }
}
