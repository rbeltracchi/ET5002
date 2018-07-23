package com.plan111mil.data.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Housing.class)
public abstract class Housing_ extends com.plan111mil.data.entity.HostService_ {

	public static volatile SingularAttribute<Housing, User> idUser;
	public static volatile ListAttribute<Housing, Room> rooms;
	public static volatile SingularAttribute<Housing, String> address;
	public static volatile SingularAttribute<Housing, String> mail;
	public static volatile SingularAttribute<Housing, String> phone;

}

