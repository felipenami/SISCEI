/**
 * 
 */
package com.br.siscei.domain.repository.matriculation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.matriculation.Course;

/**
 * @author felipenami@gmail.com 
 * @since 14/09/2016
 * @version 1.0
 * @category Repository
 */
public interface ICourseRepository extends JpaRepository<Course, Long>
{

	@Query(value = "SELECT new Course (course.id, course.name, course.description) " +
				   	"From Course course " +
			 	   	"WHERE ((FILTER( course.name, :filter ) = TRUE) OR (FILTER( course.description, :filter ) = TRUE) ) ")
			public Page<Course>listByFilters(@Param("filter") String filter, Pageable pageable);
	
}
