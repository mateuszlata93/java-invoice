package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || name.isEmpty() || price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("name should not be null");
        }
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return this.price.add(this.price.multiply(this.taxPercent));
    }
}
