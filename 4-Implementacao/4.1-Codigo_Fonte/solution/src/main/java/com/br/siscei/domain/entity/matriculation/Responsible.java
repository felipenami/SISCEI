/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "Responsible")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Responsible extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 355233818143852529L;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(nullable = false, length = 16)
	private String CPF;
	/**
	 * 
	 */
	@Column(nullable = false, length = 25)
	private String RG;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar birthDate;
	/**
	 * 
	 */
	@Column(nullable = false, length = 19)
	private String phone;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Responsible()
	{
		
	}
	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/

}
