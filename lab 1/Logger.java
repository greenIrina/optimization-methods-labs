import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Logger {
    public static Workbook book = new HSSFWorkbook();
    public static String outPutFile = ".\\Log.xls";
    private Sheet sheet;
    private Row header;
    private int headersLength;

    public Logger(String method) {
        sheet = book.createSheet(method);
        header = sheet.createRow(0);
    }

    public void createHeaders(List<String> headers) {
        headersLength = headers.size();
        for (int i = 0; i < headersLength; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers.get(i));
            sheet.autoSizeColumn(i);
        }
    }

    public void writeData(List<Double> values, int rowNum) {
        Row row = sheet.createRow(rowNum);
        for (int j = 0; j < headersLength - 1; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(values.get(j));
            sheet.autoSizeColumn(j);
        }
    }

    public void writeInFile() {
        try (FileOutputStream outputStream = new FileOutputStream(outPutFile)) {
            book.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEpsilon(double epsilon) {
        Row row = sheet.getRow(0);
        Cell cell = row.createCell(20);
        cell.setCellValue("Заданная точность: ");
        sheet.autoSizeColumn(20);
        cell = row.createCell(21);
        cell.setCellValue(epsilon);
    }

    public void writeCntFunc(int cnt) {
        Row row = sheet.getRow(1);
        Cell cell = row.createCell(20);
        cell.setCellValue("Функция считалась столько раз: ");
        sheet.autoSizeColumn(20);
        cell = row.createCell(21);
        cell.setCellValue(cnt);
    }

}
