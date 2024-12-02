package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.harjoitustyo.domain.Category;
import hh.backend.harjoitustyo.domain.CategoryRepository;
import hh.backend.harjoitustyo.domain.Manufacturer;
import hh.backend.harjoitustyo.domain.ManufacturerRepository;
import hh.backend.harjoitustyo.domain.Product;
import hh.backend.harjoitustyo.domain.ProductRepository;

@SpringBootTest(classes = HarjoitustyoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

     @Autowired
    private ProductRepository pRepository;

    @Autowired
    private CategoryRepository cRepository;

    @Autowired
    private ManufacturerRepository mRepository;

    @Test
    public void createNewProduct() {
        Category category = new Category("Peli");
        cRepository.save(category);
        
        Manufacturer manufacturer = new Manufacturer("Valmistaja4");
        mRepository.save(manufacturer);

        Product product = new Product("Palapeli", 10.90, category, manufacturer);
        pRepository.save(product);

        assertThat(product.getProductId()).isNotNull();
        assertThat(pRepository.findById(product.getProductId())).isPresent();
    }

    @Test
    public void FindProductById() {
        Category category = new Category("Turvakaukalo");
        cRepository.save(category);

        Manufacturer manufacturer = new Manufacturer("Valmistaja5");
        mRepository.save(manufacturer);

        Product product = new Product("Turvakaukalo", 150, category, manufacturer);
        pRepository.save(product);

        Optional<Product> foundProduct = pRepository.findById(product.getProductId());
        assertThat(foundProduct).isPresent();
    }

    @Test
    public void deleteProduct() {
        List<Product> products = pRepository.findByProductName("Ulkoiluhaalari");
        Product product = products.get(0);
        pRepository.delete(product);
        List<Product> newProducts = pRepository.findByProductName("Ulkoiluhaalari");
        assertThat(newProducts).hasSize(0);
    }

}
