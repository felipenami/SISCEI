/**
 * 
 */
package com.br.siscei.domain.repository.matriculation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.matriculation.Classroom;

/**
 * @author felipenami@gmail.com 
 * @since 14/09/2016
 * @version 1.0
 * @category Repository
 */
public interface IClassRoomRepository extends JpaRepository<Classroom, Long>
{
	@Query(value = "SELECT new Classroom (classroom.id, classroom.name, classroom.status, classroom.course ) " +
		   	"From Classroom classroom " +
	 	   	"WHERE ((FILTER( classroom.name, :filter ) = TRUE)) ")
	public Page<Classroom>listByFilters(@Param("filter") String filter, 
										Pageable pageable);
}
