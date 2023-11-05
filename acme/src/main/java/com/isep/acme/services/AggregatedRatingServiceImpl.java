package com.isep.acme.services;

import com.isep.acme.model.aggregatedrating.BaseAggregatedRating;
import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.repositories.aggregatedrating.IAggregatedRatingRepository;
import com.isep.acme.repositories.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AggregatedRatingServiceImpl implements AggregatedRatingService{

    @Autowired
    IAggregatedRatingRepository arRepository;

    @Autowired
    private IProductRepository pRepository;

    @Autowired
    ReviewService rService;

    @Autowired
    ProductService productService;

    @Override
    public BaseAggregatedRating save( String sku ) {

        Optional<BaseProduct> product = pRepository.findBySku( sku );

        if (product.isEmpty()){
            return null;
        }

        Double average = rService.getWeightedAverage(product.get());


        Optional<BaseAggregatedRating> r = arRepository.findByProductId(product.get());
        BaseAggregatedRating aggregateF;

        if(r.isPresent()) {
            r.get().setAverage( average );
            aggregateF = arRepository.save(r.get());
        }
        else {
            BaseAggregatedRating f = new BaseAggregatedRating(average, product.get());
            aggregateF = arRepository.save(f);
        }

        return aggregateF;
    }


}
