package com.isep.acme.repositories.image;

import com.isep.acme.model.image.ImageMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
@Profile("mongo")
public interface IImageMongoDBDriver extends MongoRepository<ImageMongo, Long> {
}
