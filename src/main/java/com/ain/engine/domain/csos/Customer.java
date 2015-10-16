package com.ain.engine.domain.csos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Young Gyu Park on 1/10/2015.
 */
@Entity
@Table(name="CUSTOMER")
@Getter @Setter @ToString
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="CUSTOMER_NO", unique=true, nullable=false)
    private long customerNo;

    @Column(name="NAME", nullable=true, length=512)
    private String name;

    @Column(name="SURNAME", nullable=true, length=512)
    private String surname;

    @Column(name="MILEAGE", scale=2, precision=10)
    private BigDecimal mileage;
}
