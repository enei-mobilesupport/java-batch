package foo.sample.db.auto.ref.model;

public class 商品区分マスタKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 商品区分マスタ.SP_ID
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    private String spId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 商品区分マスタ.商品区分_C
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    private String 商品区分C;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 商品区分マスタ.SP_ID
     *
     * @return the value of 商品区分マスタ.SP_ID
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    public String getSpId() {
        return spId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 商品区分マスタ.SP_ID
     *
     * @param spId the value for 商品区分マスタ.SP_ID
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 商品区分マスタ.商品区分_C
     *
     * @return the value of 商品区分マスタ.商品区分_C
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    public String get商品区分C() {
        return 商品区分C;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 商品区分マスタ.商品区分_C
     *
     * @param 商品区分C the value for 商品区分マスタ.商品区分_C
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    public void set商品区分C(String 商品区分C) {
        this.商品区分C = 商品区分C == null ? null : 商品区分C.trim();
    }
}