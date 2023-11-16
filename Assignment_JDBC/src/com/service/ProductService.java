package com.service;

import java.util.List;
import java.util.stream.Collectors;

import com.exception.InvalidIdException;
import com.model.Product;
import com.repository.ProductRepository;

public class ProductService {
ProductRepository productRepository=new ProductRepository();
	public void insertProduct(Product product) {
		productRepository.insertProduct(product);
		
	}
	public List<Product> fetchAllProduct() {
		return productRepository.fetchAllProduct();

	}
	public Product getOneProduct(int id) throws InvalidIdException{
		Product product=productRepository.getOneProduct(id);
		if(product.getId()==0) {
			throw new InvalidIdException("Inavalid iD given try again");
		}
		return product;
	}
	public void deleteProduct(int id) throws InvalidIdException {
		getOneProduct(id);
		productRepository.deleteProduct(id);
		
	}
	public List<Product> filterProduct(String city) {
		List<Product> list=fetchAllProduct();
		list=list.stream()
				.filter(e->e.getCategory().equalsIgnoreCase(city))
				.collect(Collectors.toList());
		return list;
	}
	}

