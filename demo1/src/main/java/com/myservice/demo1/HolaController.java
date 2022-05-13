package com.myservice.demo1;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "hola") //definimos el path de nuestro enponit o metodos
public class HolaController {
	//creamos nuestro metodo para enviar el saludo
	
	//hay que hacer el id autoincremental
	private final AtomicLong counter = new AtomicLong();//asi lo hacemos autoincremental al id
	//para los mesajes implementamos una plantilla para el saludo
	private static final String template = "Hola %s";
	@GetMapping
	//@RequestParam asi capturamos la variable que nos envia por la url
	public Hola saludar(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre) {
		//pasamos estas variables a la clase de Hola por el contructor
		//counter.incrementAndGet() asi le pasamos el id autoincrementable
		//entonces en los argeumento pasamos el id y el formato del mensaje con el template
		//y el nombre
		return new Hola(counter.incrementAndGet(),String.format(template, nombre));
	}

}
