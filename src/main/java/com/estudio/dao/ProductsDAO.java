package com.estudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudio.entitys.Product;
//interfaz con dos genericos 1ero con que entidad vamos a trabajar, 2do tipo de datos
public interface ProductsDAO extends JpaRepository<Product, Long>{ 
	

}
