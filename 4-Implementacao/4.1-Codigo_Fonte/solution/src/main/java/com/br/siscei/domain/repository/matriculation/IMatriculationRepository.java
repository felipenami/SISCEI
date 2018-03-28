/**
 * 
 */
package com.br.siscei.domain.repository.matriculation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.siscei.domain.entity.matriculation.Matriculation;

/**
 * @author felipenami@gmail.com 
 * @since 08/12/2017
 * @version 1.0
 * @category Repository
 */
public interface IMatriculationRepository extends JpaRepository<Matriculation, Long>
{

}
