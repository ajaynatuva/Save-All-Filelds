package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.PolicyViewDao;
import com.amps.policy.config.dto.CategoryDTO;
import com.amps.policy.config.dto.PolicyViewDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PolicyViewDaoImpl implements PolicyViewDao {

    @Autowired
    NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

    Logger logger = LogManager.getLogger(PolicyViewDao.class.getName());

    @Override
    public List<CategoryDTO> getCategoryData() {
        String Query = """
                SELECT category_fk, count(*) as categoryCount FROM policy.policy GROUP BY category_fk
                """;
        return ipuNamedParameterJdbcTemplate.query(Query,
                new BeanPropertyRowMapper<>(CategoryDTO.class));
    }

    private boolean isFilterEmpty(PolicyViewDTO policyDto) {
        return policyDto.getCategoryFk() == null && policyDto.getNpiLogicFk() == null
                && policyDto.getTaxIdLogicFk() == null && policyDto.getTaxonomy() == null
                && policyDto.getDeactivated() == null && policyDto.getPolicyId() == null
                && policyDto.getType() == null
                && policyDto.getMedicalPolicy() == null && policyDto.getSubPolicy() == null
                && policyDto.getClientGroup() == null && !policyDto.isCustom()
                && policyDto.getClaimType() == null;
    }

    private String getCommaSeparatedPolicyIds(List<PolicyViewDTO> policyDtoList) {
        return policyDtoList.stream()
                .map(PolicyViewDTO::getPolicyId)
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }


    @Override
    public List<PolicyViewDTO> searchCriteria(List<PolicyViewDTO> policyDtoList) {

        String policyIdsString = getCommaSeparatedPolicyIds(policyDtoList);
        PolicyViewDTO policyDto = policyDtoList.getFirst();

        String query;
        String condQuery = "";
        if (isFilterEmpty(policyDto)) {
            query = """
                    select pp.medical_policy_key_fk,
                    COUNT(DISTINCT pp.policy_id) AS count
                    from policy.policy pp where pp.policy_version = 0 and pp.is_prod_b = 1
                    group by pp.medical_policy_key_fk order by pp.medical_policy_key_fk
                    """;
        } else {
            if (policyDto.getClientGroup() != null && !Objects.equals(policyDto.getClientGroup(), "")) {
                condQuery = """
                        left join (
                            select distinct policy_id,client_group_id from policy.client_assignment
                        ) cc on cc.policy_id = pp.policy_id
                        """;
            }
            query = """
                    select pp.medical_policy_key_fk,
                    COUNT(DISTINCT pp.policy_id) AS count
                    from policy.policy pp
                    """ + condQuery + """
                    where 1=1 
                    """;
            if (policyDto.getDeactivated() == null) {
                query += " AND pp.is_prod_b = 1 ";
            }

            if(!policyDto.isCustom()){
                query += "AND pp.policy_version = 0";
            }
            query += SearchCriteria(policyDto);

            if (policyDto.getPolicyId() != null) {
                query += " AND pp.policy_id NOT IN" + "(" + policyIdsString + ")";
            }

            query += " group by pp.medical_policy_key_fk order by pp.medical_policy_key_fk";
        }
        return ipuNamedParameterJdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(PolicyViewDTO.class));
    }

    @Override
    public List<PolicyViewDTO> getSubPolicyByCat(List<PolicyViewDTO> policyDtoList) {

        String policyIdsString = getCommaSeparatedPolicyIds(policyDtoList);
        PolicyViewDTO policyDto = policyDtoList.getFirst();

        String query;
        String condQuery = "";
        if (isFilterEmpty(policyDto)) {
            query = """
                    select pp.sub_policy_key_fk,pp.medical_policy_key_fk,
                    COUNT(DISTINCT pp.policy_id) AS count
                    from policy.policy pp
                    where pp.policy_version = 0 and pp.is_prod_b = 1
                    group by pp.sub_policy_key_fk,pp.medical_policy_key_fk
                    """;
        } else {
            if (policyDto.getClientGroup() != null && !Objects.equals(policyDto.getClientGroup(), "")) {
                condQuery = """
                        left join (
                            select distinct policy_id,client_group_id from policy.client_assignment
                        ) cc on cc.policy_id = pp.policy_id
                        """;
            }
            query = """
                    select pp.sub_policy_key_fk,pp.medical_policy_key_fk,
                    COUNT(DISTINCT pp.policy_id) AS count
                    from policy.policy pp
                     """ + condQuery + """
                    where 1=1 
                    """;

            if (policyDto.getDeactivated() == null) {
                query += " AND pp.is_prod_b = 1 ";
            }

            if(!policyDto.isCustom()){
                query += " AND pp.policy_version = 0";
            }

            query += SearchCriteria(policyDto);

            if (policyDto.getPolicyId() != null) {
                query += " AND pp.policy_id NOT IN" + "(" + policyIdsString + ")";
            }

            query += " group by pp.sub_policy_key_fk,pp.medical_policy_key_fk";
        }
        return ipuNamedParameterJdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(PolicyViewDTO.class));
    }

    @Override
    public List<PolicyViewDTO> getReasonPolicies(List<PolicyViewDTO> policyDtoList) {

        String policyIdsString = getCommaSeparatedPolicyIds(policyDtoList);
        PolicyViewDTO policyDto = policyDtoList.getFirst();

        String query;
        String condQuery = "";
        if (isFilterEmpty(policyDto)) {
            query = """
                    select pp.reason_code_fk,pp.sub_policy_key_fk,
                    pp.medical_policy_key_fk,
                    COUNT(DISTINCT pp.policy_id) AS count
                    from policy.policy pp
                    where pp.policy_version = 0 and pp.is_prod_b = 1
                    group by pp.sub_policy_key_fk,
                    pp.reason_code_fk,pp.medical_policy_key_fk
                    """;
        } else {
            if (policyDto.getClientGroup() != null && policyDto.getClientGroup() != "") {
                condQuery = """
                        left join (
                            select distinct policy_id,client_group_id from policy.client_assignment
                        ) cc on cc.policy_id = pp.policy_id
                        """;
            }

            query = """
                    select pp.reason_code_fk,
                           pp.sub_policy_key_fk,
                           pp.medical_policy_key_fk,
                          COUNT(DISTINCT pp.policy_id) AS count
                          from policy.policy pp
                    """ + condQuery + """
                    where 1=1
                    """;

            if (policyDto.getDeactivated() == null) {
                query += " AND pp.is_prod_b = 1 ";
            }

            if(!policyDto.isCustom()){
                query += " AND pp.policy_version = 0";
            }

            query += SearchCriteria(policyDto);

            if (policyDto.getPolicyId() != null) {
                query += " AND pp.policy_id NOT IN" + "(" + policyIdsString + ")";
            }

            query += " group by pp.reason_code_fk,pp.sub_policy_key_fk,pp.medical_policy_key_fk";
        }
        return ipuNamedParameterJdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(PolicyViewDTO.class));
    }

    @Override
    public List<PolicyViewDTO> getTotalPolicies(List<PolicyViewDTO> policyDtoList) {

        String policyIdsString = getCommaSeparatedPolicyIds(policyDtoList);
        PolicyViewDTO policyDto = policyDtoList.getFirst();

        String query;
        String condQuery = "";
        if (isFilterEmpty(policyDto)) {
            query = """
                    SELECT DISTINCT ON (pp.policy_id) pp.policy_id,
                    pp.policy_number,
                    pp.policy_version,
                    pp.is_prod_b AS isProdB,
                    pp.reason_code_fk,
                    pp.medical_policy_key_fk,
                    pp.sub_policy_key_fk,
                    pp.npi_logic_fk,
                    pp.tax_logic_fk,
                    pp.taxonomy_logic_fk
                    FROM policy.policy pp
                    WHERE pp.policy_version = 0 AND pp.is_prod_b = 1
                    ORDER BY pp.policy_id, pp.policy_version DESC;
                    """;
        } else {
            if (policyDto.getClientGroup() != null && !Objects.equals(policyDto.getClientGroup(), "")) {
                condQuery = """
                        left join (
                            select distinct policy_id,client_group_id from policy.client_assignment
                        ) cc on cc.policy_id = pp.policy_id
                        """;
            }
            query = """
                    select DISTINCT ON (pp.policy_id) pp.policy_id,
                    pp.policy_number,
                    pp.policy_version,
                    pp.is_prod_b as isProdB,
                    pp.reason_code_fk,
                    pp.medical_policy_key_fk,
                    pp.sub_policy_key_fk,
                    pp.npi_logic_fk,pp.tax_logic_fk,
                    pp.taxonomy_logic_fk from
                    policy.policy pp
                    """ + condQuery + """
                    where 1=1
                    """;

            if (policyDto.getDeactivated() == null) {
                query += " AND pp.is_prod_b = 1 ";
            }

            if(!policyDto.isCustom()){
                query += " AND pp.policy_version = 0";
            }

            query += SearchCriteria(policyDto);

            if (policyDto.getPolicyId() != null) {
                query += " AND pp.policy_id NOT IN" + "(" + policyIdsString + ")";
            }

        }
        return ipuNamedParameterJdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(PolicyViewDTO.class));
    }

    @Override
    public void insertClaimIntroFileToDb(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] fileBytes = new byte[(int) new File(filePath).length()];
            fis.read(fileBytes);
            // Prepare the SQL statement
            String sql = "INSERT INTO policy.claim_processing_introduction(claim_introduction) VALUES (:summary)";
            Map<String, Object> params = new HashMap<>();
            params.put("summary", fileBytes);
            ipuNamedParameterJdbcTemplate.update(sql, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSummaryFilesToDb(String filePath, String originalFileName) {
        try {
            // Log folder path
            String query = null;
            // Prepare SQL statement
//			String sql = "UPDATE policy.sub_policies SET sub_policy_summary = :sub_policy_summary WHERE sub_policy_key = :sub_policy_key;";
            if (originalFileName.contains("Medical")) {
                query = "UPDATE policy.medical_policies SET medical_policy_summary = :summary WHERE medical_policy_key = :key;";
            }
            if (originalFileName.contains("Sub")) {
                // Prepare SQL statement
                query = "UPDATE policy.sub_policies SET sub_policy_summary = :summary WHERE sub_policy_key = :key;";
            }
            // Get files from the folder
            File folder = new File(filePath);
            File[] files = folder.listFiles();
            // Check if files array is null or empty
            if (files == null || files.length == 0) {
                logger.info("No files found in the specified directory.");
                return;
            }
            // Iterate over files
            for (File file : files) {
                if (file.isFile()) {

                    String fileName = file.getName();
                    try {
                        Integer keyValue = Integer.parseInt(fileName.split("\\.")[0]);
                        // Read file content into byte array
                        byte[] fileContent = new byte[(int) file.length()];
                        try (FileInputStream fis = new FileInputStream(file)) {
                            int bytesRead = fis.read(fileContent);
                            if (bytesRead != fileContent.length) {
                                throw new IOException("Failed to read the entire file content for file: " + fileName);
                            }
                        }

                        // Insert content into the database
                        Map<String, Object> params = new HashMap<>();
                        params.put("summary", fileContent);
                        params.put("key", keyValue);

                        ipuNamedParameterJdbcTemplate.update(query, params);
//                        System.out.println("Rows affected for key " + keyValue + ": " + rowsAffected);
                    } catch (NumberFormatException e) {
                        logger.error("Error parsing key value from file name: {}. Skipping this file.", fileName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String SearchCriteria(PolicyViewDTO policyDto) {

        String query = "";

        if (policyDto.getMedicalPolicy() != null) {
            query += " AND pp.medical_policy_key_fk IN ( " + policyDto.getMedicalPolicy() + ")";
        }

        if (policyDto.getSubPolicy() != null) {
            query += " AND pp.sub_policy_key_fk IN (" + policyDto.getSubPolicy() + ")";
        }

        if (policyDto.getReasonCodeFk() != null) {
            query += " AND pp.reason_code_fk = " + "'" + policyDto.getReasonCodeFk() + "'";
        }

        if (policyDto.getCategoryFk() != null) {
            query += " AND pp.category_fk = " + policyDto.getCategoryFk();
        }
        if (policyDto.getNpiLogicFk() != null) {
            query += " AND pp.npi_logic_fk = " + policyDto.getNpiLogicFk();
        }
        if (policyDto.getTaxonomy() != null) {
            query += " AND pp.taxonomy_logic_fk = " + policyDto.getTaxonomy();
        }

        if (policyDto.getTaxIdLogicFk() != null) {
            query += " AND pp.tax_logic_fk = " + policyDto.getTaxIdLogicFk();
        }
        if (policyDto.getDeactivated() != null) {
            query += " AND pp.deactivated = " + policyDto.getDeactivated();
        }
        if (policyDto.getType() != null && !Objects.equals(policyDto.getType(), "")) {
            query += " AND pp.lob_fk != 2 and pp.product_type_fk != '3'";
        }

        if (policyDto.getClaimType() != null && !Objects.equals(policyDto.getClaimType(), "")) {
            String claimData = policyDto.getClaimType();
            String[] clmTypes = claimData.split(",");
            String clmTypeAppened = "%";
            for (String clm : clmTypes) {
                clmTypeAppened += clm + "%";
            }
            query += " AND pp.claim_type like" + "'" + clmTypeAppened + "'";
        }

        if (policyDto.isCustom()) {
            query += " AND pp.custom = " + policyDto.isCustom();
        }

        if (policyDto.getClientGroup() != null) {
            query += " AND cc.client_group_id  IN ( " + policyDto.getClientGroup() + ")";
        }
        return query;
    }
}
