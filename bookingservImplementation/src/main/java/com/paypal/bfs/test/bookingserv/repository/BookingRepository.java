package com.paypal.bfs.test.bookingserv.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.bookingserv.domain.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    List<BookingEntity> findBookingEntityByFirstNameAndLastNameAndDateOfBirthAndCheckinDate(String firstName,
                                                                                            String lastName,
                                                                                            Date dateOfBirth,
                                                                                            Date checkin);

}
