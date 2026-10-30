package com.example.ShopFlowKH.config;

import com.example.ShopFlowKH.Entity.Product;
import com.example.ShopFlowKH.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            Product p1 = new Product(
                    null,
                    "Angkor Beer Can 330ml",
                    "SKU-ANG-01",
                    "885000000001",
                    "Beverages",
                    new BigDecimal("0.60"),
                    new BigDecimal("0.85"),
                    48,
                    48,
                    10
            );

            Product p2 = new Product(
                    null,
                    "Cambodia Drinking Water 500ml",
                    "SKU-WAT-01",
                    "885000000002",
                    "Beverages",
                    new BigDecimal("0.20"),
                    new BigDecimal("0.35"),
                    100,
                    100,
                    20
            );

            Product p3 = new Product(
                    null,
                    "Mondulkiri Roasted Coffee 250g",
                    "SKU-COF-01",
                    "885000000003",
                    "General",
                    new BigDecimal("3.50"),
                    new BigDecimal("5.00"),
                    25,
                    25,
                    5
            );

            Product p4 = new Product(
                    null,
                    "USB-C Fast Charging Cable 1m",
                    "SKU-CAB-01",
                    "885000000004",
                    "Electronics",
                    new BigDecimal("1.20"),
                    new BigDecimal("2.50"),
                    15,
                    15,
                    5
            );

            Product p5 = new Product(
                    null,
                    "Khmer Kbach Silver Ring",
                    "SKU-RNG-01",
                    "885000000005",
                    "Accessories",
                    new BigDecimal("12.00"),
                    new BigDecimal("22.00"),
                    8,
                    8,
                    3
            );

            productRepository.saveAll(List.of(p1, p2, p3, p4, p5));
        }
    }
}
