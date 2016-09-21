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
	
	OPEN, //0
	CLOSED; //1
	
}
