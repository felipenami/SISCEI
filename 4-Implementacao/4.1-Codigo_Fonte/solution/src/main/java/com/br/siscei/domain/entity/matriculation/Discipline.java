/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"discipline\"")
@DataTransferObject(javascript = "Discipline")
public class Discipline extends AbstractEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2335347246833082118L;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(nullable = false, length = 60)
	private String name;
	/**
	 * 
	 */
	@Column(nullable = false, length = 144)
	private String description;
	/**
	 * 
	 */
	@ManyToOne(fetch=FetchType.EAGER,optional=false, cascade={CascadeType.PERSIST} )
	private Course course;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Discipline()
	{
		
	}
	/**
	 * 
	 * @param id
	 * @param name
	 * @param description
	 */
	public Discipline(String name, String description)
	{
		this.name			= name;
		this.description	= description;
	}
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param course
	 */
	public Discipline(Long id, String name, String description, Course course)
	{
		super(id);
		this.name			= name;
		this.description	= description;
		this.course			= course;
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
		result = prime * result + ( ( course == null ) ? 0 : course.hashCode() );
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
		Discipline other = ( Discipline ) obj;
		if ( course == null )
		{
			if ( other.course != null ) return false;
		}
		else if ( !course.equals( other.course ) ) return false;
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
	 * @return the course
	 */
	public Course getCourse()
	{
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse( Course course )
	{
		this.course = course;
	}

}
