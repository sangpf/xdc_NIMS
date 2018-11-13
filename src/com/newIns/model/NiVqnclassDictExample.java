package com.newIns.model;

import java.util.ArrayList;
import java.util.List;

public class NiVqnclassDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiVqnclassDictExample() {
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

        public Criteria andVqnclassidIsNull() {
            addCriterion("vqnClassId is null");
            return (Criteria) this;
        }

        public Criteria andVqnclassidIsNotNull() {
            addCriterion("vqnClassId is not null");
            return (Criteria) this;
        }

        public Criteria andVqnclassidEqualTo(Integer value) {
            addCriterion("vqnClassId =", value, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidNotEqualTo(Integer value) {
            addCriterion("vqnClassId <>", value, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidGreaterThan(Integer value) {
            addCriterion("vqnClassId >", value, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vqnClassId >=", value, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidLessThan(Integer value) {
            addCriterion("vqnClassId <", value, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidLessThanOrEqualTo(Integer value) {
            addCriterion("vqnClassId <=", value, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidIn(List<Integer> values) {
            addCriterion("vqnClassId in", values, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidNotIn(List<Integer> values) {
            addCriterion("vqnClassId not in", values, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidBetween(Integer value1, Integer value2) {
            addCriterion("vqnClassId between", value1, value2, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassidNotBetween(Integer value1, Integer value2) {
            addCriterion("vqnClassId not between", value1, value2, "vqnclassid");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameIsNull() {
            addCriterion("vqnClassName is null");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameIsNotNull() {
            addCriterion("vqnClassName is not null");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameEqualTo(String value) {
            addCriterion("vqnClassName =", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameNotEqualTo(String value) {
            addCriterion("vqnClassName <>", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameGreaterThan(String value) {
            addCriterion("vqnClassName >", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameGreaterThanOrEqualTo(String value) {
            addCriterion("vqnClassName >=", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameLessThan(String value) {
            addCriterion("vqnClassName <", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameLessThanOrEqualTo(String value) {
            addCriterion("vqnClassName <=", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameLike(String value) {
            addCriterion("vqnClassName like", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameNotLike(String value) {
            addCriterion("vqnClassName not like", value, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameIn(List<String> values) {
            addCriterion("vqnClassName in", values, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameNotIn(List<String> values) {
            addCriterion("vqnClassName not in", values, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameBetween(String value1, String value2) {
            addCriterion("vqnClassName between", value1, value2, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassnameNotBetween(String value1, String value2) {
            addCriterion("vqnClassName not between", value1, value2, "vqnclassname");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesIsNull() {
            addCriterion("vqnClassDes is null");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesIsNotNull() {
            addCriterion("vqnClassDes is not null");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesEqualTo(String value) {
            addCriterion("vqnClassDes =", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesNotEqualTo(String value) {
            addCriterion("vqnClassDes <>", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesGreaterThan(String value) {
            addCriterion("vqnClassDes >", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesGreaterThanOrEqualTo(String value) {
            addCriterion("vqnClassDes >=", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesLessThan(String value) {
            addCriterion("vqnClassDes <", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesLessThanOrEqualTo(String value) {
            addCriterion("vqnClassDes <=", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesLike(String value) {
            addCriterion("vqnClassDes like", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesNotLike(String value) {
            addCriterion("vqnClassDes not like", value, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesIn(List<String> values) {
            addCriterion("vqnClassDes in", values, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesNotIn(List<String> values) {
            addCriterion("vqnClassDes not in", values, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesBetween(String value1, String value2) {
            addCriterion("vqnClassDes between", value1, value2, "vqnclassdes");
            return (Criteria) this;
        }

        public Criteria andVqnclassdesNotBetween(String value1, String value2) {
            addCriterion("vqnClassDes not between", value1, value2, "vqnclassdes");
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