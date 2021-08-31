package com.paypal.bfs.test.bookingserv.common;

import java.util.Optional;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.domain.AddressEntity;
import com.paypal.bfs.test.bookingserv.domain.BookingEntity;

public class Util {

    /**
     * Convert the booking domain object to booking class.
     *
     * @param entity The domain model.
     * @return Booking object.
     */
    public static Booking convert(BookingEntity entity) {

        Booking booking = new Booking();
        booking.setId(entity.getId());
        booking.setFirstName(entity.getFirstName());
        booking.setLastName(entity.getLastName());
        booking.setDateOfBirth(entity.getDateOfBirth());

        Address address = new Address();
        Optional.ofNullable(entity.getAddressEntity())
                .ifPresent(saved -> {
                    address.setLine1(saved.getLine1());
                    address.setLine2(saved.getLine2());
                    address.setCity(saved.getCity());
                    address.setState(saved.getState());
                    address.setZipCode(saved.getZipCode());
                });
        booking.setAddress(address);

        booking.setDateOfBirth(entity.getDateOfBirth());
        booking.setCheckinDate(entity.getCheckinDate());
        booking.setCheckoutDate(entity.getCheckoutDate());
        booking.setDeposit(entity.getDeposit());
        booking.setTotalPrice(entity.getTotalPrice());

        return booking;
    }

    /**
     * Convert the booking class to domain object.
     *
     * @param booking The class..
     * @return BookingEntity object.
     */
    public static BookingEntity convert(Booking booking) {

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setFirstName(booking.getFirstName());
        bookingEntity.setLastName(booking.getLastName());
        bookingEntity.setDateOfBirth(booking.getDateOfBirth());

        AddressEntity address = new AddressEntity();
        Optional.ofNullable(booking.getAddress())
                .ifPresent(newAddress -> {
                    address.setLine1(newAddress.getLine1());
                    address.setLine2(newAddress.getLine2());
                    address.setCity(newAddress.getCity());
                    address.setState(newAddress.getState());
                    address.setZipCode(newAddress.getZipCode());
                });
        bookingEntity.setAddressEntity(address);

        bookingEntity.setDateOfBirth(booking.getDateOfBirth());
        bookingEntity.setCheckinDate(booking.getCheckinDate());
        bookingEntity.setCheckoutDate(booking.getCheckoutDate());
        bookingEntity.setDeposit(booking.getDeposit());
        bookingEntity.setTotalPrice(booking.getTotalPrice());

        return bookingEntity;
    }

    public static boolean isEmpty(String string) {
        return (string == null || string.trim() == "");
    }
}
