package com.amps.policy.config.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.amps.policy.config.constants.ParameterConstants.*;

@Component
public class EmailUtil {

	@Autowired
	RestUtil restUtil;

	@Value("${ipu.email.service.host.url}")
	String  emailServiceEndpoint;

	@Value("${env.name}")
	String environment;

	public void sendNotificationEmail(String templateName, Integer policyId, String email, String sourceFileLink,
									  String deltaReportLink, String sourceName) {
		Map<String, String> emailParamsMap = new HashMap<String, String>();
		emailParamsMap.put(POLICY_ID, policyId.toString());
		emailParamsMap.put(SOURCE_FILE_LINK, sourceFileLink);
		if(environment.equalsIgnoreCase("prod"))
			emailParamsMap.put(ENVIRONMENT, "");
		else
			emailParamsMap.put(ENVIRONMENT, environment.toUpperCase());
		if(deltaReportLink!=null)
			emailParamsMap.put(DELTA_REPORT_LINK, deltaReportLink);
		emailParamsMap.put(TEMPLATE_NAME, templateName);
		emailParamsMap.put(SOURCE_NAME, sourceName);
		emailParamsMap.put(RECIPIENTS, getRecipients(email));
		emailParamsMap.put(ENVIRONMENT,environment.toUpperCase());
		restUtil.postMethod(emailServiceEndpoint + EMAIL_SERVICE_URL, emailParamsMap);
	}

	public void sendNotificationEmailConfigForSingleRule(String templateName, String policyId, String policyNumber,
														 String policyVersion, String emailIds, String configReportLink) {
		Map<String, String> emailParamsMap = new HashMap<String, String>();
		emailParamsMap.put(POLICY_ID, policyId.toString());
		emailParamsMap.put(POLICY_NUMBER, policyNumber.toString());
		emailParamsMap.put(POLICY_VERSION, policyVersion.toString());
		emailParamsMap.put(TEMPLATE_NAME, templateName);
		if(environment.equalsIgnoreCase("prod"))
			emailParamsMap.put(ENVIRONMENT, "");
		else
			emailParamsMap.put(ENVIRONMENT, environment.toUpperCase());
		if (configReportLink != null)
			emailParamsMap.put(CONFIG_REPORT_LINK, configReportLink);
		emailParamsMap.put(RECIPIENTS, getRecipients(emailIds));
		emailParamsMap.put(ENVIRONMENT,environment.toUpperCase());
		restUtil.postMethod(emailServiceEndpoint + EMAIL_SERVICE_URL, emailParamsMap);
	}


	public void sendNotificationEmailConfig(String templateName, String emailIds, String configReportLink,
											String statusreport) {
		Map<String, String> emailParamsMap = new HashMap<String, String>();
		emailParamsMap.put(TEMPLATE_NAME, templateName);
		if (configReportLink != null)
			emailParamsMap.put(CONFIG_REPORT_LINK, configReportLink);
		emailParamsMap.put(RECIPIENTS, getRecipients(emailIds));
		emailParamsMap.put(STATUS_REPORT, statusreport);

		emailParamsMap.put(ENVIRONMENT,environment.toUpperCase());

		if(environment.equalsIgnoreCase("prod"))
			emailParamsMap.put(ENVIRONMENT, "");
		else
			emailParamsMap.put(ENVIRONMENT, environment.toUpperCase());

		restUtil.postMethod(emailServiceEndpoint + EMAIL_SERVICE_URL, emailParamsMap);
	}

	String getRecipients(String userEmailId) {
		String supportEmail = "ipu-support";
		if (!environment.equalsIgnoreCase("prod")) {
			supportEmail = supportEmail + "-" + environment;
		}
		supportEmail = supportEmail + "@amps.com";
		return userEmailId + "," + supportEmail;
	}

	public void sendNotificationEmailForStageLoadFailure(String templateName, String email, String errorMessage, String sourceName) {
		Map<String, String> emailParamsMap = new HashMap<String, String>();
		if(environment.equalsIgnoreCase("prod"))
			emailParamsMap.put(ENVIRONMENT, "");
		else
			emailParamsMap.put(ENVIRONMENT, environment.toUpperCase());
		emailParamsMap.put(TEMPLATE_NAME, templateName);
		emailParamsMap.put(ERROR_MESSAGE, errorMessage);
		emailParamsMap.put(SOURCE_NAME, sourceName);
		emailParamsMap.put(RECIPIENTS, getRecipients(email));
		emailParamsMap.put(ENVIRONMENT,environment.toUpperCase());
		restUtil.postMethod(emailServiceEndpoint + EMAIL_SERVICE_URL, emailParamsMap);
	}

}
