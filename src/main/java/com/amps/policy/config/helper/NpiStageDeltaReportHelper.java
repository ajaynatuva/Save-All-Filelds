package com.amps.policy.config.helper;

import static com.amps.policy.config.constants.ParameterConstants.DELTA;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amps.policy.config.dto.NPIDeltaDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.amps.policy.config.dto.NpiDTO;
import com.amps.policy.config.util.SharePointUtil;
import org.springframework.stereotype.Service;

@Service
public class NpiStageDeltaReportHelper extends ExcelGeneratorHelper<NPIDeltaDTO>  {
    @Autowired
    SharePointUtil sharePointUtil;
    @Autowired
    Environment env;
    public byte[] createXLS(List<NPIDeltaDTO> delta, String dropboxPath, String fileName) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet xSSFSheet = workbook.createSheet(DELTA);
            String[] reportFields = getReportFields();
            List<String> filteredFields = new ArrayList<>(Arrays.asList(reportFields));
            if (delta.stream().allMatch(dto -> dto.getDelta_action() == null)) {
                filteredFields.remove("delta_action");
            }
            if (delta.stream().allMatch(dto -> dto.getReason() == null)) {
                filteredFields.remove("reason");
            }
            setUpWorkSheets(workbook, xSSFSheet, filteredFields.toArray(new String[0]));
            List<NPIDeltaDTO> sheetData = new ArrayList<>(delta);
            processWorkSheet(xSSFSheet, sheetData, workbook,null);
            File tempFile = new File(System.getProperty("java.io.tmpdir") + File.separator + fileName);
            FileOutputStream out = new FileOutputStream(tempFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(out);
            sharePointUtil.readAndUploadFileToSharePoint(tempFile, dropboxPath);
            tempFile.deleteOnExit();
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected List<Object> getColumnElements(NPIDeltaDTO delta) {
        List<Object> elements = new ArrayList<>();
        String[] fieldNames = getReportFields();

        // Determine excluded fields dynamically
        boolean excludeAction = delta.getDelta_action() == null;
        boolean excludeReason = delta.getReason() == null;

        for (String fieldName : fieldNames) {
            if ((excludeAction && fieldName.equals("delta_action")) || (excludeReason && fieldName.equals("reason"))) {
                continue; // Skip excluded fields
            }

            try {
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, NPIDeltaDTO.class);
                Method getter = pd.getReadMethod();
                elements.add(getter.invoke(delta));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return elements;
    }


    protected String[] getReportFields() {
        Field[] fields = NPIDeltaDTO.class.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++)
            fieldNames[i] = fields[i].getName();
        return fieldNames;
    }

}
