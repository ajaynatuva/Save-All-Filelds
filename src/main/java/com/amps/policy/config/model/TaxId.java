package com.amps.policy.config.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "client_tin_exclusions", schema = "policy")
public class TaxId {
    @Id
    @SequenceGenerator(name = "policy.tax_id_key_seq", sequenceName = "policy.tax_id_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.tax_id_key_seq")
    @Column(name = "tax_id_key", updatable = false)
    private Integer taxIdKey;
    @Column(name = "policy_id_fk")
    private Integer policyIdFk;
    @Column(name = "client_group_id")
    private Integer clientGroupId;
    @Column(name = "lob")
    private String lob;
    @Column(name = "claim_type")
    private String claimType;
    @Column(name = "tax_id")
    private Integer taxId;
    @Column(name = "deleted_b")
    private Integer deletedB;
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;
    @Column(name = "updated_date")
    @CreationTimestamp
    private Date updatedDate;

    public Integer getPolicyIdFk() {
        return policyIdFk;
    }

    public void setPolicyIdFk(Integer policyId) {
        this.policyIdFk = policyId;
    }

    public Integer getTaxIdKey() {
        return taxIdKey;
    }

    public void setTaxIdKey(Integer taxIdKey) {
        this.taxIdKey = taxIdKey;
    }

    public Integer getClientGroupId() {
        return clientGroupId;
    }

    public void setClientGroupId(Integer clientGroupId) {
        this.clientGroupId = clientGroupId;
    }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public Integer getDeletedB() {
        return deletedB;
    }

    public void setDeletedB(Integer deletedB) {
        this.deletedB = deletedB;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public TaxId(Integer policyId, Integer taxIdKey, Integer clientGroupId, String lob, String claimType, Integer taxId, Integer deletedB, Date createdDate, Date updatedDate) {
        this.policyIdFk = policyId;
        this.taxIdKey = taxIdKey;
        this.clientGroupId = clientGroupId;
        this.lob = lob;
        this.claimType = claimType;
        this.taxId = taxId;
        this.deletedB = deletedB;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "TaxId{" +
                "policyIdFk=" + policyIdFk +
                ", taxIdKey=" + taxIdKey +
                ", clientGroupId=" + clientGroupId +
                ", lob='" + lob + '\'' +
                ", claimType='" + claimType + '\'' +
                ", taxId=" + taxId +
                ", deletedB=" + deletedB +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public TaxId() {
    }
}
