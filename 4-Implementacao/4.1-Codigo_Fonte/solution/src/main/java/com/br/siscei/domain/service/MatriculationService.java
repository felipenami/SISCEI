/**
 * 
 */
package com.br.siscei.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.siscei.domain.entity.account.UserRole;
import com.br.siscei.domain.entity.matriculation.Matriculation;
import com.br.siscei.domain.repository.matriculation.IMatriculationRepository;

/**
 * @author felip
 *
 */
@Service
@RemoteProxy
@Transactional
public class MatriculationService
{
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IMatriculationRepository matriculationRepository;
	
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Matriculation findMatriculationById(Long id)
	{
		final Matriculation matriculation = this.matriculationRepository.findOne( id );
		Assert.notNull(matriculation, "Não foi possivel encontar a matricula com o id: "+id);
		return matriculation;
		
	}
	/**
	 * 
	 * @param matriculation
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('"+UserRole.ADMINISTRATOR_VALUE+"','"+UserRole.SECRETARY_VALUE+"')")
	public Matriculation insertMatriculation(Matriculation matriculation)
	{
		 Assert.notNull(matriculation);
		 return this.matriculationRepository.saveAndFlush( matriculation );
	}
	/**
	 * 
	 * @return
	 */
	public List<Matriculation> listAllMatriculations()
	{
		return this.matriculationRepository.findAll();
	}
	
}
