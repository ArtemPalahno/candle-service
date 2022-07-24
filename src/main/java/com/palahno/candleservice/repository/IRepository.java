package com.palahno.candleservice.repository;


/**
 * Basic repository functionality.
 *
 * @author Artem Palahno
 */
public interface IRepository<T> {

    void save(T t);

}
