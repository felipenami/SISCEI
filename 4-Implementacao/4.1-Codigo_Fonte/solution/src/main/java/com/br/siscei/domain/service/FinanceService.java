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

import com.br.siscei.domain.entity.finance.AccountsPayable;
import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.entity.finance.StatusAccountsPayable;
import com.br.siscei.domain.entity.finance.Supplier;
import com.br.siscei.domain.repository.finance.IAccountsPayableRepository;
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
	private IAccountsPayableRepository accountsPayableRepository;
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
	 *				 		     ACCOUNTSPAYABLE
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param accountsPayable
	 * @return
	 */
	public AccountsPayable insertAccountsPayable( AccountsPayable accountsPayable )
	{
		return this.accountsPayableRepository.saveAndFlush( accountsPayable );
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AccountsPayable findAccountsPayableById( Long id )
	{
		final AccountsPayable accountsPayable = this.accountsPayableRepository.findOne( id );
		Assert.notNull( accountsPayable, "Não foi possivel encontar a conta a pagar com o id: "+id );
		return accountsPayable;
	}
	/**
	 * 
	 * @param id
	 */
	public void removeAccountsPayable( Long id )
	{
		
		this.accountsPayableRepository.delete( id );
		this.accountsPayableRepository.flush();
	}
	/**
	 * 
	 * @param filter
	 * @param status
	 * @param pageable
	 * @return
	 */
	public Page<AccountsPayable> listAccountsPayableByFilters( String filter, StatusAccountsPayable status, PageRequest pageable )
	{
		return this.accountsPayableRepository.listByFilters( filter, status, pageable );
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
	
	/*-------------------------------------------------------------------
	 *				 		     BANKACCOUNT
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
		return this.supplierRepository.save( supplier );
	}
	/**
	 * 
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public Page<Supplier>listSupplierByFilters(String filter, PageRequest pageable )
	{
		return this.supplierRepository.listByFilters( filter, pageable );
	}
	
}
