package com.lilium.springangular.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface DistributedRepository<ENTITY> extends JpaRepository<ENTITY, Integer> {

    List<ENTITY> findAllModifiedSince(LocalDateTime time);

    List<ENTITY> findAll(Specification<ENTITY> specification);
}
