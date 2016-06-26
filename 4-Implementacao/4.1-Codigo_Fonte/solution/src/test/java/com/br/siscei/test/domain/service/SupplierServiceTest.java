/**
 * 
 */
package com.br.siscei.test.domain.service;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.siscei.domain.entity.address.Address;
import com.br.siscei.domain.entity.address.City;
import com.br.siscei.domain.entity.finance.Supplier;
import com.br.siscei.domain.service.AddressService;
import com.br.siscei.domain.service.SupplierService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author felip
 *
 */
public class SupplierServiceTest extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private SupplierService supplierService;
	/**
	 * 
	 */
	@Autowired
	private AddressService addressService;
	/**
	 * 
	 */
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
		final Supplier supplier = this.supplierService.findSupplierById( 9999L );
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
		
		supplier = this.supplierService.insertSupplier( supplier );
		
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
	 * Objetivo: Fail.
	 * Motivo: O objeto {@link Supplier} é instanciado e inserido corretamente
	 */
	@Test(expected = ValidationException.class )
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/address/CountryDataSet.xml",
			"/dataset/address/StateDataSet.xml",
			"/dataset/address/CityDataSet.xml",
			"/dataset/address/AddressDataSet.xml",
			"/dataset/finance/SupplierDataSet.xml",
	})
	public void insertSupplierMustFailWithoutMandatoryFields()
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
		supplier.setCompanyName( null );
		supplier.setContact( "Carlos" );
		supplier.setPhone( "35742162" );
		supplier.setTradeName( null );
		
		supplier = this.supplierService.insertSupplier( supplier );
		
		Assert.fail( "Deveria falhar se os campos estão nulos" );
		
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
	public void updateSupplierMustPass()
	{
		Supplier supplier = this.supplierService.findSupplierById( 9999L );
		Address address = this.addressService.findAddressById( 9999L );
		
		//Adress
		address.setCep( "85851-000" );
		address.setStreet( "Avenida brasil" );
		address.setCity( this.addressService.findCityById( 9998L ) );
		
		//Supplier
		supplier.setAddress( address );
		supplier.setCompanyName( "Teste razão" );
		supplier.setContact( "Felipe Nami" );
		
		supplier = this.supplierService.updateSupplier( supplier );
		
		Assert.assertNotNull( supplier );
		Assert.assertTrue( supplier.getAddress().getCep() == "85851-000" );
		Assert.assertTrue( supplier.getCompanyName() == "Teste razão" );
		Assert.assertTrue( supplier.getContact() == "Felipe Nami" );
		
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
		final Page<Supplier> supplier = this.supplierService.listSuppliersByFilters( null,null );
		
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
		
		final Page<Supplier> supplier = this.supplierService.listSuppliersByFilters( "vision", null );
		
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
		
		final Page<Supplier> supplier = this.supplierService.listSuppliersByFilters( "carlão", null );
		
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
		
		supplier = this.supplierService.insertSupplier( supplier );
		
		Assert.assertNotNull( supplier );
		Assert.assertNotNull( supplier.getId() );
		Assert.assertNotNull( supplier.getAddress() );
		Assert.assertNotNull( supplier.getCnpj() );
		Assert.assertNotNull( supplier.getCompanyName() );
		Assert.assertNotNull( supplier.getContact() );
		Assert.assertNotNull( supplier.getPhone() );
		Assert.assertNotNull( supplier.getTradeName() );
		
		supplier = this.supplierService.findSupplierById( supplier.getId() );
		Assert.assertNotNull( supplier );
		
		this.supplierService.removeSupplier( supplier.getId() );
		this.supplierService.findSupplierById( supplier.getId() );
		
	}
}
