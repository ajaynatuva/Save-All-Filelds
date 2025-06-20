package com.amps.policy.config.model;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "policy_changes", schema = "policy")
public class Changes {

    @Id
    @SequenceGenerator(name = "policy.policy_changes_key_seq", sequenceName = "policy.policy_changes_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_changes_key_seq")
    @Column(name = "policy_changes_key", updatable = false)
    private Integer policyChangesKey;

    @ManyToOne
    @JoinColumn(name = "policy_id_fk")
    private Policy policyId;

    @Column(name = "jira_id")
    private String jiraId;

    @Column(name = "jira_desc")
    private String jiraDesc;

    @Column(name = "jira_link")
    private String jiraLink;

    @Column(name = "userid")
    private String userId;

    @Column(name = "updated_on")
    @CreationTimestamp
    private Date updatedOn;

    @Column(name = "is_open_b")
    private Integer isOpenb;

    public Integer getPolicyChangesKey() {
        return policyChangesKey;
    }

    public void setPolicyChangesKey(Integer policyChangesKey) {
        this.policyChangesKey = policyChangesKey;
    }

    public Policy getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Policy policyId) {
        this.policyId = policyId;
    }

    public String getJiraId() {
        return jiraId;
    }

    public void setJiraId(String jiraId) {
        this.jiraId = jiraId;
    }

    public String getJiraDesc() {
        return jiraDesc;
    }

    public void setJiraDesc(String jiraDesc) {
        this.jiraDesc = jiraDesc;
    }

    public String getJiraLink() {
        return jiraLink;
    }

    public void setJiraLink(String jiraLink) {
        this.jiraLink = jiraLink;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getIsOpenb() {
        return isOpenb;
    }

    public void setIsOpenb(Integer isOpenb) {
        this.isOpenb = isOpenb;
    }

    public Changes(Integer policyChangesKey, Policy policyId, String jiraId, String jiraDesc, String jiraLink,
                   String userId, Date updatedOn, Integer isOpenb) {
        super();
        this.policyChangesKey = policyChangesKey;
        this.policyId = policyId;
        this.jiraId = jiraId;
        this.jiraDesc = jiraDesc;
        this.jiraLink = jiraLink;
        this.userId = userId;
        this.updatedOn = updatedOn;
        this.isOpenb = isOpenb;
    }

    public Changes() {
    }


}
