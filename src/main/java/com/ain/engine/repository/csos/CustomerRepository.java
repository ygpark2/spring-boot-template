package com.ain.engine.repository.csos;


import com.ain.engine.domain.csos.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Young Gyu Park on 1/10/2015.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByName(@Param("name") String name);
}