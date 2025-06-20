package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cci_rationale", schema = "source")
public class CciRationaleLookup {

    @Id
    @Column(name = "cci_rationale_key")
    private Integer cciRationaleKey;

    @Column(name = "cms_cci_rationale")
    private String cmsCciRationale;

    @Column(name = "cci_rationale_desc")
    private String cciRationaleDesc;

    public Integer getCciRationaleKey() {
        return cciRationaleKey;
    }

    public String getCmsCciRationale() {
        return cmsCciRationale;
    }

    public void setCmsCciRationale(String cmsCciRationale) {
        this.cmsCciRationale = cmsCciRationale;
    }

    public String getCciRationaleDesc() {
        return cciRationaleDesc;
    }

    public void setCciRationaleDesc(String cciRationaleDesc) {
        this.cciRationaleDesc = cciRationaleDesc;
    }

    public void setCciRationaleKey(Integer cciRationaleKey) {
        this.cciRationaleKey = cciRationaleKey;
    }

    @Override
    public String toString() {
        return "CciRationaleLookup [cciRationaleKey=" + cciRationaleKey + ", cmsCciRationale=" + cmsCciRationale
                + ", cciRationaleDesc=" + cciRationaleDesc + "]";
    }


}
