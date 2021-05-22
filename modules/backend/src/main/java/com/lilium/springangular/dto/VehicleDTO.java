package com.lilium.springangular.dto;

public class VehicleDTO extends BaseDTO {
    private String number;
    private VehicleTypeDTO type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VehicleTypeDTO getType() {
        return type;
    }

    public void setType(VehicleTypeDTO type) {
        this.type = type;
    }
}
