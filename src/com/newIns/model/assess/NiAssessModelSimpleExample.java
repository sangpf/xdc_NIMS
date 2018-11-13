package com.newIns.model.assess;

import java.util.ArrayList;
import java.util.List;

public class NiAssessModelSimpleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiAssessModelSimpleExample() {
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

        public Criteria andAqnidIsNull() {
            addCriterion("aqnId is null");
            return (Criteria) this;
        }

        public Criteria andAqnidIsNotNull() {
            addCriterion("aqnId is not null");
            return (Criteria) this;
        }

        public Criteria andAqnidEqualTo(Integer value) {
            addCriterion("aqnId =", value, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidNotEqualTo(Integer value) {
            addCriterion("aqnId <>", value, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidGreaterThan(Integer value) {
            addCriterion("aqnId >", value, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidGreaterThanOrEqualTo(Integer value) {
            addCriterion("aqnId >=", value, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidLessThan(Integer value) {
            addCriterion("aqnId <", value, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidLessThanOrEqualTo(Integer value) {
            addCriterion("aqnId <=", value, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidIn(List<Integer> values) {
            addCriterion("aqnId in", values, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidNotIn(List<Integer> values) {
            addCriterion("aqnId not in", values, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidBetween(Integer value1, Integer value2) {
            addCriterion("aqnId between", value1, value2, "aqnid");
            return (Criteria) this;
        }

        public Criteria andAqnidNotBetween(Integer value1, Integer value2) {
            addCriterion("aqnId not between", value1, value2, "aqnid");
            return (Criteria) this;
        }

        public Criteria andIntervalidIsNull() {
            addCriterion("intervalId is null");
            return (Criteria) this;
        }

        public Criteria andIntervalidIsNotNull() {
            addCriterion("intervalId is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalidEqualTo(Integer value) {
            addCriterion("intervalId =", value, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidNotEqualTo(Integer value) {
            addCriterion("intervalId <>", value, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidGreaterThan(Integer value) {
            addCriterion("intervalId >", value, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidGreaterThanOrEqualTo(Integer value) {
            addCriterion("intervalId >=", value, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidLessThan(Integer value) {
            addCriterion("intervalId <", value, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidLessThanOrEqualTo(Integer value) {
            addCriterion("intervalId <=", value, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidIn(List<Integer> values) {
            addCriterion("intervalId in", values, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidNotIn(List<Integer> values) {
            addCriterion("intervalId not in", values, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidBetween(Integer value1, Integer value2) {
            addCriterion("intervalId between", value1, value2, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalidNotBetween(Integer value1, Integer value2) {
            addCriterion("intervalId not between", value1, value2, "intervalid");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginIsNull() {
            addCriterion("intervalBegin is null");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginIsNotNull() {
            addCriterion("intervalBegin is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginEqualTo(Integer value) {
            addCriterion("intervalBegin =", value, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginNotEqualTo(Integer value) {
            addCriterion("intervalBegin <>", value, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginGreaterThan(Integer value) {
            addCriterion("intervalBegin >", value, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("intervalBegin >=", value, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginLessThan(Integer value) {
            addCriterion("intervalBegin <", value, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginLessThanOrEqualTo(Integer value) {
            addCriterion("intervalBegin <=", value, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginIn(List<Integer> values) {
            addCriterion("intervalBegin in", values, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginNotIn(List<Integer> values) {
            addCriterion("intervalBegin not in", values, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginBetween(Integer value1, Integer value2) {
            addCriterion("intervalBegin between", value1, value2, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalbeginNotBetween(Integer value1, Integer value2) {
            addCriterion("intervalBegin not between", value1, value2, "intervalbegin");
            return (Criteria) this;
        }

        public Criteria andIntervalendIsNull() {
            addCriterion("intervalEnd is null");
            return (Criteria) this;
        }

        public Criteria andIntervalendIsNotNull() {
            addCriterion("intervalEnd is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalendEqualTo(Integer value) {
            addCriterion("intervalEnd =", value, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendNotEqualTo(Integer value) {
            addCriterion("intervalEnd <>", value, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendGreaterThan(Integer value) {
            addCriterion("intervalEnd >", value, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendGreaterThanOrEqualTo(Integer value) {
            addCriterion("intervalEnd >=", value, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendLessThan(Integer value) {
            addCriterion("intervalEnd <", value, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendLessThanOrEqualTo(Integer value) {
            addCriterion("intervalEnd <=", value, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendIn(List<Integer> values) {
            addCriterion("intervalEnd in", values, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendNotIn(List<Integer> values) {
            addCriterion("intervalEnd not in", values, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendBetween(Integer value1, Integer value2) {
            addCriterion("intervalEnd between", value1, value2, "intervalend");
            return (Criteria) this;
        }

        public Criteria andIntervalendNotBetween(Integer value1, Integer value2) {
            addCriterion("intervalEnd not between", value1, value2, "intervalend");
            return (Criteria) this;
        }

        public Criteria andResultsummaryIsNull() {
            addCriterion("resultSummary is null");
            return (Criteria) this;
        }

        public Criteria andResultsummaryIsNotNull() {
            addCriterion("resultSummary is not null");
            return (Criteria) this;
        }

        public Criteria andResultsummaryEqualTo(String value) {
            addCriterion("resultSummary =", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryNotEqualTo(String value) {
            addCriterion("resultSummary <>", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryGreaterThan(String value) {
            addCriterion("resultSummary >", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryGreaterThanOrEqualTo(String value) {
            addCriterion("resultSummary >=", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryLessThan(String value) {
            addCriterion("resultSummary <", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryLessThanOrEqualTo(String value) {
            addCriterion("resultSummary <=", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryLike(String value) {
            addCriterion("resultSummary like", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryNotLike(String value) {
            addCriterion("resultSummary not like", value, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryIn(List<String> values) {
            addCriterion("resultSummary in", values, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryNotIn(List<String> values) {
            addCriterion("resultSummary not in", values, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryBetween(String value1, String value2) {
            addCriterion("resultSummary between", value1, value2, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultsummaryNotBetween(String value1, String value2) {
            addCriterion("resultSummary not between", value1, value2, "resultsummary");
            return (Criteria) this;
        }

        public Criteria andResultdetailIsNull() {
            addCriterion("resultDetail is null");
            return (Criteria) this;
        }

        public Criteria andResultdetailIsNotNull() {
            addCriterion("resultDetail is not null");
            return (Criteria) this;
        }

        public Criteria andResultdetailEqualTo(String value) {
            addCriterion("resultDetail =", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailNotEqualTo(String value) {
            addCriterion("resultDetail <>", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailGreaterThan(String value) {
            addCriterion("resultDetail >", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailGreaterThanOrEqualTo(String value) {
            addCriterion("resultDetail >=", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailLessThan(String value) {
            addCriterion("resultDetail <", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailLessThanOrEqualTo(String value) {
            addCriterion("resultDetail <=", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailLike(String value) {
            addCriterion("resultDetail like", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailNotLike(String value) {
            addCriterion("resultDetail not like", value, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailIn(List<String> values) {
            addCriterion("resultDetail in", values, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailNotIn(List<String> values) {
            addCriterion("resultDetail not in", values, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailBetween(String value1, String value2) {
            addCriterion("resultDetail between", value1, value2, "resultdetail");
            return (Criteria) this;
        }

        public Criteria andResultdetailNotBetween(String value1, String value2) {
            addCriterion("resultDetail not between", value1, value2, "resultdetail");
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