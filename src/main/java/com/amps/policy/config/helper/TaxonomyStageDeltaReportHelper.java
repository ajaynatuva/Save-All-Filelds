package com.amps.policy.config.helper;

import com.amps.policy.config.dto.TaxonomyDeltaDTO;
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
import java.util.Arrays;
import java.util.List;

import static com.amps.policy.config.constants.ParameterConstants.DELTA;

@Service
public class TaxonomyStageDeltaReportHelper extends ExcelGeneratorHelper<TaxonomyDeltaDTO> {
    @Autowired
    SharePointUtil sharePointUtil;
    @Autowired
    Environment env;

    public static int findFieldIndex(List<String> filteredFields, String field) {
        return filteredFields.indexOf(field);
    }

    public byte[] createXLS(List<TaxonomyDeltaDTO> delta, String dropboxPath, String fileName) {
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
            Integer taxonomyFunctionColumnIndex = findFieldIndex(filteredFields, "function");
            setUpWorkSheets(workbook, xSSFSheet, filteredFields.toArray(new String[0]));
            List<TaxonomyDeltaDTO> sheetData = new ArrayList<>(delta);
            processWorkSheet(xSSFSheet, sheetData, workbook, taxonomyFunctionColumnIndex);
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
    protected List<Object> getColumnElements(TaxonomyDeltaDTO delta) {
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
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, TaxonomyDeltaDTO.class);
                Method getter = pd.getReadMethod();
                elements.add(getter.invoke(delta));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return elements;
        //        List<Object> elements = new ArrayList<>();
//        String[] fieldNames = getReportFields();
//        for (int i = excludeAction ? 1 : 0; i < fieldNames.length; i++) {
//            try {
//                PropertyDescriptor pd = new PropertyDescriptor(fieldNames[i], TaxonomyDeltaDTO.class);
//                Method getter = pd.getReadMethod();
//                Object f = getter.invoke(delta);
//                elements.add(f);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return elements;
    }

    protected String[] getReportFields() {
        Field[] fields = TaxonomyDeltaDTO.class.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++)
            fieldNames[i] = fields[i].getName();
        return fieldNames;
    }

}
