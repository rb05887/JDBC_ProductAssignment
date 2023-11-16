package com.controller;

import java.util.List;
import java.util.Scanner;

import com.exception.InvalidIdException;
import com.model.Product;
import com.service.ProductService;

public class ProductController {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ProductService productService = new ProductService();
		while (true) {
			System.out.println("Enter database operations");
			System.out.println("1.Insert product in DB.");
			System.out.println("2.Fetch all products");
			System.out.println("3.Fetch product by id.");
			System.out.println("4.Delete product");
			System.out.println("5.Filter product by category");
			System.out.println("0. Exit");
			System.out.println("***********************");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("Exit bye...");
				break;
			}
			switch (input) {
			case 1:
				System.out.println("Enter product data");
				Product product = new Product();
				System.out.println("Enter product name ");
				product.setName(sc.next());
				System.out.println("Enter product price ");
				product.setPrice(sc.nextDouble());
				System.out.println("Enter product category ");
				product.setCategory(sc.next());
				productService.insertProduct(product);
				System.out.println("Product inserted in db");
				break;
			case 2:
				List<Product> list = productService.fetchAllProduct();
				list.stream().forEach(e -> System.out.println(e));
				break;
			case 3:
				System.out.println("Enter product Id");
				int id = sc.nextInt();
				try {
					product = productService.getOneProduct(id);
					System.out.println(product);

				} catch (InvalidIdException e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter product id");
				id = sc.nextInt();
				try {
					productService.deleteProduct(id);
					System.out.println("Product deleted..");

				} catch (InvalidIdException e1) {
					System.out.println(e1.getMessage());
				}
			case 5:
				System.out.println("Enter the category to filter the record");
				String city = sc.next();
				list = productService.filterProduct(city);
				if (list.size() == 0) {
					System.out.println("No Product found");
				}
				list.stream().forEach(e -> System.out.println(e));
				break;
			default:
				System.out.println("invalid entry try again");
				break;
			}

		}
		sc.close();

	}

}
