package com.newIns.model.vote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NiVoteOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiVoteOrderExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Integer value) {
            addCriterion("orderId =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Integer value) {
            addCriterion("orderId <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Integer value) {
            addCriterion("orderId >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderId >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Integer value) {
            addCriterion("orderId <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Integer value) {
            addCriterion("orderId <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Integer> values) {
            addCriterion("orderId in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Integer> values) {
            addCriterion("orderId not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Integer value1, Integer value2) {
            addCriterion("orderId between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
            addCriterion("orderId not between", value1, value2, "orderid");
            return (Criteria) this;
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

        public Criteria andVqnidIsNull() {
            addCriterion("vqnId is null");
            return (Criteria) this;
        }

        public Criteria andVqnidIsNotNull() {
            addCriterion("vqnId is not null");
            return (Criteria) this;
        }

        public Criteria andVqnidEqualTo(Integer value) {
            addCriterion("vqnId =", value, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidNotEqualTo(Integer value) {
            addCriterion("vqnId <>", value, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidGreaterThan(Integer value) {
            addCriterion("vqnId >", value, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vqnId >=", value, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidLessThan(Integer value) {
            addCriterion("vqnId <", value, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidLessThanOrEqualTo(Integer value) {
            addCriterion("vqnId <=", value, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidIn(List<Integer> values) {
            addCriterion("vqnId in", values, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidNotIn(List<Integer> values) {
            addCriterion("vqnId not in", values, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidBetween(Integer value1, Integer value2) {
            addCriterion("vqnId between", value1, value2, "vqnid");
            return (Criteria) this;
        }

        public Criteria andVqnidNotBetween(Integer value1, Integer value2) {
            addCriterion("vqnId not between", value1, value2, "vqnid");
            return (Criteria) this;
        }

        public Criteria andSequencenumIsNull() {
            addCriterion("sequenceNum is null");
            return (Criteria) this;
        }

        public Criteria andSequencenumIsNotNull() {
            addCriterion("sequenceNum is not null");
            return (Criteria) this;
        }

        public Criteria andSequencenumEqualTo(Integer value) {
            addCriterion("sequenceNum =", value, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumNotEqualTo(Integer value) {
            addCriterion("sequenceNum <>", value, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumGreaterThan(Integer value) {
            addCriterion("sequenceNum >", value, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sequenceNum >=", value, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumLessThan(Integer value) {
            addCriterion("sequenceNum <", value, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumLessThanOrEqualTo(Integer value) {
            addCriterion("sequenceNum <=", value, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumIn(List<Integer> values) {
            addCriterion("sequenceNum in", values, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumNotIn(List<Integer> values) {
            addCriterion("sequenceNum not in", values, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumBetween(Integer value1, Integer value2) {
            addCriterion("sequenceNum between", value1, value2, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andSequencenumNotBetween(Integer value1, Integer value2) {
            addCriterion("sequenceNum not between", value1, value2, "sequencenum");
            return (Criteria) this;
        }

        public Criteria andOrderctimeIsNull() {
            addCriterion("orderCTime is null");
            return (Criteria) this;
        }

        public Criteria andOrderctimeIsNotNull() {
            addCriterion("orderCTime is not null");
            return (Criteria) this;
        }

        public Criteria andOrderctimeEqualTo(Date value) {
            addCriterion("orderCTime =", value, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeNotEqualTo(Date value) {
            addCriterion("orderCTime <>", value, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeGreaterThan(Date value) {
            addCriterion("orderCTime >", value, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeGreaterThanOrEqualTo(Date value) {
            addCriterion("orderCTime >=", value, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeLessThan(Date value) {
            addCriterion("orderCTime <", value, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeLessThanOrEqualTo(Date value) {
            addCriterion("orderCTime <=", value, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeIn(List<Date> values) {
            addCriterion("orderCTime in", values, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeNotIn(List<Date> values) {
            addCriterion("orderCTime not in", values, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeBetween(Date value1, Date value2) {
            addCriterion("orderCTime between", value1, value2, "orderctime");
            return (Criteria) this;
        }

        public Criteria andOrderctimeNotBetween(Date value1, Date value2) {
            addCriterion("orderCTime not between", value1, value2, "orderctime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeIsNull() {
            addCriterion("answerBTime is null");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeIsNotNull() {
            addCriterion("answerBTime is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeEqualTo(Date value) {
            addCriterion("answerBTime =", value, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeNotEqualTo(Date value) {
            addCriterion("answerBTime <>", value, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeGreaterThan(Date value) {
            addCriterion("answerBTime >", value, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("answerBTime >=", value, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeLessThan(Date value) {
            addCriterion("answerBTime <", value, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeLessThanOrEqualTo(Date value) {
            addCriterion("answerBTime <=", value, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeIn(List<Date> values) {
            addCriterion("answerBTime in", values, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeNotIn(List<Date> values) {
            addCriterion("answerBTime not in", values, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeBetween(Date value1, Date value2) {
            addCriterion("answerBTime between", value1, value2, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnswerbtimeNotBetween(Date value1, Date value2) {
            addCriterion("answerBTime not between", value1, value2, "answerbtime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeIsNull() {
            addCriterion("answerETime is null");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeIsNotNull() {
            addCriterion("answerETime is not null");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeEqualTo(Date value) {
            addCriterion("answerETime =", value, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeNotEqualTo(Date value) {
            addCriterion("answerETime <>", value, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeGreaterThan(Date value) {
            addCriterion("answerETime >", value, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeGreaterThanOrEqualTo(Date value) {
            addCriterion("answerETime >=", value, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeLessThan(Date value) {
            addCriterion("answerETime <", value, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeLessThanOrEqualTo(Date value) {
            addCriterion("answerETime <=", value, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeIn(List<Date> values) {
            addCriterion("answerETime in", values, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeNotIn(List<Date> values) {
            addCriterion("answerETime not in", values, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeBetween(Date value1, Date value2) {
            addCriterion("answerETime between", value1, value2, "answeretime");
            return (Criteria) this;
        }

        public Criteria andAnsweretimeNotBetween(Date value1, Date value2) {
            addCriterion("answerETime not between", value1, value2, "answeretime");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNull() {
            addCriterion("orderStatus is null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNotNull() {
            addCriterion("orderStatus is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusEqualTo(Byte value) {
            addCriterion("orderStatus =", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotEqualTo(Byte value) {
            addCriterion("orderStatus <>", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThan(Byte value) {
            addCriterion("orderStatus >", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("orderStatus >=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThan(Byte value) {
            addCriterion("orderStatus <", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThanOrEqualTo(Byte value) {
            addCriterion("orderStatus <=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIn(List<Byte> values) {
            addCriterion("orderStatus in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotIn(List<Byte> values) {
            addCriterion("orderStatus not in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusBetween(Byte value1, Byte value2) {
            addCriterion("orderStatus between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotBetween(Byte value1, Byte value2) {
            addCriterion("orderStatus not between", value1, value2, "orderstatus");
            return (Criteria) this;
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

        public Criteria andReviewerIsNull() {
            addCriterion("reviewer is null");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNotNull() {
            addCriterion("reviewer is not null");
            return (Criteria) this;
        }

        public Criteria andReviewerEqualTo(String value) {
            addCriterion("reviewer =", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotEqualTo(String value) {
            addCriterion("reviewer <>", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThan(String value) {
            addCriterion("reviewer >", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThanOrEqualTo(String value) {
            addCriterion("reviewer >=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThan(String value) {
            addCriterion("reviewer <", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThanOrEqualTo(String value) {
            addCriterion("reviewer <=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLike(String value) {
            addCriterion("reviewer like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotLike(String value) {
            addCriterion("reviewer not like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerIn(List<String> values) {
            addCriterion("reviewer in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotIn(List<String> values) {
            addCriterion("reviewer not in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerBetween(String value1, String value2) {
            addCriterion("reviewer between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotBetween(String value1, String value2) {
            addCriterion("reviewer not between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusIsNull() {
            addCriterion("awardGetStatus is null");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusIsNotNull() {
            addCriterion("awardGetStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusEqualTo(Byte value) {
            addCriterion("awardGetStatus =", value, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusNotEqualTo(Byte value) {
            addCriterion("awardGetStatus <>", value, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusGreaterThan(Byte value) {
            addCriterion("awardGetStatus >", value, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("awardGetStatus >=", value, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusLessThan(Byte value) {
            addCriterion("awardGetStatus <", value, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusLessThanOrEqualTo(Byte value) {
            addCriterion("awardGetStatus <=", value, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusIn(List<Byte> values) {
            addCriterion("awardGetStatus in", values, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusNotIn(List<Byte> values) {
            addCriterion("awardGetStatus not in", values, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusBetween(Byte value1, Byte value2) {
            addCriterion("awardGetStatus between", value1, value2, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardgetstatusNotBetween(Byte value1, Byte value2) {
            addCriterion("awardGetStatus not between", value1, value2, "awardgetstatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusIsNull() {
            addCriterion("awardPayStatus is null");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusIsNotNull() {
            addCriterion("awardPayStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusEqualTo(Byte value) {
            addCriterion("awardPayStatus =", value, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusNotEqualTo(Byte value) {
            addCriterion("awardPayStatus <>", value, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusGreaterThan(Byte value) {
            addCriterion("awardPayStatus >", value, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("awardPayStatus >=", value, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusLessThan(Byte value) {
            addCriterion("awardPayStatus <", value, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusLessThanOrEqualTo(Byte value) {
            addCriterion("awardPayStatus <=", value, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusIn(List<Byte> values) {
            addCriterion("awardPayStatus in", values, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusNotIn(List<Byte> values) {
            addCriterion("awardPayStatus not in", values, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusBetween(Byte value1, Byte value2) {
            addCriterion("awardPayStatus between", value1, value2, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardpaystatusNotBetween(Byte value1, Byte value2) {
            addCriterion("awardPayStatus not between", value1, value2, "awardpaystatus");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeIsNull() {
            addCriterion("awardGetTime is null");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeIsNotNull() {
            addCriterion("awardGetTime is not null");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeEqualTo(Date value) {
            addCriterion("awardGetTime =", value, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeNotEqualTo(Date value) {
            addCriterion("awardGetTime <>", value, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeGreaterThan(Date value) {
            addCriterion("awardGetTime >", value, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeGreaterThanOrEqualTo(Date value) {
            addCriterion("awardGetTime >=", value, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeLessThan(Date value) {
            addCriterion("awardGetTime <", value, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeLessThanOrEqualTo(Date value) {
            addCriterion("awardGetTime <=", value, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeIn(List<Date> values) {
            addCriterion("awardGetTime in", values, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeNotIn(List<Date> values) {
            addCriterion("awardGetTime not in", values, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeBetween(Date value1, Date value2) {
            addCriterion("awardGetTime between", value1, value2, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardgettimeNotBetween(Date value1, Date value2) {
            addCriterion("awardGetTime not between", value1, value2, "awardgettime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeIsNull() {
            addCriterion("awardPayTime is null");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeIsNotNull() {
            addCriterion("awardPayTime is not null");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeEqualTo(Date value) {
            addCriterion("awardPayTime =", value, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeNotEqualTo(Date value) {
            addCriterion("awardPayTime <>", value, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeGreaterThan(Date value) {
            addCriterion("awardPayTime >", value, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("awardPayTime >=", value, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeLessThan(Date value) {
            addCriterion("awardPayTime <", value, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeLessThanOrEqualTo(Date value) {
            addCriterion("awardPayTime <=", value, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeIn(List<Date> values) {
            addCriterion("awardPayTime in", values, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeNotIn(List<Date> values) {
            addCriterion("awardPayTime not in", values, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeBetween(Date value1, Date value2) {
            addCriterion("awardPayTime between", value1, value2, "awardpaytime");
            return (Criteria) this;
        }

        public Criteria andAwardpaytimeNotBetween(Date value1, Date value2) {
            addCriterion("awardPayTime not between", value1, value2, "awardpaytime");
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