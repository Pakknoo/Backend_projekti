package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.harjoitustyo.domain.AppUser;
import hh.backend.harjoitustyo.domain.AppUserRepository;

@SpringBootTest(classes = HarjoitustyoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository urepository;

     @Test
    public void findByUsername(){
        AppUser user = urepository.findByUsername("juliam");
        assertThat(user.getEmail()).isEqualTo("julia@meikalainen@testi.com");
    }
    @Test 
    public void createNewAppUser(){
        AppUser user = new AppUser("Maija", "Meikalainen","maija.meikalainen@testi.com", "040444444","meikmaija", "password", "User");
        urepository.save(user);
        assertThat(user.getUserId()).isNotNull();
    }
    @Test 
    public void deleteUser(){
        AppUser user = urepository.findByUsername("user");
        urepository.delete(user);
        AppUser deletedUser = urepository.findByUsername("user");
        assertThat(deletedUser).isNull();

    }

}
