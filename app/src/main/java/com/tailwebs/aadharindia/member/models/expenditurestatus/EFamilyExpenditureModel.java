package com.tailwebs.aadharindia.member.models.expenditurestatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EFamilyExpenditureModel {

    @SerializedName("completed")
    @Expose
    private Boolean completed;

    @SerializedName("activated")
    @Expose
    private Boolean activated;


    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
