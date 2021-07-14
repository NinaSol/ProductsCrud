package com.estudio.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	//anotarlo como entidad, objeto que puede ser llevado a la base de datos y recuperado
@Table(name="products") //se va a guardar en la tabla products
public class Product {
	//aclaramos de nuestras propiedades en que columna se guardan
	@Id //aclaramos que es el id de la propiedad
	@Column(name="id") //asignamos nombre a la columna
	@GeneratedValue(strategy=GenerationType.IDENTITY)//aclaramos que la propiedad es autoincremental
	private Long id;
	
	@Column(name="name",nullable=false, length=30)
	private String name;
	 //JPA utiliza estas anotaciones para crear la tabla por nosotros
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
	
}
