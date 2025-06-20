package com.amps.policy.config.dto;

public class ClientAssignmentDTO1 {

    private Integer policyId;

    private Integer policyClntAssmtKey;

    private String clientCode;

    private String clientName;

    private String clientGroupCode;

    private String clientGroupName;

    private Integer clientGroupId;

    private String clientStartDate;

    private String clientEndDate;

    private Boolean excludeClientSpecificCodes;

    private boolean hp;

    private String createDate;

    private String updateDate;

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientGroupCode() {
        return clientGroupCode;
    }

    public void setClientGroupCode(String clientGroupCode) {
        this.clientGroupCode = clientGroupCode;
    }

    public String getClientGroupName() {
        return clientGroupName;
    }

    public void setClientGroupName(String clientGroupName) {
        this.clientGroupName = clientGroupName;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getPolicyClntAssmtKey() {
        return policyClntAssmtKey;
    }

    public void setPolicyClntAssmtKey(Integer policyClntAssmtKey) {
        this.policyClntAssmtKey = policyClntAssmtKey;
    }

    public Integer getClientGroupId() {
        return clientGroupId;
    }

    public void setClientGroupId(Integer clientGroupId) {
        this.clientGroupId = clientGroupId;
    }


    public String getClientStartDate() {
        return clientStartDate;
    }

    public void setClientStartDate(String clientStartDate) {
        this.clientStartDate = clientStartDate;
    }

    public String getClientEndDate() {
        return clientEndDate;
    }

    public void setClientEndDate(String clientEndDate) {
        this.clientEndDate = clientEndDate;
    }

    public Boolean getExcludeClientSpecificCodes() {
        return excludeClientSpecificCodes;
    }

    public void setExcludeClientSpecificCodes(Boolean excludeClientSpecificCodes) {
        this.excludeClientSpecificCodes = excludeClientSpecificCodes;
    }

    public boolean isHp() {
        return hp;
    }

    public void setHp(boolean hp) {
        this.hp = hp;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
