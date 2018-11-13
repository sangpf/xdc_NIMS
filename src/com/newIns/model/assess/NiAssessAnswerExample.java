package com.newIns.model.assess;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NiAssessAnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiAssessAnswerExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andAqidIsNull() {
            addCriterion("aqId is null");
            return (Criteria) this;
        }

        public Criteria andAqidIsNotNull() {
            addCriterion("aqId is not null");
            return (Criteria) this;
        }

        public Criteria andAqidEqualTo(Integer value) {
            addCriterion("aqId =", value, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidNotEqualTo(Integer value) {
            addCriterion("aqId <>", value, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidGreaterThan(Integer value) {
            addCriterion("aqId >", value, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidGreaterThanOrEqualTo(Integer value) {
            addCriterion("aqId >=", value, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidLessThan(Integer value) {
            addCriterion("aqId <", value, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidLessThanOrEqualTo(Integer value) {
            addCriterion("aqId <=", value, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidIn(List<Integer> values) {
            addCriterion("aqId in", values, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidNotIn(List<Integer> values) {
            addCriterion("aqId not in", values, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidBetween(Integer value1, Integer value2) {
            addCriterion("aqId between", value1, value2, "aqid");
            return (Criteria) this;
        }

        public Criteria andAqidNotBetween(Integer value1, Integer value2) {
            addCriterion("aqId not between", value1, value2, "aqid");
            return (Criteria) this;
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

        public Criteria andChoiceIsNull() {
            addCriterion("choice is null");
            return (Criteria) this;
        }

        public Criteria andChoiceIsNotNull() {
            addCriterion("choice is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceEqualTo(String value) {
            addCriterion("choice =", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotEqualTo(String value) {
            addCriterion("choice <>", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceGreaterThan(String value) {
            addCriterion("choice >", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceGreaterThanOrEqualTo(String value) {
            addCriterion("choice >=", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceLessThan(String value) {
            addCriterion("choice <", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceLessThanOrEqualTo(String value) {
            addCriterion("choice <=", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceLike(String value) {
            addCriterion("choice like", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotLike(String value) {
            addCriterion("choice not like", value, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceIn(List<String> values) {
            addCriterion("choice in", values, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotIn(List<String> values) {
            addCriterion("choice not in", values, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceBetween(String value1, String value2) {
            addCriterion("choice between", value1, value2, "choice");
            return (Criteria) this;
        }

        public Criteria andChoiceNotBetween(String value1, String value2) {
            addCriterion("choice not between", value1, value2, "choice");
            return (Criteria) this;
        }

        public Criteria andSelfdefineIsNull() {
            addCriterion("selfDefine is null");
            return (Criteria) this;
        }

        public Criteria andSelfdefineIsNotNull() {
            addCriterion("selfDefine is not null");
            return (Criteria) this;
        }

        public Criteria andSelfdefineEqualTo(String value) {
            addCriterion("selfDefine =", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineNotEqualTo(String value) {
            addCriterion("selfDefine <>", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineGreaterThan(String value) {
            addCriterion("selfDefine >", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineGreaterThanOrEqualTo(String value) {
            addCriterion("selfDefine >=", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineLessThan(String value) {
            addCriterion("selfDefine <", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineLessThanOrEqualTo(String value) {
            addCriterion("selfDefine <=", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineLike(String value) {
            addCriterion("selfDefine like", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineNotLike(String value) {
            addCriterion("selfDefine not like", value, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineIn(List<String> values) {
            addCriterion("selfDefine in", values, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineNotIn(List<String> values) {
            addCriterion("selfDefine not in", values, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineBetween(String value1, String value2) {
            addCriterion("selfDefine between", value1, value2, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andSelfdefineNotBetween(String value1, String value2) {
            addCriterion("selfDefine not between", value1, value2, "selfdefine");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Date value) {
            addCriterionForJDBCTime("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Date value) {
            addCriterionForJDBCTime("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Date value) {
            addCriterionForJDBCTime("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Date value) {
            addCriterionForJDBCTime("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Date> values) {
            addCriterionForJDBCTime("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Date> values) {
            addCriterionForJDBCTime("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("duration not between", value1, value2, "duration");
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