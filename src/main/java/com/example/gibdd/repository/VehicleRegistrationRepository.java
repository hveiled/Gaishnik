package com.example.gibdd.repository;

import com.example.gibdd.model.VehicleRegistrationNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRegistrationRepository extends JpaRepository<VehicleRegistrationNumber, Long> {
    VehicleRegistrationNumber findByGrantedNumber(String number);
    boolean existsByGrantedNumber(String number);
}
