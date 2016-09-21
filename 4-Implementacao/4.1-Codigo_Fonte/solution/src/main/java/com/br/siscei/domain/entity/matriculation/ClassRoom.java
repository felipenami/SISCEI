/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	@NotEmpty
	@Column(nullable = false, length = 60)
	private String name;
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER )
	private Course course;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StatusClassRoom status;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar schedule;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public ClassRoom()
	{
		
	}
	public ClassRoom(Long id, String name, Calendar schedule, StatusClassRoom status, Course course)
	{
		super(id);
		this.name		= name;
		this.schedule	= schedule;
		this.status		= status;
		this.course		= course;
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
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( schedule == null ) ? 0 : schedule.hashCode() );
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
		if ( name == null )
		{
			if ( other.name != null ) return false;
		}
		else if ( !name.equals( other.name ) ) return false;
		if ( schedule == null )
		{
			if ( other.schedule != null ) return false;
		}
		else if ( !schedule.equals( other.schedule ) ) return false;
		if ( status != other.status ) return false;
		return true;
	};
	
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
	/**
	 * @return the schedule
	 */
	public Calendar getSchedule()
	{
		return schedule;
	}
	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule( Calendar schedule )
	{
		this.schedule = schedule;
	}
	
}
