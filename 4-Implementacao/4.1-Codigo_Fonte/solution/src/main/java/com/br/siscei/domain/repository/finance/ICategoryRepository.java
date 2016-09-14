/**
 * 
 */
package com.br.siscei.domain.repository.finance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.finance.Category;

/**
 * @author felipenami@gmail.com 
 * @since 13/05/2016
 * @version 1.0
 * @category Repository
 */
public interface ICategoryRepository extends JpaRepository<Category, Long>
{
	@Query(value= "SELECT new Category( category.id, category.name ) "+
			  "FROM Category category "+
			  "WHERE ((FILTER( category.name, :filter ) = TRUE)) ")
	public Page<Category>listByFilters(@Param("filter") String filter, Pageable pageable);
}
