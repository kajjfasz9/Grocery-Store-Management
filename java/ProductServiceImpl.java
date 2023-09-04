package com.grocerymanagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocerymanagement.entity.Product;
import com.grocerymanagement.repository.ProductRepository;

import com.grocerymanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Object getProductById(Long id) {
		
		return productRepository.findById(id).get();
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public int totalInventoryValue() {
		List<Product> allProducts = productRepository.findAll();
		int totalValue = 0;
		
		for (Product x : allProducts ) {
			int price = x.getSellingPrice();
			int quantity = x.getQuantity();
			int value = price * quantity;
			
			totalValue+=value;
		}
	
		return totalValue;
	}

	@Override
	public int avgProductCost() {
		List<Product> allProducts = productRepository.findAll();
		int totalCost = 0;
		int count = 0;
		
		for (Product x : allProducts ) {
			int cost = x.getCost();
			totalCost+=cost;
			count++;
			
		}
	
		int average = totalCost / count;
		return average;
	}

	@Override
	public int avgProductSellPrice() {
		List<Product> allProducts = productRepository.findAll();
		int totalSell = 0;
		int count = 0;
		
		for (Product x : allProducts ) {
			int sell = x.getSellingPrice();
			totalSell+=sell;
			count++;
			
		}
	
		int average = totalSell / count;
		return average;
	}



	

}
