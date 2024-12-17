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

		DepartmentDaoJDBC dc = DaoFactory.createDepartmentDao();
		SellerDaoJDBC sel = DaoFactory.createSellerDao();


		Department teste = dc.findById(2);
		
		List<Seller> sellers = sel.findByDepartment(teste);
		List<Seller> allSellers = sel.findAll();
		List<Department> allDepart = dc.findAll();
		
		
		Seller seler = sel.findById(2);
		
		for (Seller test : allSellers) {
			System.out.println(test);
		}
		
		for (Department test : allDepart) {
			System.out.println(test);
		}
		
		DB.closeConnection();
		

	}
}
