package com.isep.acme.repositories.image;

import com.isep.acme.model.image.BaseImage;
import com.isep.acme.model.image.ImageMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("mongo")
public class IImageRepositoryMongoImpl implements IImageRepository {

    @Autowired
    private IImageMongoDBDriver mongoDBDriver;

    @Override
    public List<BaseImage> findAll() {
        return mongoDBDriver.findAll().stream().map(ImageMongo::toBaseImage).collect(Collectors.toList());
    }

    @Override
    public Optional<BaseImage> findById(Long imageID) {
        return mongoDBDriver.findById(imageID).map(ImageMongo::toBaseImage);
    }
}
