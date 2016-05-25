/**
 * 
 */
package com.br.siscei.domain.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.siscei.domain.entity.address.Address;

/**
 * @author felip
 *
 */
public interface IAddressRepository extends JpaRepository<Address, Long>
{
	
}
