package io.metadata.school.registration.api.controller;

import io.metadata.school.registration.api.common.exception.BadRequestException;
import io.metadata.school.registration.api.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles Http requests for reports.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/courseStudentsReport/{courseMnemonic}/{format}")
    @Operation(summary = "Filter all students with a specific course", description = "Available formats: pdf, xls, csv, html")
    public void courseStudentsReport(@PathVariable("courseMnemonic") String mnemonic, @PathVariable("format") String format, HttpServletResponse response) throws JRException, IOException, SQLException, BadRequestException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Students in course report");
        parameters.put("mnemonic", mnemonic);
        reportService.generateReport("course_students", format, parameters, response);
    }

    @GetMapping("/studentCoursesReport/{studentId}/{format}")
    @Operation(summary = "Filter all courses for a specific student", description = "Available formats: pdf, xls, csv, html")
    public void studentCoursesReport(@PathVariable("studentId") Long id, @PathVariable("format") String format, HttpServletResponse response) throws JRException, IOException, SQLException, BadRequestException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Student courses report");
        parameters.put("student_id", id);
        reportService.generateReport("student_courses", format, parameters, response);
    }

    @GetMapping("/coursesWithNoStudentsReport/{format}")
    @Operation(summary = "Filter all courses without any students", description = "Available formats: pdf, xls, csv, html")
    public void coursesWithNoStudentsReport(@PathVariable("format") String format, HttpServletResponse response) throws JRException, IOException, SQLException, BadRequestException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Courses with no students Report");
        reportService.generateReport("courses_with_no_students", format, parameters, response);
    }

    @GetMapping("/studentsWithNoCoursesReport/{format}")
    @Operation(summary = "Filter all students without any courses", description = "Available formats: pdf, xls, csv, html")
    public void studentsWithNoCoursesReport(@PathVariable("format") String format, HttpServletResponse response) throws JRException, IOException, SQLException, BadRequestException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Students with no courses Report");
        reportService.generateReport("students_with_no_courses", format, parameters, response);
    }
}
