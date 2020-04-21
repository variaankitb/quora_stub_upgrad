package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;

import javax.transaction.Transactional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "insert into category (title, description) values (?1, ?2)")
	void addCategory(String title, String description);
}
