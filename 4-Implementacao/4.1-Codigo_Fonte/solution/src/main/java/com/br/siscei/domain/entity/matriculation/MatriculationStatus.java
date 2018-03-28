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
public enum MatriculationStatus
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	
	INACTIVE, //0
	ACTIVE,//1
	
}