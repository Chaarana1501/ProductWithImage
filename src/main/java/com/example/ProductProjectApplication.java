package com.example;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.dao.ProduitDao;
import com.example.entity.Produit;

@SpringBootApplication
public class ProductProjectApplication {

	
	
	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(ProductProjectApplication.class, args);
		
		
	}
}
