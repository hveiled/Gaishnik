package com.example.gibdd.model;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Data
@Table(name = "vehicle_registration_numbers")
@Transactional
public class VehicleRegistrationNumber {

    public static final String REGION = "116 RUS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "numeric_value")
    private String numericValue;

    @Column(name = "postfix")
    private String postfix;

    @Column(name = "granted_number")
    private String grantedNumber;

    @Override
    public String toString() {
        return prefix + numericValue + postfix + " " + REGION;
    }
}
