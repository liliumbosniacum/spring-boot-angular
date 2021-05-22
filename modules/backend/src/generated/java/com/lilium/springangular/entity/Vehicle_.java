package com.lilium.springangular.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vehicle.class)
public abstract class Vehicle_ extends com.lilium.springangular.entity.DistributedEntity_ {

	public static volatile SingularAttribute<Vehicle, String> number;
	public static volatile SingularAttribute<Vehicle, VehicleType> type;

	public static final String NUMBER = "number";
	public static final String TYPE = "type";

}

