package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.EntitiesDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dp = new Department(2, "books");
		
		
		EntitiesDao<Seller> sellerDao = DaoFactory.createSellerDao();

		List<Seller> sellers = sellerDao.findByDepartment(dp);
		for(Seller seller:sellers) {
			System.out.println(seller);
		}
		System.out.println("=====================");
		sellers = sellerDao.findAll();
		
		for(Seller seller:sellers) {
			System.out.println(seller);
		}
		
		
		
	}

}
