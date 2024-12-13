package model.dao;

import java.util.List;

import model.entities.BaseEntity;

public interface ManagementDao<T extends BaseEntity> {
	void insert (T obj);
	void update (T obj);
	void deleteById (Integer id);
	T findById (Integer id);
	List<T> findAll ();
}
