package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class ExciseProduct extends Product {

    public ExciseProduct(String name, BigDecimal price) {
        super(name, price.add(new BigDecimal("5.56")), BigDecimal.ZERO);
    }
}
