/**
 * 
 */
package com.br.siscei.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.siscei.domain.entity.address.Address;
import com.br.siscei.domain.repository.address.IAddressRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class AddressService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IAddressRepository addressRepository;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param address
	 * @return
	 */
	public Address insertAddress(Address address)
	{
		return this.addressRepository.save( address );
	}
	/**
	 * 
	 */
	public Address findAddressById(Long id)
	{
		final Address address = this.addressRepository.findOne( id );
		Assert.notNull( address, "Não foi possivel encontar endereço com o id: "+id );
		return address;
	}
	
	
}
