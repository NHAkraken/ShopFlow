package com.example.ShopFlowKH.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String sku;

    @Column(nullable = false, unique = true)
    private String barcode;

    private String category = "General";

    private BigDecimal costPrice = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal sellingPrice;

    private Integer stockInHand = 0;

    private Integer openingStock = 0;

    private Integer minStockAlert = 5;
}