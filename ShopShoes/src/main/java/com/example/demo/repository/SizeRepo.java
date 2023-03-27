package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Size;

public interface SizeRepo extends JpaRepository<Size, Integer>{
	
	List<Size> findById(int id);

	@Query("SELECT s FROM Size s WHERE s.nameSize LIKE :x")
	Page<Size> searchNameSize(@Param("x") String x, Pageable pageable);
	
}
