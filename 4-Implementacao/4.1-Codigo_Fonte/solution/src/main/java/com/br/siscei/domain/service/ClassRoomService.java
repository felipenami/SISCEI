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

import com.br.siscei.domain.entity.matriculation.ClassRoom;
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
	private IClassRoomRepository classRoomRepository;
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
	public ClassRoom findClassRoomById(Long id)
	{
		final ClassRoom classRoom = this.classRoomRepository.findOne( id );
		Assert.notNull(classRoom, "Não foi possivel encontar Turma com o id: "+id);
		return classRoom;
	}
	/**
	 * 
	 * @param classRoom
	 * @return
	 */
	public ClassRoom insertClassRoom(ClassRoom classRoom)
	{
		Assert.notNull(classRoom);
		
		classRoom = this.classRoomRepository.save( classRoom );
		
		return classRoom;
	}
	/**
	 * 
	 * @param filter
	 * @param status
	 * @param pageable
	 * @return
	 */
	public Page<ClassRoom>listClassRoomsByFilters(String filter, StatusClassRoom status, PageRequest pageable)
	{
		
		return this.classRoomRepository.listByFilters( filter, pageable );
	}
	/**
	 * 
	 * @param id
	 */
	public void removeClassRoom(Long id)
	{
		ClassRoom classRoom  = new ClassRoom();
		classRoom.setId( id );
		
		this.classRoomRepository.delete( id );
		
	}
	
	
}
