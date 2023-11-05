package com.isep.acme.repositories.image;

import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

import com.isep.acme.model.image.ImageNeo4J;

@Component
@Profile("neo4j")
public interface IImageNeo4JDBDriver extends Neo4jRepository<ImageNeo4J, Long> {
}
