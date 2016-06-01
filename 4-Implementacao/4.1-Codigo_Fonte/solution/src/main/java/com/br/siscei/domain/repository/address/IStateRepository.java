/**
 * 
 */
package com.br.siscei.domain.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.siscei.domain.entity.address.State;

/**
 * @author felip
 *
 */
public interface IStateRepository extends JpaRepository<State, Long>
{

}
