package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cci_lkp", schema = "source")
public class CCI {

    @Id
    @Column(name = "cci_key")
    private Integer cciKey;

    @Column(name = "cci_desc")
    private String cciDesc;

    @Column(name = "cci_notes")
    private String cciNotes;

    public Integer getCciKey() {
        return cciKey;
    }

    public void setCciKey(Integer cciKey) {
        this.cciKey = cciKey;
    }

    public String getCciDesc() {
        return cciDesc;
    }

    public void setCciDesc(String cciDesc) {
        this.cciDesc = cciDesc;
    }

    public String getCciNotes() {
        return cciNotes;
    }

    public void setCciNotes(String cciNotes) {
        this.cciNotes = cciNotes;
    }


}
