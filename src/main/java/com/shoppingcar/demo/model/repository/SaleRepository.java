package com.shoppingcar.demo.model.repository;

import com.shoppingcar.demo.model.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Sale entity.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByClient_Id(Long id);
}
