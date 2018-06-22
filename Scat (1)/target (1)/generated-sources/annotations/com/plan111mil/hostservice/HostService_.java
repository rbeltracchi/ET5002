package com.plan111mil.hostservice;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HostService.class)
public abstract class HostService_ {

	public static volatile SingularAttribute<HostService, String> hostServiceType;
	public static volatile SingularAttribute<HostService, String> name;
	public static volatile ListAttribute<HostService, String> commodities;
	public static volatile SingularAttribute<HostService, String> description;
	public static volatile SingularAttribute<HostService, Integer> id;
	public static volatile SingularAttribute<HostService, Integer> capacity;

}

