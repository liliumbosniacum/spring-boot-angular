package com.lilium.springangular.service;

import com.lilium.springangular.api.VehicleTypeApi;
import com.lilium.springangular.converter.VehicleTypeDTOConverter;
import com.lilium.springangular.dto.VehicleTypeDTO;
import com.lilium.springangular.entity.VehicleType;
import com.lilium.springangular.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeService extends AbstractCRUDLService<VehicleType, VehicleTypeDTO> implements VehicleTypeApi {

    @Autowired
    public VehicleTypeService(final VehicleTypeRepository repository,
                              final VehicleTypeDTOConverter converter) {
        super(repository, converter);
    }

    @Override
    protected void updateEntity(VehicleType entity, VehicleTypeDTO dto) {
        entity.setName(dto.getName());
    }

    @Override
    protected String getEntityTopic() {
        return "vehicletype";
    }
}
