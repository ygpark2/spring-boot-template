package com.ain.engine.domain.admin;

import com.ain.engine.domain.AbstractEntity;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Baseclass for payment implementations.
 *
 * @author Young Gyu Park
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@ToString(callSuper = true)
public abstract class Payment extends AbstractEntity {

    @JoinColumn(name = "rborder")//
    @OneToOne(cascade = CascadeType.MERGE)//
    private final Order order;
    private final LocalDateTime paymentDate;

    protected Payment() {

        this.order = null;
        this.paymentDate = null;
    }

    /**
     * Creates a new {@link Payment} referring to the given {@link Order}.
     *
     * @param order must not be {@literal null}.
     */
    public Payment(Order order) {

        Assert.notNull(order);
        this.order = order;
        this.paymentDate = LocalDateTime.now();
    }

    /**
     * Returns a receipt for the {@link Payment}.
     *
     * @return
     */
    public Receipt getReceipt() {
        return new Receipt(paymentDate, order);
    }

    /**
     * A receipt for an {@link Order} and a payment date.
     *
     * @author Oliver Gierke
     */
    @Value
    public static class Receipt {

        private final LocalDateTime date;
        private final Order order;
    }
}