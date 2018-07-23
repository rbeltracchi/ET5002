package com.plan111mil.data.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Housing> housing;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> userType;

}

