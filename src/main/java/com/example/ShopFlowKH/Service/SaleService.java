package com.example.ShopFlowKH.Service;

import com.example.ShopFlowKH.DTO.SaleRequestDTO;
import com.example.ShopFlowKH.Entity.Product;
import com.example.ShopFlowKH.Entity.Sale;
import com.example.ShopFlowKH.Entity.SaleItem;
import com.example.ShopFlowKH.Repository.ProductRepository;
import com.example.ShopFlowKH.Repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Sale processSale(SaleRequestDTO request) {
        Sale sale = new Sale();

        String receiptCode = request.getReceiptCode();
        if (receiptCode == null || receiptCode.trim().isEmpty()) {
            receiptCode = "REC-" + System.currentTimeMillis();
        }
        sale.setReceiptCode(receiptCode);
        sale.setPaymentMethod(request.getPaymentMethod());
        sale.setTotalUsd(request.getTotalUsd());
        sale.setTotalKhr(request.getTotalKhr());
        sale.setTenderedUsd(request.getTenderedUsd());
        sale.setChangeUsd(request.getChangeUsd());
        sale.setCreatedAt(LocalDateTime.now());

        if (request.getItems() != null) {
            for (SaleRequestDTO.ItemDTO itemDTO : request.getItems()) {
                // ១. ស្វែងរកទំនិញក្នុងស្តុក
                Product product = productRepository.findById(itemDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDTO.getProductId()));

                // ២. ពិនិត្យ និងកាត់ស្តុក (Decrement Stock)
                if (product.getStockInHand() < itemDTO.getQuantity()) {
                    throw new RuntimeException("Stock not enough for product: " + product.getName());
                }
                product.setStockInHand(product.getStockInHand() - itemDTO.getQuantity());
                productRepository.save(product);

                // ៣. បង្កើត SaleItem ភ្ជាប់ទៅកាន់ Sale
                SaleItem saleItem = new SaleItem();
                saleItem.setProductId(product.getId());
                saleItem.setProductName(product.getName());
                saleItem.setQuantity(itemDTO.getQuantity());
                saleItem.setUnitPrice(itemDTO.getUnitPrice());
                saleItem.setCostPrice(itemDTO.getCostPrice() != null ? itemDTO.getCostPrice() : BigDecimal.ZERO);
                saleItem.setLineTotal(itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
                saleItem.setSale(sale);

                sale.getItems().add(saleItem);
            }
        }

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAllByOrderByCreatedAtDesc();
    }
}
