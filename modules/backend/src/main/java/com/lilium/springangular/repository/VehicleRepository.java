package com.lilium.springangular.repository;

import com.lilium.springangular.entity.Vehicle;
import com.lilium.springangular.entity.VehicleType_;
import com.lilium.springangular.entity.Vehicle_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends DistributedRepository<Vehicle> {

    class Specs {
        public static Specification<Vehicle> byVehicleType(final Integer vehicleTypeId) {
            return (root, query, cb) -> cb.equal(
                    root.get(Vehicle_.type).get(VehicleType_.ID),
                    vehicleTypeId
            );
        }
    }
}
