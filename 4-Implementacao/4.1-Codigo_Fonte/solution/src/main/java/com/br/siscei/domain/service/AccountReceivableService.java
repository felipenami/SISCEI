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

import com.br.siscei.domain.entity.finance.AccountReceivable;
import com.br.siscei.domain.repository.finance.IAccountReceivableRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class AccountReceivableService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Autowired
	private IAccountReceivableRepository accountReceivableRepository;
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param accountsPayable
	 * @return
	 */
	public AccountReceivable insertAccountReceivable( AccountReceivable accountReceivable )
	{
		accountReceivable = this.accountReceivableRepository.saveAndFlush( accountReceivable );
		 
		 return accountReceivable;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AccountReceivable findAccountReceivableById( Long id )
	{
		final AccountReceivable accountReceivable = this.accountReceivableRepository.findOne( id );
		Assert.notNull( accountReceivable, "Não foi possivel encontar a conta a receber com o id: "+id );
		return accountReceivable;
	}
	/**
	 * 
	 */
	public void removeAccountReceivable( Long id )
	{
		
		this.accountReceivableRepository.delete( id );
		this.accountReceivableRepository.flush();
	}
	/**
	 * 
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public Page<AccountReceivable> listAccountsReceivableByFilters( String filter,  PageRequest pageable )
	{
		return this.accountReceivableRepository.listByFilters( filter, pageable );
	}
	/**
	 * 
	 * @param accountPayable
	 * @return
	 */
	public AccountReceivable updateAccountReceivable(AccountReceivable accountReceivable)
	{
		Assert.notNull(accountReceivable);	
		return this.accountReceivableRepository.saveAndFlush( accountReceivable );
	}
}
