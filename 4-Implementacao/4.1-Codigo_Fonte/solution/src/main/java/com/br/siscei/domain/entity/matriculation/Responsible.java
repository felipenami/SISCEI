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

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"student\"")
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
	@Column(nullable = true, length = 16)
	private String CPF;
	/**
	 * 
	 */
	@Column(nullable = true, length = 25)
	private String RG;
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
		result = prime * result + ( ( CPF == null ) ? 0 : CPF.hashCode() );
		result = prime * result + ( ( RG == null ) ? 0 : RG.hashCode() );
		result = prime * result + ( ( birthDate == null ) ? 0 : birthDate.hashCode() );
		result = prime * result + ( ( phone == null ) ? 0 : phone.hashCode() );
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
		if ( CPF == null )
		{
			if ( other.CPF != null ) return false;
		}
		else if ( !CPF.equals( other.CPF ) ) return false;
		if ( RG == null )
		{
			if ( other.RG != null ) return false;
		}
		else if ( !RG.equals( other.RG ) ) return false;
		if ( birthDate == null )
		{
			if ( other.birthDate != null ) return false;
		}
		else if ( !birthDate.equals( other.birthDate ) ) return false;
		if ( phone == null )
		{
			if ( other.phone != null ) return false;
		}
		else if ( !phone.equals( other.phone ) ) return false;
		return true;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the cPF
	 */
	public String getCPF()
	{
		return CPF;
	}
	/**
	 * @param cPF the cPF to set
	 */
	public void setCPF( String cPF )
	{
		CPF = cPF;
	}
	/**
	 * @return the rG
	 */
	public String getRG()
	{
		return RG;
	}
	/**
	 * @param rG the rG to set
	 */
	public void setRG( String rG )
	{
		RG = rG;
	}
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

}
