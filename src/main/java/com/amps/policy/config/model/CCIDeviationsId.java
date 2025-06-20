package com.amps.policy.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;


@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class CCIDeviationsId implements java.io.Serializable {

    @Column(name = "cci_key")
    private Integer cciKey;

    @Column(name = "column_i")
    private String columnI;

    @Column(name = "column_ii")
    private String columnII;
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "client_group_id")
    private Integer clientGroupId;

    @SequenceGenerator(name = "source.deviation_key_seq", sequenceName = "source.deviation_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source.deviation_key_seq")
    @Column(name = "deviations_key")
    private Integer deviationsKey;

    public Integer getDeviationsKey() {
        return deviationsKey;
    }

    public void setDeviationsKey(Integer deviationsKey) {
        this.deviationsKey = deviationsKey;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getCciKey() {
        return cciKey;
    }

    public void setCciKey(Integer cciKey) {
        this.cciKey = cciKey;
    }

    public String getColumnI() {
        return columnI;
    }

    public void setColumnI(String columnI) {
        this.columnI = columnI;
    }

    public String getColumnII() {
        return columnII;
    }

    public void setColumnII(String columnII) {
        this.columnII = columnII;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getClientGroupId() {
        return clientGroupId;
    }

    public void setClientGroupId(Integer clientGroupId) {
        this.clientGroupId = clientGroupId;
    }

}
