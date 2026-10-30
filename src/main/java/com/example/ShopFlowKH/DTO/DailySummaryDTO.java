package com.example.ShopFlowKH.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySummaryDTO {
    private BigDecimal totalRevenueUsd;
    private BigDecimal totalRevenueKhr;
    private BigDecimal estimatedProfitUsd;
    private Long totalTransactions;
}
