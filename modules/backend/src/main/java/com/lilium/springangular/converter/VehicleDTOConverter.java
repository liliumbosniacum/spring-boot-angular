package com.lilium.springangular.converter;

import com.lilium.springangular.dto.VehicleDTO;
import com.lilium.springangular.dto.VehicleTypeDTO;
import com.lilium.springangular.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleDTOConverter extends AbstractDTOConverter<Vehicle, VehicleDTO> {

    @Override
    public VehicleDTO convert(final Vehicle entity) {
        final VehicleDTO dto = new VehicleDTO();
        super.convert(entity, dto);

        dto.setNumber(entity.getNumber());

        final VehicleTypeDTO type = new VehicleTypeDTO();
        type.setId(entity.getType().getId());
        type.setName(entity.getType().getName());
        dto.setType(type);

        return dto;
    }
}
