/**
 * 
 */
package com.br.siscei.domain.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.siscei.domain.entity.address.Country;

/**
 * @author felip
 *
 */
public interface ICountryRepository extends JpaRepository<Country, Long>
{

}
