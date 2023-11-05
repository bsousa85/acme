package com.isep.acme.repositories.user;

import com.isep.acme.model.user.UserNeo4J;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
@Profile("neo4j")
public interface IUserNeo4JDBDriver extends Neo4jRepository<UserNeo4J, Long> {

    
    @Query("MATCH (u:UserNeo4J) WHERE u.username =~ $username RETURN u")
    UserNeo4J findByUsername(@Param("username") String username);

    @Query("MATCH (u:UserNeo4J) WHERE u.nif = $nif RETURN u")
    Optional<UserNeo4J> findByNif(@Param("nif") String nif);

    
}
