package com.myservice.store.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myservice.store.product.entity.Categoria;
import com.myservice.store.product.entity.Producto;
import com.myservice.store.product.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController //indicamos que vamos a implemnetar un serivico rest
//mapeamos donde va a esar el recurso 
@RequestMapping(value = "/productos")
public class ProductoController {
    //importamos la clase de services en este caso prodcutServices
    @Autowired
    private ProductoService productoService;

    //implementamos el metodo que nos devuelva toda la lista de nuestra base de datps 
    @GetMapping //es parasaber que esperamos un GET
    /*public ResponseEntity<List<Producto>> listProduct(){
        //consultamos la lista de nuestros prodcutos
        //para eso primero definimos nuestra lista y aplicamos el metodo de buscar or todos que esta en prodcutService
        List<Producto> productos = productoService.listAllProduct();
        // a su vz hay que validar si nos devulve valores o no
        if (productos.isEmpty()){
            return ResponseEntity.noContent().build(); // devulve un 204 
        }

        return ResponseEntity.ok(productos);
    }*/
    //el mismo metood de arriab pero filtrando la categoria
    public ResponseEntity<List<Producto>> listProduct(@RequestParam(name = "categoria_id", required = false) Long categoryId){
        //required = flase quiere decir que si lo trae bien y si no igual
        //"categoria_id", es el que va a estar en el parametro de la url
        //ejejemplo http://localhost:8080/productos?categoria_id=1
        List<Producto> products = new ArrayList<>();//declaramos la lista pra poder trabajarla despues
        //validamos la categoria
        if(categoryId  == null){
            //si no hay categoria listamos todos los producto
            products = productoService.listAllProduct();
            // a su vz hay que validar si nos devulve valores o no
        if (products.isEmpty()){
            return ResponseEntity.noContent().build(); // devulve un 204 
        }
        }else{
            //si nos envia una categoria filtramos por el id de categoria
            products = productoService.findByCategory(Categoria.builder().id(categoryId).build());

            // a su vz hay que validar si nos devulve valores o no
            if (products.isEmpty()){
                return ResponseEntity.noContent().build(); // devulve que no encuentra la catgoria
            }
        }
        

        return ResponseEntity.ok(products);
    }

//si la peticion es po id este es el metodo 
    @GetMapping(value = "/{id}")   //asi indicamos que en la url debe venir ademas del mapeo por el nombre del recurso que es producto se debe agragar un /y el id para que venga por este metodo
    public ResponseEntity<Producto> listProductById(@PathVariable("id") Long id){
        //consultamos la lista de nuestros prodcutos
        //para eso primero definimos nuestra lista y aplicamos el metodo de buscar or todos que esta en prodcutService
        Producto productos = productoService.getProduct(id);
        // a su vz hay que validar si nos devulve valores o no
        if (productos == null){
            return ResponseEntity.notFound().build(); // devulve un 404 no encunetra
        }

        return ResponseEntity.ok(productos);
    }

//recibimos un prodcuto para insertar en la abse de datos
//para aplicar el @valir y demas validaciones debemos validar en la clase prodcutos los demas campos o variables 
    @PostMapping //la anotacion valid es para saber que tiene validaccion, y el bindingResult es paara obtener ese resultado de la validacion
    public ResponseEntity<Producto> createProduct(@Valid @RequestBody Producto product, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Producto productCreate =  productoService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);//asi devolvermos un estado sobre la peticion
    }

    //metodo par actualizar nuestro producto

    @PutMapping(value = "/{id}")//actualiza por ID
    public ResponseEntity<Producto> updateProduct(@PathVariable("id") Long id, @RequestBody Producto product){
        product.setId(id);
        Producto productDB =  productoService.updateProduct(product);
        if (productDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }

    //Eliminar producto
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProduct(@PathVariable("id") Long id){
        Producto productDelete = productoService.deleteProduct(id);
        if (productDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }

//mapea por stock, actualizar ssstock
    @PatchMapping (value = "/{id}/stock")
    public ResponseEntity<Producto> updateStockProduct(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity){
        //@RequestParam es para obtener la cantidad del articulo
        Producto product = productoService.updateStock(id, quantity);
        if (product == null){
        return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
}

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
