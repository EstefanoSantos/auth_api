package br.com.auth_api.repositories;

import br.com.auth_api.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.productName = ?1")
    Product findByName(String productName);
}
