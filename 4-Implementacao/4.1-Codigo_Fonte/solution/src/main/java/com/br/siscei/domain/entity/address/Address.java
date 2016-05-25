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
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"address\"")
@DataTransferObject(javascript = "Address")
public class Address extends AbstractEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3242858159131831920L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	@NotEmpty
	@Column(nullable = false, length = 144)
	private String neighborhood;
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 144)
	private String street;
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 144)
	private String cep;
	/**
	 * 
	 */
	@Column(nullable = true, length = 8)
	private Short number;
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER )
	private City city;
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Address()
	{
		
	}
	/**
	 * 
	 * @param id
	 */
	public Address(Long id)
	{
		super(id);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( city == null ) ? 0 : city.hashCode() );
		result = prime * result + ( ( neighborhood == null ) ? 0 : neighborhood.hashCode() );
		result = prime * result + ( ( number == null ) ? 0 : number.hashCode() );
		result = prime * result + ( ( street == null ) ? 0 : street.hashCode() );
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
		Address other = ( Address ) obj;
		if ( cep == null )
		{
			if ( other.cep != null ) return false;
		}
		else if ( !cep.equals( other.cep ) ) return false;
		if ( city == null )
		{
			if ( other.city != null ) return false;
		}
		else if ( !city.equals( other.city ) ) return false;
		if ( neighborhood == null )
		{
			if ( other.neighborhood != null ) return false;
		}
		else if ( !neighborhood.equals( other.neighborhood ) ) return false;
		if ( number == null )
		{
			if ( other.number != null ) return false;
		}
		else if ( !number.equals( other.number ) ) return false;
		if ( street == null )
		{
			if ( other.street != null ) return false;
		}
		else if ( !street.equals( other.street ) ) return false;
		return true;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood()
	{
		return neighborhood;
	}
	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood( String neighborhood )
	{
		this.neighborhood = neighborhood;
	}
	/**
	 * @return the street
	 */
	public String getStreet()
	{
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet( String street )
	{
		this.street = street;
	}
	/**
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}
	/**
	 * @param cep the cep to set
	 */
	public void setCep( String cep )
	{
		this.cep = cep;
	}
	/**
	 * @return the city
	 */
	public City getCity()
	{
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity( City city )
	{
		this.city = city;
	}
	/**
	 * @return the number
	 */
	public Short getNumber()
	{
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber( Short number )
	{
		this.number = number;
	}
}
