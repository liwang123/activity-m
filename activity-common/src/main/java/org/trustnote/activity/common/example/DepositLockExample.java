package org.trustnote.activity.common.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepositLockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepositLockExample() {
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

        public Criteria andWalletAddressIsNull() {
            this.addCriterion("wallet_address is null");
            return (Criteria) this;
        }

        public Criteria andWalletAddressIsNotNull() {
            this.addCriterion("wallet_address is not null");
            return (Criteria) this;
        }

        public Criteria andWalletAddressEqualTo(final String value) {
            this.addCriterion("wallet_address =", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressNotEqualTo(final String value) {
            this.addCriterion("wallet_address <>", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressGreaterThan(final String value) {
            this.addCriterion("wallet_address >", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressGreaterThanOrEqualTo(final String value) {
            this.addCriterion("wallet_address >=", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressLessThan(final String value) {
            this.addCriterion("wallet_address <", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressLessThanOrEqualTo(final String value) {
            this.addCriterion("wallet_address <=", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressLike(final String value) {
            this.addCriterion("wallet_address like", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressNotLike(final String value) {
            this.addCriterion("wallet_address not like", value, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressIn(final List<String> values) {
            this.addCriterion("wallet_address in", values, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressNotIn(final List<String> values) {
            this.addCriterion("wallet_address not in", values, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressBetween(final String value1, final String value2) {
            this.addCriterion("wallet_address between", value1, value2, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andWalletAddressNotBetween(final String value1, final String value2) {
            this.addCriterion("wallet_address not between", value1, value2, "walletAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressIsNull() {
            this.addCriterion("receipt_address is null");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressIsNotNull() {
            this.addCriterion("receipt_address is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressEqualTo(final String value) {
            this.addCriterion("receipt_address =", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressNotEqualTo(final String value) {
            this.addCriterion("receipt_address <>", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressGreaterThan(final String value) {
            this.addCriterion("receipt_address >", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressGreaterThanOrEqualTo(final String value) {
            this.addCriterion("receipt_address >=", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressLessThan(final String value) {
            this.addCriterion("receipt_address <", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressLessThanOrEqualTo(final String value) {
            this.addCriterion("receipt_address <=", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressLike(final String value) {
            this.addCriterion("receipt_address like", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressNotLike(final String value) {
            this.addCriterion("receipt_address not like", value, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressIn(final List<String> values) {
            this.addCriterion("receipt_address in", values, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressNotIn(final List<String> values) {
            this.addCriterion("receipt_address not in", values, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressBetween(final String value1, final String value2) {
            this.addCriterion("receipt_address between", value1, value2, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptAddressNotBetween(final String value1, final String value2) {
            this.addCriterion("receipt_address not between", value1, value2, "receiptAddress");
            return (Criteria) this;
        }

        public Criteria andLockAmountIsNull() {
            this.addCriterion("lock_amount is null");
            return (Criteria) this;
        }

        public Criteria andLockAmountIsNotNull() {
            this.addCriterion("lock_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLockAmountEqualTo(final BigDecimal value) {
            this.addCriterion("lock_amount =", value, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("lock_amount <>", value, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("lock_amount >", value, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("lock_amount >=", value, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountLessThan(final BigDecimal value) {
            this.addCriterion("lock_amount <", value, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("lock_amount <=", value, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountIn(final List<BigDecimal> values) {
            this.addCriterion("lock_amount in", values, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("lock_amount not in", values, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("lock_amount between", value1, value2, "lockAmount");
            return (Criteria) this;
        }

        public Criteria andLockAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("lock_amount not between", value1, value2, "lockAmount");
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

        public Criteria andReturnAmountIsNull() {
            this.addCriterion("return_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIsNotNull() {
            this.addCriterion("return_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountEqualTo(final BigDecimal value) {
            this.addCriterion("return_amount =", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("return_amount <>", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("return_amount >", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("return_amount >=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThan(final BigDecimal value) {
            this.addCriterion("return_amount <", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("return_amount <=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIn(final List<BigDecimal> values) {
            this.addCriterion("return_amount in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("return_amount not in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("return_amount between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("return_amount not between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountIsNull() {
            this.addCriterion("tfs_amount is null");
            return (Criteria) this;
        }

        public Criteria andTfsAmountIsNotNull() {
            this.addCriterion("tfs_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTfsAmountEqualTo(final BigDecimal value) {
            this.addCriterion("tfs_amount =", value, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountNotEqualTo(final BigDecimal value) {
            this.addCriterion("tfs_amount <>", value, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountGreaterThan(final BigDecimal value) {
            this.addCriterion("tfs_amount >", value, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountGreaterThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("tfs_amount >=", value, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountLessThan(final BigDecimal value) {
            this.addCriterion("tfs_amount <", value, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountLessThanOrEqualTo(final BigDecimal value) {
            this.addCriterion("tfs_amount <=", value, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountIn(final List<BigDecimal> values) {
            this.addCriterion("tfs_amount in", values, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountNotIn(final List<BigDecimal> values) {
            this.addCriterion("tfs_amount not in", values, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("tfs_amount between", value1, value2, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andTfsAmountNotBetween(final BigDecimal value1, final BigDecimal value2) {
            this.addCriterion("tfs_amount not between", value1, value2, "tfsAmount");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIsNull() {
            this.addCriterion("package_type is null");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIsNotNull() {
            this.addCriterion("package_type is not null");
            return (Criteria) this;
        }

        public Criteria andPackageTypeEqualTo(final Integer value) {
            this.addCriterion("package_type =", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotEqualTo(final Integer value) {
            this.addCriterion("package_type <>", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeGreaterThan(final Integer value) {
            this.addCriterion("package_type >", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("package_type >=", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLessThan(final Integer value) {
            this.addCriterion("package_type <", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLessThanOrEqualTo(final Integer value) {
            this.addCriterion("package_type <=", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIn(final List<Integer> values) {
            this.addCriterion("package_type in", values, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotIn(final List<Integer> values) {
            this.addCriterion("package_type not in", values, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeBetween(final Integer value1, final Integer value2) {
            this.addCriterion("package_type between", value1, value2, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("package_type not between", value1, value2, "packageType");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNull() {
            this.addCriterion("lock_status is null");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNotNull() {
            this.addCriterion("lock_status is not null");
            return (Criteria) this;
        }

        public Criteria andLockStatusEqualTo(final Integer value) {
            this.addCriterion("lock_status =", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotEqualTo(final Integer value) {
            this.addCriterion("lock_status <>", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThan(final Integer value) {
            this.addCriterion("lock_status >", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("lock_status >=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThan(final Integer value) {
            this.addCriterion("lock_status <", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThanOrEqualTo(final Integer value) {
            this.addCriterion("lock_status <=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusIn(final List<Integer> values) {
            this.addCriterion("lock_status in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotIn(final List<Integer> values) {
            this.addCriterion("lock_status not in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusBetween(final Integer value1, final Integer value2) {
            this.addCriterion("lock_status between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("lock_status not between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNull() {
            this.addCriterion("lock_time is null");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNotNull() {
            this.addCriterion("lock_time is not null");
            return (Criteria) this;
        }

        public Criteria andLockTimeEqualTo(final Date value) {
            this.addCriterion("lock_time =", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotEqualTo(final Date value) {
            this.addCriterion("lock_time <>", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThan(final Date value) {
            this.addCriterion("lock_time >", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("lock_time >=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThan(final Date value) {
            this.addCriterion("lock_time <", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("lock_time <=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeIn(final List<Date> values) {
            this.addCriterion("lock_time in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotIn(final List<Date> values) {
            this.addCriterion("lock_time not in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("lock_time between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("lock_time not between", value1, value2, "lockTime");
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

        public Criteria andCreateTimeIsNull() {
            this.addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            this.addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(final Date value) {
            this.addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(final Date value) {
            this.addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(final Date value) {
            this.addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(final Date value) {
            this.addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(final List<Date> values) {
            this.addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(final List<Date> values) {
            this.addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            this.addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            this.addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(final Date value) {
            this.addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(final Date value) {
            this.addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(final Date value) {
            this.addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(final Date value) {
            this.addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(final List<Date> values) {
            this.addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(final List<Date> values) {
            this.addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("update_time not between", value1, value2, "updateTime");
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