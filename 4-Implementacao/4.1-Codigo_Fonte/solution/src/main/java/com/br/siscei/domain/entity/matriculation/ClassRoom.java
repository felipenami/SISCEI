/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "\"classroom\"")
@DataTransferObject(javascript = "Classroom")
public class ClassRoom extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6479818172718422686L;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(nullable = false, length = 144)
	private String name;
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	private Course course;
	/**
	 * 
	 */
	@ManyToOne(fetch=FetchType.EAGER,optional=false, cascade={CascadeType.MERGE, CascadeType.PERSIST} )
	private Discipline discipline;
	/**
	 * 
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StatusClassRoom status;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public ClassRoom()
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
		result = prime * result + ( ( course == null ) ? 0 : course.hashCode() );
		result = prime * result + ( ( discipline == null ) ? 0 : discipline.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
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
		ClassRoom other = ( ClassRoom ) obj;
		if ( course == null )
		{
			if ( other.course != null ) return false;
		}
		else if ( !course.equals( other.course ) ) return false;
		if ( discipline == null )
		{
			if ( other.discipline != null ) return false;
		}
		else if ( !discipline.equals( other.discipline ) ) return false;
		if ( name == null )
		{
			if ( other.name != null ) return false;
		}
		else if ( !name.equals( other.name ) ) return false;
		if ( status != other.status ) return false;
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

	/**
	 * @return the discipline
	 */
	public Discipline getDiscipline()
	{
		return discipline;
	}

	/**
	 * @param discipline the discipline to set
	 */
	public void setDiscipline( Discipline discipline )
	{
		this.discipline = discipline;
	}

	/**
	 * @return the status
	 */
	public StatusClassRoom getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus( StatusClassRoom status )
	{
		this.status = status;
	}
	
}
