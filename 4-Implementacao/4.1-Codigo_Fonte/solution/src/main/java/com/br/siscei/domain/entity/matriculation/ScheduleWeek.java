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
public enum ScheduleWeek
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	
	MONDAY, //0
	TUESDAY,//1
	WEDNESDAY, //2
	THURSDAY, //3
	FRIDAY, //4
	SATURDAY; //5
	
}
