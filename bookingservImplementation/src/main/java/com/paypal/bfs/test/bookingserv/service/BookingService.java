package com.paypal.bfs.test.bookingserv.service;

import java.util.List;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

public interface BookingService {

    /**
     * Create a new booking.
     *
     * @param booking Booking object.
     * @return newly created booking object.
     */
    Booking create(Booking booking);

    /**
     * Get all bookings from the system.
     *
     * @return List containing all the booking entries.
     */
    List<Booking> getAll();

}
