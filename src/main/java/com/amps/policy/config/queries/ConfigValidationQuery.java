package com.amps.policy.config.queries;

public class ConfigValidationQuery {

	public static String prod = " where is_prod_b = 1";

	public static String test = " where is_prod_b = 0";

	public static String policy_data = "select distinct on (policy_id,policy_number)p.* from (";

	public static String billed_failed = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Description Does not have Billed.. ' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_id in (select distinct(policy_id) from policy.policy where policy_desc NOT ILIKE ('%billed%') and policy_desc not ilike('%CCI Modifier Exception Applies%')) and category_fk = 24\r\n"
			+ "";

	public static String cpt_length_failed = "select policy_id, policy_number, policy_version, policy_desc,deactivated, 'FAIL' as STATUS, 'invalid CPT Code found in configuration' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from \r\n"
			+ "	policy.policy where policy_id in (select distinct policy_id from policy.policy_procedures where length(cpt_from)!=5 or length(cpt_to)!=5 ) and category_fk = 24";

	public static String deny_code_failed = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Deny code is not found in configuration' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_id in (select distinct policy_id from policy.policy_procedures except select distinct policy_id from policy.policy_procedures where action_fk=2) and category_fk = 24\r\n"
			+ "";

	public static String mod1_failed = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, 'Invalid Modifier found in Procedures ' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in(select distinct policy_id from policy.policy_procedures where mod1 != '*' and  mod1 not in(select cpt_mod from source.mod_lkp )) and category_fk = 24\r\n"
			+ "";

	public static String modifier_failed = "select distinct policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as status,'Modifier is not configured in Procedures' as error_message,is_prod_b,'' as isdeactivated from policy.policy where policy_desc like ('%billed with modifier%') and policy_id in(select distinct policy_id from policy.policy_procedures where mod1=''or mod2='' or mod3='') and category_fk = 24\r\n"
			+ "";

	public static String exception_failed = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exclude_b not found in exception' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_id in (select distinct policy_id from policy.policy where policy_desc ilike('%exception%') and  policy_desc not ilike('%CCI Modifier Exception Applies%') and policy_id in(select distinct policy_id from policy.policy_procedures except select distinct policy_id from policy.policy_procedures where exclude_b=1)) and category_fk = 24\r\n"
			+ "";

	public static String billed_failed_for_deny = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'deny not found in billed' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
			+ "			where policy_desc ilike ('%billed%') and policy_desc not ilike ('%billed with modifier%') and policy_desc not ilike ('%billed with%') and policy_desc not ilike('%billed on%')\r\n"
			+ "			and policy_id in (select distinct policy_id from policy.policy_procedures \r\n"
			+ "																  where action_fk!=2) and category_fk = 24";

	public static String description_failed = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Description Does not match COnfiguration.. ' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where (policy_desc ilike ('%billed with%') or policy_desc ilike ('%billed or%'))\r\n"
			+ "			and policy_desc not ilike('%billed with modifier%') and policy_desc not ilike ('%billed%')\r\n"
			+ "			and policy_id in (select distinct policy_id from policy.policy_procedures except select policy_id from policy.policy_procedures where action_fk=3) and category_fk = 24\r\n";

	public static String billed_with_modfier_failed_for_deny = "select distinct policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as status,'Deny is not found  in billed with modifier' as error_message,is_prod_b,'' as isdeactivated \r\n"
			+ "			from policy.policy where policy_id in(\r\n"
			+ "			select p.policy_id from policy.policy p, policy.policy_procedures pp where policy_desc ilike ('%billed with modifier%') and p.policy_id=pp.policy_id and (action_fk != 2 and action_fk != 3)) and category_fk = 24";
		
		
	public static String billed_with_modfier_failed_for_deny_code = "select distinct policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as status,'Deny is not found  in billed with modifier' as error_message,is_prod_b,'' as isdeactivated \r\n"
			+ "from policy.policy  where policy_desc ilike ('% billed with modifier %') and policy_id in (select distinct policy_id from policy.policy_procedures \r\n"
			+ "			where  action_fk = 2 and (mod1 = '' or mod2 = '' or mod3 = ''))";
	
