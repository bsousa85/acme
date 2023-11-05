package com.isep.acme.services;

import com.isep.acme.model.image.BaseImage;
import com.isep.acme.repositories.image.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.isep.acme.model.ImageDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private FileStorageService service;
    @Autowired
    private IImageRepository repository;

    public Iterable<ImageDTO> getImageProduct(){
          Iterable<BaseImage> p = repository.findAll();
          List<ImageDTO> iDto= new ArrayList<>();
          for (BaseImage pd : p) {
               iDto.add(pd.toDto());
          }

          return iDto;
     };

    public Resource addImage(Resource image){
        return service.loadFileAsResource(image.getFilename());
     }
}
