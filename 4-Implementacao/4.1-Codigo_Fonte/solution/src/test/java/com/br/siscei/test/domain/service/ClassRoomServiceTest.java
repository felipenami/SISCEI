/**
 * 
 */
package com.br.siscei.test.domain.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.siscei.domain.entity.matriculation.Classroom;
import com.br.siscei.domain.entity.matriculation.Course;
import com.br.siscei.domain.entity.matriculation.Schedule;
import com.br.siscei.domain.entity.matriculation.ScheduleWeek;
import com.br.siscei.domain.entity.matriculation.StatusClassRoom;
import com.br.siscei.domain.service.ClassRoomService;
import com.br.siscei.domain.service.CourseService;
import com.br.siscei.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import scala.annotation.meta.setter;

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
			"/dataset/matriculation/ScheduleDataSet.xml",
		})
	public void findClassRoomByIdMustPass()
	{
		final Classroom classroom = this.classRoomService.findClassroomById( 9999L );
		Assert.assertNotNull(classroom);
	}
	/**
     * Objetivo: Fail.
     * Motivo: Os parâmetros informados no filtro não estão em conformidade para a consulta.
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
			"/dataset/matriculation/ScheduleDataSet.xml",
		})
	public void findClassRoomByIdMustFail()
	{
		final Classroom classroom = this.classRoomService.findClassroomById( 999L );
		Assert.assertNotNull(classroom);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link Classroom} é instanciado e inserido corretamente
     */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
		})
	public void insertClassRoomMustPass()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		

		final String beginHour = "11:00";
		final String endHour = "12:00";
		
		Set<Schedule> schedules = new HashSet<Schedule>();
		
		Schedule schedule1 = new Schedule( beginHour, endHour, ScheduleWeek.FRIDAY );
		Schedule schedule2 = new Schedule(beginHour, endHour, ScheduleWeek.MONDAY);
		Schedule schedule3 = new Schedule(beginHour, endHour, ScheduleWeek.TUESDAY);
		
		schedules.add( schedule1 );
		schedules.add( schedule2 );
		schedules.add( schedule3 );
		
		Classroom classroom = new Classroom();
		
		classroom.setName( "Turma 1" );
		classroom.setStatus( StatusClassRoom.OPEN );
		classroom.setCourse( course );
		classroom.setSchedule( schedules );
		
		classroom = this.classRoomService.insertClassroom( classroom );
		
		Assert.assertNotNull(classroom);
		Assert.assertNotNull(classroom.getName());
		Assert.assertNotNull(classroom.getStatus());
		Assert.assertNotNull(classroom.getCourse());
	}
	/**
     * Objetivo: Fail.
     * Motivo: O objeto {@link Classroom} é instanciado e inserido incorretamente
     * 
     */
	@Test(expected = IllegalArgumentException.class )
	public void insertClassRoomMustFailWithoutMandatoryFieldName()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		
		Classroom classroom = new Classroom();
		
		Set<Schedule> scedules = new HashSet<Schedule>();
		
		Schedule schedule1 = new Schedule();
		schedule1.setBeginHour( "11:00" );
		schedule1.setEndHour( "12:00" );
		schedule1.setWeekDay( ScheduleWeek.MONDAY );
		
		Schedule schedule2 = new Schedule();
		schedule2.setBeginHour( "11:00" );
		schedule2.setEndHour( "12:00" );
		schedule2.setWeekDay( ScheduleWeek.MONDAY );
		
		Schedule schedule3 = new Schedule();
		schedule3.setBeginHour( "11:00" );
		schedule3.setEndHour( "12:00" );
		schedule3.setWeekDay( ScheduleWeek.WEDNESDAY );
		
		scedules.add( schedule1 );
		scedules.add( schedule2 );
		scedules.add( schedule3 );
		
		classroom.setName( null );
		classroom.setStatus( StatusClassRoom.OPEN );
		classroom.setCourse( course );
		
		classroom = this.classRoomService.insertClassroom( classroom );
		
		Assert.fail( "Deveria falhar se os campos estão nulos" );
		
	}
	/**
     * Objetivo: Fail.
     * Motivo: O objeto {@link Classroom} é instanciado e inserido incorretamente
     * 
     */
	@Test(expected = IllegalArgumentException.class )
	public void insertClassRoomMustFailWithoutMandatoryFieldSchedule()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		
		Classroom classroom = new Classroom();
		
		classroom.setName( "sala 1" );
		classroom.setStatus( StatusClassRoom.OPEN );
		classroom.setCourse( course );
		
		classroom = this.classRoomService.insertClassroom( classroom );
		
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
			"/dataset/matriculation/ScheduleDataSet.xml",
		})
	public void listAllClassRoomsMustPass()
	{
		final Page<Classroom> classroom = this.classRoomService.listClassroomsByFilters( null, null, null );
		
		Assert.assertNotNull(classroom);
		Assert.assertTrue(classroom.getContent().size() == 3 );
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
		final Page<Classroom> classroom = this.classRoomService.listClassroomsByFilters( "Turma sala 3", null,null );
		
		Assert.assertNotNull(classroom);
		Assert.assertTrue(classroom.getContent().size() == 1 );
	}
	
	/**
	 * Objetivo: Success.
	 * Motivo: O objeto {@link Classroom} é instanciado e alterado corretamente
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
			"/dataset/matriculation/ScheduleDataSet.xml",
		})
	public void updateClassRoomMustPass()
	{
		final Course course = this.courseService.findCourseById( 9999L );
		
		Set<Schedule> schedules = new HashSet<Schedule>();
		
		Schedule schedule1 = new Schedule();
		schedule1.setBeginHour( "11:00" );
		schedule1.setEndHour( "12:00" );
		schedule1.setWeekDay( ScheduleWeek.TUESDAY );
		
		Schedule schedule2 = new Schedule();
		schedule2.setBeginHour( "11:00" );
		schedule2.setEndHour( "12:00" );
		schedule2.setWeekDay( ScheduleWeek.MONDAY );
		
		schedules.add( schedule1 );
		schedules.add( schedule2 );
		
		Classroom classroom = this.classRoomService.findClassroomById( 9999L );
		
		classroom.setName( "Turma 2" );
		classroom.setStatus( StatusClassRoom.OPEN );
		classroom.setCourse( course );
		classroom.setSchedule( schedules );
		
		classroom = this.classRoomService.insertClassroom( classroom );
		
		Assert.assertNotNull(classroom);
		Assert.assertNotNull(classroom.getId());
		Assert.assertTrue(classroom.getName() == "Turma 2");
		Assert.assertTrue(classroom.getStatus() == StatusClassRoom.OPEN);
	}
	/**
     * Objetivo: Success.
     * Motivo: O objeto {@link Classroom} é instanciado e removido, corretamente
     */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
			"/dataset/matriculation/CourseDataSet.xml",
			"/dataset/matriculation/ClassRoomDataSet.xml",
			"/dataset/matriculation/ScheduleDataSet.xml",
		})
	public void removeCourseClassRoomMustPass()
	{
		Classroom classroom = new Classroom();
		
		
		classroom = this.classRoomService.findClassroomById( 9999L );
		Assert.assertNotNull(classroom);
		
		this.classRoomService.removeClassroom( classroom.getId() );
		this.classRoomService.findClassroomById( classroom.getId() );
		
		Assert.assertNotNull(classroom);
	}
	
}
