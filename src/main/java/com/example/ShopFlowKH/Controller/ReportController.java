package com.example.ShopFlowKH.Controller;

import com.example.ShopFlowKH.DTO.DailySummaryDTO;
import com.example.ShopFlowKH.Entity.Sale;
import com.example.ShopFlowKH.Repository.SaleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final SaleRepository saleRepository;

    public ReportController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @GetMapping("/daily-summary")
    public ResponseEntity<DailySummaryDTO> getDailySummary() {
        List<Sale> sales = saleRepository.findAll();

        BigDecimal totalRevenueUsd = BigDecimal.ZERO;
        BigDecimal totalRevenueKhr = BigDecimal.ZERO;
        BigDecimal totalProfitUsd = BigDecimal.ZERO;

        for (Sale sale : sales) {
            if (sale.getTotalUsd() != null) {
                totalRevenueUsd = totalRevenueUsd.add(sale.getTotalUsd());
            }
            if (sale.getTotalKhr() != null) {
                totalRevenueKhr = totalRevenueKhr.add(sale.getTotalKhr());
            }

            if (sale.getItems() != null) {
                for (var item : sale.getItems()) {
                    BigDecimal unitPrice = item.getUnitPrice() != null ? item.getUnitPrice() : BigDecimal.ZERO;
                    BigDecimal costPrice = item.getCostPrice() != null ? item.getCostPrice() : BigDecimal.ZERO;
                    int quantity = item.getQuantity() != null ? item.getQuantity() : 0;
                    BigDecimal itemProfit = unitPrice.subtract(costPrice).multiply(BigDecimal.valueOf(quantity));
                    totalProfitUsd = totalProfitUsd.add(itemProfit);
                }
            }
        }

        DailySummaryDTO summary = new DailySummaryDTO(
                totalRevenueUsd,
                totalRevenueKhr,
                totalProfitUsd,
                (long) sales.size()
        );

        return ResponseEntity.ok(summary);
    }
}
