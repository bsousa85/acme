package com.isep.acme.repositories.user;

import com.isep.acme.model.user.UserSQL;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@Profile("sql")
public interface IUserSQLDBDriver extends CrudRepository<UserSQL, Long> {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    UserSQL findByUsername(String username);
}
