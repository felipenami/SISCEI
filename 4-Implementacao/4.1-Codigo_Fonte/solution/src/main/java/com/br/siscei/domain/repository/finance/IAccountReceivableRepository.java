/**
 * 
 */
package com.br.siscei.domain.repository.finance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.finance.AccountReceivable;

/**
 * @author felipenami@gmail.com 
 * @since 13/05/2016
 * @version 1.0
 * @category Repository
 */
public interface IAccountReceivableRepository extends JpaRepository<AccountReceivable, Long>
{ 
	@Query(value= "SELECT new AccountReceivable(accountReceivable.id, accountReceivable.dueDate, " +
												 "accountReceivable.entryDate, accountReceivable.receivementDate, " +
												 "accountReceivable.description, accountReceivable.value, accountReceivable.bankAccount, " +
												 "accountReceivable.category, accountReceivable.status) " +
											 "FROM AccountReceivable accountReceivable " +
											 "WHERE( (FILTER(accountReceivable.description, :filter) = TRUE )) " ) 
	public Page<AccountReceivable>listByFilters(@Param("filter") String filter, 
											  Pageable pageable );
}
