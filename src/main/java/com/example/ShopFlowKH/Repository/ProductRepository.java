package com.example.ShopFlowKH.Repository;

import com.example.ShopFlowKH.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // ស្វែងរកតាមរយៈបាកូដសម្រាប់កាមេរ៉ាស្កេន ឬ manual input
    Optional<Product> findByBarcode(String barcode);
}