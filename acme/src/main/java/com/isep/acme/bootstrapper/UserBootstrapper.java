package com.isep.acme.bootstrapper;

import com.isep.acme.model.user.BaseUser;
import com.isep.acme.repositories.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Role;
import com.isep.acme.model.User;
import com.isep.acme.repositories.UserRepository;

@Component
@Profile("bootstrap")
public class UserBootstrapper implements CommandLineRunner {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        //admin
        if(userRepo.findByUsername("admin1@mail.com").isEmpty()) {
            BaseUser admin1 = new BaseUser("admin1@mail.com", encoder.encode("AdminPW1"),
                    "Jose Antonio", "355489123", "Rua Um");
            admin1.addAuthority(new Role(Role.Admin));

            userRepo.save(admin1);
        }

        if(userRepo.findByUsername("admin2@mail.com").isEmpty()) {
            BaseUser mod1 = new BaseUser("admin2@mail.com", encoder.encode("AdminPW2"),
                    "Antonio Jose", "321984553", "Rua dois");
            mod1.addAuthority(new Role(Role.Mod));
            userRepo.save(mod1);
        }
        if(userRepo.findByUsername("user1@mail.com").isEmpty()) {
            BaseUser user1 = new BaseUser("user1@mail.com", encoder.encode("userPW1"),
                    "Nuno Miguel", "253647883", "Rua tres");
            user1.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user1);
        }
        if(userRepo.findByUsername("user2@mail.com").isEmpty()) {
            BaseUser user2 = new BaseUser("user2@mail.com", encoder.encode("userPW2"),
                    "Miguel Nuno", "253698854", "Rua quatro");
            user2.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user2);
        }
        if(userRepo.findByUsername("user3@mail.com").isEmpty()) {
            BaseUser user3 = new BaseUser("user3@mail.com", encoder.encode("userPW3"),
                    "Antonio Pedro", "254148863", "Rua vinte");
            user3.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user3);
        }

        if(userRepo.findByUsername("user4@mail.com").isEmpty()) {
            BaseUser user4 = new BaseUser("user4@mail.com", encoder.encode("userPW4"),
                    "Pedro Antonio", "452369871", "Rua cinco");
            user4.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user4);
        }
        if(userRepo.findByUsername("user5@mail.com").isEmpty()) {
            BaseUser user5 = new BaseUser("user5@mail.com", encoder.encode("userPW5"),
                    "Ricardo Joao", "452858596", "Rua seis");
            user5.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user5);
        }
        if(userRepo.findByUsername("user6@mail.com").isEmpty()) {
            BaseUser user6 = new BaseUser("user6@mail.com", encoder.encode("userPW6"),
                    "Joao Ricardo", "425364781", "Rua sete");
            user6.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user6);
        }
        if(userRepo.findByUsername("user7@mail.com").isEmpty()) {
            BaseUser user7 = new BaseUser("user7@mail.com", encoder.encode("userPW7"),
                    "Luis Pedro", "526397747", "Rua oito");
            user7.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user7);
        }
        if(userRepo.findByUsername("user8@mail.com").isEmpty()) {
            BaseUser user8 = new BaseUser("user8@mail.com", encoder.encode("userPW8"),
                    "Pedro Luis", "523689471", "Rua nove ");
            user8.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user8);
        }
        if(userRepo.findByUsername("user9@mail.com").isEmpty()) {
            BaseUser user9 = new BaseUser("user9@mail.com", encoder.encode("userPW9"),
                    "Marco Antonio", "253148965", "Rua dez");
            user9.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user9);
        }
        if(userRepo.findByUsername("user10@mail.com").isEmpty()) {
            BaseUser user10 = new BaseUser("user10@mail.com", encoder.encode("userPW10"),
                    "Antonio Marco", "201023056", "Rua onze");
            user10.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user10);
        }
        if(userRepo.findByUsername("user11@mail.com").isEmpty()) {
            BaseUser user11 = new BaseUser("user11@mail.com", encoder.encode("userPW11"),
                    "Rui Ricardo", "748526326", "Rua doze");
            user11.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user11);
        }

    }

}
