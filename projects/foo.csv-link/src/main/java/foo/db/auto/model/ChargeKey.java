package foo.sample.db.auto.model;

public class ChargeKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.CABLE_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String cableId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CHARGE.SETTLEMENT_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    private String settlementId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.CABLE_ID
     *
     * @return the value of CHARGE.CABLE_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getCableId() {
        return cableId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.CABLE_ID
     *
     * @param cableId the value for CHARGE.CABLE_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setCableId(String cableId) {
        this.cableId = cableId == null ? null : cableId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CHARGE.SETTLEMENT_ID
     *
     * @return the value of CHARGE.SETTLEMENT_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getSettlementId() {
        return settlementId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CHARGE.SETTLEMENT_ID
     *
     * @param settlementId the value for CHARGE.SETTLEMENT_ID
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId == null ? null : settlementId.trim();
    }
}