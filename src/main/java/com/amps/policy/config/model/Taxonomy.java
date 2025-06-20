package com.amps.policy.config.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "taxonomy", schema = "policy")
public class Taxonomy {

    @Id
    @SequenceGenerator(name = "policy.taxonomy_key_seq", sequenceName = "policy.taxonomy_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.taxonomy_key_seq")
    @Column(name = "taxonomy_key", updatable = false)
    private Integer taxonomyKey;

    @Column(name = "policy_id")
    private Integer policyId;

    @Column(name = "client_group_id")
    private Integer clientGroupId;

    @Column(name = "taxonomy_code")
    private String taxonomyCode;

    @Column(name = "spec_code")
    private String specCode;

    @Column(name = "subspec_code")
    private String subSpecCode;

    @Column(name = "subspec_desc")
    private String subSpecDesc;

    @Column(name = "function")
    private Integer function;

    @Column(name = "deleted_b")
    private Integer DeletedB;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Taxonomy() {
    }

    public Integer getTaxonomyKey() {
        return taxonomyKey;
    }

    public void setTaxonomyKey(Integer taxonomyKey) {
        this.taxonomyKey = taxonomyKey;
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

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getSubSpecCode() {
        return subSpecCode;
    }

    public void setSubSpecCode(String subSpecCode) {
        this.subSpecCode = subSpecCode;
    }

    public String getSubSpecDesc() {
        return subSpecDesc;
    }

    public void setSubSpecDesc(String subSpecDesc) {
        this.subSpecDesc = subSpecDesc;
    }

    public Integer getFunction() {
        return function;
    }

    public void setFunction(Integer function) {
        this.function = function;
    }

    public Integer getDeletedB() {
        return DeletedB;
    }

    public void setDeletedB(Integer deletedB) {
        DeletedB = deletedB;
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

}
