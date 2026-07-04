package com.hotel.reservasi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfigurasi Web MVC untuk aplikasi Hotel Reservation System
 * UAS Pemrograman II
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Route default "/" akan diteruskan langsung ke Controller,
        // namun kita bisa menambahkan mapping tambahan di sini jika diperlukan.
    }
}
