/**
 * 
 */
package com.br.siscei.domain.repository.matriculation;

import java.io.ByteArrayOutputStream;

/**
 * @author felip
 *
 */
public interface ICourseReportRepository
{

	public static final String COURSE_REPORT = "course.pdf";
	
	
	public ByteArrayOutputStream generateCourse( Long courseId );
	
}
