package foo.sample.db.auto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProvisioningLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public ProvisioningLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
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
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
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

        public Criteria andSeqnoIsNull() {
            addCriterion("SEQNO is null");
            return (Criteria) this;
        }

        public Criteria andSeqnoIsNotNull() {
            addCriterion("SEQNO is not null");
            return (Criteria) this;
        }

        public Criteria andSeqnoEqualTo(Integer value) {
            addCriterion("SEQNO =", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotEqualTo(Integer value) {
            addCriterion("SEQNO <>", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThan(Integer value) {
            addCriterion("SEQNO >", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("SEQNO >=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThan(Integer value) {
            addCriterion("SEQNO <", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThanOrEqualTo(Integer value) {
            addCriterion("SEQNO <=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoIn(List<Integer> values) {
            addCriterion("SEQNO in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotIn(List<Integer> values) {
            addCriterion("SEQNO not in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoBetween(Integer value1, Integer value2) {
            addCriterion("SEQNO between", value1, value2, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotBetween(Integer value1, Integer value2) {
            addCriterion("SEQNO not between", value1, value2, "seqno");
            return (Criteria) this;
        }

        public Criteria andCatvCodeIsNull() {
            addCriterion("CATV_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCatvCodeIsNotNull() {
            addCriterion("CATV_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCatvCodeEqualTo(String value) {
            addCriterion("CATV_CODE =", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeNotEqualTo(String value) {
            addCriterion("CATV_CODE <>", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeGreaterThan(String value) {
            addCriterion("CATV_CODE >", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CATV_CODE >=", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeLessThan(String value) {
            addCriterion("CATV_CODE <", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeLessThanOrEqualTo(String value) {
            addCriterion("CATV_CODE <=", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeLike(String value) {
            addCriterion("CATV_CODE like", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeNotLike(String value) {
            addCriterion("CATV_CODE not like", value, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeIn(List<String> values) {
            addCriterion("CATV_CODE in", values, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeNotIn(List<String> values) {
            addCriterion("CATV_CODE not in", values, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeBetween(String value1, String value2) {
            addCriterion("CATV_CODE between", value1, value2, "catvCode");
            return (Criteria) this;
        }

        public Criteria andCatvCodeNotBetween(String value1, String value2) {
            addCriterion("CATV_CODE not between", value1, value2, "catvCode");
            return (Criteria) this;
        }

        public Criteria andLogAtIsNull() {
            addCriterion("LOG_AT is null");
            return (Criteria) this;
        }

        public Criteria andLogAtIsNotNull() {
            addCriterion("LOG_AT is not null");
            return (Criteria) this;
        }

        public Criteria andLogAtEqualTo(Date value) {
            addCriterion("LOG_AT =", value, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtNotEqualTo(Date value) {
            addCriterion("LOG_AT <>", value, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtGreaterThan(Date value) {
            addCriterion("LOG_AT >", value, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtGreaterThanOrEqualTo(Date value) {
            addCriterion("LOG_AT >=", value, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtLessThan(Date value) {
            addCriterion("LOG_AT <", value, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtLessThanOrEqualTo(Date value) {
            addCriterion("LOG_AT <=", value, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtIn(List<Date> values) {
            addCriterion("LOG_AT in", values, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtNotIn(List<Date> values) {
            addCriterion("LOG_AT not in", values, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtBetween(Date value1, Date value2) {
            addCriterion("LOG_AT between", value1, value2, "logAt");
            return (Criteria) this;
        }

        public Criteria andLogAtNotBetween(Date value1, Date value2) {
            addCriterion("LOG_AT not between", value1, value2, "logAt");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdIsNull() {
            addCriterion("SUBSCRIBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdIsNotNull() {
            addCriterion("SUBSCRIBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdEqualTo(String value) {
            addCriterion("SUBSCRIBER_ID =", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdNotEqualTo(String value) {
            addCriterion("SUBSCRIBER_ID <>", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdGreaterThan(String value) {
            addCriterion("SUBSCRIBER_ID >", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSCRIBER_ID >=", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdLessThan(String value) {
            addCriterion("SUBSCRIBER_ID <", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdLessThanOrEqualTo(String value) {
            addCriterion("SUBSCRIBER_ID <=", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdLike(String value) {
            addCriterion("SUBSCRIBER_ID like", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdNotLike(String value) {
            addCriterion("SUBSCRIBER_ID not like", value, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdIn(List<String> values) {
            addCriterion("SUBSCRIBER_ID in", values, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdNotIn(List<String> values) {
            addCriterion("SUBSCRIBER_ID not in", values, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdBetween(String value1, String value2) {
            addCriterion("SUBSCRIBER_ID between", value1, value2, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andSubscriberIdNotBetween(String value1, String value2) {
            addCriterion("SUBSCRIBER_ID not between", value1, value2, "subscriberId");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNull() {
            addCriterion("OPERATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("OPERATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(String value) {
            addCriterion("OPERATE_TYPE =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(String value) {
            addCriterion("OPERATE_TYPE <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(String value) {
            addCriterion("OPERATE_TYPE >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATE_TYPE >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(String value) {
            addCriterion("OPERATE_TYPE <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(String value) {
            addCriterion("OPERATE_TYPE <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLike(String value) {
            addCriterion("OPERATE_TYPE like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotLike(String value) {
            addCriterion("OPERATE_TYPE not like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<String> values) {
            addCriterion("OPERATE_TYPE in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<String> values) {
            addCriterion("OPERATE_TYPE not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(String value1, String value2) {
            addCriterion("OPERATE_TYPE between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(String value1, String value2) {
            addCriterion("OPERATE_TYPE not between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTargetIsNull() {
            addCriterion("OPERATE_TARGET is null");
            return (Criteria) this;
        }

        public Criteria andOperateTargetIsNotNull() {
            addCriterion("OPERATE_TARGET is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTargetEqualTo(String value) {
            addCriterion("OPERATE_TARGET =", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetNotEqualTo(String value) {
            addCriterion("OPERATE_TARGET <>", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetGreaterThan(String value) {
            addCriterion("OPERATE_TARGET >", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATE_TARGET >=", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetLessThan(String value) {
            addCriterion("OPERATE_TARGET <", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetLessThanOrEqualTo(String value) {
            addCriterion("OPERATE_TARGET <=", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetLike(String value) {
            addCriterion("OPERATE_TARGET like", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetNotLike(String value) {
            addCriterion("OPERATE_TARGET not like", value, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetIn(List<String> values) {
            addCriterion("OPERATE_TARGET in", values, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetNotIn(List<String> values) {
            addCriterion("OPERATE_TARGET not in", values, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetBetween(String value1, String value2) {
            addCriterion("OPERATE_TARGET between", value1, value2, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateTargetNotBetween(String value1, String value2) {
            addCriterion("OPERATE_TARGET not between", value1, value2, "operateTarget");
            return (Criteria) this;
        }

        public Criteria andOperateContentsIsNull() {
            addCriterion("OPERATE_CONTENTS is null");
            return (Criteria) this;
        }

        public Criteria andOperateContentsIsNotNull() {
            addCriterion("OPERATE_CONTENTS is not null");
            return (Criteria) this;
        }

        public Criteria andOperateContentsEqualTo(String value) {
            addCriterion("OPERATE_CONTENTS =", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsNotEqualTo(String value) {
            addCriterion("OPERATE_CONTENTS <>", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsGreaterThan(String value) {
            addCriterion("OPERATE_CONTENTS >", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATE_CONTENTS >=", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsLessThan(String value) {
            addCriterion("OPERATE_CONTENTS <", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsLessThanOrEqualTo(String value) {
            addCriterion("OPERATE_CONTENTS <=", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsLike(String value) {
            addCriterion("OPERATE_CONTENTS like", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsNotLike(String value) {
            addCriterion("OPERATE_CONTENTS not like", value, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsIn(List<String> values) {
            addCriterion("OPERATE_CONTENTS in", values, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsNotIn(List<String> values) {
            addCriterion("OPERATE_CONTENTS not in", values, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsBetween(String value1, String value2) {
            addCriterion("OPERATE_CONTENTS between", value1, value2, "operateContents");
            return (Criteria) this;
        }

        public Criteria andOperateContentsNotBetween(String value1, String value2) {
            addCriterion("OPERATE_CONTENTS not between", value1, value2, "operateContents");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("RESULT is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("RESULT =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("RESULT <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("RESULT >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("RESULT <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("RESULT <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("RESULT like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("RESULT not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("RESULT in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("RESULT not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("RESULT between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("RESULT not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andSpIdIsNull() {
            addCriterion("SP_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpIdIsNotNull() {
            addCriterion("SP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpIdEqualTo(String value) {
            addCriterion("SP_ID =", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotEqualTo(String value) {
            addCriterion("SP_ID <>", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdGreaterThan(String value) {
            addCriterion("SP_ID >", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdGreaterThanOrEqualTo(String value) {
            addCriterion("SP_ID >=", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdLessThan(String value) {
            addCriterion("SP_ID <", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdLessThanOrEqualTo(String value) {
            addCriterion("SP_ID <=", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdLike(String value) {
            addCriterion("SP_ID like", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotLike(String value) {
            addCriterion("SP_ID not like", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdIn(List<String> values) {
            addCriterion("SP_ID in", values, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotIn(List<String> values) {
            addCriterion("SP_ID not in", values, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdBetween(String value1, String value2) {
            addCriterion("SP_ID between", value1, value2, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotBetween(String value1, String value2) {
            addCriterion("SP_ID not between", value1, value2, "spId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated do_not_delete_during_merge Wed Dec 26 15:27:34 JST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROVISIONING_LOG
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
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