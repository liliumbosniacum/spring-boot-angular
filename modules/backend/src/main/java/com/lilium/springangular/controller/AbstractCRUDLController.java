package com.lilium.springangular.controller;

import com.lilium.springangular.api.AbstractCRUDLApi;
import com.lilium.springangular.dto.BaseDTO;
import com.lilium.springangular.dto.search.PagedResponse;
import com.lilium.springangular.dto.search.SearchRequest;
import com.lilium.springangular.entity.DistributedEntity;
import org.springframework.web.bind.annotation.*;

public class AbstractCRUDLController<ENTITY extends DistributedEntity, DTO extends BaseDTO> {
    private final AbstractCRUDLApi<ENTITY, DTO> api;

    public AbstractCRUDLController(final AbstractCRUDLApi<ENTITY, DTO> api) {
        this.api = api;
    }

    @PostMapping
    public DTO save(@RequestBody DTO dto) {
        return api.save(dto);
    }

    @GetMapping("/{id}")
    public DTO getById(@PathVariable Integer id) {
        return api.getById(id);
    }

    @GetMapping("/list")
    public PagedResponse<DTO> list(SearchRequest request) {
        return api.list(request);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return api.delete(id);
    }
}
