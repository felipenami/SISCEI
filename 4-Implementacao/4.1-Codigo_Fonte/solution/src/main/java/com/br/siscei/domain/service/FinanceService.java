/**
 * 
 */
package com.br.siscei.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.siscei.domain.entity.finance.AccountPayable;
import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.entity.finance.StatusAccountPayable;
import com.br.siscei.domain.entity.finance.Supplier;
import com.br.siscei.domain.repository.finance.IAccountPayableRepository;
import com.br.siscei.domain.repository.finance.IAccountsReceivableRepository;
import com.br.siscei.domain.repository.finance.IBankAccountRepository;
import com.br.siscei.domain.repository.finance.ICategoryRepository;
import com.br.siscei.domain.repository.finance.ISupplierRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class FinanceService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IAccountPayableRepository accountPayableRepository;
	/**
	 * 
	 */
	@Autowired
	private IAccountsReceivableRepository accountsReceivableRepository;
	/**
	 * 
	 */
	@Autowired
	private IBankAccountRepository bankAccountRepository;
	/**
	 * 
	 */
	@Autowired
	private ICategoryRepository categoryRepository;
	/**
	 * 
	 */
	@Autowired
	private ISupplierRepository supplierRepository;
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
	 *				 		     ACCOUNTPAYABLE
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param accountsPayable
	 * @return
	 */
	public AccountPayable insertAccountPayable( AccountPayable accountsPayable )
	{
		return this.accountPayableRepository.saveAndFlush( accountsPayable );
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
	public Page<AccountPayable> listAccountsPayableByFilters( String filter, StatusAccountPayable status, PageRequest pageable )
	{
		return this.accountPayableRepository.listByFilters( filter, status, pageable );
	}
	/*-------------------------------------------------------------------
	 *				 		     CATEGORY
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Category findCategoryById(Long id)
	{
		final Category category = this.categoryRepository.findOne( id );
		Assert.notNull( category, "Não possivel encontrar a categoria com o id: "+id );
		return category;
	}
	/**
	 * 
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public Page<Category>listCategoriesByFilters(String filter, PageRequest pageable )
	{
		return this.categoryRepository.listByFilters( filter, pageable);
	}
	
	
	/*-------------------------------------------------------------------
	 *				 		     BANK_ACCOUNT
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
	/*-------------------------------------------------------------------
	 *				 		     SUPPLIER
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
	
}
