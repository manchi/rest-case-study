package com.paypal.bfs.test.bookingserv.service;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.common.ValidationException;

import lombok.SneakyThrows;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookingServiceTests {

    @Autowired
    BookingService bookingService;


    SimpleDateFormat simpleDateFormat;
    Booking mockBooking;

    @Before
    @SneakyThrows
    public void before() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        mockBooking = new Booking();
        mockBooking.setId(1);
        mockBooking.setFirstName("first");
        mockBooking.setLastName("last");
        mockBooking.setDateOfBirth(simpleDateFormat.parse("10-10-1980"));
        mockBooking.setDeposit(100.0);
        mockBooking.setCheckinDate(simpleDateFormat.parse("10-10-2020"));
        mockBooking.setCheckoutDate(simpleDateFormat.parse("20-10-1980"));
        mockBooking.setTotalPrice(1000.0);

        Address address = new Address();
        address.setLine1("line 1");
        address.setCity("city 1");
        address.setState("state 1");
        address.setZipCode("93245");

        mockBooking.setAddress(address);
    }

    @Test
    public void whenValidInputIsProvided_thenBookingIsCreated() {
        when(bookingService.create(mockBooking)).thenReturn(mockBooking);
        Assert.assertEquals(new Integer(1), mockBooking.getId());
    }

    @Test
    public void whenGetAllIsCalled_thenReturnValidResponse() {
        List<Booking> list = new ArrayList<>();
        list.add(mockBooking);
        when(bookingService.getAll()).thenReturn(list);
        Assert.assertEquals(1, list.size());
    }

    @Test()
    public void whenDOBIsInvalid_thenThrowError() {
        try {
            mockBooking.setDateOfBirth(simpleDateFormat.parse("20-10-2030"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(bookingService.create(mockBooking)).thenThrow(new ValidationException("exception"));
    }

    @Test()
    public void whenCheckinDateIsInvalid_thenThrowError() {
        try {
            mockBooking.setCheckinDate(simpleDateFormat.parse("20-10-2030"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(bookingService.create(mockBooking)).thenThrow(new ValidationException("exception"));
    }


}