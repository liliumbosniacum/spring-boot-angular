package com.lilium.springangular.api;

import com.lilium.springangular.dto.BaseDTO;
import com.lilium.springangular.entity.DistributedEntity;

import java.util.List;

public interface AbstractCRUDLApi<ENTITY extends DistributedEntity, DTO extends BaseDTO> {

    DTO save(DTO dto);

    DTO getById(Integer id);

    List<DTO> list();

    Boolean delete(Integer id);
}
