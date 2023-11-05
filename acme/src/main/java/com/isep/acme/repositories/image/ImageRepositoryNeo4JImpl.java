package com.isep.acme.repositories.image;

import com.isep.acme.model.image.BaseImage;
import com.isep.acme.model.image.ImageNeo4J;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("neo4j")
public class ImageRepositoryNeo4JImpl implements IImageRepository {

    @Autowired
    private IImageNeo4JDBDriver neo4JDBDriver;

    @Override
    public List<BaseImage> findAll() {
         return neo4JDBDriver.findAll().stream().map(ImageNeo4J::toBaseImage).collect(Collectors.toList());
    }

    @Override
    public Optional<BaseImage> findById(Long imageID) {
        return neo4JDBDriver.findById(imageID).map(ImageNeo4J::toBaseImage);
    }
}