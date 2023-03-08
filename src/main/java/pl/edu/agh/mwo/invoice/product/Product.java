package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || name.isEmpty() || price == null
                || price.compareTo(BigDecimal.ZERO) < 0) {
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

    @Override
    public int hashCode() {
        final int hash_salt = 31;
        int result = name != null ? name.hashCode() : 0;
        result = hash_salt * result + (taxPercent != null ? taxPercent.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if (!Objects.equals(name, product.name)) {
            return false;
        }
        if (!Objects.equals(price, product.price)) {
            return false;
        }
        return Objects.equals(taxPercent, product.taxPercent);
    }
}
