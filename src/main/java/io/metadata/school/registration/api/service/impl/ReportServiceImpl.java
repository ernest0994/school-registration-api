package io.metadata.school.registration.api.service.impl;

import io.metadata.school.registration.api.common.enums.FormatTypes;
import io.metadata.school.registration.api.common.exception.BadRequestException;
import io.metadata.school.registration.api.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

/**
 * Handles business logic implementation for reports.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final DataSource dataSource;

    public ReportServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void generateReport(String report, String format, Map<String, Object> parameters, HttpServletResponse response) throws JRException, IOException, SQLException, BadRequestException {
        InputStream reportStream = getClass().getResourceAsStream("/reports/" + report + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        if (format.equalsIgnoreCase(FormatTypes.PDF.name())) exportReportPDF(jasperPrint, response, report);
        else if (format.toUpperCase().equalsIgnoreCase(FormatTypes.XLS.name()))
            exportReportXLS(jasperPrint, response, report);
        else if (format.toUpperCase().equalsIgnoreCase(FormatTypes.CSV.name()))
            exportReportCSV(jasperPrint, response, report);
        else if (format.toUpperCase().equalsIgnoreCase(FormatTypes.HTML.name()))
            exportReportHTML(jasperPrint, response, report);
        else throw new BadRequestException("incorrect format. please specify if PDF, XLS, CSV or HTML");
    }

    @Override
    public void exportReportPDF(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + reportName + ".pdf");
        response.setContentType("application/octet-stream");

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("Ernesto");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();
    }

    public void exportReportXLS(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + reportName + ".xlsx");
        response.setContentType("application/octet-stream");

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{"Data"});
        exporter.setConfiguration(reportConfig);
        exporter.exportReport();
    }

    public void exportReportCSV(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + reportName + ".csv");
        response.setContentType("application/octet-stream");

        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(response.getOutputStream()));
        exporter.exportReport();
    }

    public void exportReportHTML(JasperPrint jasperPrint, HttpServletResponse response, String reportName) throws JRException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + reportName + ".html");
        response.setContentType("application/octet-stream");

        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
        exporter.exportReport();
    }
}
