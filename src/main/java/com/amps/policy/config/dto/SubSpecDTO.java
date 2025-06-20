package com.amps.policy.config.dto;

public class SubSpecDTO {
    private String specCode;


    private String subspecCode;


    private String subspecDesc;

    private Integer miscB;

    private String taxonomyCode;

    private String cmsSpecialityCode;

    private Integer deletedB;

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getSubspecCode() {
        return subspecCode;
    }

    public void setSubspecCode(String subspecCode) {
        this.subspecCode = subspecCode;
    }

    public String getSubspecDesc() {
        return subspecDesc;
    }

    public void setSubspecDesc(String subspecDesc) {
        this.subspecDesc = subspecDesc;
    }

    public Integer getMiscB() {
        return miscB;
    }

    public void setMiscB(Integer miscB) {
        this.miscB = miscB;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getCmsSpecialityCode() {
        return cmsSpecialityCode;
    }

    public void setCmsSpecialityCode(String cmsSpecialityCode) {
        this.cmsSpecialityCode = cmsSpecialityCode;
    }

    public Integer getDeletedB() {
        return deletedB;
    }

    public void setDeletedB(Integer deletedB) {
        this.deletedB = deletedB;
    }
}
