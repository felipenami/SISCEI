/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import com.br.siscei.domain.entity.account.User;
import com.br.siscei.domain.entity.address.Address;

/**
 * @author felip
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "Student")
public class Student extends Responsible
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5661014065274649629L; 
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private User user;
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private Address address;
	/**
	 * 
	 */
	@Column(nullable = false)
	private String motherName;
	/**
	 * 
	 */
	@Column(nullable = false)
	private String fatherName;
	/**
	 * 
	 */
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Student()
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
		result = prime * result + ( ( address == null ) ? 0 : address.hashCode() );
		result = prime * result + ( ( fatherName == null ) ? 0 : fatherName.hashCode() );
		result = prime * result + ( ( motherName == null ) ? 0 : motherName.hashCode() );
		result = prime * result + ( ( user == null ) ? 0 : user.hashCode() );
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
		Student other = ( Student ) obj;
		if ( address == null )
		{
			if ( other.address != null ) return false;
		}
		else if ( !address.equals( other.address ) ) return false;
		if ( fatherName == null )
		{
			if ( other.fatherName != null ) return false;
		}
		else if ( !fatherName.equals( other.fatherName ) ) return false;
		if ( motherName == null )
		{
			if ( other.motherName != null ) return false;
		}
		else if ( !motherName.equals( other.motherName ) ) return false;
		if ( user == null )
		{
			if ( other.user != null ) return false;
		}
		else if ( !user.equals( other.user ) ) return false;
		return true;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser( User user )
	{
		this.user = user;
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

	/**
	 * @return the motherName
	 */
	public String getMotherName()
	{
		return motherName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName( String motherName )
	{
		this.motherName = motherName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName()
	{
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName( String fatherName )
	{
		this.fatherName = fatherName;
	}

	
}
