package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Produit;

import java.util.List;;;
@Transactional
public interface ProduitDao extends JpaRepository<Produit,Long> {

	public List<Produit> findBydesignation(String des);
	
	@Query("SELECT p FROM Produit p WHERE p.designation LIKE %:x%")
	public List<Produit> ProductCOntainske(@Param("x")String contain);
	
	@Modifying(clearAutomatically=true)
	@Query("update Produit u set u.designation = ?1 , u.prix = ?2 where u.id = ?3")
	int updateProduit(String designation, double prix,Long id);
	
	
	@Query("SELECT p FROM Produit p ORDER by p.prix")
	public List<Produit> findallafteredit();
}
