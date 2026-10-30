package com.example.ShopFlowKH.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequestDTO {
    private String receiptCode;
    private String paymentMethod;
    private BigDecimal totalUsd;
    private BigDecimal totalKhr;
    private BigDecimal tenderedUsd;
    private BigDecimal changeUsd;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private String productId;
        private String productName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal costPrice;
    }
}