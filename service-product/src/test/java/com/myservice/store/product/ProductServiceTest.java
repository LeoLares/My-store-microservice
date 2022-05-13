package com.myservice.store.product;

import java.util.Optional;

import com.myservice.store.product.entity.Categoria;
import com.myservice.store.product.entity.Producto;
import com.myservice.store.product.repository.ProductRepository;
import com.myservice.store.product.service.ProductoService;
import com.myservice.store.product.service.ProductoServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//en esta ocacion apra simular el caso lo hacemos con springboot test
@SpringBootTest
public class ProductServiceTest {
    //en este caso no vamos a usar la base de datos de memoria si no que vamos a usar una herramienta llamada mockito, trabajeremos con datos mockeados
    //enotnces para espo usamos la anotacion
    @Mock //esto indica que los datos sera mockeados
    //importamos repositorio
   // @Autowired
    private ProductRepository productRepository;

    //declaraos nustro servicio
    private ProductoService productoService;

    //antes de la prueba unitaria hacemos un metodo apra mockear todos los datos
    @BeforeEach //asi indicamos que se debe ejecutar ante de nuestro test
    public void setup(){
       // inicializamos mockito
       // MockitoAnnotations.initMocks(this);deshuso
        MockitoAnnotations.openMocks(this);
        //instanciasmos productServices

        productoService =  new ProductoServiceImpl( productRepository);//por el contructor le pasaremos el repositorio mockeado para este tes debemos modifcar algunas cosas en la clase de serviceImpl
        Producto computer =  Producto.builder()//nombramos al producto computeer, le hacemos un contrauctor
                .id(1L)
                .name("computer")
                .categoria(Categoria.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        //ahora creamos el mock
        Mockito.when(productRepository.findById(1L))//Cuando buscamos un prduco con el id 1 retornaremos el prodcuto computer
            .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);//estp es para que un prodcuto si es actulaizado se actulaice el mock
    }

    //@Test
    //creamos un metodo que valide la busqueda de nuestro produto computer
    public void whenValidGetID_ThenReturnProduct(){
        Producto found = productoService.getProduct(1L);//cuando ejecute este loq ue hara es buscar el producto mockitiado y nos debe devolver el de abajo par que este bien el test ya que es e1l es id de prueba pra buscar
        //ya que el get product nos debe devolver el valor
       Assertions.assertThat(found.getName()).isEqualTo("computer");

   }


   //verficar la parte de la logica para actualizar el stock

   @Test
   public void whenValidUpdateStock_ThenReturnNewStock(){
        Producto newStock = productoService.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
   }


    
}
