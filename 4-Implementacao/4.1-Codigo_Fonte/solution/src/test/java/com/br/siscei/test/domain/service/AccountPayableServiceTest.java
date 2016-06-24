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

import com.br.siscei.domain.entity.finance.AccountPayable;
import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.entity.finance.StatusAccountPayable;
import com.br.siscei.domain.entity.finance.Supplier;
import com.br.siscei.domain.service.AccountPayableService;
import com.br.siscei.domain.service.BankAccountService;
import com.br.siscei.domain.service.CategoryService;
import com.br.siscei.domain.service.SupplierService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author felip
 *
 */
public class AccountPayableServiceTest extends AbstractIntegrationTests
{
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private AccountPayableService accountPayableService;
	/**
	 * 
	 */
	@Autowired
	private SupplierService supplierService;
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
	 *				    ACCOUNTPAYABLE  TESTS
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
			"/dataset/finance/SupplierDataSet.xml",
			"/dataset/finance/AccountPayableDataSet.xml",
		})
	public void findAccountPayableByIdMustPass()
	{
		final AccountPayable accountsPayable = this.accountPayableService.findAccountPayableById( 9999L );
		Assert.assertNotNull(accountsPayable);
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
			"/dataset/finance/SupplierDataSet.xml",
			"/dataset/finance/AccountPayableDataSet.xml",
		})
	public void insertAccountPayableMustPass()
	{
		final Category category = this.categoryService.findCategoryById( 9997L );
		final BankAccount bankAccount = this.bankAccountService.findBankAccountById( 9997L );
		final Supplier supplier = this.supplierService.findSupplierById( 9999L );
		
		final Calendar dueDate = new GregorianCalendar(2050,9,5, 11,00,00);
		final Calendar entryDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final Calendar paymentDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final BigDecimal valor = new BigDecimal("1.5");
		
		AccountPayable accountsPayable = new AccountPayable( );
		
		accountsPayable.setSupplier( supplier );
		accountsPayable.setBankAccount( bankAccount );
		accountsPayable.setCategory( category );
		accountsPayable.setDescription( "Teste 1" );
		accountsPayable.setValue( valor );
		accountsPayable.setDueDate( dueDate );
		accountsPayable.setEntryDate( entryDate );
		accountsPayable.setPaymentDate( paymentDate );
		accountsPayable.setStatus( StatusAccountPayable.NOT_PAID );
		
		accountsPayable = this.accountPayableService.insertAccountPayable( accountsPayable );

		Assert.assertNotNull( accountsPayable );
		Assert.assertNotNull( accountsPayable.getId() );
		Assert.assertNotNull( accountsPayable.getSupplier() );
		Assert.assertNotNull( accountsPayable.getBankAccount());
		Assert.assertNotNull( accountsPayable.getCategory() );
		Assert.assertNotNull( accountsPayable.getDescription() );
		Assert.assertNotNull( accountsPayable.getValue() );
		Assert.assertNotNull( accountsPayable.getDueDate() );
		Assert.assertNotNull( accountsPayable.getEntryDate() );
		Assert.assertNotNull( accountsPayable.getPaymentDate() );
		Assert.assertNotNull( accountsPayable.getStatus() );
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
			"/dataset/finance/SupplierDataSet.xml",
			"/dataset/finance/AccountPayableDataSet.xml",
		})
	public void updateAccountPayableMustPass()
	{
		AccountPayable accountPayable = this.accountPayableService.findAccountPayableById( 9999L );
		Calendar entryDate = new GregorianCalendar(2050,9,5,11,00,00);
		BigDecimal value = new BigDecimal("900.00");
		
		accountPayable.setEntryDate( entryDate );
		accountPayable.setValue( value );
		accountPayable.setDescription( "Conta a pagar teste" );
		
		accountPayable = this.accountPayableService.updateAccountPayable( accountPayable );
		
		Assert.assertNotNull( accountPayable );
		Assert.assertEquals( accountPayable.getValue(), new BigDecimal("900.00") );
		Assert.assertEquals( accountPayable.getEntryDate(), new GregorianCalendar(2050,9,5,11,00,00) );
		Assert.assertTrue( accountPayable.getDescription() == "Conta a pagar teste" );
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
			"/dataset/finance/SupplierDataSet.xml",
			"/dataset/finance/AccountPayableDataSet.xml",
		})
	public void listAllAccountsPayableMustPass()
	{
		final Page<AccountPayable> accountsPayable = this.accountPayableService.listAccountsPayableByFilters( null, null );
		
		Assert.assertNotNull(accountsPayable);
		Assert.assertTrue(accountsPayable.getContent().size() == 3 );
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
			"/dataset/finance/SupplierDataSet.xml",
			"/dataset/finance/AccountPayableDataSet.xml",
	})
	public void listAccountsPayableMustReturn1()
	{
		final Page<AccountPayable> accountsPayable = this.accountPayableService.listAccountsPayableByFilters( "carlão", null );
		
		Assert.assertNotNull(accountsPayable);
		Assert.assertTrue(accountsPayable.getContent().size() == 1 );
	}
}
