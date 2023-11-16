package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;

public class ProductRepository {
	
	private String url="jdbc:mysql://localhost:3306/jdbcproductapp";
	private String usernamedb="root";
	private String passworddb="Abc@123#";
	private String driver="com.mysql.cj.jdbc.Driver";
	Connection con;
	public void dbConnect() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection(url, usernamedb, passworddb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertProduct(Product product) {
		dbConnect();
		String sql="insert into product(name,price,category) values(?,?,?)";
		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3,product.getCategory());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		dbClose();
	}
	public List<Product> fetchAllProduct() {
		dbConnect();
		String sql = "select * from product";
		List<Product> list = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rst = preparedStatement.executeQuery();
			while (rst.next()) {
				Product product=new Product();
				// fetch each coloum from db and save it in object
				product.setId(rst.getInt("id"));
				product.setName(rst.getString("name"));
				product.setPrice(rst.getDouble("price"));
				product.setCategory(rst.getString("category"));
				// save obj in list
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	public Product getOneProduct(int id) {
		dbConnect();
		String sql = "select * from product where id=?";
		Product product=new Product();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rst = preparedStatement.executeQuery();
			if(rst.next()) {
				// fetch each coloum from db and save it in object
				product.setId(rst.getInt("id"));
				product.setName(rst.getString("name"));
				product.setPrice(rst.getDouble("price"));
				product.setCategory(rst.getString("category"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return product;
	}
	public void deleteProduct(int id) {
		dbConnect();
		String sql = "delete from product where id=?";
			try {
		    PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbClose();
	}
	}
	
	
	


