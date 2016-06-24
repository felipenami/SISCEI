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

import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.repository.finance.IBankAccountRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class BankAccountService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IBankAccountRepository bankAccountRepository;
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	public BankAccount findBankAccountById(Long id)
	{
		final BankAccount bankAccount = this.bankAccountRepository.findOne( id );
		Assert.notNull( bankAccount, "Não possivel encontrar a conta bancária com o id: "+id );
		return bankAccount;
	}
	/**
	 * 
	 * @param bankAccount
	 * @return
	 */
	public BankAccount insertBankAccount(BankAccount bankAccount)
	{
		Assert.notNull(bankAccount);
		return this.bankAccountRepository.saveAndFlush( bankAccount );
	}
	/**
	 * 
	 * @param bankAccount
	 * @return
	 */
	public BankAccount updateBankAccount(BankAccount bankAccount)
	{
		Assert.notNull(bankAccount);	
		return this.bankAccountRepository.saveAndFlush( bankAccount );
	}
	/**
	 * 
	 * @param id
	 */
	public void removeBankAccount(Long id)
	{
		BankAccount bankAccount = new BankAccount();
		bankAccount.setId( id );
		
		this.bankAccountRepository.delete( id );
		
	}
	/**
	 * 
	 */
	public Page<BankAccount>listBankAccountsByFilters(String filter, PageRequest pageable )
	{
		return this.bankAccountRepository.listByFilters( filter, pageable );
	}
	
}
