/**
 * 
 */
package com.br.siscei.test.domain.service;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.siscei.domain.entity.matriculation.Course;
import com.br.siscei.domain.entity.matriculation.CourseType;
import com.br.siscei.domain.service.CourseService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author felipeanami
 *
 */
public class CourseServiceTest extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	@Autowired
	private CourseService courseService;
	
	/*-------------------------------------------------------------------
	 *				 		    COURSE TESTS
	 *-------------------------------------------------------------------*/
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void findCourseByIdMustPass()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		Assert.assertNotNull(course);
	}
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void findCourseByIdMustFail()
	{
		final Course course = this.courseService.findCourseById( 999L );
		Assert.assertNull(course);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link Course} é instanciado e inserido corretamente
     */
	@Test
	public void insertCourseMustPass()
	{
		Course course = new Course();
		
		course.setDescription( "Descrição teste" );
		course.setName( "Sala teste" );
		course.setType( CourseType.COMPUTING );
		
		course = this.courseService.insertCourse( course );
		
		Assert.assertNotNull(course);
		Assert.assertNotNull(course.getDescription());
		Assert.assertNotNull(course.getName());
		Assert.assertNotNull(course.getType());
	}
	/**
	 * Objetivo: Fail.
	 * Motivo: O objeto {@link Course} é instanciado e inserido corretamente
	 */
	@Test(expected = ValidationException.class )
	public void insertCourseMustFailWithoutMandatoryFields()
	{
		Course course = new Course();
		
		course.setDescription( null );
		course.setName( null );
		course.setType( null);
		
		course = this.courseService.insertCourse( course );
		
		Assert.fail( "Deveria falhar se os campos estão nulos" );
	}
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void listAllCoursesMustPass()
	{
		final Page<Course> course = this.courseService.listCoursesByFilters( null, null );
													   
		Assert.assertNotNull(course);
		Assert.assertTrue(course.getContent().size() == 3 );	
	}
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void list1CourseMustPass()
	{
		final Page<Course> course = this.courseService.listCoursesByFilters( "Sala 1", null );
													   
		Assert.assertNotNull(course);
		Assert.assertTrue(course.getContent().size() == 1 );	
	}
	/**
     * Objetivo: Fail.
     * Motivo: O objeto {@link Course} é instanciado e inserido, e removido corretamente
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void removeCourseMustPass()
	{
		Course course = new Course();
		
		
		course = this.courseService.findCourseById( 9999L );
		Assert.assertNotNull(course);
		
		this.courseService.removeCourse( course.getId() );
		this.courseService.findCourseById( course.getId() );
		
		Assert.assertNotNull(course);
	}
	
	/**
	 * Objetivo: Success.
	 * Motivo: O objeto {@link Course} é instanciado e alterado corretamente
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void updateCourseMustPass()
	{
		
		Course course = this.courseService.findCourseById( 9999L );
		Assert.assertNotNull(course);
		
		course.setDescription( "Sala de aula com 25 computadores" );
		course.setName( "Sala" );
		course.setType( CourseType.ENGLISH );
		
		course = this.courseService.insertCourse( course );
		
		Assert.assertNotNull(course);
		Assert.assertNotNull(course.getId());
		Assert.assertTrue(course.getDescription() == "Sala de aula com 25 computadores");
		Assert.assertTrue(course.getName() == "Sala");
		Assert.assertTrue(course.getType() == CourseType.ENGLISH);
		
	}
	 
	
}
