package com.lilium.springangular.converter;

import com.lilium.springangular.dto.VehicleTypeDTO;
import com.lilium.springangular.entity.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeDTOConverter extends AbstractDTOConverter<VehicleType, VehicleTypeDTO> {

    @Override
    public VehicleTypeDTO convert(VehicleType entity) {
        final VehicleTypeDTO dto = new VehicleTypeDTO();
        super.convert(entity, dto);

        dto.setName(entity.getName());

        return dto;
    }
}
