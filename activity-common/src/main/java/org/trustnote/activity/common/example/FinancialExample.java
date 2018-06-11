package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhuxl
 */
public class FinancialExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinancialExample() {
        this.oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(final String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return this.orderByClause;
    }

    public void setDistinct(final boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return this.distinct;
    }

    public List<Criteria> getOredCriteria() {
        return this.oredCriteria;
    }

    public void or(final Criteria criteria) {
        this.oredCriteria.add(criteria);
    }

    public Criteria or() {
        final Criteria criteria = this.createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        final Criteria criteria = this.createCriteriaInternal();
        if (this.oredCriteria.size() == 0) {
            this.oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        final Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        this.oredCriteria.clear();
        this.orderByClause = null;
        this.distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            this.criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return this.criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return this.criteria;
        }

        public List<Criterion> getCriteria() {
            return this.criteria;
        }

        protected void addCriterion(final String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            this.criteria.add(new Criterion(condition));
        }

        protected void addCriterion(final String condition, final Object value, final String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            this.criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(final String condition, final Object value1, final Object value2, final String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            this.criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            this.addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            this.addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(final Integer value) {
            this.addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(final Integer value) {
            this.addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(final Integer value) {
            this.addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(final Integer value) {
            this.addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(final Integer value) {
            this.addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(final List<Integer> values) {
            this.addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(final List<Integer> values) {
            this.addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(final Integer value1, final Integer value2) {
            this.addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFinancialNameIsNull() {
            this.addCriterion("financial_name is null");
            return (Criteria) this;
        }

        public Criteria andFinancialNameIsNotNull() {
            this.addCriterion("financial_name is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialNameEqualTo(final String value) {
            this.addCriterion("financial_name =", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameNotEqualTo(final String value) {
            this.addCriterion("financial_name <>", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameGreaterThan(final String value) {
            this.addCriterion("financial_name >", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameGreaterThanOrEqualTo(final String value) {
            this.addCriterion("financial_name >=", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameLessThan(final String value) {
            this.addCriterion("financial_name <", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameLessThanOrEqualTo(final String value) {
            this.addCriterion("financial_name <=", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameLike(final String value) {
            this.addCriterion("financial_name like", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameNotLike(final String value) {
            this.addCriterion("financial_name not like", value, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameIn(final List<String> values) {
            this.addCriterion("financial_name in", values, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameNotIn(final List<String> values) {
            this.addCriterion("financial_name not in", values, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameBetween(final String value1, final String value2) {
            this.addCriterion("financial_name between", value1, value2, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialNameNotBetween(final String value1, final String value2) {
            this.addCriterion("financial_name not between", value1, value2, "financialName");
            return (Criteria) this;
        }

        public Criteria andFinancialRateIsNull() {
            this.addCriterion("financial_rate is null");
            return (Criteria) this;
        }

        public Criteria andFinancialRateIsNotNull() {
            this.addCriterion("financial_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialRateEqualTo(final Float value) {
            this.addCriterion("financial_rate =", value, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateNotEqualTo(final Float value) {
            this.addCriterion("financial_rate <>", value, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateGreaterThan(final Float value) {
            this.addCriterion("financial_rate >", value, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateGreaterThanOrEqualTo(final Float value) {
            this.addCriterion("financial_rate >=", value, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateLessThan(final Float value) {
            this.addCriterion("financial_rate <", value, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateLessThanOrEqualTo(final Float value) {
            this.addCriterion("financial_rate <=", value, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateIn(final List<Float> values) {
            this.addCriterion("financial_rate in", values, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateNotIn(final List<Float> values) {
            this.addCriterion("financial_rate not in", values, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateBetween(final Float value1, final Float value2) {
            this.addCriterion("financial_rate between", value1, value2, "financialRate");
            return (Criteria) this;
        }

        public Criteria andFinancialRateNotBetween(final Float value1, final Float value2) {
            this.addCriterion("financial_rate not between", value1, value2, "financialRate");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNull() {
            this.addCriterion("upt_time is null");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNotNull() {
            this.addCriterion("upt_time is not null");
            return (Criteria) this;
        }

        public Criteria andUptTimeEqualTo(final Date value) {
            this.addCriterion("upt_time =", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotEqualTo(final Date value) {
            this.addCriterion("upt_time <>", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThan(final Date value) {
            this.addCriterion("upt_time >", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("upt_time >=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThan(final Date value) {
            this.addCriterion("upt_time <", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("upt_time <=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeIn(final List<Date> values) {
            this.addCriterion("upt_time in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotIn(final List<Date> values) {
            this.addCriterion("upt_time not in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("upt_time between", value1, value2, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("upt_time not between", value1, value2, "uptTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private final String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private final String typeHandler;

        public String getCondition() {
            return this.condition;
        }

        public Object getValue() {
            return this.value;
        }

        public Object getSecondValue() {
            return this.secondValue;
        }

        public boolean isNoValue() {
            return this.noValue;
        }

        public boolean isSingleValue() {
            return this.singleValue;
        }

        public boolean isBetweenValue() {
            return this.betweenValue;
        }

        public boolean isListValue() {
            return this.listValue;
        }

        public String getTypeHandler() {
            return this.typeHandler;
        }

        protected Criterion(final String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(final String condition, final Object value, final String typeHandler) {
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

        protected Criterion(final String condition, final Object value) {
            this(condition, value, null);
        }

        protected Criterion(final String condition, final Object value, final Object secondValue, final String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(final String condition, final Object value, final Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}