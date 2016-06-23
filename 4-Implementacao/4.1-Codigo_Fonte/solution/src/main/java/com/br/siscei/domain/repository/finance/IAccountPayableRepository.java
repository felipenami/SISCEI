/**
 * 
 */
package com.br.siscei.domain.repository.finance;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.finance.AccountPayable;

/**
 * @author felipenami@gmail.com 
 * @since 13/05/2016
 * @version 1.0
 * @category Repository
 */
public interface IAccountPayableRepository extends JpaRepository<AccountPayable, Long>
{ 
	@Query(value= "SELECT new AccountPayable(accountPayable.id, accountPayable.dueDate, " +
												 "accountPayable.entryDate, accountPayable.paymentDate, " +
												 "accountPayable.description, accountPayable.value, accountPayable.bankAccount, " +
												 "accountPayable.category, accountPayable.status) " +
											 "FROM AccountPayable accountPayable " +
											 "WHERE( (FILTER(accountPayable.description, :filter) = TRUE )) " ) 
	public Page<AccountPayable>listByFilters(@Param("filter") String filter, 
											  Pageable pageable );
}
