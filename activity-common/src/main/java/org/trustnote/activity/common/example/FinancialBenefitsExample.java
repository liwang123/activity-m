package org.trustnote.activity.common.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

        public Criteria andPanicStartTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_start_time =", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_start_time <>", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("panic_start_time >", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_start_time >=", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeLessThan(final LocalDateTime value) {
            this.addCriterion("panic_start_time <", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_start_time <=", value, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("panic_start_time in", values, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("panic_start_time not in", values, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("panic_start_time between", value1, value2, "panicStartTime");
            return (Criteria) this;
        }

        public Criteria andPanicStartTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
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

        public Criteria andPanicEndTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_end_time =", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_end_time <>", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("panic_end_time >", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_end_time >=", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeLessThan(final LocalDateTime value) {
            this.addCriterion("panic_end_time <", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("panic_end_time <=", value, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("panic_end_time in", values, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("panic_end_time not in", values, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("panic_end_time between", value1, value2, "panicEndTime");
            return (Criteria) this;
        }

        public Criteria andPanicEndTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
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

        public Criteria andInterestStartTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_start_time =", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_start_time <>", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("interest_start_time >", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_start_time >=", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeLessThan(final LocalDateTime value) {
            this.addCriterion("interest_start_time <", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_start_time <=", value, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("interest_start_time in", values, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("interest_start_time not in", values, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("interest_start_time between", value1, value2, "interestStartTime");
            return (Criteria) this;
        }

        public Criteria andInterestStartTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
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

        public Criteria andInterestEndTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_end_time =", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_end_time <>", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("interest_end_time >", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_end_time >=", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeLessThan(final LocalDateTime value) {
            this.addCriterion("interest_end_time <", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("interest_end_time <=", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("interest_end_time in", values, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("interest_end_time not in", values, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("interest_end_time between", value1, value2, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
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

        public Criteria andUnlockTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("unlock_time =", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("unlock_time <>", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("unlock_time >", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("unlock_time >=", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeLessThan(final LocalDateTime value) {
            this.addCriterion("unlock_time <", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("unlock_time <=", value, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("unlock_time in", values, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("unlock_time not in", values, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("unlock_time between", value1, value2, "unlockTime");
            return (Criteria) this;
        }

        public Criteria andUnlockTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
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

        public Criteria andPanicTotalLimitEqualTo(final Integer value) {
            this.addCriterion("panic_total_limit =", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitNotEqualTo(final Integer value) {
            this.addCriterion("panic_total_limit <>", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitGreaterThan(final Integer value) {
            this.addCriterion("panic_total_limit >", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("panic_total_limit >=", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitLessThan(final Integer value) {
            this.addCriterion("panic_total_limit <", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitLessThanOrEqualTo(final Integer value) {
            this.addCriterion("panic_total_limit <=", value, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitIn(final List<Integer> values) {
            this.addCriterion("panic_total_limit in", values, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitNotIn(final List<Integer> values) {
            this.addCriterion("panic_total_limit not in", values, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitBetween(final Integer value1, final Integer value2) {
            this.addCriterion("panic_total_limit between", value1, value2, "panicTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPanicTotalLimitNotBetween(final Integer value1, final Integer value2) {
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

        public Criteria andMinAmountEqualTo(final Integer value) {
            this.addCriterion("min_amount =", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotEqualTo(final Integer value) {
            this.addCriterion("min_amount <>", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountGreaterThan(final Integer value) {
            this.addCriterion("min_amount >", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("min_amount >=", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountLessThan(final Integer value) {
            this.addCriterion("min_amount <", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountLessThanOrEqualTo(final Integer value) {
            this.addCriterion("min_amount <=", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountIn(final List<Integer> values) {
            this.addCriterion("min_amount in", values, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotIn(final List<Integer> values) {
            this.addCriterion("min_amount not in", values, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountBetween(final Integer value1, final Integer value2) {
            this.addCriterion("min_amount between", value1, value2, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotBetween(final Integer value1, final Integer value2) {
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

        public Criteria andPurchaseLimitEqualTo(final Integer value) {
            this.addCriterion("purchase_limit =", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotEqualTo(final Integer value) {
            this.addCriterion("purchase_limit <>", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitGreaterThan(final Integer value) {
            this.addCriterion("purchase_limit >", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("purchase_limit >=", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitLessThan(final Integer value) {
            this.addCriterion("purchase_limit <", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitLessThanOrEqualTo(final Integer value) {
            this.addCriterion("purchase_limit <=", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitIn(final List<Integer> values) {
            this.addCriterion("purchase_limit in", values, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotIn(final List<Integer> values) {
            this.addCriterion("purchase_limit not in", values, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitBetween(final Integer value1, final Integer value2) {
            this.addCriterion("purchase_limit between", value1, value2, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotBetween(final Integer value1, final Integer value2) {
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

        public Criteria andCrtTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("crt_time =", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("crt_time <>", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("crt_time >", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("crt_time >=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThan(final LocalDateTime value) {
            this.addCriterion("crt_time <", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("crt_time <=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("crt_time in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("crt_time not in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("crt_time between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
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

        public Criteria andUptTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("upt_time =", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("upt_time <>", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("upt_time >", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("upt_time >=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThan(final LocalDateTime value) {
            this.addCriterion("upt_time <", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("upt_time <=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("upt_time in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("upt_time not in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("upt_time between", value1, value2, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("upt_time not between", value1, value2, "uptTime");
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

        public Criteria andAlsoLockUpAmountIsNull() {
            this.addCriterion("also_lock_up_amount is null");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountIsNotNull() {
            this.addCriterion("also_lock_up_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountEqualTo(final BigDecimal value) {
            this.addCriterion("also_lock_up_amount =", value, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("also_lock_up_amount <>", value, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("also_lock_up_amount >", value, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("also_lock_up_amount >=", value, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountLessThan(final BigDecimal value) {
            this.addCriterion("also_lock_up_amount <", value, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("also_lock_up_amount <=", value, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountIn(final List<BigDecimal> values) {
            this.addCriterion("also_lock_up_amount in", values, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("also_lock_up_amount not in", values, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("also_lock_up_amount between", value1, value2, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoLockUpAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("also_lock_up_amount not between", value1, value2, "alsoLockUpAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountIsNull() {
            this.addCriterion("also_temp_amount is null");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountIsNotNull() {
            this.addCriterion("also_temp_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountEqualTo(final BigDecimal value) {
            this.addCriterion("also_temp_amount =", value, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("also_temp_amount <>", value, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("also_temp_amount >", value, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("also_temp_amount >=", value, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountLessThan(final BigDecimal value) {
            this.addCriterion("also_temp_amount <", value, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("also_temp_amount <=", value, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountIn(final List<BigDecimal> values) {
            this.addCriterion("also_temp_amount in", values, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("also_temp_amount not in", values, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("also_temp_amount between", value1, value2, "alsoTempAmount");
            return (Criteria) this;
        }

        public Criteria andAlsoTempAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("also_temp_amount not between", value1, value2, "alsoTempAmount");
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