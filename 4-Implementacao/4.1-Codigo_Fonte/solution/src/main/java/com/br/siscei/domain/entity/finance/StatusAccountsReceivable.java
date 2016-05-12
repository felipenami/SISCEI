/**
 * 
 */
package com.br.siscei.domain.entity.finance;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author felip
 *
 */
@DataTransferObject(type = "enum")
public enum StatusAccountsReceivable
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	
	RECEIVED, // 0
	NOT_RECEIVED // 1
}
