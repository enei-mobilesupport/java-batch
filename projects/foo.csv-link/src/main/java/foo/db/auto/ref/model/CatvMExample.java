package foo.sample.db.auto.ref.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatvMExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public CatvMExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCatvCdIsNull() {
            addCriterion("CATV_CD is null");
            return (Criteria) this;
        }

        public Criteria andCatvCdIsNotNull() {
            addCriterion("CATV_CD is not null");
            return (Criteria) this;
        }

        public Criteria andCatvCdEqualTo(String value) {
            addCriterion("CATV_CD =", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdNotEqualTo(String value) {
            addCriterion("CATV_CD <>", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdGreaterThan(String value) {
            addCriterion("CATV_CD >", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_CD >=", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdLessThan(String value) {
            addCriterion("CATV_CD <", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdLessThanOrEqualTo(String value) {
            addCriterion("CATV_CD <=", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdLike(String value) {
            addCriterion("CATV_CD like", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdNotLike(String value) {
            addCriterion("CATV_CD not like", value, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdIn(List<String> values) {
            addCriterion("CATV_CD in", values, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdNotIn(List<String> values) {
            addCriterion("CATV_CD not in", values, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdBetween(String value1, String value2) {
            addCriterion("CATV_CD between", value1, value2, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvCdNotBetween(String value1, String value2) {
            addCriterion("CATV_CD not between", value1, value2, "catvCd");
            return (Criteria) this;
        }

        public Criteria andCatvNameIsNull() {
            addCriterion("CATV_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCatvNameIsNotNull() {
            addCriterion("CATV_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCatvNameEqualTo(String value) {
            addCriterion("CATV_NAME =", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameNotEqualTo(String value) {
            addCriterion("CATV_NAME <>", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameGreaterThan(String value) {
            addCriterion("CATV_NAME >", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_NAME >=", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameLessThan(String value) {
            addCriterion("CATV_NAME <", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameLessThanOrEqualTo(String value) {
            addCriterion("CATV_NAME <=", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameLike(String value) {
            addCriterion("CATV_NAME like", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameNotLike(String value) {
            addCriterion("CATV_NAME not like", value, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameIn(List<String> values) {
            addCriterion("CATV_NAME in", values, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameNotIn(List<String> values) {
            addCriterion("CATV_NAME not in", values, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameBetween(String value1, String value2) {
            addCriterion("CATV_NAME between", value1, value2, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameNotBetween(String value1, String value2) {
            addCriterion("CATV_NAME not between", value1, value2, "catvName");
            return (Criteria) this;
        }

        public Criteria andCatvNameSIsNull() {
            addCriterion("CATV_NAME_S is null");
            return (Criteria) this;
        }

        public Criteria andCatvNameSIsNotNull() {
            addCriterion("CATV_NAME_S is not null");
            return (Criteria) this;
        }

        public Criteria andCatvNameSEqualTo(String value) {
            addCriterion("CATV_NAME_S =", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSNotEqualTo(String value) {
            addCriterion("CATV_NAME_S <>", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSGreaterThan(String value) {
            addCriterion("CATV_NAME_S >", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_NAME_S >=", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSLessThan(String value) {
            addCriterion("CATV_NAME_S <", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSLessThanOrEqualTo(String value) {
            addCriterion("CATV_NAME_S <=", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSLike(String value) {
            addCriterion("CATV_NAME_S like", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSNotLike(String value) {
            addCriterion("CATV_NAME_S not like", value, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSIn(List<String> values) {
            addCriterion("CATV_NAME_S in", values, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSNotIn(List<String> values) {
            addCriterion("CATV_NAME_S not in", values, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSBetween(String value1, String value2) {
            addCriterion("CATV_NAME_S between", value1, value2, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvNameSNotBetween(String value1, String value2) {
            addCriterion("CATV_NAME_S not between", value1, value2, "catvNameS");
            return (Criteria) this;
        }

        public Criteria andCatvKindIsNull() {
            addCriterion("CATV_KIND is null");
            return (Criteria) this;
        }

        public Criteria andCatvKindIsNotNull() {
            addCriterion("CATV_KIND is not null");
            return (Criteria) this;
        }

        public Criteria andCatvKindEqualTo(String value) {
            addCriterion("CATV_KIND =", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindNotEqualTo(String value) {
            addCriterion("CATV_KIND <>", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindGreaterThan(String value) {
            addCriterion("CATV_KIND >", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_KIND >=", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindLessThan(String value) {
            addCriterion("CATV_KIND <", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindLessThanOrEqualTo(String value) {
            addCriterion("CATV_KIND <=", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindLike(String value) {
            addCriterion("CATV_KIND like", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindNotLike(String value) {
            addCriterion("CATV_KIND not like", value, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindIn(List<String> values) {
            addCriterion("CATV_KIND in", values, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindNotIn(List<String> values) {
            addCriterion("CATV_KIND not in", values, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindBetween(String value1, String value2) {
            addCriterion("CATV_KIND between", value1, value2, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvKindNotBetween(String value1, String value2) {
            addCriterion("CATV_KIND not between", value1, value2, "catvKind");
            return (Criteria) this;
        }

        public Criteria andCatvTelIsNull() {
            addCriterion("CATV_TEL is null");
            return (Criteria) this;
        }

        public Criteria andCatvTelIsNotNull() {
            addCriterion("CATV_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andCatvTelEqualTo(String value) {
            addCriterion("CATV_TEL =", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelNotEqualTo(String value) {
            addCriterion("CATV_TEL <>", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelGreaterThan(String value) {
            addCriterion("CATV_TEL >", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_TEL >=", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelLessThan(String value) {
            addCriterion("CATV_TEL <", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelLessThanOrEqualTo(String value) {
            addCriterion("CATV_TEL <=", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelLike(String value) {
            addCriterion("CATV_TEL like", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelNotLike(String value) {
            addCriterion("CATV_TEL not like", value, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelIn(List<String> values) {
            addCriterion("CATV_TEL in", values, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelNotIn(List<String> values) {
            addCriterion("CATV_TEL not in", values, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelBetween(String value1, String value2) {
            addCriterion("CATV_TEL between", value1, value2, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvTelNotBetween(String value1, String value2) {
            addCriterion("CATV_TEL not between", value1, value2, "catvTel");
            return (Criteria) this;
        }

        public Criteria andCatvMailIsNull() {
            addCriterion("CATV_MAIL is null");
            return (Criteria) this;
        }

        public Criteria andCatvMailIsNotNull() {
            addCriterion("CATV_MAIL is not null");
            return (Criteria) this;
        }

        public Criteria andCatvMailEqualTo(String value) {
            addCriterion("CATV_MAIL =", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailNotEqualTo(String value) {
            addCriterion("CATV_MAIL <>", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailGreaterThan(String value) {
            addCriterion("CATV_MAIL >", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_MAIL >=", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailLessThan(String value) {
            addCriterion("CATV_MAIL <", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailLessThanOrEqualTo(String value) {
            addCriterion("CATV_MAIL <=", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailLike(String value) {
            addCriterion("CATV_MAIL like", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailNotLike(String value) {
            addCriterion("CATV_MAIL not like", value, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailIn(List<String> values) {
            addCriterion("CATV_MAIL in", values, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailNotIn(List<String> values) {
            addCriterion("CATV_MAIL not in", values, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailBetween(String value1, String value2) {
            addCriterion("CATV_MAIL between", value1, value2, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvMailNotBetween(String value1, String value2) {
            addCriterion("CATV_MAIL not between", value1, value2, "catvMail");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFIsNull() {
            addCriterion("CATV_DELETE_F is null");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFIsNotNull() {
            addCriterion("CATV_DELETE_F is not null");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFEqualTo(String value) {
            addCriterion("CATV_DELETE_F =", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFNotEqualTo(String value) {
            addCriterion("CATV_DELETE_F <>", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFGreaterThan(String value) {
            addCriterion("CATV_DELETE_F >", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_DELETE_F >=", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFLessThan(String value) {
            addCriterion("CATV_DELETE_F <", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFLessThanOrEqualTo(String value) {
            addCriterion("CATV_DELETE_F <=", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFLike(String value) {
            addCriterion("CATV_DELETE_F like", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFNotLike(String value) {
            addCriterion("CATV_DELETE_F not like", value, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFIn(List<String> values) {
            addCriterion("CATV_DELETE_F in", values, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFNotIn(List<String> values) {
            addCriterion("CATV_DELETE_F not in", values, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFBetween(String value1, String value2) {
            addCriterion("CATV_DELETE_F between", value1, value2, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andCatvDeleteFNotBetween(String value1, String value2) {
            addCriterion("CATV_DELETE_F not between", value1, value2, "catvDeleteF");
            return (Criteria) this;
        }

        public Criteria andContractFIsNull() {
            addCriterion("CONTRACT_F is null");
            return (Criteria) this;
        }

        public Criteria andContractFIsNotNull() {
            addCriterion("CONTRACT_F is not null");
            return (Criteria) this;
        }

        public Criteria andContractFEqualTo(String value) {
            addCriterion("CONTRACT_F =", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFNotEqualTo(String value) {
            addCriterion("CONTRACT_F <>", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFGreaterThan(String value) {
            addCriterion("CONTRACT_F >", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_F >=", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFLessThan(String value) {
            addCriterion("CONTRACT_F <", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_F <=", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFLike(String value) {
            addCriterion("CONTRACT_F like", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFNotLike(String value) {
            addCriterion("CONTRACT_F not like", value, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFIn(List<String> values) {
            addCriterion("CONTRACT_F in", values, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFNotIn(List<String> values) {
            addCriterion("CONTRACT_F not in", values, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFBetween(String value1, String value2) {
            addCriterion("CONTRACT_F between", value1, value2, "contractF");
            return (Criteria) this;
        }

        public Criteria andContractFNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_F not between", value1, value2, "contractF");
            return (Criteria) this;
        }

        public Criteria andInsUidIsNull() {
            addCriterion("INS_UID is null");
            return (Criteria) this;
        }

        public Criteria andInsUidIsNotNull() {
            addCriterion("INS_UID is not null");
            return (Criteria) this;
        }

        public Criteria andInsUidEqualTo(String value) {
            addCriterion("INS_UID =", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidNotEqualTo(String value) {
            addCriterion("INS_UID <>", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidGreaterThan(String value) {
            addCriterion("INS_UID >", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidGreaterThanOrEqualTo(String value) {
            addCriterion("INS_UID >=", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidLessThan(String value) {
            addCriterion("INS_UID <", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidLessThanOrEqualTo(String value) {
            addCriterion("INS_UID <=", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidLike(String value) {
            addCriterion("INS_UID like", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidNotLike(String value) {
            addCriterion("INS_UID not like", value, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidIn(List<String> values) {
            addCriterion("INS_UID in", values, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidNotIn(List<String> values) {
            addCriterion("INS_UID not in", values, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidBetween(String value1, String value2) {
            addCriterion("INS_UID between", value1, value2, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsUidNotBetween(String value1, String value2) {
            addCriterion("INS_UID not between", value1, value2, "insUid");
            return (Criteria) this;
        }

        public Criteria andInsDateIsNull() {
            addCriterion("INS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInsDateIsNotNull() {
            addCriterion("INS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInsDateEqualTo(Date value) {
            addCriterion("INS_DATE =", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateNotEqualTo(Date value) {
            addCriterion("INS_DATE <>", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateGreaterThan(Date value) {
            addCriterion("INS_DATE >", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateGreaterThanOrEqualTo(Date value) {
            addCriterion("INS_DATE >=", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateLessThan(Date value) {
            addCriterion("INS_DATE <", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateLessThanOrEqualTo(Date value) {
            addCriterion("INS_DATE <=", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateIn(List<Date> values) {
            addCriterion("INS_DATE in", values, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateNotIn(List<Date> values) {
            addCriterion("INS_DATE not in", values, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateBetween(Date value1, Date value2) {
            addCriterion("INS_DATE between", value1, value2, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateNotBetween(Date value1, Date value2) {
            addCriterion("INS_DATE not between", value1, value2, "insDate");
            return (Criteria) this;
        }

        public Criteria andUpdUidIsNull() {
            addCriterion("UPD_UID is null");
            return (Criteria) this;
        }

        public Criteria andUpdUidIsNotNull() {
            addCriterion("UPD_UID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdUidEqualTo(String value) {
            addCriterion("UPD_UID =", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidNotEqualTo(String value) {
            addCriterion("UPD_UID <>", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidGreaterThan(String value) {
            addCriterion("UPD_UID >", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidGreaterThanOrEqualTo(String value) {
            addCriterion("UPD_UID >=", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidLessThan(String value) {
            addCriterion("UPD_UID <", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidLessThanOrEqualTo(String value) {
            addCriterion("UPD_UID <=", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidLike(String value) {
            addCriterion("UPD_UID like", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidNotLike(String value) {
            addCriterion("UPD_UID not like", value, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidIn(List<String> values) {
            addCriterion("UPD_UID in", values, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidNotIn(List<String> values) {
            addCriterion("UPD_UID not in", values, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidBetween(String value1, String value2) {
            addCriterion("UPD_UID between", value1, value2, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdUidNotBetween(String value1, String value2) {
            addCriterion("UPD_UID not between", value1, value2, "updUid");
            return (Criteria) this;
        }

        public Criteria andUpdDateIsNull() {
            addCriterion("UPD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdDateIsNotNull() {
            addCriterion("UPD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdDateEqualTo(Date value) {
            addCriterion("UPD_DATE =", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotEqualTo(Date value) {
            addCriterion("UPD_DATE <>", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThan(Date value) {
            addCriterion("UPD_DATE >", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPD_DATE >=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThan(Date value) {
            addCriterion("UPD_DATE <", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThanOrEqualTo(Date value) {
            addCriterion("UPD_DATE <=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateIn(List<Date> values) {
            addCriterion("UPD_DATE in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotIn(List<Date> values) {
            addCriterion("UPD_DATE not in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateBetween(Date value1, Date value2) {
            addCriterion("UPD_DATE between", value1, value2, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotBetween(Date value1, Date value2) {
            addCriterion("UPD_DATE not between", value1, value2, "updDate");
            return (Criteria) this;
        }

        public Criteria andPgIdIsNull() {
            addCriterion("PG_ID is null");
            return (Criteria) this;
        }

        public Criteria andPgIdIsNotNull() {
            addCriterion("PG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPgIdEqualTo(String value) {
            addCriterion("PG_ID =", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdNotEqualTo(String value) {
            addCriterion("PG_ID <>", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdGreaterThan(String value) {
            addCriterion("PG_ID >", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdGreaterThanOrEqualTo(String value) {
            addCriterion("PG_ID >=", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdLessThan(String value) {
            addCriterion("PG_ID <", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdLessThanOrEqualTo(String value) {
            addCriterion("PG_ID <=", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdLike(String value) {
            addCriterion("PG_ID like", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdNotLike(String value) {
            addCriterion("PG_ID not like", value, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdIn(List<String> values) {
            addCriterion("PG_ID in", values, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdNotIn(List<String> values) {
            addCriterion("PG_ID not in", values, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdBetween(String value1, String value2) {
            addCriterion("PG_ID between", value1, value2, "pgId");
            return (Criteria) this;
        }

        public Criteria andPgIdNotBetween(String value1, String value2) {
            addCriterion("PG_ID not between", value1, value2, "pgId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CATV_M
     *
     * @mbg.generated do_not_delete_during_merge Thu Jan 10 17:02:42 JST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CATV_M
     *
     * @mbg.generated Thu Jan 10 17:02:42 JST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}