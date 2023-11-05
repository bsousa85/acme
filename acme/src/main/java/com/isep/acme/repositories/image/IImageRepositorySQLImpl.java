package com.isep.acme.repositories.image;

import com.isep.acme.model.image.BaseImage;
import com.isep.acme.model.image.ImageSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("sql")
public class IImageRepositorySQLImpl implements IImageRepository {

    @Autowired
    private IImageSQLDBDriver sqlDriver;

    @Override
    public List<BaseImage> findAll() {

        final var allImages = sqlDriver.findAll();

        List<ImageSQL> imagesList = new ArrayList<>();
        allImages.forEach(imagesList::add);

        return imagesList.stream().map(ImageSQL::toBaseImage).collect(Collectors.toList());
    }

    @Override
    public Optional<BaseImage> findById(Long imageID) {
        return sqlDriver.findById(imageID).map(ImageSQL::toBaseImage);
    }
}
