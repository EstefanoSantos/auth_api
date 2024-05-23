package br.com.auth_api.services;

import br.com.auth_api.controllers.dto.ProductDto;
import br.com.auth_api.entities.Product;
import br.com.auth_api.repositories.ProductRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductDto dto) {
        Product product = productRepository.findByName(dto.productName());

        if (product != null) {
            throw new EntityExistsException("Product already exists!");
        }

        product = new Product();
        product.setProductName(dto.productName());
        product.setPrice(dto.price());
        product.setDescription(dto.description());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).get();

        if (product == null) {
            throw new NoSuchElementException("There is no product with this id.");
        }

        productRepository.deleteById(id);
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }
}
