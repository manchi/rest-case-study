package com.paypal.bfs.test.bookingserv;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;
import com.paypal.bfs.test.bookingserv.service.BookingService;
import com.paypal.bfs.test.bookingserv.service.impl.BookingServiceImpl;

@Profile("test")
@Configuration
public class TestConfiguration {

    @Primary
    @Bean
    public BookingService bookingService(){
        return Mockito.mock(BookingService.class);
    }
}
