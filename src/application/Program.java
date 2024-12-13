package application;

import java.util.List;
import java.util.Locale;

import db.DB;
import model.dao.DaoFactory;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		Department obj = new Department(2, null);

		DepartmentDaoJDBC dc = DaoFactory.createDepartmentDao();
		SellerDaoJDBC sel = DaoFactory.createSellerDao();

		List<Seller> sellers = sel.findByDepartment(obj);
		List<Seller> allSellers = sel.findAll();

		Seller seler = sel.findById(2);
		System.out.println(seler.toString());
		
		for (Seller test : allSellers) {
			System.out.println(test);
		}
		
		sel.update(seler);
		
		DB.closeConnection();
		

	}
}
