package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ManagementDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements ManagementDao<Seller> {
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)");

			st.setString(1, "Djalma");
			st.setString(2, "djalmaxavier42@gmail.com");
			st.setTimestamp(3, Timestamp.valueOf(obj.getBirthDate()));
			st.setDouble(4, 3000.0);
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());

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
	public void update(Seller obj) {
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? WHERE Id = ?");
			
			
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setTimestamp(3, Timestamp.valueOf(obj.getBirthDate()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			

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
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("select seller.*, department.Name as DepName " + "FROM seller "
					+ "INNER JOIN department " + "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				Department dep = createDepartment(rs);
				Seller obj = createSeller(rs, dep);
				return obj;
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
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"select seller.*, department.Name as DepName " + "FROM seller " + "INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + " ORDER BY Name");

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();

			Map<Integer, Department> mp = new HashMap<>();

			while (rs.next()) {

				Department dep = mp.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = createDepartment(rs);
					mp.put(rs.getInt("DepartmentId"), dep);
				}

				list.add(createSeller(rs, dep));

			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"select seller.*, department.Name as DepName " + "FROM seller " + "INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + " ORDER BY Name");

			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();

			Map<Integer, Department> mp = new HashMap<>();

			while (rs.next()) {

				Department dep = mp.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = createDepartment(rs);
					mp.put(rs.getInt("DepartmentId"), dep);
				}

				list.add(createSeller(rs, dep));

			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Department createDepartment(ResultSet rs) throws SQLException {

		return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
	}

	private Seller createSeller(ResultSet rs, Department dep) throws SQLException {
		return new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),
				rs.getTimestamp("BirthDate").toLocalDateTime(), rs.getDouble("BaseSalary"), dep);
	}
}
