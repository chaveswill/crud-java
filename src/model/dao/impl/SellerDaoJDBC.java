package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.EntitiesDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements EntitiesDao<Seller> {

	private Connection conn = null;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) values (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("No rows affected");
			}
			else {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			}
			System.out.println("Insert successfully");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}


	}

	@Override
	public void update(Seller seller) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"update seller set Name = ?, Email = ?, BirthDate = ?, "
					+"BaseSalary = ?, DepartmentId = ? where Id = ?"
					);
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			st.setInt(6, seller.getId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"delete from seller where Id = ?"
			);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Delete succefully.");
			}else {
				throw new DbException("Delete error: This id don't exist on database");
			}
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dp = instatiateDepartment(rs);
				Seller seller = instatiateSeller(rs, dp);
				return seller;
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}

		return null;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
	}

	private Seller instatiateSeller(ResultSet rs, Department dp) throws SQLException {
		return new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"),
				rs.getDouble("BaseSalary"), dp);

	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> sellers = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Id"); 
			rs = st.executeQuery();
			Map<Integer, Department> map = new HashMap<>();			
			while(rs.next()) {				
				Department dp = map.get(rs.getInt("DepartmentId"));
				if(dp == null) {
					dp = instatiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dp);
				}
				Seller seller = instatiateSeller(rs, dp);
				sellers.add(seller);
			}
				
			return sellers;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> sellers = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name"); 
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			Map<Integer, Department> map = new HashMap<>();			
			while(rs.next()) {				
				Department dp = map.get(rs.getInt("DepartmentId"));
				if(dp == null) {
					dp = instatiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dp);
				}
				Seller seller = instatiateSeller(rs, dp);
				sellers.add(seller);
			}
				
			return sellers;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
