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

import com.br.siscei.domain.entity.address.Address;
import com.br.siscei.domain.entity.finance.AccountsPayable;
import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.entity.finance.StatusAccountsPayable;
import com.br.siscei.domain.entity.finance.Supplier;
import com.br.siscei.domain.service.AddressService;
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
	/**
	 * 
	 */
	@Autowired
	private AddressService addressService;
	
	
	/*-------------------------------------------------------------------
	 *				    ACCOUNTSPAYABLE  TESTS
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
			"/dataset/finance/AccountsPayableDataSet.xml",
		})
	public void findAccountsPayableByIdMustPass()
	{
		final AccountsPayable accountsPayable = this.financeService.findAccountsPayableById( 9999L );
		Assert.assertNotNull(accountsPayable);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link AccountsPayable} é instanciado e inserido corretamente
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
			"/dataset/finance/AccountsPayableDataSet.xml",
		})
	public void insertAccountsPayableMustPass()
	{
		final Category category = this.financeService.findCategoryById( 9997L );
		final BankAccount bankAccount = this.financeService.findBankAccountById( 9997L );
		final Supplier supplier = this.financeService.findSupplierById( 9999L );
		
		final Calendar dueDate = new GregorianCalendar(2050,9,5, 11,00,00);
		final Calendar entryDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final Calendar paymentDate = new GregorianCalendar(2050,9,5, 12,00,00);
		final BigDecimal valor = new BigDecimal("1.5");
		
		AccountsPayable accountsPayable = new AccountsPayable( );
		
		accountsPayable.setSupplier( supplier );
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
			"/dataset/finance/AccountsPayableDataSet.xml",
		})
	public void listAllAccountsPayableMustPass()
	{
		final Page<AccountsPayable> accountsPayable = this.financeService.listAccountsPayableByFilters( null, null, null );
		
		Assert.assertNotNull(accountsPayable);
		Assert.assertTrue(accountsPayable.getContent().size() == 3 );
	}
	
	/*-------------------------------------------------------------------
	 *				 		    SUPPLIER TESTS
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
			"/dataset/finance/SupplierDataSet.xml",
		})
	public void findSupplierByIdMustPass()
	{
		final Supplier supplier = this.financeService.findSupplierById( 9999L );
		Assert.assertNotNull(supplier);
	}	
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link Supplier} é instanciado e inserido corretamente
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/SupplierDataSet.xml",
		})
	public void insertSupplierMustPass()
	{
		final Address address = this.addressService.findAddressById( 9999L );
		
		Supplier supplier = new Supplier();
		
		supplier.setAddress( address );
		supplier.setCnpj( "12.012.411/0001-24" );
		supplier.setCompanyName( "Nami ltda" );
		supplier.setContact( "Carlos" );
		supplier.setPhone( "35742162" );
		supplier.setTradeName( "vision informática" );
		
		supplier = this.financeService.insertSupplier( supplier );
		
		Assert.assertNotNull( supplier );
		Assert.assertNotNull( supplier.getId() );
		Assert.assertNotNull( supplier.getAddress() );
		Assert.assertNotNull( supplier.getCnpj() );
		Assert.assertNotNull( supplier.getCompanyName() );
		Assert.assertNotNull( supplier.getContact() );
		Assert.assertNotNull( supplier.getPhone() );
		Assert.assertNotNull( supplier.getTradeName() );
		
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
			"/dataset/finance/SupplierDataSet.xml",
		})
	public void listAllSuppliersMustPass()
	{
		final Page<Supplier> supplier = this.financeService.listSupplierByFilters( null,null );
		
		Assert.assertNotNull(supplier);
		Assert.assertTrue(supplier.getContent().size() == 3 );
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
			"/dataset/finance/SupplierDataSet.xml",
		})
	public void list1SupplierMustPass()
	{
		
		final Page<Supplier> supplier = this.financeService.listSupplierByFilters( "vision", null );
		
		Assert.assertNotNull(supplier);
		Assert.assertTrue(supplier.getContent().size() == 1 );
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
			"/dataset/finance/SupplierDataSet.xml",
	})
	public void list3SupplierMustPass()
	{
		
		final Page<Supplier> supplier = this.financeService.listSupplierByFilters( "carlão", null );
		
		Assert.assertNotNull(supplier);
		Assert.assertTrue(supplier.getContent().size() == 3 );
	}	

	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link Supplier} é removido com sucesso.
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/SupplierDataSet.xml",
		})
	public void removeSupplierMustPass()
	{
		final Address address = this.addressService.findAddressById( 9999L );
		
		Supplier supplier = new Supplier();
		
		supplier.setAddress( address );
		supplier.setCnpj( "12.012.411/0001-24" );
		supplier.setCompanyName( "Nami ltda" );
		supplier.setContact( "Carlos" );
		supplier.setPhone( "35742162" );
		supplier.setTradeName( "vision informática" );
		
		supplier = this.financeService.insertSupplier( supplier );
		
		Assert.assertNotNull( supplier );
		Assert.assertNotNull( supplier.getId() );
		Assert.assertNotNull( supplier.getAddress() );
		Assert.assertNotNull( supplier.getCnpj() );
		Assert.assertNotNull( supplier.getCompanyName() );
		Assert.assertNotNull( supplier.getContact() );
		Assert.assertNotNull( supplier.getPhone() );
		Assert.assertNotNull( supplier.getTradeName() );
		
		supplier = this.financeService.findSupplierById( supplier.getId() );
		Assert.assertNotNull( supplier );
		this.financeService.removeSupplier( supplier.getId() );
		this.financeService.findSupplierById( supplier.getId() );
		
	}
	
}
