package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.harjoitustyo.web.CategoryController;
import hh.backend.harjoitustyo.web.ManufacturerController;
import hh.backend.harjoitustyo.web.ProductController;

@SpringBootTest
class HarjoitustyoApplicationTests {

	@Autowired
	private CategoryController ccontroller;

	@Autowired
	private ManufacturerController mcontroller;

	@Autowired
	private ProductController pcontroller;

	@Test
	void contextLoads() {
		assertThat(ccontroller).isNotNull();
		assertThat(mcontroller).isNotNull();
		assertThat(pcontroller).isNotNull();
	}

}
