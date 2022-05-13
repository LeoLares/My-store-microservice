package com.myservice.store.product.entity;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//para definir nuestra clase como entidad lo decoramos con la anotacion
@Entity
//ahora si el nombre de nuestra clase es distinto de la tabla de la base lo cambiamos
@Table(name = "categorias")
//@Getter @Setter //con lombok me genera los get y set
//pero con data es mejor ya que ademas de esos metodos te genera algunos mas como 
//hascode toString y mas
@Data //entonces con esto  no tenemos neceisdad de poner en el cpdigo los getter y setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Categoria {
	
	//mapeamos nuestra entidad
	@Id
	//si queremos que sea autoincremental
	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//que segeneree automaticamnete
	@Column(unique = true, nullable = false)//clave unica y no puede ser null
	//definimos los atributos de nuestra entidad
	private Long id;
	private String name;

}
