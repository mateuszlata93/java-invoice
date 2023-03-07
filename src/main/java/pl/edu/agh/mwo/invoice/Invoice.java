package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private static final AtomicInteger count = new AtomicInteger(0);
    private List<Product> products = new ArrayList<>();
    private int id;

    public Invoice(List<Product> products) {
        this.products = products;
        this.id = count.incrementAndGet();
    }

    public Invoice() {
        this.id = count.incrementAndGet();
    }

    public void addProduct(Product product) {
        //test
        if (product == null) {
            throw new IllegalArgumentException("product should not be null");
        }
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity should not be negative");
        }
        for (int i = 0; i < quantity; i++) {
            this.products.add(product);
        }
    }

    public BigDecimal getSubtotal() {
        if (products == null) {
            return BigDecimal.ZERO;
        }
        return this.products.stream()
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal getTax() {
        if (products == null) {
            return BigDecimal.ZERO;
        }
        //return null;
        BigDecimal taxTotal = products.stream()
                .map(product -> product.getPriceWithTax().subtract(product.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return taxTotal;
    }

    public BigDecimal getTotal() {
        if (products == null) {
            return BigDecimal.ZERO;
        }
        return products.stream()
                .map(product -> product.getPriceWithTax())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String printProducts() {
        return products.toString();
    }
}
