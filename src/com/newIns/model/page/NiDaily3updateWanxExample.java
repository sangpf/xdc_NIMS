package com.newIns.model.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NiDaily3updateWanxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiDaily3updateWanxExample() {
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

        public Criteria andDeliveryidIsNull() {
            addCriterion("deliveryId is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryidIsNotNull() {
            addCriterion("deliveryId is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryidEqualTo(Integer value) {
            addCriterion("deliveryId =", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidNotEqualTo(Integer value) {
            addCriterion("deliveryId <>", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidGreaterThan(Integer value) {
            addCriterion("deliveryId >", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliveryId >=", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidLessThan(Integer value) {
            addCriterion("deliveryId <", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidLessThanOrEqualTo(Integer value) {
            addCriterion("deliveryId <=", value, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidIn(List<Integer> values) {
            addCriterion("deliveryId in", values, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidNotIn(List<Integer> values) {
            addCriterion("deliveryId not in", values, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidBetween(Integer value1, Integer value2) {
            addCriterion("deliveryId between", value1, value2, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andDeliveryidNotBetween(Integer value1, Integer value2) {
            addCriterion("deliveryId not between", value1, value2, "deliveryid");
            return (Criteria) this;
        }

        public Criteria andQntypeIsNull() {
            addCriterion("qnType is null");
            return (Criteria) this;
        }

        public Criteria andQntypeIsNotNull() {
            addCriterion("qnType is not null");
            return (Criteria) this;
        }

        public Criteria andQntypeEqualTo(Byte value) {
            addCriterion("qnType =", value, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeNotEqualTo(Byte value) {
            addCriterion("qnType <>", value, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeGreaterThan(Byte value) {
            addCriterion("qnType >", value, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("qnType >=", value, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeLessThan(Byte value) {
            addCriterion("qnType <", value, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeLessThanOrEqualTo(Byte value) {
            addCriterion("qnType <=", value, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeIn(List<Byte> values) {
            addCriterion("qnType in", values, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeNotIn(List<Byte> values) {
            addCriterion("qnType not in", values, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeBetween(Byte value1, Byte value2) {
            addCriterion("qnType between", value1, value2, "qntype");
            return (Criteria) this;
        }

        public Criteria andQntypeNotBetween(Byte value1, Byte value2) {
            addCriterion("qnType not between", value1, value2, "qntype");
            return (Criteria) this;
        }

        public Criteria andQnidIsNull() {
            addCriterion("qnId is null");
            return (Criteria) this;
        }

        public Criteria andQnidIsNotNull() {
            addCriterion("qnId is not null");
            return (Criteria) this;
        }

        public Criteria andQnidEqualTo(Integer value) {
            addCriterion("qnId =", value, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidNotEqualTo(Integer value) {
            addCriterion("qnId <>", value, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidGreaterThan(Integer value) {
            addCriterion("qnId >", value, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidGreaterThanOrEqualTo(Integer value) {
            addCriterion("qnId >=", value, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidLessThan(Integer value) {
            addCriterion("qnId <", value, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidLessThanOrEqualTo(Integer value) {
            addCriterion("qnId <=", value, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidIn(List<Integer> values) {
            addCriterion("qnId in", values, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidNotIn(List<Integer> values) {
            addCriterion("qnId not in", values, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidBetween(Integer value1, Integer value2) {
            addCriterion("qnId between", value1, value2, "qnid");
            return (Criteria) this;
        }

        public Criteria andQnidNotBetween(Integer value1, Integer value2) {
            addCriterion("qnId not between", value1, value2, "qnid");
            return (Criteria) this;
        }

        public Criteria andPagestatusIsNull() {
            addCriterion("pageStatus is null");
            return (Criteria) this;
        }

        public Criteria andPagestatusIsNotNull() {
            addCriterion("pageStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPagestatusEqualTo(Byte value) {
            addCriterion("pageStatus =", value, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusNotEqualTo(Byte value) {
            addCriterion("pageStatus <>", value, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusGreaterThan(Byte value) {
            addCriterion("pageStatus >", value, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("pageStatus >=", value, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusLessThan(Byte value) {
            addCriterion("pageStatus <", value, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusLessThanOrEqualTo(Byte value) {
            addCriterion("pageStatus <=", value, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusIn(List<Byte> values) {
            addCriterion("pageStatus in", values, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusNotIn(List<Byte> values) {
            addCriterion("pageStatus not in", values, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusBetween(Byte value1, Byte value2) {
            addCriterion("pageStatus between", value1, value2, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andPagestatusNotBetween(Byte value1, Byte value2) {
            addCriterion("pageStatus not between", value1, value2, "pagestatus");
            return (Criteria) this;
        }

        public Criteria andIstopIsNull() {
            addCriterion("isTop is null");
            return (Criteria) this;
        }

        public Criteria andIstopIsNotNull() {
            addCriterion("isTop is not null");
            return (Criteria) this;
        }

        public Criteria andIstopEqualTo(Byte value) {
            addCriterion("isTop =", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotEqualTo(Byte value) {
            addCriterion("isTop <>", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopGreaterThan(Byte value) {
            addCriterion("isTop >", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopGreaterThanOrEqualTo(Byte value) {
            addCriterion("isTop >=", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopLessThan(Byte value) {
            addCriterion("isTop <", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopLessThanOrEqualTo(Byte value) {
            addCriterion("isTop <=", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopIn(List<Byte> values) {
            addCriterion("isTop in", values, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotIn(List<Byte> values) {
            addCriterion("isTop not in", values, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopBetween(Byte value1, Byte value2) {
            addCriterion("isTop between", value1, value2, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotBetween(Byte value1, Byte value2) {
            addCriterion("isTop not between", value1, value2, "istop");
            return (Criteria) this;
        }

        public Criteria andShoworderIsNull() {
            addCriterion("showOrder is null");
            return (Criteria) this;
        }

        public Criteria andShoworderIsNotNull() {
            addCriterion("showOrder is not null");
            return (Criteria) this;
        }

        public Criteria andShoworderEqualTo(Byte value) {
            addCriterion("showOrder =", value, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderNotEqualTo(Byte value) {
            addCriterion("showOrder <>", value, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderGreaterThan(Byte value) {
            addCriterion("showOrder >", value, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderGreaterThanOrEqualTo(Byte value) {
            addCriterion("showOrder >=", value, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderLessThan(Byte value) {
            addCriterion("showOrder <", value, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderLessThanOrEqualTo(Byte value) {
            addCriterion("showOrder <=", value, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderIn(List<Byte> values) {
            addCriterion("showOrder in", values, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderNotIn(List<Byte> values) {
            addCriterion("showOrder not in", values, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderBetween(Byte value1, Byte value2) {
            addCriterion("showOrder between", value1, value2, "showorder");
            return (Criteria) this;
        }

        public Criteria andShoworderNotBetween(Byte value1, Byte value2) {
            addCriterion("showOrder not between", value1, value2, "showorder");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andUtimeIsNull() {
            addCriterion("uTime is null");
            return (Criteria) this;
        }

        public Criteria andUtimeIsNotNull() {
            addCriterion("uTime is not null");
            return (Criteria) this;
        }

        public Criteria andUtimeEqualTo(Date value) {
            addCriterion("uTime =", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeNotEqualTo(Date value) {
            addCriterion("uTime <>", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeGreaterThan(Date value) {
            addCriterion("uTime >", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uTime >=", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeLessThan(Date value) {
            addCriterion("uTime <", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeLessThanOrEqualTo(Date value) {
            addCriterion("uTime <=", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeIn(List<Date> values) {
            addCriterion("uTime in", values, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeNotIn(List<Date> values) {
            addCriterion("uTime not in", values, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeBetween(Date value1, Date value2) {
            addCriterion("uTime between", value1, value2, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeNotBetween(Date value1, Date value2) {
            addCriterion("uTime not between", value1, value2, "utime");
            return (Criteria) this;
        }

        public Criteria andPtimeIsNull() {
            addCriterion("pTime is null");
            return (Criteria) this;
        }

        public Criteria andPtimeIsNotNull() {
            addCriterion("pTime is not null");
            return (Criteria) this;
        }

        public Criteria andPtimeEqualTo(Date value) {
            addCriterion("pTime =", value, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeNotEqualTo(Date value) {
            addCriterion("pTime <>", value, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeGreaterThan(Date value) {
            addCriterion("pTime >", value, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pTime >=", value, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeLessThan(Date value) {
            addCriterion("pTime <", value, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeLessThanOrEqualTo(Date value) {
            addCriterion("pTime <=", value, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeIn(List<Date> values) {
            addCriterion("pTime in", values, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeNotIn(List<Date> values) {
            addCriterion("pTime not in", values, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeBetween(Date value1, Date value2) {
            addCriterion("pTime between", value1, value2, "ptime");
            return (Criteria) this;
        }

        public Criteria andPtimeNotBetween(Date value1, Date value2) {
            addCriterion("pTime not between", value1, value2, "ptime");
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