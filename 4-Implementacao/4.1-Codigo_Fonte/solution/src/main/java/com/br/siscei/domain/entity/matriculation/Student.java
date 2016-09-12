/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import com.br.siscei.domain.entity.account.User;
import com.br.siscei.domain.entity.address.Address;

/**
 * @author felip
 *
 */
@Table(name = "\"student\"")
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
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	
	

	
}
