package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private static int nextId = 0;
    private final int id = ++nextId;

    private Map<Product, Integer> products = new HashMap<>();

    public Invoice() {
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null) {
            throw new IllegalArgumentException("product should not be null");
        } else if (quantity <= 0) {
            throw new IllegalArgumentException("quantity should not be negative");
        }
        this.products.put(product, quantity);
    }

    public BigDecimal getSubtotal() {
        if (products == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Product key : products.keySet()) {
            BigDecimal quantity = BigDecimal.valueOf(products.get(key));
            total = total.add(key.getPrice().multiply(quantity));
        }
        return total;
    }

    public BigDecimal getTax() {
        if (products == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Product key : products.keySet()) {
            totalTax = totalTax.add(key.getPriceWithTax().subtract(key.getPrice()));
        }
        return totalTax;
    }

    public BigDecimal getTotal() {
        if (products == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Product key : products.keySet()) {
            BigDecimal quantity = BigDecimal.valueOf(products.get(key));
            BigDecimal sum = key.getPriceWithTax().multiply(quantity);
            total = total.add(sum);
        }
        return total;
    }


    public int getId() {
        return id;
    }

    public String printProducts() {
        return products.toString();
    }
}
