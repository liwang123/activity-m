package org.trustnote.activity.common.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl
 */
public class FinancialLockUpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinancialLockUpExample() {
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

        public Criteria andSharedAddressIsNull() {
            this.addCriterion("shared_address is null");
            return (Criteria) this;
        }

        public Criteria andSharedAddressIsNotNull() {
            this.addCriterion("shared_address is not null");
            return (Criteria) this;
        }

        public Criteria andSharedAddressEqualTo(final String value) {
            this.addCriterion("shared_address =", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressNotEqualTo(final String value) {
            this.addCriterion("shared_address <>", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressGreaterThan(final String value) {
            this.addCriterion("shared_address >", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressGreaterThanOrEqualTo(final String value) {
            this.addCriterion("shared_address >=", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressLessThan(final String value) {
            this.addCriterion("shared_address <", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressLessThanOrEqualTo(final String value) {
            this.addCriterion("shared_address <=", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressLike(final String value) {
            this.addCriterion("shared_address like", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressNotLike(final String value) {
            this.addCriterion("shared_address not like", value, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressIn(final List<String> values) {
            this.addCriterion("shared_address in", values, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressNotIn(final List<String> values) {
            this.addCriterion("shared_address not in", values, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressBetween(final String value1, final String value2) {
            this.addCriterion("shared_address between", value1, value2, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andSharedAddressNotBetween(final String value1, final String value2) {
            this.addCriterion("shared_address not between", value1, value2, "sharedAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressIsNull() {
            this.addCriterion("device_address is null");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressIsNotNull() {
            this.addCriterion("device_address is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressEqualTo(final String value) {
            this.addCriterion("device_address =", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressNotEqualTo(final String value) {
            this.addCriterion("device_address <>", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressGreaterThan(final String value) {
            this.addCriterion("device_address >", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressGreaterThanOrEqualTo(final String value) {
            this.addCriterion("device_address >=", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressLessThan(final String value) {
            this.addCriterion("device_address <", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressLessThanOrEqualTo(final String value) {
            this.addCriterion("device_address <=", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressLike(final String value) {
            this.addCriterion("device_address like", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressNotLike(final String value) {
            this.addCriterion("device_address not like", value, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressIn(final List<String> values) {
            this.addCriterion("device_address in", values, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressNotIn(final List<String> values) {
            this.addCriterion("device_address not in", values, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressBetween(final String value1, final String value2) {
            this.addCriterion("device_address between", value1, value2, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andDeviceAddressNotBetween(final String value1, final String value2) {
            this.addCriterion("device_address not between", value1, value2, "deviceAddress");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdIsNull() {
            this.addCriterion("financial_benefits_id is null");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdIsNotNull() {
            this.addCriterion("financial_benefits_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdEqualTo(final Integer value) {
            this.addCriterion("financial_benefits_id =", value, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdNotEqualTo(final Integer value) {
            this.addCriterion("financial_benefits_id <>", value, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdGreaterThan(final Integer value) {
            this.addCriterion("financial_benefits_id >", value, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("financial_benefits_id >=", value, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdLessThan(final Integer value) {
            this.addCriterion("financial_benefits_id <", value, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdLessThanOrEqualTo(final Integer value) {
            this.addCriterion("financial_benefits_id <=", value, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdIn(final List<Integer> values) {
            this.addCriterion("financial_benefits_id in", values, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdNotIn(final List<Integer> values) {
            this.addCriterion("financial_benefits_id not in", values, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdBetween(final Integer value1, final Integer value2) {
            this.addCriterion("financial_benefits_id between", value1, value2, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andFinancialBenefitsIdNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("financial_benefits_id not between", value1, value2, "financialBenefitsId");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountIsNull() {
            this.addCriterion("lock_up_amount is null");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountIsNotNull() {
            this.addCriterion("lock_up_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountEqualTo(final BigDecimal value) {
            this.addCriterion("lock_up_amount =", value, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("lock_up_amount <>", value, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("lock_up_amount >", value, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("lock_up_amount >=", value, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountLessThan(final BigDecimal value) {
            this.addCriterion("lock_up_amount <", value, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("lock_up_amount <=", value, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountIn(final List<BigDecimal> values) {
            this.addCriterion("lock_up_amount in", values, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("lock_up_amount not in", values, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("lock_up_amount between", value1, value2, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andLockUpAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("lock_up_amount not between", value1, value2, "lockUpAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIsNull() {
            this.addCriterion("income_amount is null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIsNotNull() {
            this.addCriterion("income_amount is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountEqualTo(final BigDecimal value) {
            this.addCriterion("income_amount =", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("income_amount <>", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("income_amount >", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("income_amount >=", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountLessThan(final BigDecimal value) {
            this.addCriterion("income_amount <", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("income_amount <=", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIn(final List<BigDecimal> values) {
            this.addCriterion("income_amount in", values, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("income_amount not in", values, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("income_amount between", value1, value2, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("income_amount not between", value1, value2, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIsNull() {
            this.addCriterion("operation_time is null");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIsNotNull() {
            this.addCriterion("operation_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperationTimeEqualTo(final LocalDateTime value) {
            this.addCriterion("operation_time =", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotEqualTo(final LocalDateTime value) {
            this.addCriterion("operation_time <>", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeGreaterThan(final LocalDateTime value) {
            this.addCriterion("operation_time >", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeGreaterThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("operation_time >=", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeLessThan(final LocalDateTime value) {
            this.addCriterion("operation_time <", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeLessThanOrEqualTo(final LocalDateTime value) {
            this.addCriterion("operation_time <=", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIn(final List<LocalDateTime> values) {
            this.addCriterion("operation_time in", values, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotIn(final List<LocalDateTime> values) {
            this.addCriterion("operation_time not in", values, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("operation_time between", value1, value2, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotBetween(final LocalDateTime value1, final LocalDateTime value2) {
            this.addCriterion("operation_time not between", value1, value2, "operationTime");
            return (Criteria) this;
        }

        public Criteria andTempAmountIsNull() {
            this.addCriterion("temp_amount is null");
            return (Criteria) this;
        }

        public Criteria andTempAmountIsNotNull() {
            this.addCriterion("temp_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTempAmountEqualTo(final BigDecimal value) {
            this.addCriterion("temp_amount =", value, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("temp_amount <>", value, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("temp_amount >", value, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("temp_amount >=", value, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountLessThan(final BigDecimal value) {
            this.addCriterion("temp_amount <", value, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("temp_amount <=", value, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountIn(final List<BigDecimal> values) {
            this.addCriterion("temp_amount in", values, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("temp_amount not in", values, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("temp_amount between", value1, value2, "TempAmount");
            return (Criteria) this;
        }

        public Criteria andTempAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("temp_amount not between", value1, value2, "TempAmount");
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