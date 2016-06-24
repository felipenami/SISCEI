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

import com.br.siscei.domain.entity.finance.AccountPayable;
import com.br.siscei.domain.repository.finance.IAccountPayableRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class AccountPayableService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	@Autowired
	private IAccountPayableRepository accountPayableRepository;
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 * @param accountsPayable
	 * @return
	 */
	public AccountPayable insertAccountPayable( AccountPayable accountsPayable )
	{
		accountsPayable = this.accountPayableRepository.saveAndFlush( accountsPayable );
		 
		 return accountsPayable;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AccountPayable findAccountPayableById( Long id )
	{
		final AccountPayable accountsPayable = this.accountPayableRepository.findOne( id );
		Assert.notNull( accountsPayable, "Não foi possivel encontar a conta a pagar com o id: "+id );
		return accountsPayable;
	}
	/**
	 * 
	 * @param id
	 */
	public void removeAccountPayable( Long id )
	{
		
		this.accountPayableRepository.delete( id );
		this.accountPayableRepository.flush();
	}
	/**
	 * 
	 * @param filter
	 * @param status
	 * @param pageable
	 * @return
	 */
	public Page<AccountPayable> listAccountsPayableByFilters( String filter,  PageRequest pageable )
	{
		return this.accountPayableRepository.listByFilters( filter, pageable );
	}
	/**
	 * 
	 * @param accountPayable
	 * @return
	 */
	public AccountPayable updateAccountPayable(AccountPayable accountPayable)
	{
		Assert.notNull(accountPayable);	
		return this.accountPayableRepository.saveAndFlush( accountPayable );
	}
}
