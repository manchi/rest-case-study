package com.paypal.bfs.test.bookingserv.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.common.BookingException;
import com.paypal.bfs.test.bookingserv.service.BookingService;

import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
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

    @SneakyThrows
    @Test
    public void givenBooking_whenCreateIsCalled_thenReturnValidResponse() {
        String content = "{\n"
                         + "    \"first_name\": \"Adam\",\n"
                         + "    \"last_name\": \"Smith\",\n"
                         + "    \"date_of_birth\": \"12-03-1966\",\n"
                         + "    \"address\": {\n"
                         + "        \"line1\": \"Serrano Drive\",\n"
                         + "        \"line2\": \"2nd Street\",\n"
                         + "        \"city\": \"San Fransicso\",\n"
                         + "        \"state\": \"CA\",\n"
                         + "        \"zipcode\": \"91120\"\n"
                         + "    },\n"
                         + "    \"checkin_date\": \"12-06-2021\",\n"
                         + "    \"checkout_date\": \"15-05-2021\",\n"
                         + "    \"deposit\": \"500\",\n"
                         + "    \"total_price\": \"1890\"\n"
                         + "}";
        when(bookingService.create(any())).thenReturn(mockBooking);

        mvc.perform(
                post("/v1/bfs/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isOk());
    }


    @SneakyThrows
    @Test
    public void whenGetAllIsCalled_thenReturnValidBookingList() {
        List<Booking> list = new ArrayList<>();
        list.add(mockBooking);

        when(bookingService.getAll()).thenReturn(list);

        mvc.perform(
                get("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void whenDuplicateCreateIsCalled_thenThrowError() {
        when(bookingService.create(any())).thenReturn(mockBooking);

        when(bookingService.create(any())).thenThrow(BookingException.class);

        mvc.perform(
                post("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON).content("{}")
        )
                .andExpect(status().is5xxServerError());
    }
}
