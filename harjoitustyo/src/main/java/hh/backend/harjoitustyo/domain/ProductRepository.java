package hh.backend.harjoitustyo.domain;

import java.util.List;

//import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product>findByProductName(String productName);

}

