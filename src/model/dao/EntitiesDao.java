package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface EntitiesDao <T> {
	void insert(T obj);
	void update(T obj);
	void deleteById(Integer id);
	T findById(Integer id);
	List<T> findAll();
	List<Seller> findByDepartment(Department department);
	

}
