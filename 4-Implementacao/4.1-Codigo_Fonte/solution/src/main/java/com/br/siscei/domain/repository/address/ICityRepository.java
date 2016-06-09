/**
 * 
 */
package com.br.siscei.domain.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.address.City;
import com.br.siscei.domain.entity.address.State;

/**
 * @author felip
 *
 */
public interface ICityRepository extends JpaRepository<City, Long>
{
	@Query(value= "SELECT new City(city.id, city.name, city.state) " +
			  "FROM  City city " +
			  "WHERE (( city.state.id = :stateId )) " )
public List<City>listByState(@Param("stateId") Long stateId);
}
