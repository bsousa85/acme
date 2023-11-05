package com.isep.acme.repositories.image;

import com.isep.acme.model.image.BaseImage;

import java.util.List;
import java.util.Optional;

public interface IImageRepository {

    List<BaseImage> findAll();

    Optional<BaseImage> findById(Long imageID);
}
