package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ManagementDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements ManagementDao<Department> {
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Department obj) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO Department (Name) VALUES (?)");

			st.setString(1, obj.getName());

			int rowsAffected = st.executeUpdate();

			System.out.println("Insert done! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	@Override
	public void update(Department obj) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO Department (Name) VALUES (?)");

			st.setString(1, obj.getName());

			int rowsAffected = st.executeUpdate();

			System.out.println("Insert done! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}


	}

	@Override
	public void deleteById(Integer id) {
		Connection conn = null;
		PreparedStatement st = null;

		/*
		 * try { conn = DB.getConnection(); st =
		 * conn.prepareStatement("INSERT INTO Department (Name) VALUES (?)");
		 * 
		 * st.setString(1, obj.getName());
		 * 
		 * int rowsAffected = st.executeUpdate();
		 * 
		 * 
		 * } catch (SQLException e) { throw new DbException(e.getMessage()); } finally {
		 * DB.closeStatement(st); DB.closeConnection(); }
		 */

	}

	@Override
	public Department findById(Integer id) {

		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
