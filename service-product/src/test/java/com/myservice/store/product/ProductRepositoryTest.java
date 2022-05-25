package com.myservice.store.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import com.myservice.store.product.entity.Categoria;
import com.myservice.store.product.entity.Producto;
import com.myservice.store.product.repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    //@Test
    public void whenFindByCategory_thenReturnListProduct(){
        Producto product01 = Producto.builder()
                .name("computer")
                .categoria(Categoria.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();
        productRepository.save(product01);

        List<Producto> founds= productRepository.findByCategoria(product01.getCategoria());

        System.out.println("Mostrar resultado");
        Assertions.assertThat(founds.size()).isEqualTo(3);


    }
}