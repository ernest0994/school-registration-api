package io.metadata.school.registration.api.service;

import io.metadata.school.registration.api.common.exception.BadRequestException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Handles business logic definition for reports.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public interface ReportService {

    void generateReport(String report, String format, Map<String, Object> parameters, HttpServletResponse response) throws JRException, IOException, SQLException, BadRequestException;
    void exportReportPDF(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException;
    void exportReportXLS(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException;
    void exportReportCSV(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException;
    void exportReportHTML(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException;
}
