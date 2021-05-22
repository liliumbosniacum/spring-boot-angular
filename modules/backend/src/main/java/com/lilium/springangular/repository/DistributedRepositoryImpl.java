package com.lilium.springangular.repository;

import com.lilium.springangular.entity.DistributedEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class DistributedRepositoryImpl<ENTITY> extends SimpleJpaRepository<ENTITY, Integer> implements DistributedRepository<ENTITY>, JpaSpecificationExecutor<ENTITY> {

    public DistributedRepositoryImpl(final JpaEntityInformation<ENTITY, Integer> entityInformation, final EntityManager em) {
        super(entityInformation, em);
    }

    @Override
    public List<ENTITY> findAllModifiedSince(final LocalDateTime time) {
        return super.findAll(modifiedSince(time));
    }

    @Override
    public List<ENTITY> findAll(final Specification<ENTITY> specification) {
        return super.findAll(specification);
    }

    /**
     * Used to find all entities modified since given timestamp.
     *
     * @param timestamp Timestamp since which entities were modified.
     * @return Returns created specification.
     */
    private Specification<ENTITY> modifiedSince(final LocalDateTime timestamp) {
        return ((root, query, cb) -> cb.greaterThanOrEqualTo(
                root.get(DistributedEntity_.MODIFIED),
                timestamp
        ));
    }
}
