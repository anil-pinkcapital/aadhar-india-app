package com.tailwebs.aadharindia.member.models.creditcheckreport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CCRWrittenOffAmountsModel {

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("is_satisfied")
    @Expose
    private Boolean is_satisfied;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Boolean getIs_satisfied() {
        return is_satisfied;
    }

    public void setIs_satisfied(Boolean is_satisfied) {
        this.is_satisfied = is_satisfied;
    }
}
