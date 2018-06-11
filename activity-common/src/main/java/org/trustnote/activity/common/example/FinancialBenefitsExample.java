package org.trustnote.activity.common.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhuxl
 */
public class FinancialBenefitsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinancialBenefitsExample() {
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

        public Criteria andFinancialIdIsNull() {
            this.addCriterion("financial_id is null");
            return (Criteria) this;
        }

        public Criteria andFinancialIdIsNotNull() {
            this.addCriterion("financial_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialIdEqualTo(final Integer value) {
            this.addCriterion("financial_id =", value, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdNotEqualTo(final Integer value) {
            this.addCriterion("financial_id <>", value, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdGreaterThan(final Integer value) {
            this.addCriterion("financial_id >", value, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("financial_id >=", value, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdLessThan(final Integer value) {
            this.addCriterion("financial_id <", value, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdLessThanOrEqualTo(final Integer value) {
            this.addCriterion("financial_id <=", value, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdIn(final List<Integer> values) {
            this.addCriterion("financial_id in", values, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdNotIn(final List<Integer> values) {
            this.addCriterion("financial_id not in", values, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdBetween(final Integer value1, final Integer value2) {
            this.addCriterion("financial_id between", value1, value2, "financialId");
            return (Criteria) this;
        }

        public Criteria andFinancialIdNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("financial_id not between", value1, value2, "financialId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            this.addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            this.addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(final String value) {
            this.addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(final String value) {
            this.addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(final String value) {
            this.addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(final String value) {
            this.addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(final String value) {
            this.addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(final String value) {
            this.addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(final String value) {
            this.addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(final String value) {
            this.addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(final List<String> values) {
            this.addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(final List<String> values) {
            this.addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(final String value1, final String value2) {
            this.addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(final String value1, final String value2) {
            this.addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeIsNull() {
            this.addCriterion("panic_start_time is null");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeIsNotNull() {
            this.addCriterion("panic_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeEqualTo(final Date value) {
            this.addCriterion("panic_start_time =", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeNotEqualTo(final Date value) {
            this.addCriterion("panic_start_time <>", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeGreaterThan(final Date value) {
            this.addCriterion("panic_start_time >", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("panic_start_time >=", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeLessThan(final Date value) {
            this.addCriterion("panic_start_time <", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("panic_start_time <=", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeIn(final List<Date> values) {
            this.addCriterion("panic_start_time in", values, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeNotIn(final List<Date> values) {
            this.addCriterion("panic_start_time not in", values, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("panic_start_time between", value1, value2, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("panic_start_time not between", value1, value2, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeIsNull() {
            this.addCriterion("panic_end_time is null");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeIsNotNull() {
            this.addCriterion("panic_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeEqualTo(final Date value) {
            this.addCriterion("panic_end_time =", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeNotEqualTo(final Date value) {
            this.addCriterion("panic_end_time <>", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeGreaterThan(final Date value) {
            this.addCriterion("panic_end_time >", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("panic_end_time >=", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeLessThan(final Date value) {
            this.addCriterion("panic_end_time <", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("panic_end_time <=", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeIn(final List<Date> values) {
            this.addCriterion("panic_end_time in", values, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeNotIn(final List<Date> values) {
            this.addCriterion("panic_end_time not in", values, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("panic_end_time between", value1, value2, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("panic_end_time not between", value1, value2, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeIsNull() {
            this.addCriterion("interest_start_time is null");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeIsNotNull() {
            this.addCriterion("interest_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeEqualTo(final Date value) {
            this.addCriterion("interest_start_time =", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeNotEqualTo(final Date value) {
            this.addCriterion("interest_start_time <>", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeGreaterThan(final Date value) {
            this.addCriterion("interest_start_time >", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("interest_start_time >=", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeLessThan(final Date value) {
            this.addCriterion("interest_start_time <", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("interest_start_time <=", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeIn(final List<Date> values) {
            this.addCriterion("interest_start_time in", values, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeNotIn(final List<Date> values) {
            this.addCriterion("interest_start_time not in", values, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("interest_start_time between", value1, value2, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("interest_start_time not between", value1, value2, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIsNull() {
            this.addCriterion("interest_end_time is null");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIsNotNull() {
            this.addCriterion("interest_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeEqualTo(final Date value) {
            this.addCriterion("interest_end_time =", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotEqualTo(final Date value) {
            this.addCriterion("interest_end_time <>", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeGreaterThan(final Date value) {
            this.addCriterion("interest_end_time >", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("interest_end_time >=", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeLessThan(final Date value) {
            this.addCriterion("interest_end_time <", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("interest_end_time <=", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIn(final List<Date> values) {
            this.addCriterion("interest_end_time in", values, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotIn(final List<Date> values) {
            this.addCriterion("interest_end_time not in", values, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("interest_end_time between", value1, value2, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("interest_end_time not between", value1, value2, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeIsNull() {
            this.addCriterion("unlock_time is null");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeIsNotNull() {
            this.addCriterion("unlock_time is not null");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeEqualTo(final Date value) {
            this.addCriterion("unlock_time =", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeNotEqualTo(final Date value) {
            this.addCriterion("unlock_time <>", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeGreaterThan(final Date value) {
            this.addCriterion("unlock_time >", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("unlock_time >=", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeLessThan(final Date value) {
            this.addCriterion("unlock_time <", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("unlock_time <=", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeIn(final List<Date> values) {
            this.addCriterion("unlock_time in", values, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeNotIn(final List<Date> values) {
            this.addCriterion("unlock_time not in", values, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("unlock_time between", value1, value2, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("unlock_time not between", value1, value2, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitIsNull() {
            this.addCriterion("panic_total_limit is null");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitIsNotNull() {
            this.addCriterion("panic_total_limit is not null");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitEqualTo(final BigDecimal value) {
            this.addCriterion("panic_total_limit =", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitNotEqualTo(final BigDecimal value) {
            this.addCriterion("panic_total_limit <>", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitGreaterThan(final BigDecimal value) {
            this.addCriterion("panic_total_limit >", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("panic_total_limit >=", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitLessThan(final BigDecimal value) {
            this.addCriterion("panic_total_limit <", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("panic_total_limit <=", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitIn(final List<BigDecimal> values) {
            this.addCriterion("panic_total_limit in", values, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitNotIn(final List<BigDecimal> values) {
            this.addCriterion("panic_total_limit not in", values, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("panic_total_limit between", value1, value2, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("panic_total_limit not between", value1, value2, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andMinAmountIsNull() {
            this.addCriterion("min_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinAmountIsNotNull() {
            this.addCriterion("min_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinAmountEqualTo(final BigDecimal value) {
            this.addCriterion("min_amount =", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("min_amount <>", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("min_amount >", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("min_amount >=", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountLessThan(final BigDecimal value) {
            this.addCriterion("min_amount <", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("min_amount <=", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountIn(final List<BigDecimal> values) {
            this.addCriterion("min_amount in", values, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("min_amount not in", values, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("min_amount between", value1, value2, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("min_amount not between", value1, value2, "minAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitIsNull() {
            this.addCriterion("purchase_limit is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitIsNotNull() {
            this.addCriterion("purchase_limit is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitEqualTo(final BigDecimal value) {
            this.addCriterion("purchase_limit =", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotEqualTo(final BigDecimal value) {
            this.addCriterion("purchase_limit <>", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitGreaterThan(final BigDecimal value) {
            this.addCriterion("purchase_limit >", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("purchase_limit >=", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitLessThan(final BigDecimal value) {
            this.addCriterion("purchase_limit <", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("purchase_limit <=", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitIn(final List<BigDecimal> values) {
            this.addCriterion("purchase_limit in", values, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotIn(final List<BigDecimal> values) {
            this.addCriterion("purchase_limit not in", values, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("purchase_limit between", value1, value2, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("purchase_limit not between", value1, value2, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitIsNull() {
            this.addCriterion("remain_limit is null");
            return (Criteria) this;
        }

        public Criteria andRemainLimitIsNotNull() {
            this.addCriterion("remain_limit is not null");
            return (Criteria) this;
        }

        public Criteria andRemainLimitEqualTo(final BigDecimal value) {
            this.addCriterion("remain_limit =", value, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitNotEqualTo(final BigDecimal value) {
            this.addCriterion("remain_limit <>", value, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitGreaterThan(final BigDecimal value) {
            this.addCriterion("remain_limit >", value, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("remain_limit >=", value, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitLessThan(final BigDecimal value) {
            this.addCriterion("remain_limit <", value, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("remain_limit <=", value, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitIn(final List<BigDecimal> values) {
            this.addCriterion("remain_limit in", values, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitNotIn(final List<BigDecimal> values) {
            this.addCriterion("remain_limit not in", values, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("remain_limit between", value1, value2, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andRemainLimitNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("remain_limit not between", value1, value2, "remainLimit");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIsNull() {
            this.addCriterion("financial_status is null");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIsNotNull() {
            this.addCriterion("financial_status is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusEqualTo(final Integer value) {
            this.addCriterion("financial_status =", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotEqualTo(final Integer value) {
            this.addCriterion("financial_status <>", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusGreaterThan(final Integer value) {
            this.addCriterion("financial_status >", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("financial_status >=", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLessThan(final Integer value) {
            this.addCriterion("financial_status <", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLessThanOrEqualTo(final Integer value) {
            this.addCriterion("financial_status <=", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIn(final List<Integer> values) {
            this.addCriterion("financial_status in", values, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotIn(final List<Integer> values) {
            this.addCriterion("financial_status not in", values, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusBetween(final Integer value1, final Integer value2) {
            this.addCriterion("financial_status between", value1, value2, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("financial_status not between", value1, value2, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNull() {
            this.addCriterion("crt_time is null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNotNull() {
            this.addCriterion("crt_time is not null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeEqualTo(final Date value) {
            this.addCriterion("crt_time =", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotEqualTo(final Date value) {
            this.addCriterion("crt_time <>", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThan(final Date value) {
            this.addCriterion("crt_time >", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("crt_time >=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThan(final Date value) {
            this.addCriterion("crt_time <", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("crt_time <=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIn(final List<Date> values) {
            this.addCriterion("crt_time in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotIn(final List<Date> values) {
            this.addCriterion("crt_time not in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("crt_time between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("crt_time not between", value1, value2, "crtTime");
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