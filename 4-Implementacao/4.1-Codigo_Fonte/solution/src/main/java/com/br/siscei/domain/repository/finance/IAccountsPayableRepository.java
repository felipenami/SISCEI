/**
 * 
 */
package com.br.siscei.domain.repository.finance;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.finance.AccountsPayable;
import com.br.siscei.domain.entity.finance.StatusAccountsPayable;

/**
 * @author felipenami@gmail.com 
 * @since 13/05/2016
 * @version 1.0
 * @category Repository
 */
public interface IAccountsPayableRepository extends JpaRepository<AccountsPayable, Long>
{ 
	@Query(value= "SELECT new AccountsPayable(accountsPayable.id, accountsPayable.dueDate, " +
												 "accountsPayable.entryDate, accountsPayable.paymentDate, " +
												 "accountsPayable.description, accountsPayable.value, accountsPayable.bankAccount, " +
												 "accountsPayable.category, accountsPayable.status) " +
											 "FROM AccountsPayable accountsPayable " +
											 "WHERE( (FILTER(accountsPayable.description, :filter) = TRUE ) " +
											 	"OR (accountsPayable.status = :status OR :status = NULL ) ) ") 
	public Page<AccountsPayable>listByFilters(@Param("filter") String description, 
											  @Param("status") StatusAccountsPayable status, 
											  Pageable pageable );
}
