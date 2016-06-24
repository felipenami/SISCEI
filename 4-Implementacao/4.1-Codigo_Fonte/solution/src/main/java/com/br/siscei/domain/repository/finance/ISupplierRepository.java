/**
 * 
 */
package com.br.siscei.domain.repository.finance;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.siscei.domain.entity.finance.Supplier;

/**
 * @author felipenami@gmail.com 
 * @since 13/05/2016
 * @version 1.0
 * @category Repository
 */
public interface ISupplierRepository extends JpaRepository<Supplier, Long>
{
	@Query(value= "SELECT new Supplier(supplier.id, supplier.companyName, supplier.tradeName, supplier.phone, supplier.cnpj, supplier.contact) " +
				  "FROM  Supplier supplier " +
				  "WHERE ((FILTER(supplier.companyName, :filter) = TRUE ) OR (FILTER(supplier.tradeName, :filter ) = TRUE ) OR (FILTER(supplier.contact, :filter ) = TRUE )) " )
	public Page<Supplier>listByFilters(@Param("filter") String filter, Pageable pageable );
			
}
