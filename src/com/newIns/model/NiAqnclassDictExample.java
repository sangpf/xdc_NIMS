package com.newIns.model;

import java.util.ArrayList;
import java.util.List;

public class NiAqnclassDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiAqnclassDictExample() {
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

        public Criteria andAqnclassidIsNull() {
            addCriterion("aqnClassId is null");
            return (Criteria) this;
        }

        public Criteria andAqnclassidIsNotNull() {
            addCriterion("aqnClassId is not null");
            return (Criteria) this;
        }

        public Criteria andAqnclassidEqualTo(Integer value) {
            addCriterion("aqnClassId =", value, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidNotEqualTo(Integer value) {
            addCriterion("aqnClassId <>", value, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidGreaterThan(Integer value) {
            addCriterion("aqnClassId >", value, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("aqnClassId >=", value, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidLessThan(Integer value) {
            addCriterion("aqnClassId <", value, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidLessThanOrEqualTo(Integer value) {
            addCriterion("aqnClassId <=", value, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidIn(List<Integer> values) {
            addCriterion("aqnClassId in", values, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidNotIn(List<Integer> values) {
            addCriterion("aqnClassId not in", values, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidBetween(Integer value1, Integer value2) {
            addCriterion("aqnClassId between", value1, value2, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassidNotBetween(Integer value1, Integer value2) {
            addCriterion("aqnClassId not between", value1, value2, "aqnclassid");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameIsNull() {
            addCriterion("aqnClassName is null");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameIsNotNull() {
            addCriterion("aqnClassName is not null");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameEqualTo(String value) {
            addCriterion("aqnClassName =", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameNotEqualTo(String value) {
            addCriterion("aqnClassName <>", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameGreaterThan(String value) {
            addCriterion("aqnClassName >", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameGreaterThanOrEqualTo(String value) {
            addCriterion("aqnClassName >=", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameLessThan(String value) {
            addCriterion("aqnClassName <", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameLessThanOrEqualTo(String value) {
            addCriterion("aqnClassName <=", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameLike(String value) {
            addCriterion("aqnClassName like", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameNotLike(String value) {
            addCriterion("aqnClassName not like", value, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameIn(List<String> values) {
            addCriterion("aqnClassName in", values, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameNotIn(List<String> values) {
            addCriterion("aqnClassName not in", values, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameBetween(String value1, String value2) {
            addCriterion("aqnClassName between", value1, value2, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassnameNotBetween(String value1, String value2) {
            addCriterion("aqnClassName not between", value1, value2, "aqnclassname");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesIsNull() {
            addCriterion("aqnClassDes is null");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesIsNotNull() {
            addCriterion("aqnClassDes is not null");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesEqualTo(String value) {
            addCriterion("aqnClassDes =", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesNotEqualTo(String value) {
            addCriterion("aqnClassDes <>", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesGreaterThan(String value) {
            addCriterion("aqnClassDes >", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesGreaterThanOrEqualTo(String value) {
            addCriterion("aqnClassDes >=", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesLessThan(String value) {
            addCriterion("aqnClassDes <", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesLessThanOrEqualTo(String value) {
            addCriterion("aqnClassDes <=", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesLike(String value) {
            addCriterion("aqnClassDes like", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesNotLike(String value) {
            addCriterion("aqnClassDes not like", value, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesIn(List<String> values) {
            addCriterion("aqnClassDes in", values, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesNotIn(List<String> values) {
            addCriterion("aqnClassDes not in", values, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesBetween(String value1, String value2) {
            addCriterion("aqnClassDes between", value1, value2, "aqnclassdes");
            return (Criteria) this;
        }

        public Criteria andAqnclassdesNotBetween(String value1, String value2) {
            addCriterion("aqnClassDes not between", value1, value2, "aqnclassdes");
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