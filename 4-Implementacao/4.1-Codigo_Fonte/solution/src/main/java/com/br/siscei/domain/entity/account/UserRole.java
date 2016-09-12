package com.br.siscei.domain.entity.account;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author rodrigo.p.fraga@gmail.com
 * @since 02/06/2014
 * @version 1.0
 */
@DataTransferObject(type = "enum")
public enum UserRole implements GrantedAuthority
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	ADMINISTRATOR, // 0
	SECRETARY, // 1
	TEACHER, // 2
	STUDENT; // 3
	

	public static final String ADMINISTRATOR_VALUE 	= "ADMINISTRATOR";
	public static final String SECRETARY_VALUE 		= "SECRETARY";
	public static final String TEACHER_VALUE 		= "TEACHER";
	public static final String STUDENT_VALUE		= "STUDENT";
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority()
	{
		return this.name();
	}
}