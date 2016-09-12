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
public enum StatusClassRoom
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	
	OPEN("Aberto"),
	CLOSED("Fechado");
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTE
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	private String description;
	/*-------------------------------------------------------------------
	 *				 		     CONSTRUCTOR
	 *-------------------------------------------------------------------*/

	/**
	 * @param description
	 */
	private StatusClassRoom( String description )
	{
		this.description = description;
	}

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
