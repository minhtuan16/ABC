package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ProductSize;

public interface ProductSizeRepo extends JpaRepository<ProductSize, Integer>{
	
	@Query("SELECT ps FROM ProductSize ps JOIN Size s ON ps.size.id = s.id WHERE ps.product.id = ?1")
	List<ProductSize> findSizes(int z);
	
	@Query("SELECT ps FROM ProductSize ps JOIN ps.size s WHERE s.id = :sID")
	Page<ProductSize> search_sID(@Param("sID") int x, Pageable pageable); 
	 
	@Query("SELECT ps FROM ProductSize ps JOIN ps.product p WHERE p.id = :pID")
	Page<ProductSize> search_pID(@Param("pID") int s, Pageable pageable);
	
}
