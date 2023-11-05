package com.isep.acme.bootstrapper;

import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.repositories.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;

@Component
//@Profile("bootstrap")
public class ProductBootstrapper implements CommandLineRunner {

    @Autowired
    private IProductRepository pRepo;

    @Override
    public void run(String... args) throws Exception {
        
        if (pRepo.findBySku("asd578fgh267").isEmpty()) {
            BaseProduct p1 = new BaseProduct("asd578fgh267", "Pen", "very good nice product");
            pRepo.save(p1);
        }
        if (pRepo.findBySku("c1d4e7r8d5f2").isEmpty()) {
            BaseProduct p2 = new BaseProduct("c1d4e7r8d5f2", "Pencil", " writes ");
            pRepo.save(p2);
        }
        if (pRepo.findBySku("c4d4f1v2f5v3").isEmpty()) {
            BaseProduct p3 = new BaseProduct("c4d4f1v2f5v3", "Rubber", "erases");
            pRepo.save(p3);
        }
        if (pRepo.findBySku("v145dc2365sa").isEmpty()) {
            BaseProduct p4 = new BaseProduct("v145dc2365sa", "Wallet", "stores money");
            pRepo.save(p4);
        }
        if (pRepo.findBySku("fg54vc14tr78").isEmpty()) {
            BaseProduct p5 = new BaseProduct("fg54vc14tr78", "pencil case", " stores pencils");
            pRepo.save(p5);
        }
        if (pRepo.findBySku("12563dcfvg41").isEmpty()) {
            BaseProduct p6 = new BaseProduct("12563dcfvg41", "Glasses case", " stores glasses");
            pRepo.save(p6);
        }
        if (pRepo.findBySku("vcg46578vf32").isEmpty()) {
            BaseProduct p7 = new BaseProduct("vcg46578vf32", "tissues", " nose clearing material");
            pRepo.save(p7);
        }
        if (pRepo.findBySku("vgb576hgb675").isEmpty()) {
            BaseProduct p8 = new BaseProduct("vgb576hgb675", "mouse pad", " mouse adapted surface");
            pRepo.save(p8);
        }
        if (pRepo.findBySku("unbjh875ujg7").isEmpty()) {
            BaseProduct p9 = new BaseProduct("unbjh875ujg7", " mug ", " drink something from it");
            pRepo.save(p9);
        }
        if (pRepo.findBySku("u1f4f5e2d2xw").isEmpty()) {
            BaseProduct p10 = new BaseProduct("u1f4f5e2d2xw", " Lamp ", " it lights");
            pRepo.save(p10);
        }
        if (pRepo.findBySku("j85jg76jh845").isEmpty()) {
            BaseProduct p11 = new BaseProduct("j85jg76jh845", " Pillow ", " cold both sides");
            pRepo.save(p11);
        }
        if (pRepo.findBySku("g4f7e85f4g54").isEmpty()) {
            BaseProduct p12 = new BaseProduct("g4f7e85f4g54", " chair ", " comfortable ");
            pRepo.save(p12);
        }
    }
}
