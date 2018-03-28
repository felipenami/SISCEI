/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import java.math.BigDecimal;
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

import com.br.siscei.domain.entity.account.User;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"matriculation\"")
@DataTransferObject(javascript = "Matriculation")
public class Matriculation extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7094003988869718485L;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(nullable = false)
	private Long matriculationNumber;
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	private User student;
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	private Classroom classroom;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, length = 50)
	private Calendar matriculationDate;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, length = 50)
	private Calendar paymentDate;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private MatriculationStatus status;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Long numberOfInstallment;
	/**
	 * 
	 */
	@Column(nullable = false)
	private BigDecimal value;
	/**
	 * 
	 */
	@Column(nullable = false)
	private BigDecimal ticket;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Matriculation()
	{
		
	}
	
	public Matriculation(Long id, Long matriculationNumber, 
						 User student, Classroom classroom, 
						 Calendar paymentDate, 
						 Calendar matriculationDate, 
						 MatriculationStatus status, 
						 Long numberOfInstallment, BigDecimal value, BigDecimal ticket)
	{
		super(id);
		this.matriculationNumber 	= matriculationNumber;
		this.student 				= student;
		this.classroom 				= classroom;
		this.paymentDate 			= paymentDate;
		this.matriculationDate 		= matriculationDate;
		this.status					= status;
		this.numberOfInstallment 	= numberOfInstallment;
		this.value 					= value;
		this.ticket					= ticket;
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
		result = prime * result + ( ( classroom == null ) ? 0 : classroom.hashCode() );
		result = prime * result + ( ( matriculationDate == null ) ? 0 : matriculationDate.hashCode() );
		result = prime * result + ( ( matriculationNumber == null ) ? 0 : matriculationNumber.hashCode() );
		result = prime * result + ( ( numberOfInstallment == null ) ? 0 : numberOfInstallment.hashCode() );
		result = prime * result + ( ( paymentDate == null ) ? 0 : paymentDate.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		result = prime * result + ( ( student == null ) ? 0 : student.hashCode() );
		result = prime * result + ( ( ticket == null ) ? 0 : ticket.hashCode() );
		result = prime * result + ( ( value == null ) ? 0 : value.hashCode() );
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
		Matriculation other = ( Matriculation ) obj;
		if ( classroom == null )
		{
			if ( other.classroom != null ) return false;
		}
		else if ( !classroom.equals( other.classroom ) ) return false;
		if ( matriculationDate == null )
		{
			if ( other.matriculationDate != null ) return false;
		}
		else if ( !matriculationDate.equals( other.matriculationDate ) ) return false;
		if ( matriculationNumber == null )
		{
			if ( other.matriculationNumber != null ) return false;
		}
		else if ( !matriculationNumber.equals( other.matriculationNumber ) ) return false;
		if ( numberOfInstallment == null )
		{
			if ( other.numberOfInstallment != null ) return false;
		}
		else if ( !numberOfInstallment.equals( other.numberOfInstallment ) ) return false;
		if ( paymentDate == null )
		{
			if ( other.paymentDate != null ) return false;
		}
		else if ( !paymentDate.equals( other.paymentDate ) ) return false;
		if ( status != other.status ) return false;
		if ( student == null )
		{
			if ( other.student != null ) return false;
		}
		else if ( !student.equals( other.student ) ) return false;
		if ( ticket == null )
		{
			if ( other.ticket != null ) return false;
		}
		else if ( !ticket.equals( other.ticket ) ) return false;
		if ( value == null )
		{
			if ( other.value != null ) return false;
		}
		else if ( !value.equals( other.value ) ) return false;
		return true;
	}
	
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the matriculationNumber
	 */
	public Long getMatriculationNumber()
	{
		return matriculationNumber;
	}

	/**
	 * @param matriculationNumber the matriculationNumber to set
	 */
	public void setMatriculationNumber( Long matriculationNumber )
	{
		this.matriculationNumber = matriculationNumber;
	}

	/**
	 * @return the student
	 */
	public User getStudent()
	{
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent( User student )
	{
		this.student = student;
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

	/**
	 * @return the matriculationDate
	 */
	public Calendar getMatriculationDate()
	{
		return matriculationDate;
	}

	/**
	 * @param matriculationDate the matriculationDate to set
	 */
	public void setMatriculationDate( Calendar matriculationDate )
	{
		this.matriculationDate = matriculationDate;
	}

	/**
	 * @return the paymentDate
	 */
	public Calendar getPaymentDate()
	{
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate( Calendar paymentDate )
	{
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the status
	 */
	public MatriculationStatus getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus( MatriculationStatus status )
	{
		this.status = status;
	}

	/**
	 * @return the numberOfInstallment
	 */
	public Long getNumberOfInstallment()
	{
		return numberOfInstallment;
	}

	/**
	 * @param numberOfInstallment the numberOfInstallment to set
	 */
	public void setNumberOfInstallment( Long numberOfInstallment )
	{
		this.numberOfInstallment = numberOfInstallment;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue( BigDecimal value )
	{
		this.value = value;
	}

	/**
	 * @return the ticket
	 */
	public BigDecimal getTicket()
	{
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket( BigDecimal ticket )
	{
		this.ticket = ticket;
	}
	
	
}
