/**
 * 
 */
package com.br.siscei.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.siscei.domain.entity.finance.Supplier;
import com.br.siscei.domain.repository.finance.ISupplierRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class SupplierService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private ISupplierRepository supplierRepository;
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Supplier findSupplierById( Long id )
	{
		final Supplier supplier = this.supplierRepository.findOne( id );
		Assert.notNull( supplier, "Não possivel encontrar fornecedor com o id: "+id );
		return supplier;
	}
	/**
	 * 
	 * @param supplier
	 * @return
	 */
	public Supplier insertSupplier(Supplier supplier)
	{
		Assert.notNull(supplier);
		return this.supplierRepository.saveAndFlush( supplier );
	}
	/**
	 * 
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public Page<Supplier>listSuppliersByFilters(String filter, PageRequest pageable )
	{
		return this.supplierRepository.listByFilters( filter, pageable );
	}
	/**
	 * 
	 * @param id
	 */
	public void removeSupplier(Long id)
	{
		Supplier supplier = new Supplier();
		supplier.setId( id );
		
		this.supplierRepository.delete( id );
		
	}
	/**
	 * 
	 * @param supplier
	 * @return
	 */
	public Supplier updateSupplier(Supplier supplier)
	{
		Assert.notNull(supplier);	
		return this.supplierRepository.saveAndFlush( supplier );
	}
	
}