			public static String CCI_modifier_applies = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' modifier exception package  configured incorrectly' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%CCI Modifier Exception Applies%' and  ncci_b != 1) ";

			public static String CCI_modifier_applies_null = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' modifier exception package  configured incorrectly' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ " (select distinct policy_id from policy.policy  where policy_desc iLIKE '%CCI Modifier Exception Applies%' and ncci_b is NULL)\r\n";

			public static String CCI_modifier_notapplies = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' modifier exception package not found in desc ' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc not ilike ('%CCI Modifier Exception Applies%')  and  ncci_b = 1)";

			public static String No_ignore_mod = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Ignore modifier 59 configured incorrectly.  ' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%Ignore CMS mod%'  and  group_59_b = 0) ";

			public static String No_ignore_mod_null = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Ignore modifier 59 configured incorrectly.  ' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%Ignore CMS mod%'  and  group_59_b is NULL) ";

			public static String ignore_mod = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Ignore Modifier 59 not found in desc. ' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc not ilike ('%Ignore CMS mod%')  and  group_59_b = 1)";

			public static String No_ccimodifier_ignore_mod = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Allow CMS NCCI Modifiers and Ignore modifier 59 configured incorrectly.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%CCI Modifier Exception Applies%' and policy_desc iLIKE '%Ignore CMS mod%'  and ncci_b=0 and  group_59_b = 0) \r\n";

			public static String No_ccimodifier_ignore_mod_null = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Allow CMS NCCI Modifiers and Ignore modifier 59 configured incorrectly.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE ('%CCI Modifier Exception Applies%') and policy_desc iLIKE '%Ignore CMS mod%'  and ncci_b is NULL and  group_59_b is NULL) \r\n";

			public static String is_ccimodifier_and_ignore_mod = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Ignore Modifier 59 , allow mod ncci modifier not found in desc.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc not ilike ('%CCI Modifier Exception Applies%') and policy_desc  not ilike ('%Ignore CMS mod%')  and ncci_b=1 and  group_59_b = 1) \r\n";

			public static String No_ignore_mod_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Ignore Modifier 59 not found in desc' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%CCI Modifier Exception Applies%' and policy_desc not ilike ('%Ignore CMS mod%') and ncci_b=1 and  group_59_b = 1)\r\n";

			public static String No_CCI_modifier_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' allow cms mod description is not present in the desc' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc not ilike ('%CCI Modifier Exception Applies%') and policy_desc iLIKE '%Ignore CMS mod%' and ncci_b=1 and  group_59_b = 1)\r\n";

			public static String ignore_mod_have_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Allow CMS NCCI modifier is unchecked.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%CCI Modifier Exception Applies%' and policy_desc not ilike ('%Ignore CMS mod%') and ncci_b=0 and  group_59_b = 0)\r\n";

			public static String ignore_mod_have_desc_null = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Allow CMS NCCI modifier is unchecked.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%CCI Modifier Exception Applies%' and policy_desc not ilike ('%Ignore CMS mod%') and ncci_b is NULL and  group_59_b is NULL)\r\n";

			public static String is_ccimodifier_doesnothave_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, 'Allow Ignore Mod 59 is unchecked.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc not ilike ('%CCI Modifier Exception Applies%') and policy_desc iLIKE '%Ignore CMS mod%' and ncci_b=0 and  group_59_b = 0) \r\n";

			public static String is_ccimodifier_doesnothave_desc_null = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, 'Allow Ignore Mod 59 is unchecked.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc not ilike ('%CCI Modifier Exception Applies%') and policy_desc iLIKE '%Ignore CMS mod%' and ncci_b is NULL and  group_59_b is NULL) \r\n";

			public static String is_ignore_mod_desc_notchecked = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Allow CMS NCCI Modifiers and Ignore modifier 59 configured incorrectly and ignore mod unchecked.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%Ignore CMS mod%'  and ncci_b=1 and  group_59_b = 0)";

			public static String is_ignore_mod_desc_notchecked_null = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, ' Allow CMS NCCI Modifiers and Ignore modifier 59 configured incorrectly and ignore mod unchecked.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%Ignore CMS mod%'  and ncci_b=1 and  group_59_b is NULL)";

			public static String is_cci_modifier_desc_notchecked = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, 'Ignore mod desc is not present in descrpition.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%CCI Modifier Exception Applies%'  and ncci_b=0 and  group_59_b = 1)";

			public static String is_cci_modifier_desc_notchecked_null = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, 'Ignore mod desc is not present in descrpition.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc iLIKE '%CCI Modifier Exception Applies%'  and ncci_b is NULL and  group_59_b = 1)";

			public static String nothave_cci_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated,'FAIL' as STATUS, 'allow cms mod description is not present in the desc.' as ERROR_MESSAGE, is_prod_b,'' as isdeactivated from policy.policy where policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy where policy_desc not ilike ('%CCI Modifier Exception Applies%')  and ncci_b=1 and  group_59_b = 1)";
			
			public static String Icd_desc_unchecked = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'missing diagnosis or icd in rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%diagnosis%') and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 0 \r\n"
					+ "	) and (select count (distinct dx_link)from policy.policy_procedures)=1 \r\n";
					
			public static String Icd_desc_unchecked1 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'missing diagnosis or icd in rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%ICD%') and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 0 \r\n"
					+ "	) and (select count (distinct dx_link)from policy.policy_procedures)=1 \r\n";
			
			public static String nothave_Icd_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'missing diagnosis or icd in rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where policy_desc not ilike ('%diagnosis%') AND policy_desc not ilike('%Icd%') and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 1) and policy_id in (select distinct policy_id from policy.policy_dx)";
			
			public static String nothave_Icd_desc1 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'missing diagnosis or icd in rule description' \r\n"
					+ "as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where policy_desc not ilike ('%diagnosis%') AND policy_desc not ilike('%Icd%') \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 1 )\r\n"
					+ "and policy_id not in(select distinct policy_id from policy.policy_dx)\r\n";
			
			public static String have_diags_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'missing diagnosis or icd in rule description' as ERROR_MESSAGE,is_prod_b,'' \r\n"
					+ "as isdeactivated from policy.policy\r\n"
					+ "where policy_desc ilike ('%diagnosis%')  \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 1)\r\n"
					+ "and policy_id not in(select distinct policy_id from policy.policy_dx) \r\n";
			
			public static String have_Icd_desc = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'missing diagnosis or icd in rule description' as ERROR_MESSAGE,is_prod_b,'' \r\n"
					+ "as isdeactivated from policy.policy\r\n"
					+ "where policy_desc ilike ('%Icd%')  \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 1)\r\n"
					+ "and policy_id not in(select distinct policy_id from policy.policy_dx) \r\n";
			
			public static String duplicate_dosFrom  = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'missing diagnosis or icd in rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where policy_id in (select distinct policy_id from policy.policy_dx group by policy_id,diag_from,diag_to,dos_from having count(*)>1)";
			
			public static String duplicate_data = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,\r\n"
					+ "'Duplicate data is there' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where  policy_desc ilike ('%diagnosis%') \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 1)\r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_dx group by policy_id,diag_from,diag_to,dos_from having count(*)>1) \r\n";
			
			public static String same_day = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%same day%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != 0 or days_hi != 0) or (days_lo is NUll or days_hi is NULL)))";
		
			public static String day_prior = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%day prior%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != 1 or days_hi != 1) or (days_lo is NUll or days_hi is NULL)))";
			
			public static String Previous_90_days = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%Previous 90 days%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != -1 or days_hi != -90) or (days_lo is NUll or days_hi is NULL)))";
			
			public static String Previous_10_days = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%Previous 10 days%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != -1 or days_hi != -10) or (days_lo is NUll or days_hi is NULL)))";
			
			public static String with_In_Ten_days = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%within 10 days%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != -1 or days_hi != -10) or (days_lo is NUll or days_hi is NULL)))";
		
			public static String with_In_90_days = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%within 90 days%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != -1 or days_hi != -90) or (days_lo is NUll or days_hi is NULL)))";
		
			public static String day_prior_surgery = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%day prior to surgery%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != +1 or days_hi != +1)  or (days_lo is NUll or days_hi is NULL)))";
			
			public static String Deny_codes = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Deny Config Failed'\r\n"
					+ "as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_id in (select policy_id from policy.policy_procedures where action_fk = 2 and (clm_link_fk = 1 or clm_link_fk = 0)  and ((days_lo != 0 or days_hi != 0) or (days_lo is NUll or days_hi is NULL)))\r\n";

			public static String same_date = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match rule description' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "	where policy_desc ilike ('%same date%')  and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and ((days_lo != 0 or days_hi != 0) or (days_lo is NUll or days_hi is NULL)))";
		
