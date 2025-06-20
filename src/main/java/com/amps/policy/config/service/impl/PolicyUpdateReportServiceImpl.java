package com.amps.policy.config.service.impl;

import com.amps.policy.config.dto.PolicyUpdateResultReportDTO;
import com.amps.policy.config.dto.PolicyUploadDTO;
import com.amps.policy.config.service.PolicyUpdateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.amps.policy.config.constants.ParameterConstants.*;

@Service
public class PolicyUpdateReportServiceImpl implements PolicyUpdateReportService {

    @Autowired
    NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

    @Override
    public List<PolicyUpdateResultReportDTO> getPolicyUpdateReport(List<PolicyUpdateResultReportDTO> reportDTO) {
        List<PolicyUpdateResultReportDTO> sameOrSimData = new ArrayList<>();
        for (PolicyUpdateResultReportDTO dto : reportDTO) {
            String status = dto.getStatus() == null ? "" : dto.getStatus();

            List<String> allCptCodes = parseCsv(dto.getCptCode());
            List<String> modifier = parseCsv(dto.getModifier());
            List<String> keyWord = parseCsv(dto.getKeyWord());
            List<String> revenueCode = parseCsv(dto.getRevenueCode());
            List<String> billType = parseCsv(dto.getBillType());
            List<String> diagnosis = parseCsv(dto.getPolicyDiagnosis());
            List<String> placeOfService = parseCsv(dto.getPlaceOfService());
            List<String> conditionCode = parseCsv(dto.getConditionCode());
            List<String> numberSeries = List.of(dto.getNumber().toString());

            ensureNonEmpty(
                    allCptCodes,
                    modifier,
                    revenueCode,
                    placeOfService,
                    keyWord,
                    billType,
                    diagnosis,
                    conditionCode
            );

            List<List<String>> result = mapLists(numberSeries, allCptCodes, modifier, keyWord, revenueCode, billType, diagnosis, placeOfService, conditionCode);
            String query = null;
            for (int i = 0; i <= result.size() - 1; i++) {
                MapSqlParameterSource params = createQueryParams(result.get(i));
                String filterQuery = filterData(params, status);
                String filterQuery1 = filterData1(params, status);
                query = getQuery(status, dto.getCptCode(), filterQuery, filterQuery1, dto.getBillType(), dto.getPolicyDiagnosis(),
                        dto.getConditionCode(), dto.getKeyWord(), dto.getModifier(), dto.getRevenueCode(), dto.getPlaceOfService());
                if (query != null) {
                    sameOrSimData.addAll(ipuNamedParameterJdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(PolicyUpdateResultReportDTO.class)));
                }
            }
            updateNumberSeries(sameOrSimData, numberSeries, status);
        }
        return sameOrSimData;
    }


    private void ensureNonEmpty(List<String>... lists) {
        for (List<String> list : lists) {
            if (list.isEmpty()) {
                list.add("");
            }
        }
    }

    private List<String> parseCsv(String csv) {
        return csv != null && !csv.equals("undefined") ? Arrays.asList(csv.split(",")) : new ArrayList<>();
    }

    private String getQuery(String status, String cptCode, String filterQuery, String filterQuery1, String billType, String diagnosis, String conditionCode, String keyWord,
                            String modifier, String revenueCode, String placeOfService) {
        if (Objects.equals(cptCode, "undefined") && status.isEmpty()) {
            if (modifier != null || revenueCode != null || placeOfService != null) {
                return buildBlankQuery(filterQuery, filterQuery1);
            } else if (billType != null || diagnosis != null || conditionCode != null || keyWord != null) {
                return policyLevelInformation(filterQuery);
            }
        } else {
            if (status.equalsIgnoreCase("NEW")) {
                return buildNewQuery(filterQuery, filterQuery1);
            }
            if (status.isEmpty() || status.equalsIgnoreCase("CHG") || status.equalsIgnoreCase("DEL")) {
                return buildBlankQuery(filterQuery, filterQuery1);
            }
        }
        return null;
    }

    private MapSqlParameterSource createQueryParams(List<String> params) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(KEYWORD, params.get(3));
        inQueryParams.addValue(REVENUE_CODE, params.get(4));
        inQueryParams.addValue(CPT_CODES, params.get(1));
        inQueryParams.addValue(MODIFIER, params.get(2));
        inQueryParams.addValue(BILL_TYPE, params.get(5));
        inQueryParams.addValue(DIAGNOSIS, params.get(6));
        inQueryParams.addValue(PLACE_OF_SERVICE, params.get(7));
        inQueryParams.addValue(CONDITION_CODE, params.get(8));
        return inQueryParams;
    }

    private void updateNumberSeries(List<PolicyUpdateResultReportDTO> data, List<String> numberSeries, String status) {
        for (PolicyUpdateResultReportDTO dto : data) {
            if (dto.getNumber() == null) {
                dto.setNumber(Integer.valueOf(numberSeries.get(0)));
                dto.setStatus(status);
            }
        }
    }

    public String filterData1(MapSqlParameterSource params, String status) {
        String query = "";
        if (status.equalsIgnoreCase("NEW") || (status.isEmpty() || status.equalsIgnoreCase("CHG") || status.equalsIgnoreCase("DEL"))) {
            if (params.getValue(BILL_TYPE) != "" && params.getValue(BILL_TYPE) != null) {
                query += " AND pb.bill_type::text ilike " + "'%" + params.getValue(BILL_TYPE) + "%'";
            }
            if (params.getValue(DIAGNOSIS) != "" && params.getValue(DIAGNOSIS) != null) {
                query += " AND (dx.diag_from = " + "'" + params.getValue(DIAGNOSIS) + "'" + " OR dx.diag_to = " + "'" + params.getValue(DIAGNOSIS) + "')";
            }
            if (params.getValue(CONDITION_CODE) != "" && params.getValue(CONDITION_CODE) != null) {
                query += " AND cc.condition_code::text ilike " + "'%" + params.getValue("condCode") + "%'";
            }
        }
        return query;
    }

    public String filterData(MapSqlParameterSource params, String status) {
        String query = "";
        if (params.getValue(CPT_CODES) == "" && (params.getValue(MODIFIER) == "" || params.getValue(REVENUE_CODE) == "" || params.getValue(PLACE_OF_SERVICE) == "") &&
                (params.getValue(BILL_TYPE) != "" || params.getValue(DIAGNOSIS) != "" || params.getValue(CONDITION_CODE) != "" || params.getValue(KEYWORD) != "") &&
                (status.isEmpty() || status.equalsIgnoreCase("CHG") || status.equalsIgnoreCase("DEL"))) {
            if (params.getValue(BILL_TYPE) != "" && params.getValue(BILL_TYPE) != null) {
                query += " and pb.bill_type::text ilike " + "'%" + params.getValue(BILL_TYPE) + "%'";
            }
            if (params.getValue(DIAGNOSIS) != "" && params.getValue(DIAGNOSIS) != null) {
                query += " and (dx.diag_from = " + "'" + params.getValue(DIAGNOSIS) + "'" + " OR dx.diag_to = " + "'" + params.getValue(DIAGNOSIS) + "')";
            }
            if (params.getValue(CONDITION_CODE) != "" && params.getValue(CONDITION_CODE) != null) {
                query += " and cc.condition_code::text ilike " + "'%" + params.getValue(CONDITION_CODE) + "%'";
            }
            if (params.getValue(KEYWORD) != "" && params.getValue(KEYWORD) != null) {
                query += " and p.policy_desc::text ilike " + "'%" + params.getValue(KEYWORD) + "%'";
            }
        } else {
            if (params.getValue(CPT_CODES) != "" && params.getValue(CPT_CODES) != null) {
                if (status.equalsIgnoreCase("NEW")) {
                    query += " and (pp.cpt_from IN (select same_or_sim_code from source.same_or_sim where cpt_code::text ilike " + "'%" + params.getValue(CPT_CODES) + "%'))";
                } else {
                    query += " AND pp.cpt_from::text ilike" + "'%" + params.getValue(CPT_CODES) + "%'";
                }
            }
            if (params.getValue(MODIFIER) != "" && params.getValue(MODIFIER) != null) {
                query += " AND (mod1 = " + "'" + params.getValue(MODIFIER) + "'" + " OR mod2 = " + "'" + params.getValue(MODIFIER) + "'" + "  " +
                        "OR mod3 = " + "'" + params.getValue(MODIFIER) + "')";
            }
            if (params.getValue(PLACE_OF_SERVICE) != "" && params.getValue(PLACE_OF_SERVICE) != null) {
                query += " AND pp.pos::text ilike " + "'%" + params.getValue(PLACE_OF_SERVICE) + "%'";
            }
            if (params.getValue(KEYWORD) != "" && params.getValue(KEYWORD) != null) {
                query += " AND p.policy_desc::text ilike " + "'%" + params.getValue(KEYWORD) + "%'";
            }
            if (params.getValue(REVENUE_CODE) != "" && params.getValue(REVENUE_CODE) != null) {
                query += " AND '" + params.getValue(REVENUE_CODE) + "' BETWEEN rev_from AND rev_to";
            }
        }
        return query;
    }

    private String buildNewQuery(String filterQuery, String filterQuery1) {
        return
                """
                        with c1 as (Select  p.policy_id,c.policy_category_desc as policyCategory,
                                    mp.medical_policy_desc as medicalPolicy,
                                    sp.sub_policy_desc as subPolicy,
                                     case  when P.CUSTOM =  false then 'NO'
                                           when P.CUSTOM =  true then 'YES'
                                          END  as customVersion,
                                    lb.lob_title as lob,
                                    p.policy_number||'.'||p.policy_version as policyVersion ,
                                    p.policy_desc as medicalPolicyDesc  ,
                                    pp.cpt_from  sameOrSimCode ,
                                    (:cptCodes) as cptCode,
                                    :keyWord as keyWord ,
                                    COALESCE(NULLIF(pp.mod1, '*'), NULLIF(pp.mod2, '*'), pp.mod3) AS modifier,
                                   pp.pos  placeOfService ,
                                   case when rev_from=:revCode then rev_from else case when rev_to=:revCode then rev_to else\s
                                   case when (:revCode)='' then rev_from||'-'||rev_to else (:revCode) end
                                   end end as revenueCode,
                                   pp.dos_from as dosFrom ,
                                   pp.dos_to as dosTo ,
                                   pa.policy_cpt_action_code as cptHcpcsAction ,
                                   P.claim_type as claimType ,
                                   bt.policy_bill_type_action_code as policyBillTypeActionCode ,
                                   pp.exclude_b as exclusion,
                                   pp.rev_from,
                                   pp.rev_to,
                                   p.is_prod_b as isProd ,
                                     p.deactivated as deactivated ,
                                       p.disabled as disabled\s
                                    from policy.policy p
                                        left join policy.medical_policies mp on mp.medical_policy_key = p.medical_policy_key_fk
                                        left join policy.sub_policies sp on sp.sub_policy_key = p.sub_policy_key_fk
                                        left join policy.policy_procedures pp on pp.policy_id = p.policy_id
                                        left join policy.policy_cpt_action_lkp pa on pa.policy_cpt_action_key = pp.action_fk
                                        left join policy.policy_category_lkp c on c.policy_category_lkp_id=p.category_fk
                                        left join policy.lob_lkp lb on lb.lob_key=p.lob_fk
                                        left join policy.policy_bill_type_action_lkp
                                        bt on bt.policy_bill_type_action_key=p.bill_type_action_fk
                                     where 1=1
                         """ + filterQuery + """
                        ),
                         c2 as( select p.policy_id, string_agg(distinct pb.bill_type,',' ORDER BY bill_type ) as billType ,
                                     string_agg(distinct dx.diag_from || '-' || dx.diag_to, ', ') as policyDiagnosis\s
                                     from policy.policy p
                                     left join policy.policy_bill_types pb on p.policy_id=pb.policy_id
                                     left join policy.policy_condition_code cc on cc.policy_id = p.policy_id
                                     left join policy.policy_dx dx on dx.policy_id=p.policy_id
                                     where 1=1
                        """ + filterQuery1 + """
                        group by p.policy_id
                        )
                        select \s
                                                           -- All columns from c1
                                    c1.policy_id,
                                    c1.policyCategory,
                                    c1.medicalPolicy,
                                    c1.subPolicy,
                                    c1.customVersion,
                                    c1.lob,
                                    c1.policyVersion,
                                    c1.medicalPolicyDesc,
                                    c1.sameOrSimCode,
                                    c1.cptCode,
                                    c1.keyWord,
                                    c1.modifier,
                                    c1.placeOfService,
                                    c1.revenueCode,
                                    c1.dosFrom,
                                    c1.dosTo,
                                    c1.cptHcpcsAction,
                                    c1.claimType,
                                    c1.policyBillTypeActionCode,
                                    c1.exclusion,
                                    c1.rev_from,
                                    c1.rev_to,
                                    c1.isProd,
                                    c1.deactivated,
                                    c1.disabled,
                                    -- All columns from c2
                                    c2.billType,
                                    c2.policyDiagnosis
                            from c1 INNER join c2 on c1.policy_id=c2.policy_id;
                        """;
    }

    private String buildBlankQuery(String filterQuery, String filterQuery1) {
        return
                """
                        with c1 as (Select  p.policy_id,c.policy_category_desc as policyCategory,
                                              mp.medical_policy_desc as medicalPolicy,
                                              sp.sub_policy_desc as subPolicy,
                                               case  when P.CUSTOM =  false then 'NO'
                                                     when P.CUSTOM =  true then 'YES'
                                                    END  as customVersion,
                                              lb.lob_title  as lob,
                                              p.policy_number||'.'||p.policy_version as policyVersion,
                                              p.policy_desc as medicalPolicyDesc ,
                                              '' as sameOrSimCode,
                                              pp.cpt_from as cptCode,
                                              (:keyWord) as keyWord,
                                              COALESCE(NULLIF(pp.mod1, '*'), NULLIF(pp.mod2, '*'), pp.mod3) AS modifier,
                                             pp.pos as placeOfService,
                                             case when rev_from=:revCode then rev_from else case when rev_to=:revCode then rev_to else\s
                                             case when (:revCode)='' then rev_from||'-'||rev_to else (:revCode) end
                                             end end as revenueCode,
                                             pp.dos_from as dosFrom,
                                             pp.dos_to as dosTo,
                                             pa.policy_cpt_action_code as cptHcpcsAction,
                                             P.claim_type as claimType,
                                             bt.policy_bill_type_action_code as policyBillTypeActionCode,
                                             pp.exclude_b as exclusion,
                                             pp.rev_from,
                                             pp.rev_to,
                                              p.is_prod_b as isProd,
                                             p.deactivated as deactivated,
                                              p.disabled as disabled
                                              from policy.policy p
                                                  left join policy.medical_policies mp on mp.medical_policy_key = p.medical_policy_key_fk
                                                  left join policy.sub_policies sp on sp.sub_policy_key = p.sub_policy_key_fk
                                                  left join policy.policy_procedures pp on pp.policy_id = p.policy_id
                                                  left join policy.policy_cpt_action_lkp pa on pa.policy_cpt_action_key = pp.action_fk
                                                  left join policy.policy_category_lkp c on c.policy_category_lkp_id=p.category_fk
                                                  left join policy.lob_lkp lb on lb.lob_key=p.lob_fk
                                                  left join policy.policy_bill_type_action_lkp
                                                  bt on bt.policy_bill_type_action_key=p.bill_type_action_fk
                                               where 1=1
                            """ + filterQuery + """
                                 ),
                                c2 as(select p.policy_id, string_agg(distinct pb.bill_type,',' ORDER BY bill_type ) as billType,
                                             string_agg( distinct dx.diag_from || '-' || dx.diag_to, ', ') as policyDiagnosis
                                             from policy.policy p
                                             left join policy.policy_bill_types pb on p.policy_id=pb.policy_id
                                             left join policy.policy_condition_code cc on cc.policy_id = p.policy_id
                                             left join policy.policy_dx dx on dx.policy_id=p.policy_id
                                             where 1=1
                        """ + filterQuery1 + """
                        group by p.policy_id
                                      )
                                      select\s
                                                                       -- All columns from c1
                                                c1.policy_id,
                                                c1.policyCategory,
                                                c1.medicalPolicy,
                                                c1.subPolicy,
                                                c1.customVersion,
                                                c1.lob,
                                                c1.policyVersion,
                                                c1.medicalPolicyDesc,
                                                c1.sameOrSimCode,
                                                c1.cptCode,
                                                c1.keyWord,
                                                c1.modifier,
                                                c1.placeOfService,
                                                c1.revenueCode,
                                                c1.dosFrom,
                                                c1.dosTo,
                                                c1.cptHcpcsAction,
                                                c1.claimType,
                                                c1.policyBillTypeActionCode,
                                                c1.exclusion,
                                                c1.rev_from,
                                                c1.rev_to,
                                                c1.isProd,
                                                c1.deactivated,
                                                c1.disabled,
                                                -- All columns from c2
                                                c2.billType,
                                                c2.policyDiagnosis
                                          from c1 INNER join c2 on c1.policy_id=c2.policy_id
                        """;
    }

    private String policyLevelInformation(String filterQuery) {
        return """
                     WITH policy_level AS (
                         SELECT\s
                             p.policy_id,
                             p.policy_number AS policyNumber,
                             c.policy_category_desc AS policyCategory,
                             m.medical_policy_title AS medicalPolicy,
                             s.sub_policy_title AS subPolicy,
                             CASE WHEN p.custom = FALSE THEN 'NO' ELSE 'YES' END AS customVersion,
                             lb.lob_title AS lob,
                             p.policy_number || '.' || p.policy_version AS policyVersion,
                             p.policy_desc AS medicalPolicyDesc,
                             (:keyWord) AS keyWord,
                             p.claim_type AS claimType,
                             bt.policy_bill_type_action_code AS policyBillTypeActionCode,
                             p.is_prod_b AS isProd,
                             p.deactivated AS deactivated,
                             p.disabled AS disabled
                         FROM\s
                             policy.policy p
                             LEFT JOIN policy.lob_lkp lb ON lb.lob_key = p.lob_fk
                             LEFT JOIN policy.medical_policies m ON m.medical_policy_key = p.medical_policy_key_fk
                             LEFT JOIN policy.sub_policies s ON s.sub_policy_key = p.sub_policy_key_fk
                             LEFT JOIN policy.policy_category_lkp c ON c.policy_category_lkp_id = p.category_fk
                             LEFT JOIN policy.policy_bill_type_action_lkp bt ON bt.policy_bill_type_action_key = p.bill_type_action_fk
                     ),
                     policy_details AS (
                         SELECT\s
                             p.policy_id,
                             STRING_AGG(DISTINCT pb.bill_type, ',' ORDER BY pb.bill_type) AS billType,
                             STRING_AGG(DISTINCT dx.diag_from, ',' ORDER BY dx.diag_from) AS policyDiagnosis
                         FROM\s
                             policy.policy p
                             LEFT JOIN policy.policy_bill_types pb ON p.policy_id = pb.policy_id
                             LEFT JOIN policy.policy_condition_code cc ON p.policy_id = cc.policy_id
                             LEFT JOIN policy.policy_dx dx ON p.policy_id = dx.policy_id
                         WHERE 1=1
                """ + filterQuery + """
                   GROUP BY\s
                            p.policy_id
                    )
                    SELECT \s
                        pl.policy_id,
                        pl.policyNumber,
                        pl.policyCategory,
                        pl.medicalPolicy,
                        pl.subPolicy,
                        pl.customVersion,
                        pl.lob,
                        pl.policyVersion,
                        pl.medicalPolicyDesc,
                        pl.keyWord,
                        pl.claimType,
                        pl.policyBillTypeActionCode,
                        pl.isProd,
                        pl.deactivated,
                        pl.disabled,
                        pd.billType,
                        pd.policyDiagnosis
                    FROM\s
                        policy_level pl
                        INNER JOIN policy_details pd ON pl.policy_id = pd.policy_id;
                """;
    }

    @Override
    public List<PolicyUploadDTO> getSameOrSimData() {

        String query = "select * from source.same_or_sim";

        List<PolicyUploadDTO> resultSet = ipuNamedParameterJdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(PolicyUploadDTO.class));
        return resultSet;
    }

    @SafeVarargs
    private static List<List<String>> mapLists(List<String>... lists) {
        List<List<String>> result = new ArrayList<>();
        if (lists.length == 0) return result;
        // Start recursion with the first list and an empty list
        mapRecursive(result, new ArrayList<>(), 0, lists);
        return result;
    }

    private static void mapRecursive(List<List<String>> result, List<String> current, int depth, List<String>[] lists) {
        if (depth == lists.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (String element : lists[depth]) {
            current.add(element);
            mapRecursive(result, current, depth + 1, lists);
            current.remove(current.size() - 1); // backtrack
        }
    }
}
