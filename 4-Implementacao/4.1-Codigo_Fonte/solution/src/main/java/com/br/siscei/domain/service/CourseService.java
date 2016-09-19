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

import com.br.siscei.domain.entity.matriculation.Course;
import com.br.siscei.domain.entity.matriculation.Discipline;
import com.br.siscei.domain.repository.matriculation.ICourseRepository;
import com.br.siscei.domain.repository.matriculation.IDisciplineRepository;

/**
 * @author felipenami
 *
 */
@Service
@RemoteProxy
@Transactional
public class CourseService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private ICourseRepository courseRepository;
	/**
	 * 
	 */
	@Autowired
	private IDisciplineRepository disciplineRepository;
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
	public Course findCourseById(Long id)
	{
		final Course course = this.courseRepository.findOne( id );
		Assert.notNull(course, "Não foi possivel encontar o curso com o id: "+id);
		return course;
	}
	/**
	 * @param course
	 * @return
	 */
	public Course insertCourse(Course course)
	{
		Assert.notNull(course);
		
		if(course.getDiscipline()!= null)
		{
			for( Discipline discipline : course.getDiscipline() )
			{
				discipline.setCourse( course );
			}
		}
		
		course =  this.courseRepository.saveAndFlush( course );
		
		return course;
	}
	
	public Page<Course> listCoursesByFilters(String filter, PageRequest pageable)
	{
		return this.courseRepository.listByFilters( filter, pageable );
	}
	/**
	 * 
	 * @param id
	 */
	public void removeCourse(Long id)
	{
		Course course = new Course();
		course.setId( id );
		
		this.courseRepository.delete( id );
		
	}
	/*-------------------------------------------------------------------
	 *				 		     DISCIPLINE
	 *-------------------------------------------------------------------*/	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Discipline findDisciplineById( Long id )
	{
		final Discipline discipline = this.disciplineRepository.findOne( id );
		Assert.notNull(discipline, "Não foi possivel encontar a disciplina com o id: "+id);
		return discipline;
	}
	/**
	 * 
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public Page<Discipline> listDisciplinesByFilters(String filter, PageRequest pageable)
	{
		return this.disciplineRepository.listByFilters( filter, pageable );
	}
	
}
