package com.amps.policy.config.helper;

import com.amps.policy.config.dto.ProcsDeltaDTO;
import com.amps.policy.config.util.SharePointUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.amps.policy.config.constants.ParameterConstants.DELTA;

@Service
public class ProceduresStageDeltaReportHelper extends ExcelGeneratorHelper<ProcsDeltaDTO> {

//	@Autowired
//	DropboxUtil dropboxUtil;
	
	@Autowired
	SharePointUtil sharePointUtil;

	@Autowired
	Environment env;

	public byte[] createXLS(List<ProcsDeltaDTO> delta, String dropboxPath, String fileName) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet xSSFSheet = workbook.createSheet(DELTA);
			String[] reportFields = getReportFields();

			setUpWorkSheets(workbook, xSSFSheet, reportFields);

			List<ProcsDeltaDTO> sheetData = new ArrayList<ProcsDeltaDTO>();
			for (ProcsDeltaDTO dto : delta)
				sheetData.add(dto);
			processWorkSheet(xSSFSheet, sheetData, workbook, null);
			File tempFile = new File(System.getProperty("java.io.tmpdir") + File.separator + fileName);
			FileOutputStream out = new FileOutputStream(tempFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(out);
			sharePointUtil.readAndUploadFileToSharePoint(tempFile, dropboxPath);
			tempFile.deleteOnExit();
			return bos.toByteArray();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	protected List<Object> getColumnElements(ProcsDeltaDTO delta) {
		List<Object> elements = new ArrayList<>();
		String[] fieldNames = getReportFields();
		for (int i = 0; i < fieldNames.length; i++) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(fieldNames[i], ProcsDeltaDTO.class);
				Method getter = pd.getReadMethod();
				Object f = getter.invoke(delta);
				elements.add(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return elements;
	}

	protected String[] getReportFields() {
		Field[] fields = ProcsDeltaDTO.class.getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++)
			fieldNames[i] = fields[i].getName();
		return fieldNames;
	}

}
