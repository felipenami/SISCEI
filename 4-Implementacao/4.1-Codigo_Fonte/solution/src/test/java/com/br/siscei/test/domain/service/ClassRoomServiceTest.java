/**
 * 
 */
package com.br.siscei.test.domain.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.siscei.domain.entity.matriculation.ClassRoom;
import com.br.siscei.domain.entity.matriculation.Course;
import com.br.siscei.domain.entity.matriculation.StatusClassRoom;
import com.br.siscei.domain.service.ClassRoomService;
import com.br.siscei.domain.service.CourseService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author biogasfert
 *
 */
public class ClassRoomServiceTest extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private ClassRoomService classRoomService;
	/**
	 * 
	 */
	@Autowired
	private CourseService courseService;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		    CLASSROOM TESTS
	 *-------------------------------------------------------------------*/
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
		})
	public void findClassRoomByIdMustPass()
	{
		final ClassRoom classRoom = this.classRoomService.findClassRoomById( 9999L );
		Assert.assertNotNull(classRoom);
	}
	/**
     * Objetivo: Fail.
     * Motivo: Os parâmetros informados no filtro não estão em conformidade para a consulta.
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
		})
	public void findClassRoomByIdMustFail()
	{
		final ClassRoom classRoom = this.classRoomService.findClassRoomById( 999L );
		Assert.assertNotNull(classRoom);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link ClassRoom} é instanciado e inserido corretamente
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void insertClassRoomMustPass()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		final Calendar schedule = new GregorianCalendar(2050,9,5, 11,00,00);
		
		ClassRoom classRoom = new ClassRoom();
		
		classRoom.setName( "Turma 1" );
		classRoom.setStatus( StatusClassRoom.OPEN );
		classRoom.setSchedule( schedule );
		classRoom.setCourse( course );
		
		classRoom = this.classRoomService.insertClassRoom( classRoom );
		
		Assert.assertNotNull(classRoom);
		Assert.assertNotNull(classRoom.getName());
		Assert.assertNotNull(classRoom.getStatus());
		Assert.assertNotNull(classRoom.getCourse());
	}
	/**
     * Objetivo: Fail.
     * Motivo: O objeto {@link ClassRoom} é instanciado e inserido incorretamente
     * 
     */
	@Test(expected = IllegalArgumentException.class )
	public void insertClassRoomMustFailWithoutMandatoryFieldName()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		final Calendar schedule = new GregorianCalendar(2050,9,5, 11,00,00);
		
		ClassRoom classRoom = new ClassRoom();
		
		classRoom.setName( null );
		classRoom.setStatus( StatusClassRoom.OPEN );
		classRoom.setSchedule( schedule );
		classRoom.setCourse( course );
		
		classRoom = this.classRoomService.insertClassRoom( classRoom );
		
		Assert.fail( "Deveria falhar se os campos estão nulos" );
		
	}
	/**
     * Objetivo: Fail.
     * Motivo: O objeto {@link ClassRoom} é instanciado e inserido incorretamente
     * 
     */
	@Test(expected = IllegalArgumentException.class )
	public void insertClassRoomMustFailWithoutMandatoryFieldSchedule()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		
		ClassRoom classRoom = new ClassRoom();
		
		classRoom.setName( "sala 1" );
		classRoom.setStatus( StatusClassRoom.OPEN );
		classRoom.setSchedule( null );
		classRoom.setCourse( course );
		
		classRoom = this.classRoomService.insertClassRoom( classRoom );
		
		Assert.fail( "Deveria falhar se os campos estão nulos" );
	}
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
		})
	public void listAllClassRoomsMustPass()
	{
		final Page<ClassRoom> classRoom = this.classRoomService.listClassRoomsByFilters( null, null, null );
		
		Assert.assertNotNull(classRoom);
		Assert.assertTrue(classRoom.getContent().size() == 3 );
	}
	/**
     * Objetivo: Success.
     * Motivo: Todos os parâmetros informados no filtro estão em conformidade para a consulta.
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
		})
	public void listAllClassRoomsMustReturn1()
	{
		final Page<ClassRoom> classRoom = this.classRoomService.listClassRoomsByFilters( "Turma sala 3", null,null );
		
		Assert.assertNotNull(classRoom);
		Assert.assertTrue(classRoom.getContent().size() == 1 );
	}
	
	/**
	 * Objetivo: Success.
	 * Motivo: O objeto {@link ClassRoom} é instanciado e alterado corretamente
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
		})
	public void updateClassRoomMustPass()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		final Calendar schedule = new GregorianCalendar(2050,9,5, 11,00,00);
		
		ClassRoom classRoom = new ClassRoom();
		
		classRoom.setName( "Turma 1" );
		classRoom.setStatus( StatusClassRoom.OPEN );
		classRoom.setSchedule( schedule );
		classRoom.setCourse( course );
		
		classRoom = this.classRoomService.insertClassRoom( classRoom );
		
		Assert.assertNotNull(classRoom);
		Assert.assertNotNull(classRoom.getId());
		Assert.assertTrue(classRoom.getName() == "Turma 1");
		Assert.assertTrue(classRoom.getStatus() == StatusClassRoom.OPEN);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link ClassRoom} é instanciado e removido, corretamente
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
		})
	public void removeCourseClassRoomMustPass()
	{
		ClassRoom classRoom = new ClassRoom();
		
		
		classRoom = this.classRoomService.findClassRoomById( 9999L );
		Assert.assertNotNull(classRoom);
		
		this.classRoomService.removeClassRoom( classRoom.getId() );
		this.classRoomService.findClassRoomById( classRoom.getId() );
		
		Assert.assertNotNull(classRoom);
	}
	
}
