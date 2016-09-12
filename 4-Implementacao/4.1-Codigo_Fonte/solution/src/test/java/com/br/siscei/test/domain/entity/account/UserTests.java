package com.br.siscei.test.domain.entity.account;

import org.junit.Assert;
import org.junit.Test;

import com.br.siscei.domain.entity.account.User;
import com.br.siscei.domain.entity.account.UserRole;
import com.br.siscei.test.domain.AbstractUnitTests;

/**
 * 
 * @author rodrigo.p.fraga@gmail.com
 * @since 09/05/2013
 * @version 1.0
 */
public class UserTests extends AbstractUnitTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
     * 
     */
	@Test
	public void getAuthoritiesMustPass()
	{
		final User user = new User();
		user.setRole( UserRole.ADMINISTRATOR );
		
		Assert.assertNotNull( user.getAuthorities() );
		Assert.assertTrue( user.getAuthorities().contains( UserRole.ADMINISTRATOR ) );
		Assert.assertTrue( user.getAuthorities().contains( UserRole.SECRETARY ) );
		Assert.assertTrue( user.getAuthorities().contains( UserRole.TEACHER ) );
		Assert.assertTrue( user.getAuthorities().contains( UserRole.STUDENT ) );
	}
}
