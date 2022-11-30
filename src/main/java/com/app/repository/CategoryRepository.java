package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.app.entity.Category;

/**
 * @author tamnc
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
	Optional<Category> findByCategoryCodeAndStatus(String categoryCode, int status);
	
	List<Category> findByStatus(int status);
}
