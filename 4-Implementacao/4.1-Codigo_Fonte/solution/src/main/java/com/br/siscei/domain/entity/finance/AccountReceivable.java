/**
 * 
 */
package com.br.siscei.domain.entity.finance;

import java.io.Serializable;
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
import org.hibernate.validator.constraints.NotEmpty;

import com.br.siscei.domain.entity.account.User;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@Table(name = "\"accountReceivable\"")
@DataTransferObject(javascript = "AccountReceivable")
public class AccountReceivable extends AbstractEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3913066197964595574L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, length = 50)
	private Calendar dueDate;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, length = 50)
	private Calendar entryDate;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, length = 50)
	private Calendar receivementDate;
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 500)
	private String description;
	/**
	 * 
	 */
	@Column(nullable = false)
	private BigDecimal value;
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER )
	private BankAccount bankAccount;
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER )
	private Category category;
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	private User student;
	/**
	 * 
	 */
	@Enumerated(EnumType.ORDINAL)
	private StatusAccountsReceivable status;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public AccountReceivable ()
	{
		
	}
	/**
	 * 
	 */
	public AccountReceivable ( Long id )
	{
		super(id);	
	}
	/**
	 * 
	 */
	public AccountReceivable ( Long id, Calendar dueDate, Calendar entryDate, 
			Calendar receivementDate, String description, BigDecimal value, BankAccount bankAccount, Category category, StatusAccountsReceivable status )
	{
		super(id);
		this.dueDate 			= dueDate;
		this.entryDate 			= entryDate;
		this.receivementDate 	= receivementDate;
		this.description 		= description;
		this.value 				= value;
		this.bankAccount 		= bankAccount;
		this.category 			= category;
		this.status 			= status;
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
		result = prime * result + ( ( bankAccount == null ) ? 0 : bankAccount.hashCode() );
		result = prime * result + ( ( category == null ) ? 0 : category.hashCode() );
		result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
		result = prime * result + ( ( dueDate == null ) ? 0 : dueDate.hashCode() );
		result = prime * result + ( ( entryDate == null ) ? 0 : entryDate.hashCode() );
		result = prime * result + ( ( receivementDate == null ) ? 0 : receivementDate.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		result = prime * result + ( ( student == null ) ? 0 : student.hashCode() );
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
		AccountReceivable other = ( AccountReceivable ) obj;
		if ( bankAccount == null )
		{
			if ( other.bankAccount != null ) return false;
		}
		else if ( !bankAccount.equals( other.bankAccount ) ) return false;
		if ( category == null )
		{
			if ( other.category != null ) return false;
		}
		else if ( !category.equals( other.category ) ) return false;
		if ( description == null )
		{
			if ( other.description != null ) return false;
		}
		else if ( !description.equals( other.description ) ) return false;
		if ( dueDate == null )
		{
			if ( other.dueDate != null ) return false;
		}
		else if ( !dueDate.equals( other.dueDate ) ) return false;
		if ( entryDate == null )
		{
			if ( other.entryDate != null ) return false;
		}
		else if ( !entryDate.equals( other.entryDate ) ) return false;
		if ( receivementDate == null )
		{
			if ( other.receivementDate != null ) return false;
		}
		else if ( !receivementDate.equals( other.receivementDate ) ) return false;
		if ( status != other.status ) return false;
		if ( student == null )
		{
			if ( other.student != null ) return false;
		}
		else if ( !student.equals( other.student ) ) return false;
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
	 * @return the dueDate
	 */
	public Calendar getDueDate()
	{
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate( Calendar dueDate )
	{
		this.dueDate = dueDate;
	}
	/**
	 * @return the entryDate
	 */
	public Calendar getEntryDate()
	{
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate( Calendar entryDate )
	{
		this.entryDate = entryDate;
	}
	/**
	 * @return the receivementDate
	 */
	public Calendar getReceivementDate()
	{
		return receivementDate;
	}
	/**
	 * @param receivementDate the receivementDate to set
	 */
	public void setReceivementDate( Calendar receivementDate )
	{
		this.receivementDate = receivementDate;
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
	 * @return the bankAccount
	 */
	public BankAccount getBankAccount()
	{
		return bankAccount;
	}
	/**
	 * @param bankAccount the bankAccount to set
	 */
	public void setBankAccount( BankAccount bankAccount )
	{
		this.bankAccount = bankAccount;
	}
	/**
	 * @return the category
	 */
	public Category getCategory()
	{
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory( Category category )
	{
		this.category = category;
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
	 * @return the status
	 */
	public StatusAccountsReceivable getStatus()
	{
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus( StatusAccountsReceivable status )
	{
		this.status = status;
	}
	
}
