/**
 * 
 */
package com.br.siscei.domain.entity.finance;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 *
 */
@Entity
@Audited
@Table(name = "\"bankAccount\"")
@DataTransferObject(javascript = "BankAccount")
public class BankAccount extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5485794424064855189L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String name;
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 500)
	private String description;
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private BigDecimal balance;
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public BankAccount()
	{
		
	}
	/**
	 * 
	 * @param id
	 */
	public BankAccount( Long id )
	{
		super(id);
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
		result = prime * result + ( ( balance == null ) ? 0 : balance.hashCode() );
		result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
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
		BankAccount other = ( BankAccount ) obj;
		if ( balance == null )
		{
			if ( other.balance != null ) return false;
		}
		else if ( !balance.equals( other.balance ) ) return false;
		if ( description == null )
		{
			if ( other.description != null ) return false;
		}
		else if ( !description.equals( other.description ) ) return false;
		if ( name == null )
		{
			if ( other.name != null ) return false;
		}
		else if ( !name.equals( other.name ) ) return false;
		return true;
	}
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription( String description )
	{
		this.description = description;
	}
	/**
	 * @return the balance
	 */
	public BigDecimal getBalance()
	{
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance( BigDecimal balance )
	{
		this.balance = balance;
	}
	
}
