package com.tailwebs.aadharindia.member.models.memberstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tailwebs.aadharindia.member.models.applicantstatus.LoanTakerApplicantStatusModel;

public class LoanTakerMemberStatusResponseModel {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("errors")
    @Expose
    private Object errors;

    @SerializedName("notice")
    @Expose
    private String notice;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("loan_taker")
    @Expose
    private LoanTakerMemberStatusModel loanTakerMemberStatusModel;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public LoanTakerMemberStatusModel getLoanTakerMemberStatusModel() {
        return loanTakerMemberStatusModel;
    }

    public void setLoanTakerMemberStatusModel(LoanTakerMemberStatusModel loanTakerMemberStatusModel) {
        this.loanTakerMemberStatusModel = loanTakerMemberStatusModel;
    }
}
