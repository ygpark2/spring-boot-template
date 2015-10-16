package com.ain.engine.domain.admin;

import com.ain.engine.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * A line item.
 *
 * @author Young Gyu Park
 */
@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LineItem extends AbstractEntity {

    private final String name;
    private final int quantity;
    private final Milk milk;
    private final Size size;

    public LineItem(String name) {
        this(name, 1, Milk.SEMI, Size.LARGE);
    }

}