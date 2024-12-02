package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.harjoitustyo.domain.Manufacturer;
import hh.backend.harjoitustyo.domain.ManufacturerRepository;


@SpringBootTest(classes = HarjoitustyoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ManufacturerRepositoryTest {

    @Autowired
    private ManufacturerRepository mrepository;

    @Test
    public void findByManufacturerName() {
        List<Manufacturer> manufacturers = mrepository.findByManufacturerName("Valmistaja1");
        assertThat(manufacturers).hasSize(1);
    }
    @Test
    public void createNewManufacturer(){
        Manufacturer manufacturer = new Manufacturer("Uusivalmistaja");
        mrepository.save(manufacturer);
        assertThat(manufacturer.getManufacturerName()).isNotNull();
    }
    @Test 
    public void deleteManufacturer(){
        Manufacturer manufacturer = new Manufacturer("Valmistaja1");
        mrepository.save(manufacturer);
        Long id = manufacturer.getManufacturerId();
        assertThat(id).isNotNull();
        mrepository.delete(manufacturer);
        Manufacturer deletedManufacturer = mrepository.findById(id).orElse(null);
        assertThat(deletedManufacturer).isNull();
    }    

}
