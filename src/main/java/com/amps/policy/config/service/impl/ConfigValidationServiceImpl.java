package com.amps.policy.config.service.impl;

import com.amps.policy.config.dto.ConfigValidationDTO;
import com.amps.policy.config.dto.ConfigValidationReportDTO;
import com.amps.policy.config.helper.ConfigValidationReportHelper;
import com.amps.policy.config.service.ConfigValidationService;
import com.amps.policy.config.util.EmailUtil;
import com.amps.policy.config.util.SharePointUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import static com.amps.policy.config.constants.ParameterConstants.*;

@Service
public class ConfigValidationServiceImpl implements ConfigValidationService {
	
	Logger logger = LogManager.getLogger(ConfigValidationServiceImpl.class);

	@Autowired
	JdbcTemplate ipuJdbcTemplate;

	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	SharePointUtil sharePointUtil;
	
	@Value("${sharepoint.app.link}")
	String sharePointAppLink;

	@Autowired
	ConfigValidationReportHelper configValidationReportHelper;

	public void generateConfigValidationReport(ConfigValidationDTO configValidationDTO) throws IOException {
		
		List<ConfigValidationReportDTO> finalResult = new ArrayList<ConfigValidationReportDTO>();
		String configValidationType=configValidationDTO.getSelectedType();
		String policyId=configValidationDTO.getPolicyId();
		String policyNumber=configValidationDTO.getPolicyNumber();
		String policyVersion=configValidationDTO.getPolicyVersion();
		String emailId= configValidationDTO.getEmailId();
		try {

			Connection con = ipuJdbcTemplate.getDataSource().getConnection();
			con.setAutoCommit(false);
			String query="call policy.p_report_get_config_val_scenario_type(?,?";
			if(configValidationDTO.getSelectedType().equals("SINGLE_POLICY")) {
				query+=",?);";
			}else {
				query+=");";
			}
			CallableStatement callableStatement = con
					.prepareCall(query);
			if(configValidationDTO.getSelectedType().equals("SINGLE_POLICY")) {
				callableStatement.setString(3, policyId);
			}
			callableStatement.setString(1, configValidationType);
			callableStatement.setObject(2, null);
			callableStatement.registerOutParameter(2,Types.REF_CURSOR);
			callableStatement.execute();
			ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
			
			while (resultSet.next()) {

				ConfigValidationReportDTO Report = new ConfigValidationReportDTO();
				Report.setPolicy_id((Integer) resultSet.getInt("policy_id"));
				Report.setPolicy_number((Integer) resultSet.getInt("policy_number"));
				Report.setPolicy_version((Integer) resultSet.getInt("policy_version"));
				Report.setPolicy_desc((String) resultSet.getString("policy_desc"));
				Report.setStatus((String) resultSet.getString("status"));
				Report.setError_message((String) resultSet.getString("error_message"));
				Report.setIs_prod_b((Integer) resultSet.getInt("is_prod_b"));

				finalResult.add(Report);

			}

			resultSet.close();
			con.close();
			
			switch(configValidationType) {
			case "ALL" :createXLAndSaveToSharePoint(finalResult, "ALL", emailId);
						break;
			case "PROD" :createXLAndSaveToSharePoint(finalResult, "PROD", emailId);
						break;
			case "TEST" :createXLAndSaveToSharePoint(finalResult, "TEST", emailId);
						break;
				
			}

			String sharePointPath = sharePointUtil.getSharePointConfigValidationPathForSingleRule(policyNumber, policyVersion);
			String configReport = "Config_Report_" + policyNumber + "-" + policyVersion + ".xlsx";

			if (configValidationType.equals("SINGLE_POLICY") && policyNumber != null) {
				configValidationReportHelper.createXLS(finalResult, sharePointPath, configReport);
				String templateName = CONFIG_VALIDATION_REPORT_FOR_SINGLE_RULE;
				String configReportLink = sharePointAppLink + sharePointUtil.getURL(sharePointPath);
				emailUtil.sendNotificationEmailConfigForSingleRule(templateName, policyId, policyNumber, policyVersion,
						emailId, configReportLink);

			}
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorEmail(emailId, configValidationType);
		}
	}

	public void createXLAndSaveToSharePoint(List<ConfigValidationReportDTO> query, String configReportPath,
			String configEmailIds) {
		try {
			String sharePointPath = sharePointUtil.getSharePointConfigValidationPath(configReportPath);
			String configFileName = "Config_Report.xlsx";
			configValidationReportHelper.createXLS(query, sharePointPath, configFileName);
			sendEmail(sharePointPath, configReportPath, configEmailIds);
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorEmail(configEmailIds, configReportPath);
		}
	}

	public void sendEmail(String sharePointPath, String configReportPath, String configEmailIds) {
		String templateName = CONFIG_VALIDATION_REPORT;
		String configReportLink = sharePointAppLink + sharePointUtil.getURL(sharePointPath);
		emailUtil.sendNotificationEmailConfig(templateName, configEmailIds, configReportLink, configReportPath);
	}

	public void sendErrorEmail(String configEmailIds, String configReportPath) {
		String templateName = CONFIG_VALIDATION_REPORT_FAILURE;
		String configReportLink = null;
		emailUtil.sendNotificationEmailConfig(templateName, configEmailIds, configReportLink, configReportPath);
	}
}
