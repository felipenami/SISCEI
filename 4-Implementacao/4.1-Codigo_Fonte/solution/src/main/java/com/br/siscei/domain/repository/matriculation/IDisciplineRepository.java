/**
 * 
 */
package com.br.siscei.domain.repository.matriculation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.matriculation.Discipline;

/**
 * @author felipenami@gmail.com 
 * @since 14/09/2016
 * @version 1.0
 * @category Repository
 */
public interface IDisciplineRepository extends JpaRepository<Discipline, Long>
{
	@Query(value = "SELECT new Discipline (discipline.id, discipline.name, discipline.description, discipline.course) " +
		   	"From Discipline discipline " +
	 	   	"WHERE ((FILTER( discipline.name, :filter ) = TRUE)) ")
	public Page<Discipline>listByFilters(@Param("filter") String filter, Pageable pageable);
}
