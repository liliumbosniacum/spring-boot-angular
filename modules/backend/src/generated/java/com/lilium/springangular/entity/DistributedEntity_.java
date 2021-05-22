package com.lilium.springangular.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DistributedEntity.class)
public abstract class DistributedEntity_ {

	public static volatile SingularAttribute<DistributedEntity, LocalDateTime> created;
	public static volatile SingularAttribute<DistributedEntity, LocalDateTime> modified;
	public static volatile SingularAttribute<DistributedEntity, Integer> id;

	public static final String CREATED = "created";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";

}

