/**
 * 
 */
package com.br.siscei.domain.repository.finance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.finance.BankAccount;

/**
 * @author felipenami@gmail.com 
 * @since 13/05/2016
 * @version 1.0
 * @category Repository
 */
public interface IBankAccountRepository extends JpaRepository<BankAccount, Long>
{
	@Query(value= "SELECT new BankAccount( bankAccount.id, bankAccount.name, bankAccount.description, bankAccount.balance ) "+
				  "FROM BankAccount bankAccount "+
				  "WHERE ((FILTER( bankAccount.name, :filter ) = TRUE) OR (FILTER( bankAccount.description, :filter ) = TRUE )) ")
	public Page<BankAccount>listByFilters(@Param("filter") String filter, Pageable pageable);
}
