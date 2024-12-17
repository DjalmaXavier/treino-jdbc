package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("UPDATE seller SET Name = ? WHERE Id = ?");

			st.setString(1, obj.getName());
			st.setInt(1, obj.getId());

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
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("DELETE FROM Department WHERE Id = ?");
			
			st.setInt(1, id);
			
			
			int rows = st.executeUpdate();
			
			System.out.println("Delete done! Rows affected: " + rows);
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * from Department WHERE Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dp = new Department(rs.getInt("Id"), rs.getString("Name"));
				return dp;
			}
			
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("Select * FROM department");
			
			rs = st.executeQuery();
			
			List<Department> dpList = new ArrayList<Department>();
			
			while(rs.next()) {
				dpList.add(new Department(rs.getInt("Id"), rs.getString("Name")));
			}
			
			return dpList;
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	

		return null;
	}

}
