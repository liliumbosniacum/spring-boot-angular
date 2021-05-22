package com.lilium.springangular.service;

import com.lilium.springangular.api.VehicleApi;
import com.lilium.springangular.converter.VehicleDTOConverter;
import com.lilium.springangular.dto.VehicleDTO;
import com.lilium.springangular.entity.Vehicle;
import com.lilium.springangular.entity.VehicleType;
import com.lilium.springangular.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService extends AbstractCRUDLService<Vehicle, VehicleDTO> implements VehicleApi {
    private final VehicleTypeService vehicleTypeService;

    @Autowired
    public VehicleService(final VehicleRepository repository,
                          final VehicleDTOConverter converter,
                          final VehicleTypeService vehicleTypeService) {
        super(repository, converter);
        this.vehicleTypeService = vehicleTypeService;
    }

    @Override
    protected void updateEntity(final Vehicle entity, final VehicleDTO dto) {
        entity.setNumber(dto.getNumber());

        // Find vehicle type entity and set it to the vehicle
        final VehicleType vehicleType = vehicleTypeService.findEntityById(
                dto.getType().getId()
        );
        entity.setType(vehicleType);
    }

    @Override
    protected String getEntityTopic() {
        return "vehicle";
    }
}
