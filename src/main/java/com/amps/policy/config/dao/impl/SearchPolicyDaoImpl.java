package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.SearchPolicyDao;
import com.amps.policy.config.dto.PolicySearchDTO;
import com.amps.policy.config.dto.PolicySearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchPolicyDaoImpl implements SearchPolicyDao {
    @Autowired
    NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

    @Override
    public List<PolicySearchResultDTO> serachCriteria(PolicySearchDTO policyDto) {


        String query =  """
				SELECT DISTINCT
			    p.policy_number,
			    p.policy_version,
			    p.policy_id,
			    p.custom,
			    p.medical_policy_key_fk,
			    p.sub_policy_key_fk,
			    p.category_fk,
			    p.policy_desc
			FROM
			    policy.policy p
			LEFT JOIN (
			    SELECT DISTINCT
			        policy_id
			    FROM
			        policy.policy_procedures pp
			) pp_unique ON p.policy_id = pp_unique.policy_id
			LEFT JOIN (
			    SELECT DISTINCT
			        policy_id
			    FROM
			        policy.policy_bill_types pb 
			) pb_unique ON p.policy_id = pb_unique.policy_id
			WHERE
			    1=1
							""";
        if (policyDto.getCptCode() != null && !policyDto.getCptCode().isBlank()) {
			String cptCodes = policyDto.getCptCode();
			String duelCptCodes = cptCodes.replace(",", "|");			
		    query += "AND EXISTS (SELECT 1 FROM policy.policy_procedures pp WHERE pp.policy_id = p.policy_id AND pp.cpt_from SIMILAR TO '%(" + duelCptCodes + ")%') ";
		}
        if (policyDto.getProductType() != null && !policyDto.getProductType().isBlank()) {
            String getProductType = policyDto.getProductType();
            String duelgetProductType = getProductType.replace(",", "|");
            query += " AND p.product_type_fk SIMILAR TO '%(" + duelgetProductType + ")%'";
        }
        if (policyDto.getPos() != null && !policyDto.getPos().isBlank()) {
            String posValue = "'" + policyDto.getPos().replace(",", "', '") + "'";
            query += " AND  pp.pos::text in" + "(" + posValue + ")";
        }
        if (policyDto.getBillType() != null && !policyDto.getBillType().isBlank()) {
			String billTypeValues = policyDto.getBillType().replace(",", "|");
		    query += " AND EXISTS (SELECT 1 FROM policy.policy_bill_types pb WHERE pb.policy_id = p.policy_id AND pb.bill_type SIMILAR TO '%(" + billTypeValues + ")%') ";
		}

        if (policyDto.getPolicyId() != null && String.valueOf(policyDto.getPolicyId()) != "") {
            query += " AND p.policy_id::text like " + "'%" + policyDto.getPolicyId() + "%'";
        }
		if (policyDto.getPolicyNumber() != null && !policyDto.getPolicyNumber().isEmpty()) {
			String policyNum = policyDto.getPolicyNumber();
			String[] policyNumberAndVersionArray = policyNum.replaceAll(" ", "").split(",");
			query += " AND (";
			for (int i = 0; i < policyNumberAndVersionArray.length; i++) {
				String[] polVerArray = policyNumberAndVersionArray[i].split("\\.");
				query += "p.policy_number::text = '" + polVerArray[0] + "'";
				if (polVerArray.length > 1) {
					query += " AND p.policy_version::text = '" + polVerArray[1] + "'";
				}

				if (i != policyNumberAndVersionArray.length - 1) {
					query += " OR ";
				}
			}
			query += ")";
		}

        if (policyDto.getCategory() != null && !policyDto.getCategory().isBlank()) {
            String[] categoryArray = policyDto.getCategory().replaceAll(" ", "").split(",");
            query += " AND (";
            for (int i = 0; i < categoryArray.length; i++) {
                query += "p.category_fk = " + categoryArray[i];
                if (!(i == categoryArray.length - 1)) {
                    query += " or ";
                }
            }
            query += ")";
        }
        if (policyDto.getReason() != null && policyDto.getReason() != "") {
            String[] reasonCodeArray = policyDto.getReason().split(",");
            query += " AND (";
            for (int i = 0; i < reasonCodeArray.length; i++) {
                query += "p.reason_code_fk = " + "'" + reasonCodeArray[i] + "'";
                if (!(i == reasonCodeArray.length - 1)) {
                    query += " or ";
                }
            }
            query += ")";
        }
        if (policyDto.getDescription() != null && !policyDto.getDescription().isBlank()) {
            query += " AND p.policy_desc::text ilike " + "'%" + policyDto.getDescription() + "%'";
        }
        if (policyDto.getLob() != null && String.valueOf(policyDto.getLob()) != "") {
            query += " AND p.lob_fk = " + policyDto.getLob();
        }
        if (policyDto.getEbc() != null && !policyDto.getEbc().isBlank()) {
            String[] ebcArray = policyDto.getEbc().split(",");
            query += " AND (";
            for (int i = 0; i < ebcArray.length; i++) {
                query += "p.enforce_before_category = " + ebcArray[i];
                if (!(i == ebcArray.length - 1)) {
                    query += " or ";
                }
            }
            query += ")";
        }
        if (policyDto.getDeactivated() != null && String.valueOf(policyDto.getDeactivated()) != "") {
            query += " AND p.deactivated::text like " + "'%" + policyDto.getDeactivated() + "%'";
        }
        if (policyDto.getDisabled() != null && String.valueOf(policyDto.getDisabled()) != "") {
            query += " AND p.disabled::text like " + "'%" + policyDto.getDisabled() + "%'";
        }
        if (policyDto.getClaimType() != null && !policyDto.getClaimType().isBlank()) {
            String claimData = policyDto.getClaimType();
            String[] clmTypes = claimData.split(",");
            String clmTypeAppened = "%";
            for (String clm : clmTypes) {
                clmTypeAppened += clm + "%";
            }
            query += " AND p.claim_type like" + "'" + clmTypeAppened + "'";
        }
        if (policyDto.getMedicalPolicy() != null && !policyDto.getMedicalPolicy().isBlank()) {
            String[] medicalPolicyArray = policyDto.getMedicalPolicy().split(",");
            query += " AND (";
            for (int i = 0; i < medicalPolicyArray.length; i++) {
                query += "p.medical_policy_key_fk = " + medicalPolicyArray[i];
                if (!(i == medicalPolicyArray.length - 1)) {
                    query += " or ";
                }
            }
            query += ")";
        }
        if (policyDto.getSubPolicy() != null && !policyDto.getSubPolicy().isBlank()) {
            String[] subPolicyArray = policyDto.getSubPolicy().split(",");
            query += " AND (";
            for (int i = 0; i < subPolicyArray.length; i++) {
                query += "p.sub_policy_key_fk = " + subPolicyArray[i];
                if (!(i == subPolicyArray.length - 1)) {
                    query += " or ";
                }
            }
            query += ")";

        }
        if (policyDto.getReference() != null && !policyDto.getReference().isBlank()) {
            query += " AND p.reference::text ilike " + "'%" + policyDto.getReference() + "%'";
        }
        if (policyDto.getPriority() != null && String.valueOf(policyDto.getPriority()) != "") {
            query += " AND p.priority::text like " + "'%" + policyDto.getPriority() + "%'";
        }
        if (policyDto.getCreateDate() != null && String.valueOf(policyDto.getCreateDate()) != "") {
            query += " AND p.created_date::text like " + "'%" + policyDto.getCreateDate() + "%'";
        }
        
        if (policyDto.getClientGroup() != null && String.valueOf(policyDto.getClientGroup()) != "") {
            query += "AND EXISTS (SELECT 1 FROM policy.client_assignment WHERE policy_id = p.policy_id AND client_group_id = " + policyDto.getClientGroup() + ")";
        }

        List<PolicySearchResultDTO> resultSet = ipuNamedParameterJdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(PolicySearchResultDTO.class));

        return resultSet.stream()
                .sorted(Comparator.comparing(PolicySearchResultDTO::getPolicyNumber)
                        .thenComparing(PolicySearchResultDTO::getPolicyVersion))
                .collect(Collectors.toList());
    }
}
