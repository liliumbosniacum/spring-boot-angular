package com.lilium.springangular;

import com.lilium.springangular.dto.VehicleDTO;
import com.lilium.springangular.dto.VehicleTypeDTO;
import com.lilium.springangular.dto.search.PagedResponse;
import com.lilium.springangular.dto.search.SearchRequest;
import com.lilium.springangular.entity.Vehicle;
import com.lilium.springangular.repository.VehicleRepository;
import com.lilium.springangular.service.VehicleService;
import com.lilium.springangular.service.VehicleTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
public class VehicleServiceIntegrationTest {
    private VehicleTypeDTO type;

    @Autowired
    public VehicleService service;

    @Autowired
    public VehicleTypeService typeService;

    @Autowired
    public VehicleRepository vehicleRepository;

    @BeforeEach
    public void setup() {
        VehicleTypeDTO dto = new VehicleTypeDTO();
        dto.setName("Vehicle type");

        type = typeService.save(dto);
    }

    @Test
    public void testVehicleNumberNull() {
        final VehicleDTO dto = new VehicleDTO();
        dto.setType(type);

        // Should not be saved
        assertThat(service.save(dto)).isNull();
    }

    @Test
    public void testVehicleNumberUnique() {
        final VehicleDTO dto = new VehicleDTO();
        dto.setNumber("SBA - 1");
        dto.setType(type);

        assertThatCode(() -> service.save(dto)).doesNotThrowAnyException();
        assertThat(service.save(dto)).isNull();
    }

    @Test
    void testVehicleCRUDL() {
        final VehicleDTO dto = new VehicleDTO();
        dto.setNumber("Vehicle test number");
        dto.setType(type);

        final VehicleDTO savedVehicle = service.save(dto);
        assertThat(savedVehicle).isNotNull();
        assertThat(savedVehicle.getId()).isNotNull();
        assertThat(savedVehicle.getCreated()).isNotNull();
        assertThat(savedVehicle.getModified()).isNotNull();
        assertThat(savedVehicle.getNumber()).isEqualTo(dto.getNumber());

        final VehicleDTO vehicleByID = service.getById(savedVehicle.getId());
        assertThat(vehicleByID).isNotNull();
        assertThat(vehicleByID)
                .extracting(
                        VehicleDTO::getId,
                        VehicleDTO::getNumber
                )
                .contains(
                        savedVehicle.getId(),
                        savedVehicle.getNumber()
                );

        final PagedResponse<VehicleDTO> list = service.list(new SearchRequest());
        final List<VehicleDTO> vehicles = list.getContent();
        assertThat(vehicles).isNotEmpty();

        savedVehicle.setNumber("new vehicle number");
        final VehicleDTO updatedVehicle = service.save(savedVehicle);
        assertThat(updatedVehicle).isNotNull();
        assertThat(updatedVehicle.getNumber()).isEqualTo(savedVehicle.getNumber());
        assertThat(updatedVehicle.getModified()).isAfter(savedVehicle.getModified());

        final Boolean deleted = service.delete(savedVehicle.getId());
        assertThat(deleted).isTrue();
        assertThat(service.getById(savedVehicle.getId())).isNull();

        assertThat(service.delete(677774354)).isFalse();
    }

    @Test
    public void findModifiedSince() {
        final VehicleDTO dto = new VehicleDTO();
        dto.setType(type);

        // Create 3 vehicles
        dto.setNumber("V1");
        service.save(dto);

        dto.setNumber("V2");
        final VehicleDTO v2 = service.save(dto);

        dto.setNumber("V3");
        service.save(dto);

        final PagedResponse<VehicleDTO> list = service.list(new SearchRequest());
        assertThat(list.getContent())
                .extracting(VehicleDTO::getNumber)
                .contains("V1", "V2", "V3");

        // Remember timestamp before modification
        final LocalDateTime timestampBeforeModification = LocalDateTime.now();

        // Modify second vehicle
        v2.setNumber("V2 - edited");
        service.save(v2);

        List<Vehicle> allModifiedSince = vehicleRepository.findAllModifiedSince(timestampBeforeModification);
        assertThat(allModifiedSince).hasSize(1);
        assertThat(allModifiedSince.get(0).getNumber()).isEqualTo(v2.getNumber());
    }

    @Test
    public void findByVehicleType() {
        VehicleTypeDTO t1 = new VehicleTypeDTO();
        t1.setName("T1");
        t1 = typeService.save(t1);

        VehicleTypeDTO t2 = new VehicleTypeDTO();
        t2.setName("T2");
        t2 = typeService.save(t2);

        VehicleDTO v1 = new VehicleDTO();
        v1.setType(t1);
        v1.setNumber("xx-1");
        v1 = service.save(v1);

        VehicleDTO v2 = new VehicleDTO();
        v2.setType(t2);
        v2.setNumber("xx-2");
        v2 = service.save(v2);

        final List<Vehicle> r1 = vehicleRepository.findAll(VehicleRepository.Specs.byVehicleType(t1.getId()));
        final List<Vehicle> r2 = vehicleRepository.findAll(VehicleRepository.Specs.byVehicleType(t2.getId()));

        assertThat(r1).hasSize(1);
        assertThat(r1).extracting(Vehicle::getId).containsExactly(v1.getId());

        assertThat(r2).hasSize(1);
        assertThat(r2).extracting(Vehicle::getId).containsExactly(v2.getId());
    }
}
