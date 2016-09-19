/**
 * 
 */
package com.br.siscei.test.domain.service;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.siscei.domain.entity.matriculation.Course;
import com.br.siscei.domain.entity.matriculation.Discipline;
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
	/**
	 * 
	 */
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
			"/dataset/matriculation/DisciplineDataSet.xml",
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
			"/dataset/matriculation/DisciplineDataSet.xml",
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
		Set<Discipline> disciplines = new HashSet<Discipline>();
				
		Discipline discipline = new Discipline("Excel", "Informática Básica");		
		Discipline discipline1 = new Discipline("World", "Informática Básica");
		Discipline discipline2 = new Discipline("Power Point","Informática Básica");
		
		disciplines.add( discipline );
		disciplines.add( discipline1 );
		disciplines.add( discipline2 );
		
		
		Course course = new Course();
		
		course.setDescription( "Descrição teste" );
		course.setName( "Curso teste" );
		course.setDiscipline( disciplines );
		
		course = this.courseService.insertCourse( course );
		
		Assert.assertNotNull(course);
		Assert.assertNotNull(course.getDescription());
		Assert.assertNotNull(course.getName());
		Assert.assertNotNull(course.getDiscipline());
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
		final Page<Course> course = this.courseService.listCoursesByFilters( "Excel", null );
													   
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
		Set<Discipline> disciplines = new HashSet<Discipline>();
		
		Discipline discipline = new Discipline("Excel", "Informática Básica");		
		Discipline discipline1 = new Discipline("World", "Informática Básica");
		
		
		disciplines.add( discipline );
		disciplines.add( discipline1 );
		
		
		Course course = this.courseService.findCourseById( 9999L );
		Assert.assertNotNull(course);
		
		course.setDescription( "Curso de excel" );
		course.setName( "Excel" );
		course.setDiscipline( disciplines );
		
		course = this.courseService.insertCourse( course );
		
		Assert.assertNotNull(course);
		Assert.assertNotNull(course.getId());
		Assert.assertTrue(course.getDescription() == "Curso de excel");
		Assert.assertTrue(course.getName() == "Excel");
		
	}
	 
	
}
