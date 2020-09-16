package com.shoppingcar.demo.model.repository;

import com.shoppingcar.demo.model.domain.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sale entity.
 */
@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

}
