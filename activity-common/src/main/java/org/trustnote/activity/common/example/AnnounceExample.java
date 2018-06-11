package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnounceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnnounceExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleCnIsNull() {
            addCriterion("title_cn is null");
            return (Criteria) this;
        }

        public Criteria andTitleCnIsNotNull() {
            addCriterion("title_cn is not null");
            return (Criteria) this;
        }

        public Criteria andTitleCnEqualTo(String value) {
            addCriterion("title_cn =", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnNotEqualTo(String value) {
            addCriterion("title_cn <>", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnGreaterThan(String value) {
            addCriterion("title_cn >", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnGreaterThanOrEqualTo(String value) {
            addCriterion("title_cn >=", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnLessThan(String value) {
            addCriterion("title_cn <", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnLessThanOrEqualTo(String value) {
            addCriterion("title_cn <=", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnLike(String value) {
            addCriterion("title_cn like", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnNotLike(String value) {
            addCriterion("title_cn not like", value, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnIn(List<String> values) {
            addCriterion("title_cn in", values, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnNotIn(List<String> values) {
            addCriterion("title_cn not in", values, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnBetween(String value1, String value2) {
            addCriterion("title_cn between", value1, value2, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleCnNotBetween(String value1, String value2) {
            addCriterion("title_cn not between", value1, value2, "titleCn");
            return (Criteria) this;
        }

        public Criteria andTitleEnIsNull() {
            addCriterion("title_en is null");
            return (Criteria) this;
        }

        public Criteria andTitleEnIsNotNull() {
            addCriterion("title_en is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEnEqualTo(String value) {
            addCriterion("title_en =", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnNotEqualTo(String value) {
            addCriterion("title_en <>", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnGreaterThan(String value) {
            addCriterion("title_en >", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnGreaterThanOrEqualTo(String value) {
            addCriterion("title_en >=", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnLessThan(String value) {
            addCriterion("title_en <", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnLessThanOrEqualTo(String value) {
            addCriterion("title_en <=", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnLike(String value) {
            addCriterion("title_en like", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnNotLike(String value) {
            addCriterion("title_en not like", value, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnIn(List<String> values) {
            addCriterion("title_en in", values, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnNotIn(List<String> values) {
            addCriterion("title_en not in", values, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnBetween(String value1, String value2) {
            addCriterion("title_en between", value1, value2, "titleEn");
            return (Criteria) this;
        }

        public Criteria andTitleEnNotBetween(String value1, String value2) {
            addCriterion("title_en not between", value1, value2, "titleEn");
            return (Criteria) this;
        }

        public Criteria andViewedCnIsNull() {
            addCriterion("viewed_cn is null");
            return (Criteria) this;
        }

        public Criteria andViewedCnIsNotNull() {
            addCriterion("viewed_cn is not null");
            return (Criteria) this;
        }

        public Criteria andViewedCnEqualTo(Integer value) {
            addCriterion("viewed_cn =", value, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnNotEqualTo(Integer value) {
            addCriterion("viewed_cn <>", value, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnGreaterThan(Integer value) {
            addCriterion("viewed_cn >", value, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnGreaterThanOrEqualTo(Integer value) {
            addCriterion("viewed_cn >=", value, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnLessThan(Integer value) {
            addCriterion("viewed_cn <", value, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnLessThanOrEqualTo(Integer value) {
            addCriterion("viewed_cn <=", value, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnIn(List<Integer> values) {
            addCriterion("viewed_cn in", values, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnNotIn(List<Integer> values) {
            addCriterion("viewed_cn not in", values, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnBetween(Integer value1, Integer value2) {
            addCriterion("viewed_cn between", value1, value2, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedCnNotBetween(Integer value1, Integer value2) {
            addCriterion("viewed_cn not between", value1, value2, "viewedCn");
            return (Criteria) this;
        }

        public Criteria andViewedEnIsNull() {
            addCriterion("viewed_en is null");
            return (Criteria) this;
        }

        public Criteria andViewedEnIsNotNull() {
            addCriterion("viewed_en is not null");
            return (Criteria) this;
        }

        public Criteria andViewedEnEqualTo(Integer value) {
            addCriterion("viewed_en =", value, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnNotEqualTo(Integer value) {
            addCriterion("viewed_en <>", value, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnGreaterThan(Integer value) {
            addCriterion("viewed_en >", value, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnGreaterThanOrEqualTo(Integer value) {
            addCriterion("viewed_en >=", value, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnLessThan(Integer value) {
            addCriterion("viewed_en <", value, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnLessThanOrEqualTo(Integer value) {
            addCriterion("viewed_en <=", value, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnIn(List<Integer> values) {
            addCriterion("viewed_en in", values, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnNotIn(List<Integer> values) {
            addCriterion("viewed_en not in", values, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnBetween(Integer value1, Integer value2) {
            addCriterion("viewed_en between", value1, value2, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andViewedEnNotBetween(Integer value1, Integer value2) {
            addCriterion("viewed_en not between", value1, value2, "viewedEn");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNull() {
            addCriterion("release_time is null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNotNull() {
            addCriterion("release_time is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeEqualTo(Date value) {
            addCriterion("release_time =", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotEqualTo(Date value) {
            addCriterion("release_time <>", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThan(Date value) {
            addCriterion("release_time >", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("release_time >=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThan(Date value) {
            addCriterion("release_time <", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("release_time <=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIn(List<Date> values) {
            addCriterion("release_time in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotIn(List<Date> values) {
            addCriterion("release_time not in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("release_time between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("release_time not between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andPlacedTopIsNull() {
            addCriterion("placed_top is null");
            return (Criteria) this;
        }

        public Criteria andPlacedTopIsNotNull() {
            addCriterion("placed_top is not null");
            return (Criteria) this;
        }

        public Criteria andPlacedTopEqualTo(Integer value) {
            addCriterion("placed_top =", value, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopNotEqualTo(Integer value) {
            addCriterion("placed_top <>", value, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopGreaterThan(Integer value) {
            addCriterion("placed_top >", value, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopGreaterThanOrEqualTo(Integer value) {
            addCriterion("placed_top >=", value, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopLessThan(Integer value) {
            addCriterion("placed_top <", value, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopLessThanOrEqualTo(Integer value) {
            addCriterion("placed_top <=", value, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopIn(List<Integer> values) {
            addCriterion("placed_top in", values, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopNotIn(List<Integer> values) {
            addCriterion("placed_top not in", values, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopBetween(Integer value1, Integer value2) {
            addCriterion("placed_top between", value1, value2, "placedTop");
            return (Criteria) this;
        }

        public Criteria andPlacedTopNotBetween(Integer value1, Integer value2) {
            addCriterion("placed_top not between", value1, value2, "placedTop");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNull() {
            addCriterion("available is null");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNotNull() {
            addCriterion("available is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableEqualTo(Integer value) {
            addCriterion("available =", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotEqualTo(Integer value) {
            addCriterion("available <>", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThan(Integer value) {
            addCriterion("available >", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThanOrEqualTo(Integer value) {
            addCriterion("available >=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThan(Integer value) {
            addCriterion("available <", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThanOrEqualTo(Integer value) {
            addCriterion("available <=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableIn(List<Integer> values) {
            addCriterion("available in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotIn(List<Integer> values) {
            addCriterion("available not in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableBetween(Integer value1, Integer value2) {
            addCriterion("available between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotBetween(Integer value1, Integer value2) {
            addCriterion("available not between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andLastModifedIsNull() {
            addCriterion("last_modifed is null");
            return (Criteria) this;
        }

        public Criteria andLastModifedIsNotNull() {
            addCriterion("last_modifed is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifedEqualTo(Date value) {
            addCriterion("last_modifed =", value, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedNotEqualTo(Date value) {
            addCriterion("last_modifed <>", value, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedGreaterThan(Date value) {
            addCriterion("last_modifed >", value, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedGreaterThanOrEqualTo(Date value) {
            addCriterion("last_modifed >=", value, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedLessThan(Date value) {
            addCriterion("last_modifed <", value, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedLessThanOrEqualTo(Date value) {
            addCriterion("last_modifed <=", value, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedIn(List<Date> values) {
            addCriterion("last_modifed in", values, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedNotIn(List<Date> values) {
            addCriterion("last_modifed not in", values, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedBetween(Date value1, Date value2) {
            addCriterion("last_modifed between", value1, value2, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastModifedNotBetween(Date value1, Date value2) {
            addCriterion("last_modifed not between", value1, value2, "lastModifed");
            return (Criteria) this;
        }

        public Criteria andLastByIsNull() {
            addCriterion("last_by is null");
            return (Criteria) this;
        }

        public Criteria andLastByIsNotNull() {
            addCriterion("last_by is not null");
            return (Criteria) this;
        }

        public Criteria andLastByEqualTo(Integer value) {
            addCriterion("last_by =", value, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByNotEqualTo(Integer value) {
            addCriterion("last_by <>", value, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByGreaterThan(Integer value) {
            addCriterion("last_by >", value, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_by >=", value, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByLessThan(Integer value) {
            addCriterion("last_by <", value, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByLessThanOrEqualTo(Integer value) {
            addCriterion("last_by <=", value, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByIn(List<Integer> values) {
            addCriterion("last_by in", values, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByNotIn(List<Integer> values) {
            addCriterion("last_by not in", values, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByBetween(Integer value1, Integer value2) {
            addCriterion("last_by between", value1, value2, "lastBy");
            return (Criteria) this;
        }

        public Criteria andLastByNotBetween(Integer value1, Integer value2) {
            addCriterion("last_by not between", value1, value2, "lastBy");
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