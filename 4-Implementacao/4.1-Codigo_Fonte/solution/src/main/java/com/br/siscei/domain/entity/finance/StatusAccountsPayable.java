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
public enum StatusAccountsPayable
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	
	PAID, // 0
	NOT_PAID // 1
}
