package com.paypal.bfs.test.bookingserv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.bookingserv.domain.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

}
