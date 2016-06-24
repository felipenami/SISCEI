/**
 * 
 */
package com.br.siscei.domain.entity.finance;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import com.br.siscei.domain.entity.address.Address;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"supplier\"")
@DataTransferObject(javascript = "Supplier")
public class Supplier extends AbstractEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7743587586947583168L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false, length = 144)
	private String companyName;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false, length = 144)
	private String tradeName;
	/**
	 * 
	 */
	@Column(nullable = false, length = 15)
	private String phone;
	/**
	 * 
	 */
	@Column(nullable = false, length = 20)
	private String cnpj;
	/**
	 * 
	 */
	@Column(nullable = false, length = 144)
	private String contact;
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private Address address;
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Supplier()
	{
		
	}
	/**
	 * 
	 * @param id
	 */
	public Supplier(Long id)
	{
		super(id);
	}
	
	public Supplier( Long id, String companyName, String tradeName, String phone, String cnpj, String contact)
	{
		super(id);
		this.companyName = companyName;
		this.tradeName = tradeName;
		this.phone = phone;
		this.cnpj = cnpj;
		this.contact = contact;
	}
	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( address == null ) ? 0 : address.hashCode() );
		result = prime * result + ( ( cnpj == null ) ? 0 : cnpj.hashCode() );
		result = prime * result + ( ( companyName == null ) ? 0 : companyName.hashCode() );
		result = prime * result + ( ( contact == null ) ? 0 : contact.hashCode() );
		result = prime * result + ( ( phone == null ) ? 0 : phone.hashCode() );
		result = prime * result + ( ( tradeName == null ) ? 0 : tradeName.hashCode() );
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Supplier other = ( Supplier ) obj;
		if ( address == null )
		{
			if ( other.address != null ) return false;
		}
		else if ( !address.equals( other.address ) ) return false;
		if ( cnpj == null )
		{
			if ( other.cnpj != null ) return false;
		}
		else if ( !cnpj.equals( other.cnpj ) ) return false;
		if ( companyName == null )
		{
			if ( other.companyName != null ) return false;
		}
		else if ( !companyName.equals( other.companyName ) ) return false;
		if ( contact == null )
		{
			if ( other.contact != null ) return false;
		}
		else if ( !contact.equals( other.contact ) ) return false;
		if ( phone == null )
		{
			if ( other.phone != null ) return false;
		}
		else if ( !phone.equals( other.phone ) ) return false;
		if ( tradeName == null )
		{
			if ( other.tradeName != null ) return false;
		}
		else if ( !tradeName.equals( other.tradeName ) ) return false;
		return true;
	}
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the companyName
	 */
	public String getCompanyName()
	{
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName( String companyName )
	{
		this.companyName = companyName;
	}
	/**
	 * @return the tradeName
	 */
	public String getTradeName()
	{
		return tradeName;
	}
	/**
	 * @param tradeName the tradeName to set
	 */
	public void setTradeName( String tradeName )
	{
		this.tradeName = tradeName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone( String phone )
	{
		this.phone = phone;
	}
	/**
	 * @return the cnpj
	 */
	public String getCnpj()
	{
		return cnpj;
	}
	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj( String cnpj )
	{
		this.cnpj = cnpj;
	}
	/**
	 * @return the contact
	 */
	public String getContact()
	{
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact( String contact )
	{
		this.contact = contact;
	}
	/**
	 * @return the address
	 */
	public Address getAddress()
	{
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress( Address address )
	{
		this.address = address;
	}
	
}
