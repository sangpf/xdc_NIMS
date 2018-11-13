package com.newIns.model.vote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NiVoteDeliveryWanxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NiVoteDeliveryWanxExample() {
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

        public Criteria andCollectnumIsNull() {
            addCriterion("collectNum is null");
            return (Criteria) this;
        }

        public Criteria andCollectnumIsNotNull() {
            addCriterion("collectNum is not null");
            return (Criteria) this;
        }

        public Criteria andCollectnumEqualTo(Integer value) {
            addCriterion("collectNum =", value, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumNotEqualTo(Integer value) {
            addCriterion("collectNum <>", value, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumGreaterThan(Integer value) {
            addCriterion("collectNum >", value, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collectNum >=", value, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumLessThan(Integer value) {
            addCriterion("collectNum <", value, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumLessThanOrEqualTo(Integer value) {
            addCriterion("collectNum <=", value, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumIn(List<Integer> values) {
            addCriterion("collectNum in", values, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumNotIn(List<Integer> values) {
            addCriterion("collectNum not in", values, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumBetween(Integer value1, Integer value2) {
            addCriterion("collectNum between", value1, value2, "collectnum");
            return (Criteria) this;
        }

        public Criteria andCollectnumNotBetween(Integer value1, Integer value2) {
            addCriterion("collectNum not between", value1, value2, "collectnum");
            return (Criteria) this;
        }

        public Criteria andBtimeIsNull() {
            addCriterion("bTime is null");
            return (Criteria) this;
        }

        public Criteria andBtimeIsNotNull() {
            addCriterion("bTime is not null");
            return (Criteria) this;
        }

        public Criteria andBtimeEqualTo(Date value) {
            addCriterion("bTime =", value, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeNotEqualTo(Date value) {
            addCriterion("bTime <>", value, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeGreaterThan(Date value) {
            addCriterion("bTime >", value, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bTime >=", value, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeLessThan(Date value) {
            addCriterion("bTime <", value, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeLessThanOrEqualTo(Date value) {
            addCriterion("bTime <=", value, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeIn(List<Date> values) {
            addCriterion("bTime in", values, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeNotIn(List<Date> values) {
            addCriterion("bTime not in", values, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeBetween(Date value1, Date value2) {
            addCriterion("bTime between", value1, value2, "btime");
            return (Criteria) this;
        }

        public Criteria andBtimeNotBetween(Date value1, Date value2) {
            addCriterion("bTime not between", value1, value2, "btime");
            return (Criteria) this;
        }

        public Criteria andEtimeIsNull() {
            addCriterion("eTime is null");
            return (Criteria) this;
        }

        public Criteria andEtimeIsNotNull() {
            addCriterion("eTime is not null");
            return (Criteria) this;
        }

        public Criteria andEtimeEqualTo(Date value) {
            addCriterion("eTime =", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotEqualTo(Date value) {
            addCriterion("eTime <>", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeGreaterThan(Date value) {
            addCriterion("eTime >", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eTime >=", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeLessThan(Date value) {
            addCriterion("eTime <", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeLessThanOrEqualTo(Date value) {
            addCriterion("eTime <=", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeIn(List<Date> values) {
            addCriterion("eTime in", values, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotIn(List<Date> values) {
            addCriterion("eTime not in", values, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeBetween(Date value1, Date value2) {
            addCriterion("eTime between", value1, value2, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotBetween(Date value1, Date value2) {
            addCriterion("eTime not between", value1, value2, "etime");
            return (Criteria) this;
        }

        public Criteria andResonIsNull() {
            addCriterion("reson is null");
            return (Criteria) this;
        }

        public Criteria andResonIsNotNull() {
            addCriterion("reson is not null");
            return (Criteria) this;
        }

        public Criteria andResonEqualTo(String value) {
            addCriterion("reson =", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonNotEqualTo(String value) {
            addCriterion("reson <>", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonGreaterThan(String value) {
            addCriterion("reson >", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonGreaterThanOrEqualTo(String value) {
            addCriterion("reson >=", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonLessThan(String value) {
            addCriterion("reson <", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonLessThanOrEqualTo(String value) {
            addCriterion("reson <=", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonLike(String value) {
            addCriterion("reson like", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonNotLike(String value) {
            addCriterion("reson not like", value, "reson");
            return (Criteria) this;
        }

        public Criteria andResonIn(List<String> values) {
            addCriterion("reson in", values, "reson");
            return (Criteria) this;
        }

        public Criteria andResonNotIn(List<String> values) {
            addCriterion("reson not in", values, "reson");
            return (Criteria) this;
        }

        public Criteria andResonBetween(String value1, String value2) {
            addCriterion("reson between", value1, value2, "reson");
            return (Criteria) this;
        }

        public Criteria andResonNotBetween(String value1, String value2) {
            addCriterion("reson not between", value1, value2, "reson");
            return (Criteria) this;
        }

        public Criteria andShowtitleIsNull() {
            addCriterion("showTitle is null");
            return (Criteria) this;
        }

        public Criteria andShowtitleIsNotNull() {
            addCriterion("showTitle is not null");
            return (Criteria) this;
        }

        public Criteria andShowtitleEqualTo(String value) {
            addCriterion("showTitle =", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleNotEqualTo(String value) {
            addCriterion("showTitle <>", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleGreaterThan(String value) {
            addCriterion("showTitle >", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleGreaterThanOrEqualTo(String value) {
            addCriterion("showTitle >=", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleLessThan(String value) {
            addCriterion("showTitle <", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleLessThanOrEqualTo(String value) {
            addCriterion("showTitle <=", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleLike(String value) {
            addCriterion("showTitle like", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleNotLike(String value) {
            addCriterion("showTitle not like", value, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleIn(List<String> values) {
            addCriterion("showTitle in", values, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleNotIn(List<String> values) {
            addCriterion("showTitle not in", values, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleBetween(String value1, String value2) {
            addCriterion("showTitle between", value1, value2, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowtitleNotBetween(String value1, String value2) {
            addCriterion("showTitle not between", value1, value2, "showtitle");
            return (Criteria) this;
        }

        public Criteria andShowdesIsNull() {
            addCriterion("showDes is null");
            return (Criteria) this;
        }

        public Criteria andShowdesIsNotNull() {
            addCriterion("showDes is not null");
            return (Criteria) this;
        }

        public Criteria andShowdesEqualTo(String value) {
            addCriterion("showDes =", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesNotEqualTo(String value) {
            addCriterion("showDes <>", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesGreaterThan(String value) {
            addCriterion("showDes >", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesGreaterThanOrEqualTo(String value) {
            addCriterion("showDes >=", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesLessThan(String value) {
            addCriterion("showDes <", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesLessThanOrEqualTo(String value) {
            addCriterion("showDes <=", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesLike(String value) {
            addCriterion("showDes like", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesNotLike(String value) {
            addCriterion("showDes not like", value, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesIn(List<String> values) {
            addCriterion("showDes in", values, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesNotIn(List<String> values) {
            addCriterion("showDes not in", values, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesBetween(String value1, String value2) {
            addCriterion("showDes between", value1, value2, "showdes");
            return (Criteria) this;
        }

        public Criteria andShowdesNotBetween(String value1, String value2) {
            addCriterion("showDes not between", value1, value2, "showdes");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andCollectednumIsNull() {
            addCriterion("collectedNum is null");
            return (Criteria) this;
        }

        public Criteria andCollectednumIsNotNull() {
            addCriterion("collectedNum is not null");
            return (Criteria) this;
        }

        public Criteria andCollectednumEqualTo(Integer value) {
            addCriterion("collectedNum =", value, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumNotEqualTo(Integer value) {
            addCriterion("collectedNum <>", value, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumGreaterThan(Integer value) {
            addCriterion("collectedNum >", value, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collectedNum >=", value, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumLessThan(Integer value) {
            addCriterion("collectedNum <", value, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumLessThanOrEqualTo(Integer value) {
            addCriterion("collectedNum <=", value, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumIn(List<Integer> values) {
            addCriterion("collectedNum in", values, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumNotIn(List<Integer> values) {
            addCriterion("collectedNum not in", values, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumBetween(Integer value1, Integer value2) {
            addCriterion("collectedNum between", value1, value2, "collectednum");
            return (Criteria) this;
        }

        public Criteria andCollectednumNotBetween(Integer value1, Integer value2) {
            addCriterion("collectedNum not between", value1, value2, "collectednum");
            return (Criteria) this;
        }

        public Criteria andTag1strIsNull() {
            addCriterion("tag1Str is null");
            return (Criteria) this;
        }

        public Criteria andTag1strIsNotNull() {
            addCriterion("tag1Str is not null");
            return (Criteria) this;
        }

        public Criteria andTag1strEqualTo(String value) {
            addCriterion("tag1Str =", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strNotEqualTo(String value) {
            addCriterion("tag1Str <>", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strGreaterThan(String value) {
            addCriterion("tag1Str >", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strGreaterThanOrEqualTo(String value) {
            addCriterion("tag1Str >=", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strLessThan(String value) {
            addCriterion("tag1Str <", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strLessThanOrEqualTo(String value) {
            addCriterion("tag1Str <=", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strLike(String value) {
            addCriterion("tag1Str like", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strNotLike(String value) {
            addCriterion("tag1Str not like", value, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strIn(List<String> values) {
            addCriterion("tag1Str in", values, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strNotIn(List<String> values) {
            addCriterion("tag1Str not in", values, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strBetween(String value1, String value2) {
            addCriterion("tag1Str between", value1, value2, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag1strNotBetween(String value1, String value2) {
            addCriterion("tag1Str not between", value1, value2, "tag1str");
            return (Criteria) this;
        }

        public Criteria andTag2strIsNull() {
            addCriterion("tag2Str is null");
            return (Criteria) this;
        }

        public Criteria andTag2strIsNotNull() {
            addCriterion("tag2Str is not null");
            return (Criteria) this;
        }

        public Criteria andTag2strEqualTo(String value) {
            addCriterion("tag2Str =", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strNotEqualTo(String value) {
            addCriterion("tag2Str <>", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strGreaterThan(String value) {
            addCriterion("tag2Str >", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strGreaterThanOrEqualTo(String value) {
            addCriterion("tag2Str >=", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strLessThan(String value) {
            addCriterion("tag2Str <", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strLessThanOrEqualTo(String value) {
            addCriterion("tag2Str <=", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strLike(String value) {
            addCriterion("tag2Str like", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strNotLike(String value) {
            addCriterion("tag2Str not like", value, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strIn(List<String> values) {
            addCriterion("tag2Str in", values, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strNotIn(List<String> values) {
            addCriterion("tag2Str not in", values, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strBetween(String value1, String value2) {
            addCriterion("tag2Str between", value1, value2, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag2strNotBetween(String value1, String value2) {
            addCriterion("tag2Str not between", value1, value2, "tag2str");
            return (Criteria) this;
        }

        public Criteria andTag3strIsNull() {
            addCriterion("tag3Str is null");
            return (Criteria) this;
        }

        public Criteria andTag3strIsNotNull() {
            addCriterion("tag3Str is not null");
            return (Criteria) this;
        }

        public Criteria andTag3strEqualTo(String value) {
            addCriterion("tag3Str =", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strNotEqualTo(String value) {
            addCriterion("tag3Str <>", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strGreaterThan(String value) {
            addCriterion("tag3Str >", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strGreaterThanOrEqualTo(String value) {
            addCriterion("tag3Str >=", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strLessThan(String value) {
            addCriterion("tag3Str <", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strLessThanOrEqualTo(String value) {
            addCriterion("tag3Str <=", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strLike(String value) {
            addCriterion("tag3Str like", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strNotLike(String value) {
            addCriterion("tag3Str not like", value, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strIn(List<String> values) {
            addCriterion("tag3Str in", values, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strNotIn(List<String> values) {
            addCriterion("tag3Str not in", values, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strBetween(String value1, String value2) {
            addCriterion("tag3Str between", value1, value2, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag3strNotBetween(String value1, String value2) {
            addCriterion("tag3Str not between", value1, value2, "tag3str");
            return (Criteria) this;
        }

        public Criteria andTag4strIsNull() {
            addCriterion("tag4Str is null");
            return (Criteria) this;
        }

        public Criteria andTag4strIsNotNull() {
            addCriterion("tag4Str is not null");
            return (Criteria) this;
        }

        public Criteria andTag4strEqualTo(String value) {
            addCriterion("tag4Str =", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strNotEqualTo(String value) {
            addCriterion("tag4Str <>", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strGreaterThan(String value) {
            addCriterion("tag4Str >", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strGreaterThanOrEqualTo(String value) {
            addCriterion("tag4Str >=", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strLessThan(String value) {
            addCriterion("tag4Str <", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strLessThanOrEqualTo(String value) {
            addCriterion("tag4Str <=", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strLike(String value) {
            addCriterion("tag4Str like", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strNotLike(String value) {
            addCriterion("tag4Str not like", value, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strIn(List<String> values) {
            addCriterion("tag4Str in", values, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strNotIn(List<String> values) {
            addCriterion("tag4Str not in", values, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strBetween(String value1, String value2) {
            addCriterion("tag4Str between", value1, value2, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag4strNotBetween(String value1, String value2) {
            addCriterion("tag4Str not between", value1, value2, "tag4str");
            return (Criteria) this;
        }

        public Criteria andTag5strIsNull() {
            addCriterion("tag5Str is null");
            return (Criteria) this;
        }

        public Criteria andTag5strIsNotNull() {
            addCriterion("tag5Str is not null");
            return (Criteria) this;
        }

        public Criteria andTag5strEqualTo(String value) {
            addCriterion("tag5Str =", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strNotEqualTo(String value) {
            addCriterion("tag5Str <>", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strGreaterThan(String value) {
            addCriterion("tag5Str >", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strGreaterThanOrEqualTo(String value) {
            addCriterion("tag5Str >=", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strLessThan(String value) {
            addCriterion("tag5Str <", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strLessThanOrEqualTo(String value) {
            addCriterion("tag5Str <=", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strLike(String value) {
            addCriterion("tag5Str like", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strNotLike(String value) {
            addCriterion("tag5Str not like", value, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strIn(List<String> values) {
            addCriterion("tag5Str in", values, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strNotIn(List<String> values) {
            addCriterion("tag5Str not in", values, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strBetween(String value1, String value2) {
            addCriterion("tag5Str between", value1, value2, "tag5str");
            return (Criteria) this;
        }

        public Criteria andTag5strNotBetween(String value1, String value2) {
            addCriterion("tag5Str not between", value1, value2, "tag5str");
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

        public Criteria andLottteryreadyIsNull() {
            addCriterion("lottteryReady is null");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyIsNotNull() {
            addCriterion("lottteryReady is not null");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyEqualTo(Byte value) {
            addCriterion("lottteryReady =", value, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyNotEqualTo(Byte value) {
            addCriterion("lottteryReady <>", value, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyGreaterThan(Byte value) {
            addCriterion("lottteryReady >", value, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyGreaterThanOrEqualTo(Byte value) {
            addCriterion("lottteryReady >=", value, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyLessThan(Byte value) {
            addCriterion("lottteryReady <", value, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyLessThanOrEqualTo(Byte value) {
            addCriterion("lottteryReady <=", value, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyIn(List<Byte> values) {
            addCriterion("lottteryReady in", values, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyNotIn(List<Byte> values) {
            addCriterion("lottteryReady not in", values, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyBetween(Byte value1, Byte value2) {
            addCriterion("lottteryReady between", value1, value2, "lottteryready");
            return (Criteria) this;
        }

        public Criteria andLottteryreadyNotBetween(Byte value1, Byte value2) {
            addCriterion("lottteryReady not between", value1, value2, "lottteryready");
            return (Criteria) this;
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