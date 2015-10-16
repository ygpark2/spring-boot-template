package com.ain.engine.domain.csos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Young Gyu Park on 1/10/2015.
 */
@Entity
@Table(name="ADDRESS")
@Getter @Setter @ToString
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @Column(name="LINE1", nullable=false, length=512)
    private String line1;

}
