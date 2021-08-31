package com.paypal.bfs.test.bookingserv.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.common.BookingException;
import com.paypal.bfs.test.bookingserv.common.Util;
import com.paypal.bfs.test.bookingserv.common.ValidationException;
import com.paypal.bfs.test.bookingserv.domain.BookingEntity;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;
import com.paypal.bfs.test.bookingserv.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking create(Booking booking) {
        validate(booking);

        List<BookingEntity> entries =
                bookingRepository.findBookingEntityByFirstNameAndLastNameAndDateOfBirthAndCheckinDate(
                        booking.getFirstName(),
                        booking.getLastName(),
                        booking.getDateOfBirth(),
                        booking.getCheckinDate());
        if (entries.size() > 0) {
            // duplicate record
            throw new BookingException("The booking already exists for the user.");
        }

        BookingEntity saved = bookingRepository.save(Util.convert(booking));
        Booking response = Util.convert(saved);
        return response;
    }

    private void validate(Booking booking) throws ValidationException {

        if (Util.isEmpty(booking.getFirstName()) || Util.isEmpty(booking.getLastName())) {
            throw new ValidationException("The First name and Last name are required to create a booking.");
        }

        if (booking.getDateOfBirth().after(new Date())) {
            throw new ValidationException("The date of birth cannot be greater than today's date.");
        }

        if (booking.getCheckinDate().after(booking.getCheckoutDate())) {
            throw new ValidationException("The checkin date cannot be greater checkout date.");
        }

    }

    @Override
    public List<Booking> getAll() {
        List<BookingEntity> records = bookingRepository.findAll();

        List<Booking> bookingList = records.stream()
                .map(entity -> Util.convert(entity))
                .collect(Collectors.toList());
        return bookingList;
    }
}
