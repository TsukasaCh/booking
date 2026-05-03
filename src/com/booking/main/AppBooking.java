package com.booking.main;

import com.booking.views.BookingView;

import javax.swing.*;

public class AppBooking {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingView view = new BookingView();
            view.setVisible(true);
        });
    }
}
