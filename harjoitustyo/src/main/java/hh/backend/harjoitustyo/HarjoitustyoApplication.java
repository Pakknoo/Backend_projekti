package hh.backend.harjoitustyo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.harjoitustyo.domain.AppUser;
import hh.backend.harjoitustyo.domain.AppUserRepository;
import hh.backend.harjoitustyo.domain.Category;
import hh.backend.harjoitustyo.domain.CategoryRepository;
import hh.backend.harjoitustyo.domain.Manufacturer;
import hh.backend.harjoitustyo.domain.ManufacturerRepository;
import hh.backend.harjoitustyo.domain.Product;
import hh.backend.harjoitustyo.domain.ProductRepository;

@SpringBootApplication
public class HarjoitustyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository pRepository, CategoryRepository cRepository,  ManufacturerRepository mRepository, AppUserRepository uRepository  ){
		return (args)->{

			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			AppUser user3 = new AppUser("julia","Meikalainen", "julia@meikalainen@testi.com",  "0505555555", "juliam", "$2a$10$5iSrtgYiWl4ofyEx4.aPA.EvvqMVuSPqYTUvVGLzB222j56wx8bBq", "ADMIN");
			AppUser user4 = new AppUser("anssi", "$2a$10$HhJmSSVyr4HTykER1K/ufOQXIyPXSxZqY/NeutTCOGW.yB9ErK/2u", "USER");
			uRepository.save(user1);
			uRepository.save(user2);
			uRepository.save(user3);
			uRepository.save(user4);

			Category c1 = new Category("Vaate");
			Category c2 = new Category("Lelu");
			Category c3 = new Category("Ruokailu");
			cRepository.save(c1);
			cRepository.save(c2);
			cRepository.save(c3);

			Manufacturer m1 = new Manufacturer("Valmistaja1");
			Manufacturer m2 = new Manufacturer("Valmistaja2");
			Manufacturer m3 = new Manufacturer("Valmistaja3");
			mRepository.save(m1);
			mRepository.save(m2);
			mRepository.save(m3);

			AppUser u1 = new AppUser("Leena", "Virtanen", "leena.virtanen@esimerkki.com", "0401111111", "leena", "leena", "admin");
			AppUser u2 = new AppUser("Seppo", "Korhonen", "seppo.korhonen@esimerkki.com", "0402222222", "seppo", "seppo", "user");
			uRepository.save(u1);
			uRepository.save(u2);

			Product p1 = new Product("Ulkoiluhaalari", 6.50,c1, m1 );
			Product p2 = new Product("Pehmonalle", 6.50,c2, m2 );
			Product p3 = new Product("Tuttipullo", 7.50,c3, m3 );
			pRepository.save(p1);
			pRepository.save(p2);
			pRepository.save(p3);
		};
	}

}
