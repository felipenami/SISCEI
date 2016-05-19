/**
 * 
 */
package com.br.siscei.test.domain.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.siscei.domain.entity.finance.AccountsPayable;
import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.entity.finance.StatusAccountsPayable;
import com.br.siscei.domain.service.FinanceService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author felip
 *
 */
public class FinanceServiceTest extends AbstractIntegrationTests
{

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	@Autowired
	private FinanceService financeService;
	
	
	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/finance/BankAccountDataSet.xml",
		"/dataset/finance/CategoryDataSet.xml",
		"/dataset/finance/AccountsPayableDataSet.xml",
	})
	public void findAccountsPayableByIdMustPass()
	{
		final AccountsPayable accountsPayable = this.financeService.findAccountsPayableById( 9999L );
		Assert.assertNotNull(accountsPayable);
	}
	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/finance/BankAccountDataSet.xml",
			"/dataset/finance/CategoryDataSet.xml",
			"/dataset/finance/AccountsPayableDataSet.xml",
		})
	public void insertAccountsPayableMustPass()
	{
		final Category category = this.financeService.findCategoryById( 9997L );
		final BankAccount bankAccount = this.financeService.findBankAccountById( 9997L );

		final Calendar dueDate = new GregorianCalendar(2050,9,5, 11,00,00);
		final Calendar entryDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final Calendar paymentDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final BigDecimal valor = new BigDecimal("1.5");
		
		AccountsPayable accountsPayable = new AccountsPayable( );
		
		accountsPayable.setBankAccount( bankAccount );
		accountsPayable.setCategory( category );
		accountsPayable.setDescription( "Teste 1" );
		accountsPayable.setValue( valor );
		accountsPayable.setDueDate( dueDate );
		accountsPayable.setEntryDate( entryDate );
		accountsPayable.setPaymentDate( paymentDate );
		accountsPayable.setStatus( StatusAccountsPayable.NOT_PAID );
		
		accountsPayable = this.financeService.insertAccountsPayable( accountsPayable );

		Assert.assertNotNull( accountsPayable );
		Assert.assertNotNull( accountsPayable.getId() );
		Assert.assertNotNull( accountsPayable.getStatus() );
		Assert.assertNotNull( accountsPayable.getDescription() );
		Assert.assertNotNull( accountsPayable.getBankAccount());
		Assert.assertNotNull( accountsPayable.getCategory() );
	}	
	
	
	
}
