package com.example.ShopFlowKH.Controller;


import com.example.ShopFlowKH.DTO.SaleRequestDTO;
import com.example.ShopFlowKH.Entity.Sale;
import com.example.ShopFlowKH.Service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // ការពារកុំឱ្យ Browser Block CORS[cite: 11]
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // POST /api/sales (ដំណើរការ Checkout)[cite: 12]
    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleRequestDTO request) {
        try {
            Sale savedSale = saleService.processSale(request);
            return ResponseEntity.ok(savedSale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/sales (ទាញយករបាយការណ៍លក់ទាំងអស់)[cite: 12]
    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }
}