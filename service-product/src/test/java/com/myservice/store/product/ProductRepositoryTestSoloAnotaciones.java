package com.myservice.store.product;
/*
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.myservice.store.product.entity.Categoria;
import com.myservice.store.product.entity.Producto;
import com.myservice.store.product.repository.ProductRepository;


//para el test unnitario lo decoramos con 
@DataJpaTest //significa que vamos atester un jpa
public class ProductRepositoryTest {

	//lo primero ijectamos nuesto product repository
	@Autowired //hace que no haga falta intanciar
    private ProductRepository productRepository;
	
	//para probaar el test lo decoramos con esa anotacion
	/*@Test
	//definimos le metodo donde vamos a programar la prueba unitaria
	public void buscarPorCategoria() {
		//creamos el producto que luego insertaremos en la base y luego buscamos
		//la categoria de esete prodcuto
		Producto product01 = Producto.builder()
				.name("Computer")
				//para este de caetgoria ponemos las mismas anotaciones que producto y as usar el builder
				.categoria(Categoria.builder().id(1L).build())
				//.categoria(Categoria.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();
				//ya tenemos nuestro producto de prueba ahora guardamos 
        productRepository.save(product01);
		//retorna lista de prodcuto cuando buscamos por categoria
		List<Producto> founds= productRepository.findByCategoria(product01.getCategoria());
		//comprobamos si la busqueda es buena si retorna mas o igual a 3
		Assertions.assertThat(founds.size()).isEqualTo(3);*/

/*
		@Test
		public void buscarPorCategoria(){
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
	
			Assertions.assertThat(founds.size()).isEqualTo(3);
	}
}*/
