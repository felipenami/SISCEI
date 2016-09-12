/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author felip
 *
 */
@DataTransferObject(type = "enum")
public enum CourseType
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	COMPUTING("Informática"),
	ENGLISH("Inglês");
	
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTE
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     CONSTRUCTOR
	 *-------------------------------------------------------------------*/

	/**
	 * @param description
	 */
	private CourseType( String description )
	{
		this.description = description;
	}
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
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
	
}
