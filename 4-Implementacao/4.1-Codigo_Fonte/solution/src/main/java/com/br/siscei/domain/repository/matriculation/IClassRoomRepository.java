/**
 * 
 */
package com.br.siscei.domain.repository.matriculation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.matriculation.ClassRoom;

/**
 * @author felipenami@gmail.com 
 * @since 14/09/2016
 * @version 1.0
 * @category Repository
 */
public interface IClassRoomRepository extends JpaRepository<ClassRoom, Long>
{
	@Query(value = "SELECT new ClassRoom (classRoom.id, classRoom.name, classRoom.schedule, classRoom.status, classRoom.course ) " +
		   	"From ClassRoom classRoom " +
	 	   	"WHERE ((FILTER( classRoom.name, :filter ) = TRUE)) ")
	public Page<ClassRoom>listByFilters(@Param("filter") String filter, 
										Pageable pageable);
}
