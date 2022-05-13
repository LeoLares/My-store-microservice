package com.myservice.store.product.service;

import java.util.List;

import com.myservice.store.product.entity.Categoria;
import com.myservice.store.product.entity.Producto;

public interface ProductoService {
    //aca estaran los metodos que definiran las capas de servicio
    //estos son los metodos
    public List<Producto> listAllProduct(); //listan todos los productos
    public Producto getProduct(Long id);//obtenemos un producto

    public Producto createProduct(Producto product);//creamoas un prodcuto
    public Producto updateProduct(Producto product);//actualizamos un producto
    public  Producto deleteProduct(Long id);//eliminamos un producto
    public List<Producto> findByCategory(Categoria category);//buscar por categira
    public Producto updateStock(Long id, Double quantity);//actualizar su stock
    
}
