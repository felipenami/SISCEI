/**
 * 
 */
package com.br.siscei.domain.repository.matriculation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.siscei.domain.entity.matriculation.Student;

/**
 * @author felipenami@gmail.com 
 * @since 14/09/2016
 * @version 1.0
 * @category Repository
 */
public interface IStudentRepository extends JpaRepository<Student, Long>
{

}
