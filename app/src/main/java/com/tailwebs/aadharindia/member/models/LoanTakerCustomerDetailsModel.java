package com.tailwebs.aadharindia.member.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tailwebs.aadharindia.center.searchinmap.models.ResidentAddressModel;
import com.tailwebs.aadharindia.postapproval.models.ApprovedLoanDetailModel;
import com.tailwebs.aadharindia.models.HomeAddress;
import com.tailwebs.aadharindia.models.ProfileImages;

public class LoanTakerCustomerDetailsModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("loan_taker_id")
    @Expose
    private String loan_taker_id;

    @SerializedName("aadhar_number")
    @Expose
    private String aadhar_number;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("group_id")
    @Expose
    private String group_id;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("aadhar_co")
    @Expose
    private String aadhar_co;

    @SerializedName("primary_phone_number")
    @Expose
    private String primary_phone_number;

    @SerializedName("secondary_phone_number")
    @Expose
    private String secondary_phone_number;

    @SerializedName("landline_phone_number")
    @Expose
    private String landline_phone_number;

    @SerializedName("pan_number")
    @Expose
    private String pan_number;

    @SerializedName("voter_id")
    @Expose
    private String voter_id;

    @SerializedName("aadhar_co_relation_id")
    @Expose
    private String aadhar_co_relation_id;

    @SerializedName("aadhar_co_relation_name")
    @Expose
    private String aadhar_co_relation_name;

    @SerializedName("is_married")
    @Expose
    private Boolean is_married;

    @SerializedName("is_fresh_customer")
    @Expose
    private String is_fresh_customer;

    @SerializedName("loan_cycle")
    @Expose
    private String loan_cycle;

    @SerializedName("is_residing_address")
    @Expose
    private Boolean is_residing_address;

    @SerializedName("resident_address_id")
    @Expose
    private String resident_address_id;

    @SerializedName("profile_images")
    @Expose
    private ProfileImages profileImages;

    @SerializedName("customer")
    @Expose
    private CustomerModel customerModel;

    @SerializedName("aadhar_address")
    @Expose
    private HomeAddress homeAddress;

    @SerializedName("resident_address")
    @Expose
    private ResidentAddressModel residentAddressModel;

    @SerializedName("calculated_emi")
    @Expose
    private CalculatedEMIModel calculatedEMIModel;

    @SerializedName("aadhar_co_relation")
    @Expose
    private AadharCoRelationModel aadharCoRelationModel;

    @SerializedName("approved_loan_detail")
    @Expose
    private ApprovedLoanDetailModel approvedLoanDetailModel;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAadhar_number() {
        return aadhar_number;
    }

    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoan_taker_id() {
        return loan_taker_id;
    }

    public void setLoan_taker_id(String loan_taker_id) {
        this.loan_taker_id = loan_taker_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAadhar_co() {
        return aadhar_co;
    }

    public void setAadhar_co(String aadhar_co) {
        this.aadhar_co = aadhar_co;
    }

    public String getPrimary_phone_number() {
        return primary_phone_number;
    }

    public void setPrimary_phone_number(String primary_phone_number) {
        this.primary_phone_number = primary_phone_number;
    }

    public String getSecondary_phone_number() {
        return secondary_phone_number;
    }

    public void setSecondary_phone_number(String secondary_phone_number) {
        this.secondary_phone_number = secondary_phone_number;
    }

    public String getLandline_phone_number() {
        return landline_phone_number;
    }

    public void setLandline_phone_number(String landline_phone_number) {
        this.landline_phone_number = landline_phone_number;
    }

    public String getPan_number() {
        return pan_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public String getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

    public Boolean getIs_residing_address() {
        return is_residing_address;
    }

    public void setIs_residing_address(Boolean is_residing_address) {
        this.is_residing_address = is_residing_address;
    }

    public String getResident_address_id() {
        return resident_address_id;
    }

    public void setResident_address_id(String resident_address_id) {
        this.resident_address_id = resident_address_id;
    }

    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    public ResidentAddressModel getResidentAddressModel() {
        return residentAddressModel;
    }

    public void setResidentAddressModel(ResidentAddressModel residentAddressModel) {
        this.residentAddressModel = residentAddressModel;
    }

    public ProfileImages getProfileImages() {
        return profileImages;
    }

    public void setProfileImages(ProfileImages profileImages) {
        this.profileImages = profileImages;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public CalculatedEMIModel getCalculatedEMIModel() {
        return calculatedEMIModel;
    }

    public void setCalculatedEMIModel(CalculatedEMIModel calculatedEMIModel) {
        this.calculatedEMIModel = calculatedEMIModel;
    }

    public Boolean getIs_married() {
        return is_married;
    }

    public void setIs_married(Boolean is_married) {
        this.is_married = is_married;
    }

    public String getIs_fresh_customer() {
        return is_fresh_customer;
    }

    public void setIs_fresh_customer(String is_fresh_customer) {
        this.is_fresh_customer = is_fresh_customer;
    }

    public String getLoan_cycle() {
        return loan_cycle;
    }

    public void setLoan_cycle(String loan_cycle) {
        this.loan_cycle = loan_cycle;
    }

    public AadharCoRelationModel getAadharCoRelationModel() {
        return aadharCoRelationModel;
    }

    public void setAadharCoRelationModel(AadharCoRelationModel aadharCoRelationModel) {
        this.aadharCoRelationModel = aadharCoRelationModel;
    }

    public String getAadhar_co_relation_id() {
        return aadhar_co_relation_id;
    }

    public void setAadhar_co_relation_id(String aadhar_co_relation_id) {
        this.aadhar_co_relation_id = aadhar_co_relation_id;
    }

    public String getAadhar_co_relation_name() {
        return aadhar_co_relation_name;
    }

    public void setAadhar_co_relation_name(String aadhar_co_relation_name) {
        this.aadhar_co_relation_name = aadhar_co_relation_name;
    }

    public ApprovedLoanDetailModel getApprovedLoanDetailModel() {
        return approvedLoanDetailModel;
    }

    public void setApprovedLoanDetailModel(ApprovedLoanDetailModel approvedLoanDetailModel) {
        this.approvedLoanDetailModel = approvedLoanDetailModel;
    }
}
