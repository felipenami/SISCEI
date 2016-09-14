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
import com.br.siscei.domain.repository.matriculation.ICourseRepository;

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
	 * 
	 * @param course
	 * @return
	 */
	public Course insertCourse(Course course)
	{
		Assert.notNull(course);
		return this.courseRepository.saveAndFlush( course );
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
	
}
