/**
 * 
 */
package com.br.siscei.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.siscei.domain.entity.address.Address;
import com.br.siscei.domain.entity.address.City;
import com.br.siscei.domain.entity.address.State;
import com.br.siscei.domain.repository.address.IAddressRepository;
import com.br.siscei.domain.repository.address.ICityRepository;
import com.br.siscei.domain.repository.address.IStateRepository;

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
	@Autowired
	/**
	 */
	private ICityRepository cityRepository;
	/**
	 * 
	 */
	@Autowired
	private IStateRepository stateRepository;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Address findAddressById(Long id)
	{
		final Address address = this.addressRepository.findOne( id );
		Assert.notNull( address, "Não foi possivel encontar endereço com o id: "+id );
		return address;
	}
	
	/*-------------------------------------------------------------------
	 *				 		     CITY
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	public City findCityById(Long id)
	{
		final City city = this.cityRepository.findOne( id );
		Assert.notNull( city, "Não foi possivel encontar cidade com o id: "+id );
		return city;	
	}
	/**
	 * 
	 * @param filter
	 * @param state
	 * @param pageable
	 * @return
	 */
	public List<City> listCitiesByState( Long stateId)
	{
		return this.cityRepository.listByState( stateId );
	}
	
	/*-------------------------------------------------------------------
	 *				 		     STATE
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public List<State> listAllStates()
	{
		return this.stateRepository.findAll();
	}
	/*-------------------------------------------------------------------
	 *				 		     COUNTRY
	 *-------------------------------------------------------------------*/
	
	
}
