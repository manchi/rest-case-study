package com.paypal.bfs.test.bookingserv.api;

import java.util.List;

import javax.validation.Valid;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BookingResource {
    /**
     * Create {@link Booking} resource
     *
     * @param booking the booking object
     * @return the created booking
     */
    @RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<Booking> create(@Valid @RequestBody Booking booking);

    @RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Booking>> getAll();
}
