package com.estudio.rest;

import com.estudio.entitys.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudio.dao.ProductsDAO;

//podemos exponer los metodos como servicio rest con anotaciones
@RestController // anotacion que aclara que la clase es un servicio rest
@RequestMapping("/products") // url donde se exponen todos los servicios o metodos de esta clase
public class ProductsREST {
	@Autowired // inyecta un objeto real ya que es una interfaz
	private ProductsDAO productDAO;

	@GetMapping
	public ResponseEntity<List<Product>> getProduct() {
		// conecta a la db, consulto los registros, los convirtio en objeto y los
		// devuelve de tipo List
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	@RequestMapping(value="{productId}") // /products/{productId}
	public ResponseEntity<Product> getProductById(@PathVariable("productId")Long productId){
		ResponseEntity<Product> response = ResponseEntity.noContent().build();
		Optional<Product> optionalProduct = productDAO.findById(productId);//optional protect us from a null value
		
		if(optionalProduct != null) {
			response = ResponseEntity.ok(optionalProduct.get());
		}
		return response;
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product addProduct = productDAO.save(product);
		return ResponseEntity.ok(addProduct);
	}
	
	@DeleteMapping(value="{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId")Long productId){
		productDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		ResponseEntity<Product> response = ResponseEntity.notFound().build();
		Optional<Product> optionalProduct = productDAO.findById(product.getId());//optional protect us from a null value
		
		if(optionalProduct != null) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productDAO.save(updateProduct);
			response = ResponseEntity.ok(updateProduct);
		}
		return response;
	}
	
	

	// @GetMapping //se expone en la raiz
	// @RequestMapping(value="greet", method=RequestMethod.GET) //aclaramos la url
	// especifica del metodo
	// public String hello() {
	// return "hello world";
	// }
}
