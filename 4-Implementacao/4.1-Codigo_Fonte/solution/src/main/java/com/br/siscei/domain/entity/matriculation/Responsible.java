/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"responsible\"")
@DataTransferObject(javascript = "Responsible")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Responsible extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 355233818143852529L;
	/**
	 * 
	 */
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
	@Column(nullable = true, length = 16)
	private String cpf;
	/**
	 * 
	 */
	@Column(nullable = true, length = 25)
	private String rg;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar birthDate;
	/**
	 * 
	 */
	@Column(nullable = false, length = 19)
	private String phone;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Responsible()
	{
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( birthDate == null ) ? 0 : birthDate.hashCode() );
		result = prime * result + ( ( cpf == null ) ? 0 : cpf.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( phone == null ) ? 0 : phone.hashCode() );
		result = prime * result + ( ( rg == null ) ? 0 : rg.hashCode() );
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
		Responsible other = ( Responsible ) obj;
		if ( birthDate == null )
		{
			if ( other.birthDate != null ) return false;
		}
		else if ( !birthDate.equals( other.birthDate ) ) return false;
		if ( cpf == null )
		{
			if ( other.cpf != null ) return false;
		}
		else if ( !cpf.equals( other.cpf ) ) return false;
		if ( name == null )
		{
			if ( other.name != null ) return false;
		}
		else if ( !name.equals( other.name ) ) return false;
		if ( phone == null )
		{
			if ( other.phone != null ) return false;
		}
		else if ( !phone.equals( other.phone ) ) return false;
		if ( rg == null )
		{
			if ( other.rg != null ) return false;
		}
		else if ( !rg.equals( other.rg ) ) return false;
		return true;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the birthDate
	 */
	public Calendar getBirthDate()
	{
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate( Calendar birthDate )
	{
		this.birthDate = birthDate;
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
	 * @return the cpf
	 */
	public String getCpf()
	{
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf( String cpf )
	{
		this.cpf = cpf;
	}
	/**
	 * @return the rg
	 */
	public String getRg()
	{
		return rg;
	}
	/**
	 * @param rg the rg to set
	 */
	public void setRg( String rg )
	{
		this.rg = rg;
	}
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

}
