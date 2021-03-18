package com.lilium.springangular.repository;

import com.lilium.springangular.entity.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends DistributedRepository<Vehicle> {
}
