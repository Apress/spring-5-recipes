package com.apress.springrecipes.court.web.view;

import com.apress.springrecipes.court.domain.Reservation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ExcelReservationSummary extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings({"unchecked"})
        final List<Reservation> reservations = (List<Reservation>) model.get("reservations");
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Sheet sheet = workbook.createSheet();

        addHeaderRow(sheet);

        reservations.forEach(reservation -> createRow(dateFormat, sheet, reservation));
    }

    private void addHeaderRow(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell((short) 0).setCellValue("Court Name");
        header.createCell((short) 1).setCellValue("Date");
        header.createCell((short) 2).setCellValue("Hour");
        header.createCell((short) 3).setCellValue("Player Name");
        header.createCell((short) 4).setCellValue("Player Phone");
    }


    private void createRow(DateFormat dateFormat, Sheet sheet, Reservation reservation) {
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        row.createCell((short) 0).setCellValue(reservation.getCourtName());
        row.createCell((short) 1).setCellValue(
                dateFormat.format(reservation.getDate()));
        row.createCell((short) 2).setCellValue(reservation.getHour());
        row.createCell((short) 3).setCellValue(
                reservation.getPlayer().getName());
        row.createCell((short) 4).setCellValue(
                reservation.getPlayer().getPhone());
    }
}
