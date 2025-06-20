package com.amps.policy.config.dto;


import java.sql.Date;

public class ClientGroupProductDTO {

    Integer clientGroupId;
    Date clientStartDate;
    Date clientEndDate;

    public int getClientGroupId() {
        return clientGroupId;
    }

    public void setClientGroupId(int clientGroupId) {
        this.clientGroupId = clientGroupId;
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
}
