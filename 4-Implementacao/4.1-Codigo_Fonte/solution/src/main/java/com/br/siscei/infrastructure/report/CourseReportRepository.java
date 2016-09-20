/**
 * 
 */
package com.br.siscei.infrastructure.report;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.siscei.domain.repository.matriculation.ICourseReportRepository;

import br.com.eits.common.infrastructure.report.IReportManager;

/**
 * @author felip
 *
 */
@Component
public class CourseReportRepository implements ICourseReportRepository
{

	private static final String COURSE_PATH = "/reports/matriculation/course/course.jasper";
	
	
	@Autowired
	private IReportManager reportManager;
	
	
	@Override
	public ByteArrayOutputStream generateCourse( Long courseId )
	{
		final Map<String, Object> parameter = new HashMap<>();
		
		parameter.put("courseId" , courseId);
		
		return this.reportManager.exportToPDF( parameter, COURSE_PATH );
	}

}
