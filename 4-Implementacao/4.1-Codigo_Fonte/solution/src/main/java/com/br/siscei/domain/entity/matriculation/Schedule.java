/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import java.util.Calendar;

import javax.persistence.CascadeType;
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

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"schedule\"")
@DataTransferObject(javascript = "Schedule")
public class Schedule extends AbstractEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5016970616816569724L;
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
	@Column(nullable = false)
	private String endHour;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String beginHour;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ScheduleWeek  weekDay;
	/**
	 * 
	 */
	@ManyToOne(fetch=FetchType.EAGER,optional=false, cascade={CascadeType.PERSIST} )
	private Classroom classroom;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Schedule()
	{
		
	}
	/**
	 * 
	 * @param id
	 * @param beginHour
	 * @param endHour
	 * @param weekDay
	 */
	public Schedule(Long id, String beginHour, String endHour, ScheduleWeek weekDay)
	{
		super(id);
		this.beginHour	=	beginHour;
		this.endHour	=	endHour;
		this.weekDay	=	weekDay;
	}
	/**
	 * 
	 * @param beginHour
	 * @param endHour
	 * @param weekDay
	 */
	public Schedule( String beginHour, String endHour, ScheduleWeek weekDay)
	{
		this.beginHour	=	beginHour;
		this.endHour	=	endHour;
		this.weekDay	=	weekDay;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( beginHour == null ) ? 0 : beginHour.hashCode() );
		result = prime * result + ( ( classroom == null ) ? 0 : classroom.hashCode() );
		result = prime * result + ( ( endHour == null ) ? 0 : endHour.hashCode() );
		result = prime * result + ( ( weekDay == null ) ? 0 : weekDay.hashCode() );
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
		Schedule other = ( Schedule ) obj;
		if ( beginHour == null )
		{
			if ( other.beginHour != null ) return false;
		}
		else if ( !beginHour.equals( other.beginHour ) ) return false;
		if ( classroom == null )
		{
			if ( other.classroom != null ) return false;
		}
		else if ( !classroom.equals( other.classroom ) ) return false;
		if ( endHour == null )
		{
			if ( other.endHour != null ) return false;
		}
		else if ( !endHour.equals( other.endHour ) ) return false;
		if ( weekDay != other.weekDay ) return false;
		return true;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the endHour
	 */
	public String getEndHour()
	{
		return endHour;
	}
	/**
	 * @param endHour the endHour to set
	 */
	public void setEndHour( String endHour )
	{
		this.endHour = endHour;
	}
	/**
	 * @return the beginHour
	 */
	public String getBeginHour()
	{
		return beginHour;
	}
	/**
	 * @param beginHour the beginHour to set
	 */
	public void setBeginHour( String beginHour )
	{
		this.beginHour = beginHour;
	}
	/**
	 * @return the weekDay
	 */
	public ScheduleWeek getWeekDay()
	{
		return weekDay;
	}
	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay( ScheduleWeek weekDay )
	{
		this.weekDay = weekDay;
	}
	/**
	 * @return the classroom
	 */
	public Classroom getClassroom()
	{
		return classroom;
	}
	/**
	 * @param classroom the classroom to set
	 */
	public void setClassroom( Classroom classroom )
	{
		this.classroom = classroom;
	}
	
}
