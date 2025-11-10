import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " - " + category + " - $" + price;
    }
}

public class ProductStream {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 80000, "Electronics"),
            new Product("Smartphone", 50000, "Electronics"),
            new Product("Shirt", 1500, "Clothing"),
            new Product("Jeans", 2500, "Clothing"),
            new Product("Washing Machine", 40000, "Appliances"),
            new Product("Microwave", 12000, "Appliances")
        );

        // 1. Group products by category
        Map<String, List<Product>> groupByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("Products grouped by category:");
        groupByCategory.forEach((category, list) -> {
            System.out.println(category + " -> " + list);
        });

        // 2. Most expensive product in each category
        Map<String, Optional<Product>> mostExpensive = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));

        System.out.println("\nMost expensive product in each category:");
        mostExpensive.forEach((category, product) ->
            System.out.println(category + ": " + product.get())
        );

        // 3. Average price of all products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));

        System.out.println("\nAverage price of all products: " + avgPrice);
    }
}
