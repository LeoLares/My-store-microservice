package com.myservice.store.product.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myservice.store.product.entity.Categoria;
import com.myservice.store.product.entity.Producto;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Long>{
	//cuando heredamos la clase de jpa esta espera la entidad y el id
	//eta o la de crudRepository se heredan por que ya viene inlcuido todo el manejo de datos
	//no hace falta hacer los query
	//generamos un query que nos permita tenes todos los prodcuto de una categoria
	//deter,minada
	//la regla para hacer una busqueda por un atributo de tu entidad es con findBy
	//y el nombre del atributo que buscamos
	public abstract ArrayList<Producto> findByCategoria(Categoria categoria);

}
