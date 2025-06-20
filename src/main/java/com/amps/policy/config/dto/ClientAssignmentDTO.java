package com.amps.policy.config.dto;

import java.util.Date;

public class ClientAssignmentDTO {

    private Integer policyId;

    private Integer policyClntAssmtKey;

    private String clientCode;

    private String clientName;

    private String clientGroupCode;

    private String clientGroupName;

    private Integer clientGroupId;

    private Date clientStartDate;

    private Date clientEndDate;

    private Boolean excludeClientSpecificCodes;

    private boolean hp;

    private Date createDate;

    private Date updateDate;

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

    public Date getClientStartDate() {
        return clientStartDate;
    }

    public void setClientStartDate(Date clientStartDate) {
        this.clientStartDate = clientStartDate;
    }

    public Date getClientEndDate() {
        return clientEndDate;
    }

    public void setClientEndDate(Date clientEndDate) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
