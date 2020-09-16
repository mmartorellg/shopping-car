package com.shoppingcar.demo.controller;

import com.shoppingcar.demo.dto.ProductDto;
import com.shoppingcar.demo.exceptions.ProductException;
import com.shoppingcar.demo.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public static void writeLog(String text) {
        log.error(text);
    }

    /**
     * POST  /products : Create a new product.
     *
     * @param productDTO the productDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) throws URISyntaxException, ProductException {
        log.debug("REST request to save Product : {}", productDTO);

        ProductDto result = productService.save(productDTO);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
                .body(result);
    }

    /**
     * PUT  /products : Updates an existing product.
     *
     * @param productDTO the productDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productDTO
     */
    @PutMapping("/products")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDTO) throws ProductException {
        log.debug("REST request to update Product : {}", productDTO);
        ProductDto result = productService.save(productDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * GET  /products : get all the products.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() throws ProductException {
        log.debug("REST request to get all Products");
        return productService.findAll();
    }

    /**
     * GET  /products/:id : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws ProductException {
        log.debug("REST request to get Product : {}", id);

        ProductDto productDTO = productService.findOne(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    /**
     * DELETE  /products/:id : delete the "id" product.
     *
     * @param id the id of the productDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductException {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
