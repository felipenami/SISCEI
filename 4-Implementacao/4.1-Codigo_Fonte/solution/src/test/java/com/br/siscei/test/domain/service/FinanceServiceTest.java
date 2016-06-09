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
import com.br.siscei.domain.entity.address.City;
import com.br.siscei.domain.entity.finance.AccountPayable;
import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.entity.finance.StatusAccountPayable;
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
			"/dataset/finance/AccountPayableDataSet.xml",
		})
	public void findAccountsPayableByIdMustPass()
	{
		final AccountPayable accountsPayable = this.financeService.findAccountPayableById( 9999L );
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
	public void insertAccountsPayableMustPass()
	{
		final Category category = this.financeService.findCategoryById( 9997L );
		final BankAccount bankAccount = this.financeService.findBankAccountById( 9997L );
		final Supplier supplier = this.financeService.findSupplierById( 9999L );
		
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
		
		accountsPayable = this.financeService.insertAccountPayable( accountsPayable );

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
			"/dataset/finance/AccountPayableDataSet.xml",
		})
	public void listAllAccountsPayableMustPass()
	{
		final Page<AccountPayable> accountsPayable = this.financeService.listAccountsPayableByFilters( null, null, null );
		
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
		final City city = this.addressService.findCityById( 9999L );
		
		Address address = new Address();
		address.setCep( "85851-010" );
		address.setCity( city );
		address.setNeighborhood( "Centro" );
		address.setStreet( "Rua Almirante barroso ");
		
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
		final Page<Supplier> supplier = this.financeService.listSuppliersByFilters( null,null );
		
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
		
		final Page<Supplier> supplier = this.financeService.listSuppliersByFilters( "vision", null );
		
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
		
		final Page<Supplier> supplier = this.financeService.listSuppliersByFilters( "carlão", null );
		
		Assert.assertNotNull(supplier);
		Assert.assertTrue(supplier.getContent().size() == 3 );
	}	

	/**
     * Objetivo: Fail.
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
		final City city = this.addressService.findCityById( 9999L );
		
		Address address = new Address();
		address.setCep( "85851-010" );
		address.setCity( city );
		address.setNeighborhood( "Centro" );
		address.setStreet( "Rua Almirante barroso ");
		
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
	
	/*-------------------------------------------------------------------
	 *				 		    BANK_ACCOUNT TESTS
	 *-------------------------------------------------------------------*/	
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/finance/BankAccountDataSet.xml",
		})
	public void findBankAccountByIdMustPass()
	{
		final BankAccount bankAccount = this.financeService.findBankAccountById( 9999L );
		Assert.assertNotNull(bankAccount);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link BankAccount} é instanciado e inserido corretamente
     */
	@Test
	public void insertBankAccountMustPass()
	{
		
		final BigDecimal balance = new BigDecimal("1.5");
		
		BankAccount bankAccount = new BankAccount();
		
		bankAccount.setName( "HSBC" );
		bankAccount.setDescription( "Conta pessoal" );
		bankAccount.setBalance( balance );
		
		bankAccount  = this.financeService.insertBankAccount( bankAccount  );
		
		Assert.assertNotNull(bankAccount );
		Assert.assertNotNull(bankAccount.getId());
		Assert.assertNotNull(bankAccount.getDescription());
		Assert.assertNotNull(bankAccount.getName());
		Assert.assertNotNull(bankAccount.getBalance());
		
	}
	/**
	 * Objetivo: Success.
	 * Motivo: O objeto {@link BankAccount} é instanciado e inserido corretamente
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/finance/BankAccountDataSet.xml",
		})
	public void updateBankAccountMustPass()
	{
		
		BankAccount bankAccount = this.financeService.findBankAccountById( 9999L );
		
		final BigDecimal balance = new BigDecimal("1.500");
		
		bankAccount.setName( "HSBC2" );
		bankAccount.setDescription( "Conta pessoal2" );
		bankAccount.setBalance( balance );
		
		bankAccount  = this.financeService.insertBankAccount( bankAccount  );
		
		Assert.assertNotNull(bankAccount );
		Assert.assertNotNull(bankAccount.getId());
		Assert.assertTrue(bankAccount.getDescription() == "Conta pessoal2");
		Assert.assertTrue(bankAccount.getName() == "HSBC2");
		Assert.assertTrue(bankAccount.getBalance() == balance );
		
	}
	/**
     * Objetivo: Fail.
     * Motivo: O objeto {@link BankAccount} é instanciado e inserido, e removido corretamente
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/finance/BankAccountDataSet.xml",
		})
	public void removeBankAccountMustPass()
	{
		
		final BigDecimal balance = new BigDecimal("1.5");
		
		BankAccount bankAccount = new BankAccount();
		
		bankAccount.setName( "HSBC" );
		bankAccount.setDescription( "Conta pessoal" );
		bankAccount.setBalance( balance );
		
		bankAccount  = this.financeService.insertBankAccount( bankAccount  );
		
		Assert.assertNotNull(bankAccount );
		Assert.assertNotNull(bankAccount.getId());
		Assert.assertNotNull(bankAccount.getDescription());
		Assert.assertNotNull(bankAccount.getName());
		Assert.assertNotNull(bankAccount.getBalance());
		
		
		bankAccount = this.financeService.findBankAccountById( 9999L );
		Assert.assertNotNull(bankAccount);
		
		this.financeService.removeBankAccount( bankAccount.getId() );
		this.financeService.findBankAccountById( bankAccount.getId() );
		
		
	}
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/finance/BankAccountDataSet.xml",
		})
	public void listAllBankAccountMustPass()
	{
		final Page<BankAccount> bankAccount = this.financeService.listBankAccountsByFilters( null, null );
		
		Assert.assertNotNull(bankAccount);
		Assert.assertTrue(bankAccount.getContent().size() == 3 );		
	}
	/**
	 * Objetivo: Success.
	 * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/finance/BankAccountDataSet.xml",
	})
	public void list1BankAccountMustPass()
	{
		final Page<BankAccount> bankAccount = this.financeService.listBankAccountsByFilters( "HSBC", null );
		
		Assert.assertNotNull(bankAccount);
		Assert.assertTrue(bankAccount.getContent().size() == 1 );		
	}
	
}
