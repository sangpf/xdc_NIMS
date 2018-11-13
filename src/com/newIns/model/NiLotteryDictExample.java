package com.newIns.model;

import java.util.ArrayList;
import java.util.List;

public class NiLotteryDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiLotteryDictExample() {
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

        public Criteria andLotteryidIsNull() {
            addCriterion("lotteryId is null");
            return (Criteria) this;
        }

        public Criteria andLotteryidIsNotNull() {
            addCriterion("lotteryId is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryidEqualTo(Integer value) {
            addCriterion("lotteryId =", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidNotEqualTo(Integer value) {
            addCriterion("lotteryId <>", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidGreaterThan(Integer value) {
            addCriterion("lotteryId >", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("lotteryId >=", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidLessThan(Integer value) {
            addCriterion("lotteryId <", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidLessThanOrEqualTo(Integer value) {
            addCriterion("lotteryId <=", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidIn(List<Integer> values) {
            addCriterion("lotteryId in", values, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidNotIn(List<Integer> values) {
            addCriterion("lotteryId not in", values, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidBetween(Integer value1, Integer value2) {
            addCriterion("lotteryId between", value1, value2, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidNotBetween(Integer value1, Integer value2) {
            addCriterion("lotteryId not between", value1, value2, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotterynameIsNull() {
            addCriterion("lotteryName is null");
            return (Criteria) this;
        }

        public Criteria andLotterynameIsNotNull() {
            addCriterion("lotteryName is not null");
            return (Criteria) this;
        }

        public Criteria andLotterynameEqualTo(String value) {
            addCriterion("lotteryName =", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameNotEqualTo(String value) {
            addCriterion("lotteryName <>", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameGreaterThan(String value) {
            addCriterion("lotteryName >", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameGreaterThanOrEqualTo(String value) {
            addCriterion("lotteryName >=", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameLessThan(String value) {
            addCriterion("lotteryName <", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameLessThanOrEqualTo(String value) {
            addCriterion("lotteryName <=", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameLike(String value) {
            addCriterion("lotteryName like", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameNotLike(String value) {
            addCriterion("lotteryName not like", value, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameIn(List<String> values) {
            addCriterion("lotteryName in", values, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameNotIn(List<String> values) {
            addCriterion("lotteryName not in", values, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameBetween(String value1, String value2) {
            addCriterion("lotteryName between", value1, value2, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andLotterynameNotBetween(String value1, String value2) {
            addCriterion("lotteryName not between", value1, value2, "lotteryname");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRanknumIsNull() {
            addCriterion("rankNum is null");
            return (Criteria) this;
        }

        public Criteria andRanknumIsNotNull() {
            addCriterion("rankNum is not null");
            return (Criteria) this;
        }

        public Criteria andRanknumEqualTo(Byte value) {
            addCriterion("rankNum =", value, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumNotEqualTo(Byte value) {
            addCriterion("rankNum <>", value, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumGreaterThan(Byte value) {
            addCriterion("rankNum >", value, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumGreaterThanOrEqualTo(Byte value) {
            addCriterion("rankNum >=", value, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumLessThan(Byte value) {
            addCriterion("rankNum <", value, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumLessThanOrEqualTo(Byte value) {
            addCriterion("rankNum <=", value, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumIn(List<Byte> values) {
            addCriterion("rankNum in", values, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumNotIn(List<Byte> values) {
            addCriterion("rankNum not in", values, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumBetween(Byte value1, Byte value2) {
            addCriterion("rankNum between", value1, value2, "ranknum");
            return (Criteria) this;
        }

        public Criteria andRanknumNotBetween(Byte value1, Byte value2) {
            addCriterion("rankNum not between", value1, value2, "ranknum");
            return (Criteria) this;
        }

        public Criteria andAward1nameIsNull() {
            addCriterion("award1Name is null");
            return (Criteria) this;
        }

        public Criteria andAward1nameIsNotNull() {
            addCriterion("award1Name is not null");
            return (Criteria) this;
        }

        public Criteria andAward1nameEqualTo(String value) {
            addCriterion("award1Name =", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameNotEqualTo(String value) {
            addCriterion("award1Name <>", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameGreaterThan(String value) {
            addCriterion("award1Name >", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameGreaterThanOrEqualTo(String value) {
            addCriterion("award1Name >=", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameLessThan(String value) {
            addCriterion("award1Name <", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameLessThanOrEqualTo(String value) {
            addCriterion("award1Name <=", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameLike(String value) {
            addCriterion("award1Name like", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameNotLike(String value) {
            addCriterion("award1Name not like", value, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameIn(List<String> values) {
            addCriterion("award1Name in", values, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameNotIn(List<String> values) {
            addCriterion("award1Name not in", values, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameBetween(String value1, String value2) {
            addCriterion("award1Name between", value1, value2, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1nameNotBetween(String value1, String value2) {
            addCriterion("award1Name not between", value1, value2, "award1name");
            return (Criteria) this;
        }

        public Criteria andAward1idIsNull() {
            addCriterion("award1Id is null");
            return (Criteria) this;
        }

        public Criteria andAward1idIsNotNull() {
            addCriterion("award1Id is not null");
            return (Criteria) this;
        }

        public Criteria andAward1idEqualTo(Integer value) {
            addCriterion("award1Id =", value, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idNotEqualTo(Integer value) {
            addCriterion("award1Id <>", value, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idGreaterThan(Integer value) {
            addCriterion("award1Id >", value, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idGreaterThanOrEqualTo(Integer value) {
            addCriterion("award1Id >=", value, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idLessThan(Integer value) {
            addCriterion("award1Id <", value, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idLessThanOrEqualTo(Integer value) {
            addCriterion("award1Id <=", value, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idIn(List<Integer> values) {
            addCriterion("award1Id in", values, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idNotIn(List<Integer> values) {
            addCriterion("award1Id not in", values, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idBetween(Integer value1, Integer value2) {
            addCriterion("award1Id between", value1, value2, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1idNotBetween(Integer value1, Integer value2) {
            addCriterion("award1Id not between", value1, value2, "award1id");
            return (Criteria) this;
        }

        public Criteria andAward1rateIsNull() {
            addCriterion("award1Rate is null");
            return (Criteria) this;
        }

        public Criteria andAward1rateIsNotNull() {
            addCriterion("award1Rate is not null");
            return (Criteria) this;
        }

        public Criteria andAward1rateEqualTo(Float value) {
            addCriterion("award1Rate =", value, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateNotEqualTo(Float value) {
            addCriterion("award1Rate <>", value, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateGreaterThan(Float value) {
            addCriterion("award1Rate >", value, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateGreaterThanOrEqualTo(Float value) {
            addCriterion("award1Rate >=", value, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateLessThan(Float value) {
            addCriterion("award1Rate <", value, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateLessThanOrEqualTo(Float value) {
            addCriterion("award1Rate <=", value, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateIn(List<Float> values) {
            addCriterion("award1Rate in", values, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateNotIn(List<Float> values) {
            addCriterion("award1Rate not in", values, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateBetween(Float value1, Float value2) {
            addCriterion("award1Rate between", value1, value2, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1rateNotBetween(Float value1, Float value2) {
            addCriterion("award1Rate not between", value1, value2, "award1rate");
            return (Criteria) this;
        }

        public Criteria andAward1desIsNull() {
            addCriterion("award1Des is null");
            return (Criteria) this;
        }

        public Criteria andAward1desIsNotNull() {
            addCriterion("award1Des is not null");
            return (Criteria) this;
        }

        public Criteria andAward1desEqualTo(String value) {
            addCriterion("award1Des =", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desNotEqualTo(String value) {
            addCriterion("award1Des <>", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desGreaterThan(String value) {
            addCriterion("award1Des >", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desGreaterThanOrEqualTo(String value) {
            addCriterion("award1Des >=", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desLessThan(String value) {
            addCriterion("award1Des <", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desLessThanOrEqualTo(String value) {
            addCriterion("award1Des <=", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desLike(String value) {
            addCriterion("award1Des like", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desNotLike(String value) {
            addCriterion("award1Des not like", value, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desIn(List<String> values) {
            addCriterion("award1Des in", values, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desNotIn(List<String> values) {
            addCriterion("award1Des not in", values, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desBetween(String value1, String value2) {
            addCriterion("award1Des between", value1, value2, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward1desNotBetween(String value1, String value2) {
            addCriterion("award1Des not between", value1, value2, "award1des");
            return (Criteria) this;
        }

        public Criteria andAward2nameIsNull() {
            addCriterion("award2Name is null");
            return (Criteria) this;
        }

        public Criteria andAward2nameIsNotNull() {
            addCriterion("award2Name is not null");
            return (Criteria) this;
        }

        public Criteria andAward2nameEqualTo(String value) {
            addCriterion("award2Name =", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameNotEqualTo(String value) {
            addCriterion("award2Name <>", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameGreaterThan(String value) {
            addCriterion("award2Name >", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameGreaterThanOrEqualTo(String value) {
            addCriterion("award2Name >=", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameLessThan(String value) {
            addCriterion("award2Name <", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameLessThanOrEqualTo(String value) {
            addCriterion("award2Name <=", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameLike(String value) {
            addCriterion("award2Name like", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameNotLike(String value) {
            addCriterion("award2Name not like", value, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameIn(List<String> values) {
            addCriterion("award2Name in", values, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameNotIn(List<String> values) {
            addCriterion("award2Name not in", values, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameBetween(String value1, String value2) {
            addCriterion("award2Name between", value1, value2, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2nameNotBetween(String value1, String value2) {
            addCriterion("award2Name not between", value1, value2, "award2name");
            return (Criteria) this;
        }

        public Criteria andAward2idIsNull() {
            addCriterion("award2Id is null");
            return (Criteria) this;
        }

        public Criteria andAward2idIsNotNull() {
            addCriterion("award2Id is not null");
            return (Criteria) this;
        }

        public Criteria andAward2idEqualTo(Integer value) {
            addCriterion("award2Id =", value, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idNotEqualTo(Integer value) {
            addCriterion("award2Id <>", value, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idGreaterThan(Integer value) {
            addCriterion("award2Id >", value, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idGreaterThanOrEqualTo(Integer value) {
            addCriterion("award2Id >=", value, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idLessThan(Integer value) {
            addCriterion("award2Id <", value, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idLessThanOrEqualTo(Integer value) {
            addCriterion("award2Id <=", value, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idIn(List<Integer> values) {
            addCriterion("award2Id in", values, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idNotIn(List<Integer> values) {
            addCriterion("award2Id not in", values, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idBetween(Integer value1, Integer value2) {
            addCriterion("award2Id between", value1, value2, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2idNotBetween(Integer value1, Integer value2) {
            addCriterion("award2Id not between", value1, value2, "award2id");
            return (Criteria) this;
        }

        public Criteria andAward2rateIsNull() {
            addCriterion("award2Rate is null");
            return (Criteria) this;
        }

        public Criteria andAward2rateIsNotNull() {
            addCriterion("award2Rate is not null");
            return (Criteria) this;
        }

        public Criteria andAward2rateEqualTo(Float value) {
            addCriterion("award2Rate =", value, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateNotEqualTo(Float value) {
            addCriterion("award2Rate <>", value, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateGreaterThan(Float value) {
            addCriterion("award2Rate >", value, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateGreaterThanOrEqualTo(Float value) {
            addCriterion("award2Rate >=", value, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateLessThan(Float value) {
            addCriterion("award2Rate <", value, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateLessThanOrEqualTo(Float value) {
            addCriterion("award2Rate <=", value, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateIn(List<Float> values) {
            addCriterion("award2Rate in", values, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateNotIn(List<Float> values) {
            addCriterion("award2Rate not in", values, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateBetween(Float value1, Float value2) {
            addCriterion("award2Rate between", value1, value2, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2rateNotBetween(Float value1, Float value2) {
            addCriterion("award2Rate not between", value1, value2, "award2rate");
            return (Criteria) this;
        }

        public Criteria andAward2desIsNull() {
            addCriterion("award2Des is null");
            return (Criteria) this;
        }

        public Criteria andAward2desIsNotNull() {
            addCriterion("award2Des is not null");
            return (Criteria) this;
        }

        public Criteria andAward2desEqualTo(String value) {
            addCriterion("award2Des =", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desNotEqualTo(String value) {
            addCriterion("award2Des <>", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desGreaterThan(String value) {
            addCriterion("award2Des >", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desGreaterThanOrEqualTo(String value) {
            addCriterion("award2Des >=", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desLessThan(String value) {
            addCriterion("award2Des <", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desLessThanOrEqualTo(String value) {
            addCriterion("award2Des <=", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desLike(String value) {
            addCriterion("award2Des like", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desNotLike(String value) {
            addCriterion("award2Des not like", value, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desIn(List<String> values) {
            addCriterion("award2Des in", values, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desNotIn(List<String> values) {
            addCriterion("award2Des not in", values, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desBetween(String value1, String value2) {
            addCriterion("award2Des between", value1, value2, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward2desNotBetween(String value1, String value2) {
            addCriterion("award2Des not between", value1, value2, "award2des");
            return (Criteria) this;
        }

        public Criteria andAward3nameIsNull() {
            addCriterion("award3Name is null");
            return (Criteria) this;
        }

        public Criteria andAward3nameIsNotNull() {
            addCriterion("award3Name is not null");
            return (Criteria) this;
        }

        public Criteria andAward3nameEqualTo(String value) {
            addCriterion("award3Name =", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameNotEqualTo(String value) {
            addCriterion("award3Name <>", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameGreaterThan(String value) {
            addCriterion("award3Name >", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameGreaterThanOrEqualTo(String value) {
            addCriterion("award3Name >=", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameLessThan(String value) {
            addCriterion("award3Name <", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameLessThanOrEqualTo(String value) {
            addCriterion("award3Name <=", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameLike(String value) {
            addCriterion("award3Name like", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameNotLike(String value) {
            addCriterion("award3Name not like", value, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameIn(List<String> values) {
            addCriterion("award3Name in", values, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameNotIn(List<String> values) {
            addCriterion("award3Name not in", values, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameBetween(String value1, String value2) {
            addCriterion("award3Name between", value1, value2, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3nameNotBetween(String value1, String value2) {
            addCriterion("award3Name not between", value1, value2, "award3name");
            return (Criteria) this;
        }

        public Criteria andAward3idIsNull() {
            addCriterion("award3Id is null");
            return (Criteria) this;
        }

        public Criteria andAward3idIsNotNull() {
            addCriterion("award3Id is not null");
            return (Criteria) this;
        }

        public Criteria andAward3idEqualTo(Integer value) {
            addCriterion("award3Id =", value, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idNotEqualTo(Integer value) {
            addCriterion("award3Id <>", value, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idGreaterThan(Integer value) {
            addCriterion("award3Id >", value, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idGreaterThanOrEqualTo(Integer value) {
            addCriterion("award3Id >=", value, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idLessThan(Integer value) {
            addCriterion("award3Id <", value, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idLessThanOrEqualTo(Integer value) {
            addCriterion("award3Id <=", value, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idIn(List<Integer> values) {
            addCriterion("award3Id in", values, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idNotIn(List<Integer> values) {
            addCriterion("award3Id not in", values, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idBetween(Integer value1, Integer value2) {
            addCriterion("award3Id between", value1, value2, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3idNotBetween(Integer value1, Integer value2) {
            addCriterion("award3Id not between", value1, value2, "award3id");
            return (Criteria) this;
        }

        public Criteria andAward3rateIsNull() {
            addCriterion("award3Rate is null");
            return (Criteria) this;
        }

        public Criteria andAward3rateIsNotNull() {
            addCriterion("award3Rate is not null");
            return (Criteria) this;
        }

        public Criteria andAward3rateEqualTo(Float value) {
            addCriterion("award3Rate =", value, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateNotEqualTo(Float value) {
            addCriterion("award3Rate <>", value, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateGreaterThan(Float value) {
            addCriterion("award3Rate >", value, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateGreaterThanOrEqualTo(Float value) {
            addCriterion("award3Rate >=", value, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateLessThan(Float value) {
            addCriterion("award3Rate <", value, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateLessThanOrEqualTo(Float value) {
            addCriterion("award3Rate <=", value, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateIn(List<Float> values) {
            addCriterion("award3Rate in", values, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateNotIn(List<Float> values) {
            addCriterion("award3Rate not in", values, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateBetween(Float value1, Float value2) {
            addCriterion("award3Rate between", value1, value2, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3rateNotBetween(Float value1, Float value2) {
            addCriterion("award3Rate not between", value1, value2, "award3rate");
            return (Criteria) this;
        }

        public Criteria andAward3desIsNull() {
            addCriterion("award3Des is null");
            return (Criteria) this;
        }

        public Criteria andAward3desIsNotNull() {
            addCriterion("award3Des is not null");
            return (Criteria) this;
        }

        public Criteria andAward3desEqualTo(String value) {
            addCriterion("award3Des =", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desNotEqualTo(String value) {
            addCriterion("award3Des <>", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desGreaterThan(String value) {
            addCriterion("award3Des >", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desGreaterThanOrEqualTo(String value) {
            addCriterion("award3Des >=", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desLessThan(String value) {
            addCriterion("award3Des <", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desLessThanOrEqualTo(String value) {
            addCriterion("award3Des <=", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desLike(String value) {
            addCriterion("award3Des like", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desNotLike(String value) {
            addCriterion("award3Des not like", value, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desIn(List<String> values) {
            addCriterion("award3Des in", values, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desNotIn(List<String> values) {
            addCriterion("award3Des not in", values, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desBetween(String value1, String value2) {
            addCriterion("award3Des between", value1, value2, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward3desNotBetween(String value1, String value2) {
            addCriterion("award3Des not between", value1, value2, "award3des");
            return (Criteria) this;
        }

        public Criteria andAward4nameIsNull() {
            addCriterion("award4Name is null");
            return (Criteria) this;
        }

        public Criteria andAward4nameIsNotNull() {
            addCriterion("award4Name is not null");
            return (Criteria) this;
        }

        public Criteria andAward4nameEqualTo(String value) {
            addCriterion("award4Name =", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameNotEqualTo(String value) {
            addCriterion("award4Name <>", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameGreaterThan(String value) {
            addCriterion("award4Name >", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameGreaterThanOrEqualTo(String value) {
            addCriterion("award4Name >=", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameLessThan(String value) {
            addCriterion("award4Name <", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameLessThanOrEqualTo(String value) {
            addCriterion("award4Name <=", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameLike(String value) {
            addCriterion("award4Name like", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameNotLike(String value) {
            addCriterion("award4Name not like", value, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameIn(List<String> values) {
            addCriterion("award4Name in", values, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameNotIn(List<String> values) {
            addCriterion("award4Name not in", values, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameBetween(String value1, String value2) {
            addCriterion("award4Name between", value1, value2, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4nameNotBetween(String value1, String value2) {
            addCriterion("award4Name not between", value1, value2, "award4name");
            return (Criteria) this;
        }

        public Criteria andAward4idIsNull() {
            addCriterion("award4Id is null");
            return (Criteria) this;
        }

        public Criteria andAward4idIsNotNull() {
            addCriterion("award4Id is not null");
            return (Criteria) this;
        }

        public Criteria andAward4idEqualTo(Integer value) {
            addCriterion("award4Id =", value, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idNotEqualTo(Integer value) {
            addCriterion("award4Id <>", value, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idGreaterThan(Integer value) {
            addCriterion("award4Id >", value, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idGreaterThanOrEqualTo(Integer value) {
            addCriterion("award4Id >=", value, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idLessThan(Integer value) {
            addCriterion("award4Id <", value, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idLessThanOrEqualTo(Integer value) {
            addCriterion("award4Id <=", value, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idIn(List<Integer> values) {
            addCriterion("award4Id in", values, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idNotIn(List<Integer> values) {
            addCriterion("award4Id not in", values, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idBetween(Integer value1, Integer value2) {
            addCriterion("award4Id between", value1, value2, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4idNotBetween(Integer value1, Integer value2) {
            addCriterion("award4Id not between", value1, value2, "award4id");
            return (Criteria) this;
        }

        public Criteria andAward4rateIsNull() {
            addCriterion("award4Rate is null");
            return (Criteria) this;
        }

        public Criteria andAward4rateIsNotNull() {
            addCriterion("award4Rate is not null");
            return (Criteria) this;
        }

        public Criteria andAward4rateEqualTo(Float value) {
            addCriterion("award4Rate =", value, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateNotEqualTo(Float value) {
            addCriterion("award4Rate <>", value, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateGreaterThan(Float value) {
            addCriterion("award4Rate >", value, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateGreaterThanOrEqualTo(Float value) {
            addCriterion("award4Rate >=", value, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateLessThan(Float value) {
            addCriterion("award4Rate <", value, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateLessThanOrEqualTo(Float value) {
            addCriterion("award4Rate <=", value, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateIn(List<Float> values) {
            addCriterion("award4Rate in", values, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateNotIn(List<Float> values) {
            addCriterion("award4Rate not in", values, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateBetween(Float value1, Float value2) {
            addCriterion("award4Rate between", value1, value2, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4rateNotBetween(Float value1, Float value2) {
            addCriterion("award4Rate not between", value1, value2, "award4rate");
            return (Criteria) this;
        }

        public Criteria andAward4desIsNull() {
            addCriterion("award4Des is null");
            return (Criteria) this;
        }

        public Criteria andAward4desIsNotNull() {
            addCriterion("award4Des is not null");
            return (Criteria) this;
        }

        public Criteria andAward4desEqualTo(String value) {
            addCriterion("award4Des =", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desNotEqualTo(String value) {
            addCriterion("award4Des <>", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desGreaterThan(String value) {
            addCriterion("award4Des >", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desGreaterThanOrEqualTo(String value) {
            addCriterion("award4Des >=", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desLessThan(String value) {
            addCriterion("award4Des <", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desLessThanOrEqualTo(String value) {
            addCriterion("award4Des <=", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desLike(String value) {
            addCriterion("award4Des like", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desNotLike(String value) {
            addCriterion("award4Des not like", value, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desIn(List<String> values) {
            addCriterion("award4Des in", values, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desNotIn(List<String> values) {
            addCriterion("award4Des not in", values, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desBetween(String value1, String value2) {
            addCriterion("award4Des between", value1, value2, "award4des");
            return (Criteria) this;
        }

        public Criteria andAward4desNotBetween(String value1, String value2) {
            addCriterion("award4Des not between", value1, value2, "award4des");
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