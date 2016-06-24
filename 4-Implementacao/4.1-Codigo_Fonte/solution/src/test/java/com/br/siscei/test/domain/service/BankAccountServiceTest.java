/**
 * 
 */
package com.br.siscei.test.domain.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.siscei.domain.entity.finance.BankAccount;
import com.br.siscei.domain.service.BankAccountService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author felip
 *
 */
public class BankAccountServiceTest extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private BankAccountService bankAccountService;
	/**
	 * 
	 */
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
		final BankAccount bankAccount = this.bankAccountService.findBankAccountById( 9999L );
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
		
		bankAccount  = this.bankAccountService.insertBankAccount( bankAccount  );
		
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
		
		BankAccount bankAccount = this.bankAccountService.findBankAccountById( 9999L );
		
		final BigDecimal balance = new BigDecimal("1.500");
		
		bankAccount.setName( "HSBC2" );
		bankAccount.setDescription( "Conta pessoal2" );
		bankAccount.setBalance( balance );
		
		bankAccount  = this.bankAccountService.insertBankAccount( bankAccount  );
		
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
		
		bankAccount  = this.bankAccountService.insertBankAccount( bankAccount  );
		
		Assert.assertNotNull(bankAccount );
		Assert.assertNotNull(bankAccount.getId());
		Assert.assertNotNull(bankAccount.getDescription());
		Assert.assertNotNull(bankAccount.getName());
		Assert.assertNotNull(bankAccount.getBalance());
		
		
		bankAccount = this.bankAccountService.findBankAccountById( 9999L );
		Assert.assertNotNull(bankAccount);
		
		this.bankAccountService.removeBankAccount( bankAccount.getId() );
		this.bankAccountService.findBankAccountById( bankAccount.getId() );
		
		
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
		final Page<BankAccount> bankAccount = this.bankAccountService.listBankAccountsByFilters( null, null );
		
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
		final Page<BankAccount> bankAccount = this.bankAccountService.listBankAccountsByFilters( "HSBC", null );
		
		Assert.assertNotNull(bankAccount);
		Assert.assertTrue(bankAccount.getContent().size() == 1 );		
	}
}
