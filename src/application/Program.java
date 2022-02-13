package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		Department dp = new Department(2, "Books");
		Seller peter = new Seller(12, "Peter", "peter@email.com", new Date(), 4000.00, dp);
		
		
		
	}

}
