package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.EntitiesDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		EntitiesDao<Seller> sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		
		
		
		
	}

}
