package com.newIns.model;

import java.util.ArrayList;
import java.util.List;

public class NiSqnclassDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiSqnclassDictExample() {
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

        public Criteria andSqnclassidIsNull() {
            addCriterion("sqnClassId is null");
            return (Criteria) this;
        }

        public Criteria andSqnclassidIsNotNull() {
            addCriterion("sqnClassId is not null");
            return (Criteria) this;
        }

        public Criteria andSqnclassidEqualTo(Integer value) {
            addCriterion("sqnClassId =", value, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidNotEqualTo(Integer value) {
            addCriterion("sqnClassId <>", value, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidGreaterThan(Integer value) {
            addCriterion("sqnClassId >", value, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sqnClassId >=", value, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidLessThan(Integer value) {
            addCriterion("sqnClassId <", value, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidLessThanOrEqualTo(Integer value) {
            addCriterion("sqnClassId <=", value, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidIn(List<Integer> values) {
            addCriterion("sqnClassId in", values, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidNotIn(List<Integer> values) {
            addCriterion("sqnClassId not in", values, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidBetween(Integer value1, Integer value2) {
            addCriterion("sqnClassId between", value1, value2, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassidNotBetween(Integer value1, Integer value2) {
            addCriterion("sqnClassId not between", value1, value2, "sqnclassid");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameIsNull() {
            addCriterion("sqnClassName is null");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameIsNotNull() {
            addCriterion("sqnClassName is not null");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameEqualTo(String value) {
            addCriterion("sqnClassName =", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameNotEqualTo(String value) {
            addCriterion("sqnClassName <>", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameGreaterThan(String value) {
            addCriterion("sqnClassName >", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameGreaterThanOrEqualTo(String value) {
            addCriterion("sqnClassName >=", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameLessThan(String value) {
            addCriterion("sqnClassName <", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameLessThanOrEqualTo(String value) {
            addCriterion("sqnClassName <=", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameLike(String value) {
            addCriterion("sqnClassName like", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameNotLike(String value) {
            addCriterion("sqnClassName not like", value, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameIn(List<String> values) {
            addCriterion("sqnClassName in", values, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameNotIn(List<String> values) {
            addCriterion("sqnClassName not in", values, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameBetween(String value1, String value2) {
            addCriterion("sqnClassName between", value1, value2, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassnameNotBetween(String value1, String value2) {
            addCriterion("sqnClassName not between", value1, value2, "sqnclassname");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesIsNull() {
            addCriterion("sqnClassDes is null");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesIsNotNull() {
            addCriterion("sqnClassDes is not null");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesEqualTo(String value) {
            addCriterion("sqnClassDes =", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesNotEqualTo(String value) {
            addCriterion("sqnClassDes <>", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesGreaterThan(String value) {
            addCriterion("sqnClassDes >", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesGreaterThanOrEqualTo(String value) {
            addCriterion("sqnClassDes >=", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesLessThan(String value) {
            addCriterion("sqnClassDes <", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesLessThanOrEqualTo(String value) {
            addCriterion("sqnClassDes <=", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesLike(String value) {
            addCriterion("sqnClassDes like", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesNotLike(String value) {
            addCriterion("sqnClassDes not like", value, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesIn(List<String> values) {
            addCriterion("sqnClassDes in", values, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesNotIn(List<String> values) {
            addCriterion("sqnClassDes not in", values, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesBetween(String value1, String value2) {
            addCriterion("sqnClassDes between", value1, value2, "sqnclassdes");
            return (Criteria) this;
        }

        public Criteria andSqnclassdesNotBetween(String value1, String value2) {
            addCriterion("sqnClassDes not between", value1, value2, "sqnclassdes");
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