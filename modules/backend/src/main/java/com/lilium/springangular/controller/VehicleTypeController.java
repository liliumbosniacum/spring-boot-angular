package com.lilium.springangular.controller;

import com.lilium.springangular.api.VehicleTypeApi;
import com.lilium.springangular.dto.VehicleTypeDTO;
import com.lilium.springangular.entity.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicletypes")
public class VehicleTypeController extends AbstractCRUDLController<VehicleType, VehicleTypeDTO> {

    @Autowired
    public VehicleTypeController(final VehicleTypeApi api) {
        super(api);
    }
}
