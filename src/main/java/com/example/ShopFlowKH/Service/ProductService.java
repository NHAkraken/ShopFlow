package com.example.ShopFlowKH.Service;

import com.example.ShopFlowKH.Entity.Product;
import com.example.ShopFlowKH.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. CREATE
    public Product saveProduct(Product product) {
        if (product.getOpeningStock() == null || product.getOpeningStock() == 0) {
            product.setOpeningStock(product.getStockInHand());
        }
        return productRepository.save(product);
    }

    // 2. READ ALL
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 3. READ BY ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // 4. READ BY BARCODE (សម្រាប់ POS Scanner)
    public Optional<Product> getProductByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    // 5. UPDATE
    public Product updateProduct(String id, Product updatedProduct) {
        return productRepository.findById(id).map(prod -> {
            prod.setName(updatedProduct.getName());
            prod.setBarcode(updatedProduct.getBarcode());
            prod.setSku(updatedProduct.getSku());
            prod.setCategory(updatedProduct.getCategory());
            prod.setCostPrice(updatedProduct.getCostPrice());
            prod.setSellingPrice(updatedProduct.getSellingPrice());
            prod.setStockInHand(updatedProduct.getStockInHand());
            prod.setMinStockAlert(updatedProduct.getMinStockAlert());
            return productRepository.save(prod);
        }).orElse(null);
    }

    // 6. ADJUST STOCK (+ ឬ - ចំនួនស្តុក)
    public Product adjustStock(String id, int quantityChange) {
        return productRepository.findById(id).map(prod -> {
            prod.setStockInHand(Math.max(0, prod.getStockInHand() + quantityChange));
            return productRepository.save(prod);
        }).orElse(null);
    }

    // 7. DELETE
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}