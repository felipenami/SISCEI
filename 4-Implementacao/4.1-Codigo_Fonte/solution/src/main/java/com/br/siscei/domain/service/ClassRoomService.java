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

import com.br.siscei.domain.entity.matriculation.Classroom;
import com.br.siscei.domain.entity.matriculation.StatusClassRoom;
import com.br.siscei.domain.repository.matriculation.IClassRoomRepository;

/**
 * @author felipenami
 *
 */
@Service
@RemoteProxy
@Transactional
public class ClassRoomService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IClassRoomRepository classroomRepository;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Classroom findClassroomById(Long id)
	{
		final Classroom classroom = this.classroomRepository.findOne( id );
		Assert.notNull(classroom, "Não foi possivel encontar Turma com o id: "+id);
		return classroom;
	}
	/**
	 * 
	 * @param classroom
	 * @return
	 */
	public Classroom insertClassroom(Classroom classroom)
	{
		Assert.notNull(classroom);
		classroom.addList();
		classroom = this.classroomRepository.saveAndFlush( classroom );
		
		return classroom;
	}
	/**
	 * 
	 * @param filter
	 * @param status
	 * @param pageable
	 * @return
	 */
	public Page<Classroom>listClassroomsByFilters(String filter, StatusClassRoom status, PageRequest pageable)
	{
		
		return this.classroomRepository.listByFilters( filter, pageable );
	}
	/**
	 * 
	 * @param id
	 */
	public void removeClassroom(Long id)
	{
		Classroom classroom  = new Classroom();
		classroom.setId( id );
		
		this.classroomRepository.delete( id );
		
	}
	
	
}
