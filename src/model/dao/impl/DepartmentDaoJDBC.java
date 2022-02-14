package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EntitiesDao;
import model.entities.Department;
import model.entities.Seller;


public class DepartmentDaoJDBC implements EntitiesDao<Department> {
	Connection conn = null;
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
		
	}
	@Override
	public void insert(Department dep) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into department (Id, Name) values (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, dep.getId());
			st.setString(2, dep.getName());	
			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("No rows affected");
			}
			
			System.out.println("Insert successfully");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}



		
	}
	@Override
	public void update(Department dep) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update department set Id = ?, Name = ? where DepartmentId = ?",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, dep.getId());
			st.setString(2, dep.getName());	
			st.setInt(3, dep.getId());
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
				"delete from department where Id = ?"
			);
			st.setInt(1, id);
			st.executeUpdate();
			
					
		} catch (SQLException e) {
			throw new DbException("Delete error: "+e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}
	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dp = instatiateDepartment(rs);
				return dp;
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}

		return null;

	}
	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> deps = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT * FROM department");
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dp = instatiateDepartment(rs);
				deps.add(dp);
			}
			

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}

		return deps;
	}
	@Override
	public List<Seller> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
	}
	
}
