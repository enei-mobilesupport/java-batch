package foo.sample.db.auto.model;

import java.util.Date;

public class Charge extends ChargeKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.RESULT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String resultCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.CATV_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String catvCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SUBSCRIBER_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String subscriberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SP_SUBSCRIBER_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String spSubscriberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SERVICE_START_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private Date serviceStartAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SERVICE_END_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private Date serviceEndAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.CHARGE_ON
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private Date chargeOn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SP_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String spId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SP_PRODUCT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String spProductCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SP_PRODUCT_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String spProductName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SMS_PRODUCT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String smsProductCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SMS_PRODUCT_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String smsProductName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.CHARGE_TYPE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String chargeType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.AMOUNT_TAX_EXCLUDED
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private Integer amountTaxExcluded;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.AMOUNT_TAX_INCLUDED
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private Integer amountTaxIncluded;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SP_LINK_FILE_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String spLinkFileName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.INSERT_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private Date insertAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.UPDATE_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private Date updateAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.RESULT_CODE
     *
     * @return the value of CHARGE.RESULT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.RESULT_CODE
     *
     * @param resultCode the value for CHARGE.RESULT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode == null ? null : resultCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.CATV_CODE
     *
     * @return the value of CHARGE.CATV_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getCatvCode() {
        return catvCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.CATV_CODE
     *
     * @param catvCode the value for CHARGE.CATV_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setCatvCode(String catvCode) {
        this.catvCode = catvCode == null ? null : catvCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SUBSCRIBER_ID
     *
     * @return the value of CHARGE.SUBSCRIBER_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSubscriberId() {
        return subscriberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SUBSCRIBER_ID
     *
     * @param subscriberId the value for CHARGE.SUBSCRIBER_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId == null ? null : subscriberId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SP_SUBSCRIBER_ID
     *
     * @return the value of CHARGE.SP_SUBSCRIBER_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSpSubscriberId() {
        return spSubscriberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SP_SUBSCRIBER_ID
     *
     * @param spSubscriberId the value for CHARGE.SP_SUBSCRIBER_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSpSubscriberId(String spSubscriberId) {
        this.spSubscriberId = spSubscriberId == null ? null : spSubscriberId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SERVICE_START_AT
     *
     * @return the value of CHARGE.SERVICE_START_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Date getServiceStartAt() {
        return serviceStartAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SERVICE_START_AT
     *
     * @param serviceStartAt the value for CHARGE.SERVICE_START_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setServiceStartAt(Date serviceStartAt) {
        this.serviceStartAt = serviceStartAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SERVICE_END_AT
     *
     * @return the value of CHARGE.SERVICE_END_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Date getServiceEndAt() {
        return serviceEndAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SERVICE_END_AT
     *
     * @param serviceEndAt the value for CHARGE.SERVICE_END_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setServiceEndAt(Date serviceEndAt) {
        this.serviceEndAt = serviceEndAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.CHARGE_ON
     *
     * @return the value of CHARGE.CHARGE_ON
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Date getChargeOn() {
        return chargeOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.CHARGE_ON
     *
     * @param chargeOn the value for CHARGE.CHARGE_ON
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setChargeOn(Date chargeOn) {
        this.chargeOn = chargeOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SP_ID
     *
     * @return the value of CHARGE.SP_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSpId() {
        return spId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SP_ID
     *
     * @param spId the value for CHARGE.SP_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SP_PRODUCT_CODE
     *
     * @return the value of CHARGE.SP_PRODUCT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSpProductCode() {
        return spProductCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SP_PRODUCT_CODE
     *
     * @param spProductCode the value for CHARGE.SP_PRODUCT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSpProductCode(String spProductCode) {
        this.spProductCode = spProductCode == null ? null : spProductCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SP_PRODUCT_NAME
     *
     * @return the value of CHARGE.SP_PRODUCT_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSpProductName() {
        return spProductName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SP_PRODUCT_NAME
     *
     * @param spProductName the value for CHARGE.SP_PRODUCT_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSpProductName(String spProductName) {
        this.spProductName = spProductName == null ? null : spProductName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SMS_PRODUCT_CODE
     *
     * @return the value of CHARGE.SMS_PRODUCT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSmsProductCode() {
        return smsProductCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SMS_PRODUCT_CODE
     *
     * @param smsProductCode the value for CHARGE.SMS_PRODUCT_CODE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSmsProductCode(String smsProductCode) {
        this.smsProductCode = smsProductCode == null ? null : smsProductCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SMS_PRODUCT_NAME
     *
     * @return the value of CHARGE.SMS_PRODUCT_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSmsProductName() {
        return smsProductName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SMS_PRODUCT_NAME
     *
     * @param smsProductName the value for CHARGE.SMS_PRODUCT_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSmsProductName(String smsProductName) {
        this.smsProductName = smsProductName == null ? null : smsProductName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.CHARGE_TYPE
     *
     * @return the value of CHARGE.CHARGE_TYPE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.CHARGE_TYPE
     *
     * @param chargeType the value for CHARGE.CHARGE_TYPE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.AMOUNT_TAX_EXCLUDED
     *
     * @return the value of CHARGE.AMOUNT_TAX_EXCLUDED
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Integer getAmountTaxExcluded() {
        return amountTaxExcluded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.AMOUNT_TAX_EXCLUDED
     *
     * @param amountTaxExcluded the value for CHARGE.AMOUNT_TAX_EXCLUDED
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setAmountTaxExcluded(Integer amountTaxExcluded) {
        this.amountTaxExcluded = amountTaxExcluded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.AMOUNT_TAX_INCLUDED
     *
     * @return the value of CHARGE.AMOUNT_TAX_INCLUDED
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Integer getAmountTaxIncluded() {
        return amountTaxIncluded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.AMOUNT_TAX_INCLUDED
     *
     * @param amountTaxIncluded the value for CHARGE.AMOUNT_TAX_INCLUDED
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setAmountTaxIncluded(Integer amountTaxIncluded) {
        this.amountTaxIncluded = amountTaxIncluded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SP_LINK_FILE_NAME
     *
     * @return the value of CHARGE.SP_LINK_FILE_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSpLinkFileName() {
        return spLinkFileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SP_LINK_FILE_NAME
     *
     * @param spLinkFileName the value for CHARGE.SP_LINK_FILE_NAME
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSpLinkFileName(String spLinkFileName) {
        this.spLinkFileName = spLinkFileName == null ? null : spLinkFileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.INSERT_AT
     *
     * @return the value of CHARGE.INSERT_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Date getInsertAt() {
        return insertAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.INSERT_AT
     *
     * @param insertAt the value for CHARGE.INSERT_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.UPDATE_AT
     *
     * @return the value of CHARGE.UPDATE_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.UPDATE_AT
     *
     * @param updateAt the value for CHARGE.UPDATE_AT
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}