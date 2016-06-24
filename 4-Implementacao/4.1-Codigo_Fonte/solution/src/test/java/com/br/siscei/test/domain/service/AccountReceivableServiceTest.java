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
import org.springframework.data.domain.Page;

import com.br.siscei.domain.entity.account.User;
import com.br.siscei.domain.entity.finance.AccountPayable;
import com.br.siscei.domain.entity.finance.AccountReceivable;
import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.entity.finance.StatusAccountsReceivable;
import com.br.siscei.domain.service.AccountReceivableService;
import com.br.siscei.domain.service.AccountService;
import com.br.siscei.domain.service.BankAccountService;
import com.br.siscei.domain.service.CategoryService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author felip
 *
 */
public class AccountReceivableServiceTest extends AbstractIntegrationTests
{ 
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private AccountReceivableService accountReceivableService;
	/**
	 * 
	 */
	@Autowired
	private AccountService accountService;
	/**
	 * 
	 */
	@Autowired
	private BankAccountService bankAccountService;
	/**
	 * 
	 */
	@Autowired
	private CategoryService categoryService;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				    ACCOUNTRECEIVABLE  TESTS
	 *-------------------------------------------------------------------*/
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/BankAccountDataSet.xml",
			"/dataset/finance/CategoryDataSet.xml",
			"/dataset/account/UserDataSet.xml",
			"/dataset/finance/AccountReceivableDataSet.xml",
		})
	public void findAccountReceivableByIdMustPass()
	{
		final AccountReceivable accountReceivable = this.accountReceivableService.findAccountReceivableById( 9999L );
		Assert.assertNotNull(accountReceivable);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link AccountPayable} é instanciado e inserido corretamente
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/BankAccountDataSet.xml",
			"/dataset/finance/CategoryDataSet.xml",
			"/dataset/account/UserDataSet.xml",
			"/dataset/finance/AccountReceivableDataSet.xml",
		})
	public void insertAccountReceivableMustPass()
	{
		final Category category = this.categoryService.findCategoryById( 9997L );
		final BankAccount bankAccount = this.bankAccountService.findBankAccountById( 9997L );
		final User student = this.accountService.findUserById( 9999L );
		
		final Calendar dueDate = new GregorianCalendar(2050,9,5, 11,00,00);
		final Calendar entryDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final Calendar receivementDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final BigDecimal valor = new BigDecimal("1.5");
		
		AccountReceivable accountReceivable = new AccountReceivable( );
		
		accountReceivable.setStudent( student );
		accountReceivable.setBankAccount( bankAccount );
		accountReceivable.setCategory( category );
		accountReceivable.setDescription( "Teste 1" );
		accountReceivable.setValue( valor );
		accountReceivable.setDueDate( dueDate );
		accountReceivable.setEntryDate( entryDate );
		accountReceivable.setReceivementDate( receivementDate );
		accountReceivable.setStatus( StatusAccountsReceivable.NOT_RECEIVED);
		
		accountReceivable = this.accountReceivableService.insertAccountReceivable( accountReceivable );

		Assert.assertNotNull( accountReceivable );
		Assert.assertNotNull( accountReceivable.getId() );
		Assert.assertNotNull( accountReceivable.getStudent() );
		Assert.assertNotNull( accountReceivable.getBankAccount());
		Assert.assertNotNull( accountReceivable.getCategory() );
		Assert.assertNotNull( accountReceivable.getDescription() );
		Assert.assertNotNull( accountReceivable.getValue() );
		Assert.assertNotNull( accountReceivable.getDueDate() );
		Assert.assertNotNull( accountReceivable.getEntryDate() );
		Assert.assertNotNull( accountReceivable.getReceivementDate());
		Assert.assertNotNull( accountReceivable.getStatus() );
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link AccountPayable} é instanciado e inserido corretamente
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/BankAccountDataSet.xml",
			"/dataset/finance/CategoryDataSet.xml",
			"/dataset/account/UserDataSet.xml",
			"/dataset/finance/AccountReceivableDataSet.xml",
		})
	public void updateAccountReceivableMustPass()
	{
		AccountReceivable accountReceivable = this.accountReceivableService.findAccountReceivableById( 9999L );
		Calendar entryDate = new GregorianCalendar(2050,9,5,11,00,00);
		BigDecimal value = new BigDecimal("900.00");
		
		accountReceivable.setEntryDate( entryDate );
		accountReceivable.setValue( value );
		accountReceivable.setDescription( "Conta a receber teste" );
		
		accountReceivable = this.accountReceivableService.updateAccountReceivable( accountReceivable );
		
		Assert.assertNotNull( accountReceivable );
		Assert.assertEquals( accountReceivable.getValue(), new BigDecimal("900.00") );
		Assert.assertEquals( accountReceivable.getEntryDate(), new GregorianCalendar(2050,9,5,11,00,00) );
		Assert.assertTrue( accountReceivable.getDescription() == "Conta a receber teste" );
	}
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/BankAccountDataSet.xml",
			"/dataset/finance/CategoryDataSet.xml",
			"/dataset/account/UserDataSet.xml",
			"/dataset/finance/AccountReceivableDataSet.xml",
		})
	public void listAllAccountsReceivableMustPass()
	{
		final Page<AccountReceivable> accountReceivable = this.accountReceivableService.listAccountsReceivableByFilters( null, null );
		
		Assert.assertNotNull(accountReceivable);
		Assert.assertTrue(accountReceivable.getContent().size() == 3 );
	}
	/**
	 * Objetivo: Success.
	 * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/BankAccountDataSet.xml",
			"/dataset/finance/CategoryDataSet.xml",
			"/dataset/account/UserDataSet.xml",
			"/dataset/finance/AccountReceivableDataSet.xml",
	})
	public void listAccountsReceivableMustReturn1()
	{
		final Page<AccountReceivable> accountReceivable = this.accountReceivableService.listAccountsReceivableByFilters( "carlão", null );
		
		Assert.assertNotNull(accountReceivable);
		Assert.assertTrue(accountReceivable.getContent().size() == 1 );
	}
	
}
