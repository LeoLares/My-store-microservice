package com.myservice.store.product.service;

import java.util.Date;
import java.util.List;

import com.myservice.store.product.entity.Categoria;
import com.myservice.store.product.entity.Producto;
import com.myservice.store.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service //para saber que es de tipo servicio
@RequiredArgsConstructor //sin autowired solo iyectamos esta dependencia de auto genrear el contructor de la clase
public class ProductoServiceImpl implements ProductoService {

    //@Autowired //inyeccion de dependecias
    //par la pruab de mock en ves de autowired ponemos un contructor en las anotacoines arriba de la clase

    //importamos nuestro repositorio
    private  final ProductRepository productRepository;
    @Override
    public List<Producto> listAllProduct() {
        // buscamos por todos los metodos
        return productRepository.findAll();
    }

    @Override
    public Producto getProduct(Long id) {
        // Buscamos por ID
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Producto createProduct(Producto product) {
        // Creamos prodcuto
        //cuando recibimos el producto seteamos algunos valores
        //como el status y la fecha
        product.setStatus("CREATED");
        //tambien la fecha 
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Producto updateProduct(Producto product) {
        // Para actualizar el prodcuto debemos verificar si este existe
        //primero hacemos la cnsulta a la base de datos
        Producto  productDB = getProduct(product.getId());
        //validamos que devolvio la consulta
        if(productDB == null){
            return null;
        }
        //ahora acutlaizmos el prodcuto con los campos que queramos actualizar
        //ejemplo el name
        productDB.setName(product.getName()); 
        //la descripcion
        productDB.setDescription(product.getDescription());
        productDB.setCategoria(product.getCategoria());
        productDB.setPrice(product.getPrice());
//guardamos los cambios
        return productRepository.save(productDB);
    }

    @Override
    public Producto deleteProduct(Long id) {
        // Para eleimiar debemos primero validar si existe
        Producto  productDB = getProduct(id);
        //validamos que devolvio la consulta
        if(productDB == null){
            return null;
        }
        //en cASO DE EXISTIR VAMOS A CAMBIAR EL ESTADO A ELIMINADO
        productDB.setStatus("DELETE");//solo se eliminan atraves de estados

        return productRepository.save(productDB);
    }

    @Override
    public List<Producto> findByCategory(Categoria category) {
        // Ahora por categoria
        return productRepository.findByCategoria(category);
       
    }

    @Override
    public Producto updateStock(Long id, Double quantity) {
        // para actuaizar el stock se recibi el id del producto y la cantidad a actualizar
        // Para eleimiar debemos primero validar si existe
        Producto  productDB = getProduct(id);
        //validamos que devolvio la consulta
        if(productDB == null){
            return null;
        }
        //si existe hacemos
        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }
    
}
