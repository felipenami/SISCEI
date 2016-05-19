/**
 * 
 */
package com.br.siscei.domain.repository.finance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.siscei.domain.entity.finance.AccountsReceivable;

/**
 * @author felipenami@gmail.com 
 * @since 13/05/2016
 * @version 1.0
 * @category Repository
 */
public interface IAccountsReceivableRepository extends JpaRepository<AccountsReceivable, Long>
{

}
