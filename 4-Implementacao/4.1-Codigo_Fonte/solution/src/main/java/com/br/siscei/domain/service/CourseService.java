/**
 * 
 */
package com.br.siscei.domain.service;

import java.io.ByteArrayOutputStream;

import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.siscei.domain.entity.matriculation.Course;
import com.br.siscei.domain.entity.matriculation.Discipline;
import com.br.siscei.domain.repository.matriculation.ICourseReportRepository;
import com.br.siscei.domain.repository.matriculation.ICourseRepository;
import com.br.siscei.domain.repository.matriculation.IDisciplineRepository;

import br.com.eits.common.infrastructure.file.MimeType;

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
	@Autowired
	private ICourseReportRepository courseReportRepository;
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
		
		course.addList();
		
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
	/**
	 * 
	 * @param courseId
	 * @return
	 */
	public FileTransfer downloadCourseReport (Long courseId )
	{
		final ByteArrayOutputStream reportOutputStream = this.courseReportRepository.generateCourse( courseId );
		
		final FileTransfer fileTransfer = new FileTransfer( ICourseReportRepository.COURSE_REPORT, MimeType.PDF.value, reportOutputStream.toByteArray() );
		
		return fileTransfer;
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
