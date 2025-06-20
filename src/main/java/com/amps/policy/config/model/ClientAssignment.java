package com.amps.policy.config.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "client_assignment", schema = "policy")
public class ClientAssignment {


    @Id
    @SequenceGenerator(name = "policy.policy_client_assmt_seq", sequenceName = "policy.policy_client_assmt_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_client_assmt_seq")
    @Column(name = "policy_clnt_assmt_key", updatable = false)
    private Integer policyClntAssmtKey;

    @Column(name = "policy_id")
    private Integer policyId;

    @Column(name = "client_group_id")
    private Integer clientGroupId;

    @Column(name = "client_start_date")
    private Date clientStartDate;

    @Column(name = "client_end_date")
    private Date clientEndDate;

    @Column(name = "exclude_client_specific_codes")
    private Boolean excludeClientSpecificCodes;

    @Column(name = "hp")
    private boolean hp;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public Integer getPolicyClntAssmtKey() {
        return policyClntAssmtKey;
    }

    public void setPolicyClntAssmtKey(Integer policyClntAssmtKey) {
        this.policyClntAssmtKey = policyClntAssmtKey;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
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
