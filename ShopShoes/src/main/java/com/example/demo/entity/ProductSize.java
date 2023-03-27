package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product_size")
@Data
public class ProductSize {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "so_luong")
	private Long quantity;
	
	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;
	
	@ManyToOne 
	@JoinColumn(name = "product_id")
	private Product product;
	
}
