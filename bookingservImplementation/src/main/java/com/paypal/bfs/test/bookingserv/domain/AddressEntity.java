package com.paypal.bfs.test.bookingserv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Table(name = "address")
@Data
@ToString
@EqualsAndHashCode
public class AddressEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column
    String line1;

    @Column
    String line2;

    @Column
    String city;

    @Column
    String state;

    @Column(name = "zip_code")
    String zipCode;
}
