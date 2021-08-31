package com.paypal.bfs.test.bookingserv.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "booking")
@Data
@ToString
@EqualsAndHashCode
public class BookingEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "dob")
    Date dateOfBirth;

    @Column(name = "checkin_date")
    Date checkinDate;

    @Column(name = "checkout_date")
    Date checkoutDate;

    @Column(name = "deposit")
    Double deposit;

    @Column(name = "total_price")
    Double totalPrice;

    @OneToOne(targetEntity = AddressEntity.class, cascade = CascadeType.ALL)
    private AddressEntity addressEntity;
}

