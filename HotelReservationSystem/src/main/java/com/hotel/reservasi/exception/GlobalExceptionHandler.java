package com.hotel.reservasi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;

/**
 * Global Exception Handler menggunakan @ControllerAdvice
 * Menangani semua error secara terpusat
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle 404 - Halaman tidak ditemukan
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundError(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorCode", "404");
        model.addAttribute("errorMessage", "Halaman yang Anda cari tidak ditemukan.");
        return "error";
    }

    /**
     * Handle No Resource Found (Spring 6+)
     */
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoResourceFound(NoResourceFoundException ex, Model model) {
        model.addAttribute("errorCode", "404");
        model.addAttribute("errorMessage", "Halaman yang Anda cari tidak ditemukan.");
        return "error";
    }

    /**
     * Handle ResourceNotFoundException
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorCode", "404");
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * Handle IllegalArgumentException - Input tidak valid
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorCode", "400");
        model.addAttribute("errorMessage", "Input tidak valid: " + ex.getMessage());
        return "error";
    }

    /**
     * Handle Database Error
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDatabaseError(SQLException ex, Model model) {
        model.addAttribute("errorCode", "500");
        model.addAttribute("errorMessage", "Terjadi kesalahan pada database. Pastikan koneksi MySQL aktif.");
        return "error";
    }

    /**
     * Handle semua error lainnya
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralError(Exception ex, Model model) {
        model.addAttribute("errorCode", "500");
        model.addAttribute("errorMessage", "Terjadi kesalahan internal: " + ex.getMessage());
        return "error";
    }
}
