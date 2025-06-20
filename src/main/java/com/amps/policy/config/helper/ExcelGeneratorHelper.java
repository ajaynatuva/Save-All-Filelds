package com.amps.policy.config.helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Color;
import java.util.Date;
import java.util.List;

public abstract class ExcelGeneratorHelper<T> {

	protected void setUpWorkSheets(XSSFWorkbook workbook, XSSFSheet sheet, String[] field) {

		XSSFRow row = sheet.createRow(0);
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
//		font.setColor(new XSSFColor(Color.BLACK));
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(new XSSFColor(Color.WHITE, new DefaultIndexedColorMap()));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		int len = field.length;

		for (int i = 0; i < len; i++) {
			XSSFCell genericCell = row.createCell(i);
			genericCell.setCellValue(field[i].toUpperCase());
		}

		for (int k = 0; k < row.getLastCellNum(); k++) {
			row.getCell(k).setCellStyle(style);
		}
	}

	protected void processWorkSheet(XSSFSheet sheet, List<T> entries, XSSFWorkbook workbook, Integer taxonomyFunctionColumnIndex) {
		int exCount = 0;
		int lastCellNum = 0;

		for (T entry : entries) {
			XSSFRow row = sheet.createRow(++exCount);
			List<Object> elements = getColumnElements(entry);
			writeRecord(row, elements, workbook, taxonomyFunctionColumnIndex);
			lastCellNum = row.getLastCellNum();
		}

		for (int i = 0; i < lastCellNum; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	protected void writeRecord(XSSFRow row, List<Object> elements, XSSFWorkbook workbook, Integer taxonomyFunctionColumnIndex) {
		int len = elements.size();

		CellStyle cellStyle = workbook.createCellStyle();

		CreationHelper createHelper = workbook.getCreationHelper();
		short dateFormat = createHelper.createDataFormat().getFormat("yyyy-dd-MM");
		cellStyle.setDataFormat(dateFormat);

		for (int i = 0; i < len; i++) {
			Cell genericCell = row.createCell(i);
			Object value = elements.get(i);
			if (taxonomyFunctionColumnIndex!=null && i == taxonomyFunctionColumnIndex) {
				if (value instanceof Integer) {
					if ((Integer) value == 0) {
						genericCell.setCellValue("Exclude");
					} else if ((Integer) value == 1) {
						genericCell.setCellValue("Applies To");
					} else {
						genericCell.setCellValue((Integer) value);
					}
				} else {
					if (value == null)
						genericCell.setCellValue("None");
				}
			} else {
				if (elements.get(i) == null) {
					genericCell.setCellValue("None");
				} else if (elements.get(i) instanceof Integer) {
					genericCell.setCellValue((Integer) elements.get(i));
				} else if (elements.get(i) instanceof Boolean) {
					genericCell.setCellValue((Boolean) elements.get(i));
				} else if (elements.get(i) instanceof Double) {
					genericCell.setCellValue((Double) elements.get(i));
				} else if (elements.get(i) instanceof Float) {
					genericCell.setCellValue((Float) elements.get(i));
				} else if (elements.get(i) instanceof String) {
					genericCell.setCellValue((String) elements.get(i));
				} else if (elements.get(i) instanceof Long) {
					genericCell.setCellValue((Long) elements.get(i));
				} else if (elements.get(i) instanceof Date) {
					genericCell.setCellStyle(cellStyle);
					;
					genericCell.setCellValue((Date) elements.get(i));
				}

			}
		}
	}

	abstract protected List<Object> getColumnElements(T model);
}
