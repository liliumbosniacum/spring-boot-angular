package com.lilium.springangular.service;

import com.lilium.springangular.api.VehicleApi;
import com.lilium.springangular.converter.VehicleDTOConverter;
import com.lilium.springangular.dto.VehicleDTO;
import com.lilium.springangular.entity.Vehicle;
import com.lilium.springangular.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService extends AbstractCRUDLService<Vehicle, VehicleDTO> implements VehicleApi {

    @Autowired
    public VehicleService(final VehicleRepository repository,
                          final VehicleDTOConverter converter) {
        super(repository, converter);
    }

    @Override
    protected void updateEntity(Vehicle entity, VehicleDTO dto) {
        entity.setNumber(dto.getNumber());
    }

    @Override
    protected String getEntityTopic() {
        return "vehicle";
    }
}
