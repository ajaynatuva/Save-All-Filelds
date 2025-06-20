package com.amps.policy.config.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "client_npi_exclusions", schema = "policy")
public class Npi {
    @Id
    @SequenceGenerator(name = "policy.npi_key_seq", sequenceName = "policy.npi_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.npi_key_seq")
    @Column(name = "npi_key", updatable = false)
    private Integer npiKey;
    @Column(name = "policy_id_fk")
    private Integer policyIdFk;
    @Column(name = "client_group_id")
    private Integer clientGroupId;
    @Column(name = "lob")
    private String lob;
    @Column(name = "claim_type")
    private String claimType;
    @Column(name = "npi")
    private Long npi;
    @Column(name = "deleted_b")
    private Integer deletedB;
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;
    @Column(name = "updated_date")
    @CreationTimestamp
    private Date updatedDate;

    public Npi() {
    }

    public Npi(Integer policyId, Integer npiKey, Integer clientGroupId, String lob, String claimType, Long npi, Integer deletedB, Date createdDate, Date updatedDate) {
        this.policyIdFk = policyId;
        this.npiKey = npiKey;
        this.clientGroupId = clientGroupId;
        this.lob = lob;
        this.claimType = claimType;
        this.npi = npi;
        this.deletedB = deletedB;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Integer getNpiKey() {
        return npiKey;
    }

    public void setNpiKey(Integer npiKey) {
        this.npiKey = npiKey;
    }

    public Integer getPolicyIdFk() {
        return policyIdFk;
    }

    public void setPolicyIdFk(Integer policyIdFk) {
        this.policyIdFk = policyIdFk;
    }

    public Integer getClientGroupId() {
        return clientGroupId;
    }

    public void setClientGroupId(Integer clientGroupId) {
        this.clientGroupId = clientGroupId;
    }

    public Long getNpi() {
        return npi;
    }

    public void setNpi(Long npi) {
        this.npi = npi;
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

    @Override
    public String toString() {
        return "Npi{" +
                "npiKey=" + npiKey +
                ", policyIdFk=" + policyIdFk +
                ", clientGroupId=" + clientGroupId +
                ", lob='" + lob + '\'' +
                ", claimType='" + claimType + '\'' +
                ", npi=" + npi +
                ", deletedB=" + deletedB +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
