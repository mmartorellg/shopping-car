package com.shoppingcar.demo.controller;

import com.shoppingcar.demo.dto.ProductDto;
import com.shoppingcar.demo.dto.SaleDto;
import com.shoppingcar.demo.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    public static void writeLog(String text) {
        log.error(text);
    }

    /**
     * POST  /sales : Create a new sale.
     *
     * @param saleDTO the saleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new saleDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sales")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDTO) throws URISyntaxException {
        log.debug("REST request to save Sale : {}", saleDTO);

        SaleDto result = saleService.save(saleDTO);
        return ResponseEntity.created(new URI("/api/sales/" + result.getId()))
                .body(result);
    }

    /**
     * GET  /sales : get all the sales.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sales in body
     */
    @GetMapping("/sales")
    public List<SaleDto> getAllSales() {
        log.debug("REST request to get all sales");
        return saleService.findAll();
    }


    @GetMapping("/sales/{id}")
    public Single<List<SaleDto>> getAllSalesByUserId(@PathVariable Long id) {
        log.debug("Request to get all sales from userId : {}", id);
        return saleService.findAllSalesById(id);
    }

    /**
     * POST  /sales : Create a new saleDetail.
     *
     * @param products the product list of sale to create
     * @param clientId the id of client
     * @return the ResponseEntity with status 201 (Created) and with body the new saleDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @PostMapping("/sales/detail/{clientId}")
    public ResponseEntity<SaleDto> createSaleDetail(@RequestBody List<ProductDto> products, @PathVariable Long clientId) throws URISyntaxException {
        log.debug("REST request to save SaleDetail : {}", clientId);
        SaleDto result = saleService.createSaleDetail(clientId, products);
        return ResponseEntity.created(new URI("/api/sales/detail/" + result.getId()))
                .body(result);
    }
}
