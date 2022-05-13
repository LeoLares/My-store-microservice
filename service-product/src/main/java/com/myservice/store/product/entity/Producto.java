package com.myservice.store.product.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Productos")
//@Getter @Setter //con lombok me genera los get y set
//pero con data es mejor ya que ademas de esos metodos te genera algunos mas como 
//hascode toString y mas
@Data //entonces con esto  no tenemos neceisdad de poner en el cpdigo los getter y setter
//esto que haremos ahora es para poder insertar un producto
@AllArgsConstructor @NoArgsConstructor @Builder 
//te geernear el contrcutor con las varibeles asignadas
//@NoArgsConstructor //genera un contructor sin argumentos7
//@Builder // nos va a permitir hacer nueva instancias de nuestra entidad lo usaremos para
//la clase de test unitario
public class Producto {

	//mapeamos nuestra entidad luego de definir los atributos
		@Id
		//si queremos que sea autoincremental
		 
		@GeneratedValue(strategy = GenerationType.IDENTITY)//que segeneree automaticamnete
		@Column(unique = true, nullable = false)//clave unica y no puede ser null
		//definimos los atributos de nuestra entidad
		private Long id;
		//validamos que los campos, en esta caso el name no esten vacios o nulos 
		@NotEmpty(message = "El nombre no debe ser vacío")//de esta manera hacemos que el name si viwne vacio devuelva ese mensaje
		private String name;
		private String description;
		//validamos el stock para que sea mayor a cero
		//lo hacemos con la anotacion 
		@Positive(message = "El valor debe ser mayor a 0")
		private Double stock;
		private Double price;
		private String status;
		//si el nombre de nuestro campo en la base es distinto de el de la variable
		//lo podemos modificar asi
		@Column(name = "created_At")
		@Temporal(TemporalType.DATE) //para saber con el tipo de fecha que queremos trabajar
		private Date createdAt;
		
		//ahora vamos a mapear nustra entidad cprducto con la de categoria
		// su relacion es de muchos a uno, una cateogira tiene varios prodcutos
		//para usamos las sigueinte anotaciones

		//validamos la catetogria para que no sea nulla
		@NotNull(message= "La categoria no puede estar vacia")
		@ManyToOne(fetch = FetchType.LAZY) //lazy solo carga cunado es usadda, eager carga 
		//todos los valores
		//mapeo de las columnas
		@JoinColumn(name = "categoria_id") //con ese campo se enlazan
		@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) // esta anoacion se debe a que esta en modo lazy el fetch, con esta antacion evistamos un error 500
		private Categoria categoria;
		
}
