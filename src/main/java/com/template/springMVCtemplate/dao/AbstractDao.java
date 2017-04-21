package com.template.springMVCtemplate.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by ud on 20/4/17.
 * This is a abstract dao that will be extended by all dao implementation.We have this dao as generic.
 *
 * @PK : It is the datatype of primary key
 * @T : It is type of Object class used to implement ta DAO.
 * This DAO class can be copied and used in every type of Hibernate Application.
 */
public abstract class AbstractDao<PK extends Serializable,T> {

    private final Class<T> persistentClass;

    /**
     * Initializing the type of dao with the class type.
     */

    public AbstractDao(){
        this.persistentClass=(Class<T>)
                ((ParameterizedType) this.getClass().getGenericSuperclass())
                        .getActualTypeArguments()[1];
    }

    /**
     * The session bean is created in HibernateConfig.java and is Autowired here.
     */
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    /**+
     *
     * persist ,delete,update are used to do crud operations
     * criteria offers a way of creating custom SQL queries known as criteria queries.
     *
     * Below are some points about creteria and HQL
     *
     * HQL is to perform both select and non-select operations on the data,  but Criteria is only for selecting the data,
     * we cannot perform non-select operations using criteria
     * HQL is suitable for executing Static Queries, where as Criteria is suitable for executing Dynamic Queries
     * HQL doesn’t support pagination concept, but we can achieve pagination with Criteria
     * Criteria used to take more time to execute then HQL
     * With Criteria we are safe with SQL Injection because of its dynamic query generation but in HQL as your queries are
     * either fixed or parametrized, there is no safe from SQL Injection.
     */
    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    public void update(T entity) {
        getSession().update(entity);
    }
}
