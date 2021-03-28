package com.lilium.springangular.controller;

import com.lilium.springangular.api.VehicleApi;
import com.lilium.springangular.dto.VehicleDTO;
import com.lilium.springangular.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController extends AbstractCRUDLController<Vehicle, VehicleDTO> {

    @Autowired
    public VehicleController(final VehicleApi api) {
        super(api);
    }
}
