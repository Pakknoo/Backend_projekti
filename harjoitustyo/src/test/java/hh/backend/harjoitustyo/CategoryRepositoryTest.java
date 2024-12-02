package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.harjoitustyo.domain.Category;
import hh.backend.harjoitustyo.domain.CategoryRepository;

@SpringBootTest(classes = HarjoitustyoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByCategoryName() {
        List<Category> categories = crepository.findByCategoryName("Ruokailu");
        assertThat(categories).hasSize(1);
    }
    @Test
    public void createNewCategory(){
        Category category = new Category("Turvakaukalot");
        crepository.save(category);
        assertThat(category.getCategoryName()).isNotNull();
    }
    @Test 
    public void deleteCategory(){
        Category category = new Category("Vaate");
        crepository.save(category);
        Long id = category.getCategoryId();
        assertThat(id).isNotNull();
        crepository.delete(category);
        Category deletedCategory = crepository.findById(id).orElse(null);
        assertThat(deletedCategory).isNull();
    }    
}