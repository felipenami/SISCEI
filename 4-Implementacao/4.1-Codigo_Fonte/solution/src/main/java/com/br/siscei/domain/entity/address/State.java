/**
 * 
 */
package com.br.siscei.domain.entity.address;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"state\"")
@DataTransferObject(javascript = "State")
public class State extends AbstractEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3259328987710038100L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(nullable = false, length = 300)
	private String name;
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER )
	private Country country;
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	public State ()
	{
		
	}
	/**
	 * 
	 */
	public State( Long id )
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
		result = prime * result + ( ( country == null ) ? 0 : country.hashCode() );
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
		State other = ( State ) obj;
		if ( country == null )
		{
			if ( other.country != null ) return false;
		}
		else if ( !country.equals( other.country ) ) return false;
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
	 * @return the country
	 */
	public Country getCountry()
	{
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry( Country country )
	{
		this.country = country;
	}
}
