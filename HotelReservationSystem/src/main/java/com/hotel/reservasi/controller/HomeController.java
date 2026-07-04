package com.hotel.reservasi.controller;

import com.hotel.reservasi.service.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller untuk halaman utama / Dashboard
 */
@Controller
public class HomeController {

    private final ReservasiService reservasiService;

    @Autowired
    public HomeController(ReservasiService reservasiService) {
        this.reservasiService = reservasiService;
    }

    /**
     * Halaman dashboard utama
     */
    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        // Total reservasi
        model.addAttribute("totalReservasi", reservasiService.getTotalReservasi());

        // Jumlah kamar per tipe
        model.addAttribute("jumlahPerTipeKamar", reservasiService.getJumlahPerTipeKamar());

        // Jumlah per status
        model.addAttribute("jumlahPerStatus", reservasiService.getJumlahPerStatus());

        // Reservasi terbaru (5 data)
        model.addAttribute("reservasiTerbaru", reservasiService.getReservasiTerbaru());

        return "dashboard";
    }
}