//			public static String Bwor_cond = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration does not match' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
//					+ "	where policy_desc not ilike ('%same day%') and policy_desc not ilike ('%same date%') and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and (days_lo = 0 or days_hi = 0))";
			
			public static String Bwor_cond1 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Description does not match' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated \r\n"
					+ " from policy.policy\r\n"
					+ " where policy_desc not ilike ('%day prior%') and policy_desc not ilike ('%day prior to surgery%')\r\n"
					+ " and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and (days_lo = 1 and days_hi = 1))";
			
			public static String Bwor_cond2 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Description does not match' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated \r\n"
					+ " from policy.policy\r\n"
					+ " where policy_desc not ilike ('%Previous 90 days%') and policy_desc not ilike ('%within 90 days%')\r\n"
					+ " and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and (days_lo = -1 and days_hi = -90))";
			
			public static String Bwor_cond3 = " select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Description does not match' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated \r\n"
					+ " from policy.policy\r\n"
					+ " where policy_desc not ilike ('%Previous 10 days%') and policy_desc not ilike ('%within 10 days%')\r\n"
					+ " and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 3 and (days_lo = -1 and days_hi = -10))";
			
			public static String Billed_With_Icd = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Action Configured incorrectly' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where policy_desc  ilike ('% Billed with ICD %') and policy_id in (select distinct policy_id from policy.policy_procedures where (dx_link = 0 or dx_link is NULL))\r\n"
					+ "and policy_id in(select distinct policy_id from policy.policy_dx where action != 2)";
			
			public static String Billed_With_Icd1 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Action Configured incorrectly' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where policy_desc  ilike ('% Billed with ICD %') and policy_id in (select distinct policy_id from policy.policy_procedures where dx_link = 1)\r\n"
					+ "and policy_id in(select distinct policy_id from policy.policy_dx where action != 2)";
			
			public static String Claim_Test = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration Error - Claim link' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where npi_logic_fk = 2 and policy_id in (select distinct policy_id from policy.policy_procedures where clm_link_fk =1 and (exclude_b = 0 or exclude_b is NULL))\r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration Error - Claim link' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where tax_logic_fk = 2 and policy_id in (select distinct policy_id from policy.policy_procedures where clm_link_fk =1 and (exclude_b = 0 or exclude_b is NULL))\r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'Configuration Error - Claim link' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where subspec_logic_fk = 3 and policy_id in (select distinct policy_id from policy.policy_procedures where clm_link_fk =1 and (exclude_b = 0 or exclude_b is NULL))";
			
			public static String exclusion_checked = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,'desc donot have exception word' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy\r\n"
					+ "where policy_desc not ilike ('%exception%') and policy_id in (select distinct policy_id from policy.policy_procedures where exclude_b = 1 )";
			
			public static String exception1 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%E/M services billed with Modifier 24;%')\r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures  where (mod1 = '24' or mod2 = '24' or mod3 = '24') \r\n"
					+ " and action_fk = 2 and (exclude_b = 0 or exclude_b is NULL)) \r\n"
					+ " union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%E/M services billed with Modifier 24;%')\r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures  where (mod1 = '24' or mod2 = '24' or mod3 = '24') \r\n"
					+ " and action_fk != 2 and exclude_b = 1)";
			
			public static String exception2 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where  policy_desc ilike ('%Evaluation and Management services  billed with modifier 24%')\r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures  where (mod1 = '24' or mod2 = '24' or mod3 = '24') \r\n"
					+ " and action_fk = 2 and (exclude_b = 0 or exclude_b is NULL))\r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where  policy_desc ilike ('%Evaluation and Management services  billed with modifier 24%')\r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures  where (mod1 = '24' or mod2 = '24' or mod3 = '24') \r\n"
					+ " and action_fk != 2 and exclude_b = 1)";

			public static String exception3 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% E/M services billed with Modifier 25; %') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '25' or mod2 = '25' or mod3 = '25') \r\n"
					+ "and  action_fk = 2 and (exclude_b = 0 or exclude_b is NULL))\r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% E/M services billed with Modifier 25; %') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '25' or mod2 = '25' or mod3 = '25')\r\n"
					+ "and  action_fk != 2 and exclude_b = 1)";

			public static String exception4 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where  policy_desc ilike ('%Evaluation and Management services  billed with modifier 25%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '25' or mod2 = '25' or mod3 = '25') \r\n"
					+ "and  action_fk = 2 and (exclude_b = 0 or exclude_b is NULL)) \r\n"
					+ " union \r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where  policy_desc ilike ('%Evaluation and Management services  billed with modifier 25%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '25' or mod2 = '25' or mod3 = '25') \r\n"
					+ "and  action_fk != 2 and exclude_b = 1)";

			public static String exception5 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%Modifier 57 appended to E/M codes%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '57' or mod2 = '57' or mod3 = '57') and  action_fk  = 2 and\r\n"
					+ "(exclude_b = 0 or exclude_b is NULL)) \r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%Modifier 57 appended to E/M codes%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '57' or mod2 = '57' or mod3 = '57') and  action_fk  != 2 and\r\n"
					+ "exclude_b = 1)";

			public static String exception6 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%99291-99292 billed with modifier FT.%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where  (cpt_from = '99291' or cpt_from = '99292')\r\n"
					+ " and (mod1 = 'FT' or mod2 = 'FT' or mod3 = 'FT')  and (exclude_b = 0 or exclude_b is NULL))";

			public static String exception7 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%Surgical services billed with Modifier 78;%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where  (mod1 = '78' or mod2 = '78' or mod3 = '78') and action_fk = 3 and (exclude_b = 0 or exclude_b is NULL))\r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%Surgical services billed with Modifier 78;%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where  (mod1 = '78' or mod2 = '78' or mod3 = '78') and action_fk != 3 \r\n"
					+ " and exclude_b = 1)";

			public static String exception8 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%Codes 99291-99292 when billed with ICD codes S00-S99.929S, T07-T14.91XS, T20-T32.99, T79-T79.7XXS, T79.A-T79.A9XS, or T79.8-T79.9XXS;%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where dx_link = 1 and policy_id in (select distinct policy_id from policy.policy_dx\r\n"
					+ "where diag_from \r\n"
					+ "not ilike('%S00-S99.929S %') and diag_from not ilike('%T07-T14.91XS%') and diag_from not ilike('%T20-T32.99%') \r\n"
					+ "and not diag_from ilike('% T79-T79.7XXS %') and not diag_from ilike('% T79.A-T79.A9XS %') and not diag_from ilike('%T79.8-T79.9XXS%')))\r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('%Codes 99291-99292 when billed with ICD codes S00-S99.929S, T07-T14.91XS, T20-T32.99, T79-T79.7XXS, T79.A-T79.A9XS, or T79.8-T79.9XXS;%') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where dx_link = 1 and policy_id not in (select distinct policy_id from policy.policy_dx)) ";

			public static String exception9 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% Modifier 54, 55 or 56 submitted on the billed with 10-day global surgical procedure %') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '54' or mod1 = '55' or mod1 = '56' or\r\n"
					+ " mod2 = '54' or mod2 = '55' or mod2 = '56' or mod3 = '54' or mod3 = '55' or mod3 = '56') \r\n"
					+ " and action_fk = 3 and (exclude_b = 0 or exclude_b is NULL)) \r\n"
					+ "union\r\n"
					+ "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% Modifier 54, 55 or 56 submitted on the billed with 10-day global surgical procedure %') \r\n"
					+ "and policy_id in \r\n"
					+ "(select distinct policy_id from policy.policy_procedures where (mod1 = '54' or mod1 = '55' or mod1 = '56' \r\n"
					+ "or mod2 = '54' or mod2 = '55' or mod2 = '56' or mod3 = '54' or mod3 = '55' or mod3 = '56')\r\n"
					+ " and action_fk != 3 and exclude_b = 1)";

			public static String exception10 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% 90 day codes billed on the same date of service %') \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where (exclude_b = 0 or exclude_b is NULL)) ";

			public static String exception11 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% Therapeutic shoes (A5500-A5514), Pessary (A4561-A4563), Other supplies and Devices (A9150-A9270, A9273-A9300)%') \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 2 and  (exclude_b = 0 or exclude_b is NULL))";

			public static String exception12 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% A4641, A4642, A9500-A9700 A4648 and A4650 %') \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where action_fk = 2 and  (exclude_b = 0 or exclude_b is NULL)) ";

			public static String exception13 = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS, 'Exception not checked' as \r\n"
					+ "ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where policy_desc  ilike ('% 01996 billed with modifier 59, XU %') \r\n"
					+ "and policy_id in (select distinct policy_id from policy.policy_procedures where (exclude_b = 0 or exclude_b is NULL)) \r\n"
			        + "order by policy_id)p";

			
//			public static String allExceptions = "select S.policy_id,S.policy_number,S.policy_version,S.policy_desc, S.deactivated,CASE WHEN S.ERROR_MESSAGE IS NULL THEN 'PASS' ELSE 'FAIL'\r\n"
//					+ "	END STATUS ,S.ERROR_MESSAGE,S.is_prod_b, '' as isdeactivated  from \r\n"
//					+ "	(select policy_id,policy_number,policy_version,policy_desc,deactivated, 'FAIL' as STATUS,\r\n"
//					+ "            case\r\n"
//					+ "            when policy_desc ilike ('%AS, 80, 81 or 82 on the surgical procedure codes or E/M codes%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%99291-99292 billed with modifier FT%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%E/M services billed with Modifier 24%') or policy_desc ilike ('%Evaluation and Management services  billed with modifier 24%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%E/M services billed with Modifier 25%') or policy_desc ilike ('%Evaluation and Management services  billed with modifier 25%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%Surgical services billed with Modifier 78%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%01996 billed with modifier 59, XU%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('% Modifier 57 appended to E/M codes%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%A4641, A4642, A9500-A9700 A4648 and A4650%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%Therapeutic shoes (A5500-A5514), Pessary (A4561-A4563), Other supplies and Devices (A9150-A9270, A9273-A9300)%') then 'Exception not checked'\r\n"
//					+ "            when policy_desc ilike ('%90 day codes billed on the same date of service%') then 'Exception not checked'\r\n"
//					+ "	           when policy_desc ilike ('%99291-99292 billed with ICD-10 codes S00-S99.929S, T07-T14.91XS, T20-T32.99, \r\n"
//					+ "									T79-T79.7XXS, T79.A-T79.A9XS, or T79.8-T79.9XXS%') then 'Exception not checked'\r\n"
//					+ "            end\r\n"
//					+ "            as ERROR_MESSAGE,\r\n"
//					+ "            is_prod_b,'' as isdeactivated\r\n"
//					+ "            from policy.policy where policy_id in\r\n"
//					+ "	 (select distinct policy_id from policy.policy_procedures where exclude_b = 0 or exclude_b is NULL))S \r\n"
//					+ "order by policy_id, status ASC)p";

	public static String pass_condition_all_policy = "select policy_id,policy_number,policy_version,policy_desc,deactivated, 'Pass' as STATUS, 'none' as ERROR_MESSAGE,is_prod_b,'' as isdeactivated from policy.policy where category_fk = 24 order by policy_id ";

	public static String union = "  UNION  ";

}