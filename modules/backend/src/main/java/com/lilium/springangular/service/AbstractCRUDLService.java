package com.lilium.springangular.service;

import com.lilium.springangular.api.AbstractCRUDLApi;
import com.lilium.springangular.converter.AbstractDTOConverter;
import com.lilium.springangular.dto.BaseDTO;
import com.lilium.springangular.entity.DistributedEntity;
import com.lilium.springangular.repository.DistributedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCRUDLService<ENTITY extends DistributedEntity, DTO extends BaseDTO>
        implements AbstractCRUDLApi<ENTITY, DTO> {

    // region Member
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCRUDLService.class);
    private DistributedRepository<ENTITY> repository;
    private AbstractDTOConverter<ENTITY, DTO> converter;
    private Class<ENTITY> entityClass;
    // endregion

    // region Constructor
    public AbstractCRUDLService(final DistributedRepository<ENTITY> repository,
                                final AbstractDTOConverter<ENTITY, DTO> converter) {
        this.repository = repository;
        this.converter = converter;

        final Class<?>[] params = GenericTypeResolver.resolveTypeArguments(getClass(), AbstractCRUDLService.class);
        this.entityClass = (Class<ENTITY>) params[0];
    }
    // endregion

    // region Implementation
    protected abstract void updateEntity(final ENTITY entity, final DTO dto);

    @Override
    public DTO save(DTO dto) {
        final ENTITY entity;

        if (dto.isNew()) {
            entity = initEntity();
        } else {
            entity = findEntityById(dto.getId());
        }

        if (entity == null) {
            LOG.error("Failed to save entity of class '{}'", entityClass.getSimpleName());
            return null;
        }

        // Set modified timestamp
        entity.setModified(LocalDateTime.now());

        // Update entity
        updateEntity(entity, dto);

        // Save entity
        final ENTITY savedEntity = repository.save(entity);

        // Convert to DTO and return it
        return converter.convert(savedEntity);
    }

    @Override
    public DTO getById(Integer id) {
        final ENTITY entity = findEntityById(id);

        if (entity == null) {
            LOG.error("Failed to find entity with ID '{}'", id);
            return null;
        }

        return converter.convert(entity);
    }

    @Override
    public List<DTO> list() {
        return getDtos(repository.findAll());
    }

    @Override
    public Boolean delete(Integer id) {
        final ENTITY entity = findEntityById(id);

        if (entity == null) {
            LOG.error("Failed to delete entity with ID '{}' as it does not exist", id);
            return false;
        }

        try {
            repository.delete(entity);
            return true;
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
    // endregion

    // region Helper
    private ENTITY findEntityById(final Integer id) {
        return repository.findById(id).orElse(null);
    }

    private List<DTO> getDtos(final List<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return converter.convertList(entities);
    }

    private ENTITY initEntity() {
        try {
            final ENTITY entity = entityClass.newInstance();
            entity.setCreated(LocalDateTime.now());

            return entity;
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
    // endregion
}
