package com.example.ShopFlowKH.Repository;

import com.example.ShopFlowKH.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findAllByOrderByCreatedAtDesc();
}