package com.ain.engine;

import com.ain.engine.domain.csos.Address;
import com.ain.engine.domain.csos.Customer;
import com.ain.engine.repository.csos.AddressRepository;
import com.ain.engine.repository.csos.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationLoader.class)
@Log4j2
public class ApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Before
    public void setUp() {
        // this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

    }

    @Test
    @Transactional
    public void doTest() {
        Customer customer = new Customer();
        customer.setName("TestFoo");
        customer.setSurname("TestFooSurname");
        Customer c2 = customerRepository.save(customer);
        for (Customer c : customerRepository.findAll()){
            log.info(c.getName());
        }

        Customer c = customerRepository.findByName("TestFoo");
        log.info(c.getName());

        Address a = new Address();
        a.setLine1("abc_test");
        a.setCustomer(c);
        addressRepository.save(a);


    }

}