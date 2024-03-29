package foo.sample.db.auto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CampaignMasterExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public CampaignMasterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
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
     * This method corresponds to the database table CAMPAIGN_MASTER
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
     * This method corresponds to the database table CAMPAIGN_MASTER
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGN_MASTER
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
     * This class corresponds to the database table CAMPAIGN_MASTER
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

        public Criteria andCampaignIdIsNull() {
            addCriterion("CAMPAIGN_ID is null");
            return (Criteria) this;
        }

        public Criteria andCampaignIdIsNotNull() {
            addCriterion("CAMPAIGN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignIdEqualTo(String value) {
            addCriterion("CAMPAIGN_ID =", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotEqualTo(String value) {
            addCriterion("CAMPAIGN_ID <>", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdGreaterThan(String value) {
            addCriterion("CAMPAIGN_ID >", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdGreaterThanOrEqualTo(String value) {
            addCriterion("CAMPAIGN_ID >=", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLessThan(String value) {
            addCriterion("CAMPAIGN_ID <", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLessThanOrEqualTo(String value) {
            addCriterion("CAMPAIGN_ID <=", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLike(String value) {
            addCriterion("CAMPAIGN_ID like", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotLike(String value) {
            addCriterion("CAMPAIGN_ID not like", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdIn(List<String> values) {
            addCriterion("CAMPAIGN_ID in", values, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotIn(List<String> values) {
            addCriterion("CAMPAIGN_ID not in", values, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdBetween(String value1, String value2) {
            addCriterion("CAMPAIGN_ID between", value1, value2, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotBetween(String value1, String value2) {
            addCriterion("CAMPAIGN_ID not between", value1, value2, "campaignId");
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

        public Criteria andCampaignStartAtIsNull() {
            addCriterion("CAMPAIGN_START_AT is null");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtIsNotNull() {
            addCriterion("CAMPAIGN_START_AT is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtEqualTo(Date value) {
            addCriterion("CAMPAIGN_START_AT =", value, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtNotEqualTo(Date value) {
            addCriterion("CAMPAIGN_START_AT <>", value, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtGreaterThan(Date value) {
            addCriterion("CAMPAIGN_START_AT >", value, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtGreaterThanOrEqualTo(Date value) {
            addCriterion("CAMPAIGN_START_AT >=", value, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtLessThan(Date value) {
            addCriterion("CAMPAIGN_START_AT <", value, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtLessThanOrEqualTo(Date value) {
            addCriterion("CAMPAIGN_START_AT <=", value, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtIn(List<Date> values) {
            addCriterion("CAMPAIGN_START_AT in", values, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtNotIn(List<Date> values) {
            addCriterion("CAMPAIGN_START_AT not in", values, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtBetween(Date value1, Date value2) {
            addCriterion("CAMPAIGN_START_AT between", value1, value2, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignStartAtNotBetween(Date value1, Date value2) {
            addCriterion("CAMPAIGN_START_AT not between", value1, value2, "campaignStartAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtIsNull() {
            addCriterion("CAMPAIGN_END_AT is null");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtIsNotNull() {
            addCriterion("CAMPAIGN_END_AT is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtEqualTo(Date value) {
            addCriterion("CAMPAIGN_END_AT =", value, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtNotEqualTo(Date value) {
            addCriterion("CAMPAIGN_END_AT <>", value, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtGreaterThan(Date value) {
            addCriterion("CAMPAIGN_END_AT >", value, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("CAMPAIGN_END_AT >=", value, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtLessThan(Date value) {
            addCriterion("CAMPAIGN_END_AT <", value, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtLessThanOrEqualTo(Date value) {
            addCriterion("CAMPAIGN_END_AT <=", value, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtIn(List<Date> values) {
            addCriterion("CAMPAIGN_END_AT in", values, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtNotIn(List<Date> values) {
            addCriterion("CAMPAIGN_END_AT not in", values, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtBetween(Date value1, Date value2) {
            addCriterion("CAMPAIGN_END_AT between", value1, value2, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andCampaignEndAtNotBetween(Date value1, Date value2) {
            addCriterion("CAMPAIGN_END_AT not between", value1, value2, "campaignEndAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtIsNull() {
            addCriterion("INSERT_AT is null");
            return (Criteria) this;
        }

        public Criteria andInsertAtIsNotNull() {
            addCriterion("INSERT_AT is not null");
            return (Criteria) this;
        }

        public Criteria andInsertAtEqualTo(Date value) {
            addCriterion("INSERT_AT =", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtNotEqualTo(Date value) {
            addCriterion("INSERT_AT <>", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtGreaterThan(Date value) {
            addCriterion("INSERT_AT >", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtGreaterThanOrEqualTo(Date value) {
            addCriterion("INSERT_AT >=", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtLessThan(Date value) {
            addCriterion("INSERT_AT <", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtLessThanOrEqualTo(Date value) {
            addCriterion("INSERT_AT <=", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtIn(List<Date> values) {
            addCriterion("INSERT_AT in", values, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtNotIn(List<Date> values) {
            addCriterion("INSERT_AT not in", values, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtBetween(Date value1, Date value2) {
            addCriterion("INSERT_AT between", value1, value2, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtNotBetween(Date value1, Date value2) {
            addCriterion("INSERT_AT not between", value1, value2, "insertAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNull() {
            addCriterion("UPDATE_AT is null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNotNull() {
            addCriterion("UPDATE_AT is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtEqualTo(Date value) {
            addCriterion("UPDATE_AT =", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotEqualTo(Date value) {
            addCriterion("UPDATE_AT <>", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThan(Date value) {
            addCriterion("UPDATE_AT >", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_AT >=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThan(Date value) {
            addCriterion("UPDATE_AT <", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_AT <=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIn(List<Date> values) {
            addCriterion("UPDATE_AT in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotIn(List<Date> values) {
            addCriterion("UPDATE_AT not in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtBetween(Date value1, Date value2) {
            addCriterion("UPDATE_AT between", value1, value2, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_AT not between", value1, value2, "updateAt");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CAMPAIGN_MASTER
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
     * This class corresponds to the database table CAMPAIGN_MASTER
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