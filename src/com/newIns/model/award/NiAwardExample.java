package com.newIns.model.award;

import java.util.ArrayList;
import java.util.List;

public class NiAwardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiAwardExample() {
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

        public Criteria andAwardidIsNull() {
            addCriterion("awardId is null");
            return (Criteria) this;
        }

        public Criteria andAwardidIsNotNull() {
            addCriterion("awardId is not null");
            return (Criteria) this;
        }

        public Criteria andAwardidEqualTo(Integer value) {
            addCriterion("awardId =", value, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidNotEqualTo(Integer value) {
            addCriterion("awardId <>", value, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidGreaterThan(Integer value) {
            addCriterion("awardId >", value, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidGreaterThanOrEqualTo(Integer value) {
            addCriterion("awardId >=", value, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidLessThan(Integer value) {
            addCriterion("awardId <", value, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidLessThanOrEqualTo(Integer value) {
            addCriterion("awardId <=", value, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidIn(List<Integer> values) {
            addCriterion("awardId in", values, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidNotIn(List<Integer> values) {
            addCriterion("awardId not in", values, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidBetween(Integer value1, Integer value2) {
            addCriterion("awardId between", value1, value2, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardidNotBetween(Integer value1, Integer value2) {
            addCriterion("awardId not between", value1, value2, "awardid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidIsNull() {
            addCriterion("awardPoolId is null");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidIsNotNull() {
            addCriterion("awardPoolId is not null");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidEqualTo(Integer value) {
            addCriterion("awardPoolId =", value, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidNotEqualTo(Integer value) {
            addCriterion("awardPoolId <>", value, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidGreaterThan(Integer value) {
            addCriterion("awardPoolId >", value, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidGreaterThanOrEqualTo(Integer value) {
            addCriterion("awardPoolId >=", value, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidLessThan(Integer value) {
            addCriterion("awardPoolId <", value, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidLessThanOrEqualTo(Integer value) {
            addCriterion("awardPoolId <=", value, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidIn(List<Integer> values) {
            addCriterion("awardPoolId in", values, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidNotIn(List<Integer> values) {
            addCriterion("awardPoolId not in", values, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidBetween(Integer value1, Integer value2) {
            addCriterion("awardPoolId between", value1, value2, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardpoolidNotBetween(Integer value1, Integer value2) {
            addCriterion("awardPoolId not between", value1, value2, "awardpoolid");
            return (Criteria) this;
        }

        public Criteria andAwardnumIsNull() {
            addCriterion("awardNum is null");
            return (Criteria) this;
        }

        public Criteria andAwardnumIsNotNull() {
            addCriterion("awardNum is not null");
            return (Criteria) this;
        }

        public Criteria andAwardnumEqualTo(Integer value) {
            addCriterion("awardNum =", value, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumNotEqualTo(Integer value) {
            addCriterion("awardNum <>", value, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumGreaterThan(Integer value) {
            addCriterion("awardNum >", value, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("awardNum >=", value, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumLessThan(Integer value) {
            addCriterion("awardNum <", value, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumLessThanOrEqualTo(Integer value) {
            addCriterion("awardNum <=", value, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumIn(List<Integer> values) {
            addCriterion("awardNum in", values, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumNotIn(List<Integer> values) {
            addCriterion("awardNum not in", values, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumBetween(Integer value1, Integer value2) {
            addCriterion("awardNum between", value1, value2, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardnumNotBetween(Integer value1, Integer value2) {
            addCriterion("awardNum not between", value1, value2, "awardnum");
            return (Criteria) this;
        }

        public Criteria andAwardpicIsNull() {
            addCriterion("awardPic is null");
            return (Criteria) this;
        }

        public Criteria andAwardpicIsNotNull() {
            addCriterion("awardPic is not null");
            return (Criteria) this;
        }

        public Criteria andAwardpicEqualTo(String value) {
            addCriterion("awardPic =", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicNotEqualTo(String value) {
            addCriterion("awardPic <>", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicGreaterThan(String value) {
            addCriterion("awardPic >", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicGreaterThanOrEqualTo(String value) {
            addCriterion("awardPic >=", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicLessThan(String value) {
            addCriterion("awardPic <", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicLessThanOrEqualTo(String value) {
            addCriterion("awardPic <=", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicLike(String value) {
            addCriterion("awardPic like", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicNotLike(String value) {
            addCriterion("awardPic not like", value, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicIn(List<String> values) {
            addCriterion("awardPic in", values, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicNotIn(List<String> values) {
            addCriterion("awardPic not in", values, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicBetween(String value1, String value2) {
            addCriterion("awardPic between", value1, value2, "awardpic");
            return (Criteria) this;
        }

        public Criteria andAwardpicNotBetween(String value1, String value2) {
            addCriterion("awardPic not between", value1, value2, "awardpic");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andAwardnameIsNull() {
            addCriterion("awardName is null");
            return (Criteria) this;
        }

        public Criteria andAwardnameIsNotNull() {
            addCriterion("awardName is not null");
            return (Criteria) this;
        }

        public Criteria andAwardnameEqualTo(String value) {
            addCriterion("awardName =", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameNotEqualTo(String value) {
            addCriterion("awardName <>", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameGreaterThan(String value) {
            addCriterion("awardName >", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameGreaterThanOrEqualTo(String value) {
            addCriterion("awardName >=", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameLessThan(String value) {
            addCriterion("awardName <", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameLessThanOrEqualTo(String value) {
            addCriterion("awardName <=", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameLike(String value) {
            addCriterion("awardName like", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameNotLike(String value) {
            addCriterion("awardName not like", value, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameIn(List<String> values) {
            addCriterion("awardName in", values, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameNotIn(List<String> values) {
            addCriterion("awardName not in", values, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameBetween(String value1, String value2) {
            addCriterion("awardName between", value1, value2, "awardname");
            return (Criteria) this;
        }

        public Criteria andAwardnameNotBetween(String value1, String value2) {
            addCriterion("awardName not between", value1, value2, "awardname");
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