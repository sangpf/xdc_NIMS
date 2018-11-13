package com.newIns.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NiAdInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiAdInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andAdidIsNull() {
            addCriterion("adId is null");
            return (Criteria) this;
        }

        public Criteria andAdidIsNotNull() {
            addCriterion("adId is not null");
            return (Criteria) this;
        }

        public Criteria andAdidEqualTo(Integer value) {
            addCriterion("adId =", value, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidNotEqualTo(Integer value) {
            addCriterion("adId <>", value, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidGreaterThan(Integer value) {
            addCriterion("adId >", value, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidGreaterThanOrEqualTo(Integer value) {
            addCriterion("adId >=", value, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidLessThan(Integer value) {
            addCriterion("adId <", value, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidLessThanOrEqualTo(Integer value) {
            addCriterion("adId <=", value, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidIn(List<Integer> values) {
            addCriterion("adId in", values, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidNotIn(List<Integer> values) {
            addCriterion("adId not in", values, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidBetween(Integer value1, Integer value2) {
            addCriterion("adId between", value1, value2, "adid");
            return (Criteria) this;
        }

        public Criteria andAdidNotBetween(Integer value1, Integer value2) {
            addCriterion("adId not between", value1, value2, "adid");
            return (Criteria) this;
        }

        public Criteria andAdtitleIsNull() {
            addCriterion("adTitle is null");
            return (Criteria) this;
        }

        public Criteria andAdtitleIsNotNull() {
            addCriterion("adTitle is not null");
            return (Criteria) this;
        }

        public Criteria andAdtitleEqualTo(String value) {
            addCriterion("adTitle =", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleNotEqualTo(String value) {
            addCriterion("adTitle <>", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleGreaterThan(String value) {
            addCriterion("adTitle >", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleGreaterThanOrEqualTo(String value) {
            addCriterion("adTitle >=", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleLessThan(String value) {
            addCriterion("adTitle <", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleLessThanOrEqualTo(String value) {
            addCriterion("adTitle <=", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleLike(String value) {
            addCriterion("adTitle like", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleNotLike(String value) {
            addCriterion("adTitle not like", value, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleIn(List<String> values) {
            addCriterion("adTitle in", values, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleNotIn(List<String> values) {
            addCriterion("adTitle not in", values, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleBetween(String value1, String value2) {
            addCriterion("adTitle between", value1, value2, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdtitleNotBetween(String value1, String value2) {
            addCriterion("adTitle not between", value1, value2, "adtitle");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridIsNull() {
            addCriterion("adCustomerId is null");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridIsNotNull() {
            addCriterion("adCustomerId is not null");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridEqualTo(Integer value) {
            addCriterion("adCustomerId =", value, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridNotEqualTo(Integer value) {
            addCriterion("adCustomerId <>", value, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridGreaterThan(Integer value) {
            addCriterion("adCustomerId >", value, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridGreaterThanOrEqualTo(Integer value) {
            addCriterion("adCustomerId >=", value, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridLessThan(Integer value) {
            addCriterion("adCustomerId <", value, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridLessThanOrEqualTo(Integer value) {
            addCriterion("adCustomerId <=", value, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridIn(List<Integer> values) {
            addCriterion("adCustomerId in", values, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridNotIn(List<Integer> values) {
            addCriterion("adCustomerId not in", values, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridBetween(Integer value1, Integer value2) {
            addCriterion("adCustomerId between", value1, value2, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdcustomeridNotBetween(Integer value1, Integer value2) {
            addCriterion("adCustomerId not between", value1, value2, "adcustomerid");
            return (Criteria) this;
        }

        public Criteria andAdtypeIsNull() {
            addCriterion("adType is null");
            return (Criteria) this;
        }

        public Criteria andAdtypeIsNotNull() {
            addCriterion("adType is not null");
            return (Criteria) this;
        }

        public Criteria andAdtypeEqualTo(Byte value) {
            addCriterion("adType =", value, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeNotEqualTo(Byte value) {
            addCriterion("adType <>", value, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeGreaterThan(Byte value) {
            addCriterion("adType >", value, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("adType >=", value, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeLessThan(Byte value) {
            addCriterion("adType <", value, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeLessThanOrEqualTo(Byte value) {
            addCriterion("adType <=", value, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeIn(List<Byte> values) {
            addCriterion("adType in", values, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeNotIn(List<Byte> values) {
            addCriterion("adType not in", values, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeBetween(Byte value1, Byte value2) {
            addCriterion("adType between", value1, value2, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdtypeNotBetween(Byte value1, Byte value2) {
            addCriterion("adType not between", value1, value2, "adtype");
            return (Criteria) this;
        }

        public Criteria andAdimgIsNull() {
            addCriterion("adImg is null");
            return (Criteria) this;
        }

        public Criteria andAdimgIsNotNull() {
            addCriterion("adImg is not null");
            return (Criteria) this;
        }

        public Criteria andAdimgEqualTo(String value) {
            addCriterion("adImg =", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgNotEqualTo(String value) {
            addCriterion("adImg <>", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgGreaterThan(String value) {
            addCriterion("adImg >", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgGreaterThanOrEqualTo(String value) {
            addCriterion("adImg >=", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgLessThan(String value) {
            addCriterion("adImg <", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgLessThanOrEqualTo(String value) {
            addCriterion("adImg <=", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgLike(String value) {
            addCriterion("adImg like", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgNotLike(String value) {
            addCriterion("adImg not like", value, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgIn(List<String> values) {
            addCriterion("adImg in", values, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgNotIn(List<String> values) {
            addCriterion("adImg not in", values, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgBetween(String value1, String value2) {
            addCriterion("adImg between", value1, value2, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdimgNotBetween(String value1, String value2) {
            addCriterion("adImg not between", value1, value2, "adimg");
            return (Criteria) this;
        }

        public Criteria andAdctimeIsNull() {
            addCriterion("adCTime is null");
            return (Criteria) this;
        }

        public Criteria andAdctimeIsNotNull() {
            addCriterion("adCTime is not null");
            return (Criteria) this;
        }

        public Criteria andAdctimeEqualTo(Date value) {
            addCriterion("adCTime =", value, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeNotEqualTo(Date value) {
            addCriterion("adCTime <>", value, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeGreaterThan(Date value) {
            addCriterion("adCTime >", value, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeGreaterThanOrEqualTo(Date value) {
            addCriterion("adCTime >=", value, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeLessThan(Date value) {
            addCriterion("adCTime <", value, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeLessThanOrEqualTo(Date value) {
            addCriterion("adCTime <=", value, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeIn(List<Date> values) {
            addCriterion("adCTime in", values, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeNotIn(List<Date> values) {
            addCriterion("adCTime not in", values, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeBetween(Date value1, Date value2) {
            addCriterion("adCTime between", value1, value2, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdctimeNotBetween(Date value1, Date value2) {
            addCriterion("adCTime not between", value1, value2, "adctime");
            return (Criteria) this;
        }

        public Criteria andAdlinkIsNull() {
            addCriterion("adLink is null");
            return (Criteria) this;
        }

        public Criteria andAdlinkIsNotNull() {
            addCriterion("adLink is not null");
            return (Criteria) this;
        }

        public Criteria andAdlinkEqualTo(String value) {
            addCriterion("adLink =", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkNotEqualTo(String value) {
            addCriterion("adLink <>", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkGreaterThan(String value) {
            addCriterion("adLink >", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkGreaterThanOrEqualTo(String value) {
            addCriterion("adLink >=", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkLessThan(String value) {
            addCriterion("adLink <", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkLessThanOrEqualTo(String value) {
            addCriterion("adLink <=", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkLike(String value) {
            addCriterion("adLink like", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkNotLike(String value) {
            addCriterion("adLink not like", value, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkIn(List<String> values) {
            addCriterion("adLink in", values, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkNotIn(List<String> values) {
            addCriterion("adLink not in", values, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkBetween(String value1, String value2) {
            addCriterion("adLink between", value1, value2, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdlinkNotBetween(String value1, String value2) {
            addCriterion("adLink not between", value1, value2, "adlink");
            return (Criteria) this;
        }

        public Criteria andAdstatusIsNull() {
            addCriterion("adStatus is null");
            return (Criteria) this;
        }

        public Criteria andAdstatusIsNotNull() {
            addCriterion("adStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAdstatusEqualTo(Byte value) {
            addCriterion("adStatus =", value, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusNotEqualTo(Byte value) {
            addCriterion("adStatus <>", value, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusGreaterThan(Byte value) {
            addCriterion("adStatus >", value, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("adStatus >=", value, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusLessThan(Byte value) {
            addCriterion("adStatus <", value, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusLessThanOrEqualTo(Byte value) {
            addCriterion("adStatus <=", value, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusIn(List<Byte> values) {
            addCriterion("adStatus in", values, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusNotIn(List<Byte> values) {
            addCriterion("adStatus not in", values, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusBetween(Byte value1, Byte value2) {
            addCriterion("adStatus between", value1, value2, "adstatus");
            return (Criteria) this;
        }

        public Criteria andAdstatusNotBetween(Byte value1, Byte value2) {
            addCriterion("adStatus not between", value1, value2, "adstatus");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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