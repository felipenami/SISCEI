/**
 * 
 */
package com.br.siscei.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.siscei.domain.entity.finance.Category;
import com.br.siscei.domain.repository.finance.ICategoryRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class CategoryService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private ICategoryRepository categoryRepository;
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Category findCategoryById(Long id)
	{
		final Category category = this.categoryRepository.findOne( id );
		Assert.notNull( category, "Não possivel encontrar a categoria com o id: "+id );
		return category;
	}
	/**
	 * 
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public Page<Category>listCategoriesByFilters(String filter, PageRequest pageable )
	{
		return this.categoryRepository.listByFilters( filter, pageable);
	}
	
}
