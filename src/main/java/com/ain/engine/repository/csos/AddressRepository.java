package com.ain.engine.repository.csos;

import com.ain.engine.domain.csos.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Young Gyu Park on 1/10/2015.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, QueryDslPredicateExecutor<Address> {

    @Query(value = "SELECT * FROM whatever_custom_query here s WHERE s.reconciliation_setting_id=?1", nativeQuery = true)
    Address doAFoo(String s);

}