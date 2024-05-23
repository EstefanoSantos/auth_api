package br.com.auth_api.controllers;

import br.com.auth_api.controllers.dto.ProductDto;
import br.com.auth_api.entities.Product;
import br.com.auth_api.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/newProduct")
    @ResponseBody
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Product> create(@RequestBody ProductDto dto) {
        Product product = productService.createProduct(dto);

        return ResponseEntity.ok(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deleteProduct/{productId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void delete(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
    }

    @ResponseBody
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findProduct() {
        List<Product> products = productService.productList();

        return ResponseEntity.ok(products);
    }
}
