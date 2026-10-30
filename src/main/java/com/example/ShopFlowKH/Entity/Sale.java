package com.example.ShopFlowKH.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    @Column(nullable = false, unique = true)
    private String receiptCode; // ឧទាហរណ៍៖ REC-9021

    private String paymentMethod; // cash ឬ khqr

    private BigDecimal totalUsd;
    private BigDecimal totalKhr;
    private BigDecimal tenderedUsd;
    private BigDecimal changeUsd;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();
}
