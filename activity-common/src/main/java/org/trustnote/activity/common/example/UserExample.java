package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhuxl
 */
public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andRealnameIsNull() {
            this.addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            this.addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(final String value) {
            this.addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(final String value) {
            this.addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(final String value) {
            this.addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(final String value) {
            this.addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(final String value) {
            this.addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(final String value) {
            this.addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(final String value) {
            this.addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(final String value) {
            this.addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(final List<String> values) {
            this.addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(final List<String> values) {
            this.addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(final String value1, final String value2) {
            this.addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(final String value1, final String value2) {
            this.addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            this.addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            this.addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(final String value) {
            this.addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(final String value) {
            this.addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(final String value) {
            this.addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(final String value) {
            this.addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(final String value) {
            this.addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(final String value) {
            this.addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(final String value) {
            this.addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(final String value) {
            this.addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(final List<String> values) {
            this.addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(final List<String> values) {
            this.addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(final String value1, final String value2) {
            this.addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(final String value1, final String value2) {
            this.addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            this.addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            this.addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(final String value) {
            this.addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(final String value) {
            this.addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(final String value) {
            this.addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(final String value) {
            this.addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(final String value) {
            this.addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(final String value) {
            this.addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(final String value) {
            this.addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(final String value) {
            this.addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(final List<String> values) {
            this.addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(final List<String> values) {
            this.addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(final String value1, final String value2) {
            this.addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(final String value1, final String value2) {
            this.addCriterion("phone not between", value1, value2, "phone");
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

        public Criteria andStateIsNull() {
            this.addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            this.addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(final Integer value) {
            this.addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(final Integer value) {
            this.addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(final Integer value) {
            this.addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(final Integer value) {
            this.addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(final Integer value) {
            this.addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(final List<Integer> values) {
            this.addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(final List<Integer> values) {
            this.addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(final Integer value1, final Integer value2) {
            this.addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUserDescIsNull() {
            this.addCriterion("user_desc is null");
            return (Criteria) this;
        }

        public Criteria andUserDescIsNotNull() {
            this.addCriterion("user_desc is not null");
            return (Criteria) this;
        }

        public Criteria andUserDescEqualTo(final String value) {
            this.addCriterion("user_desc =", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescNotEqualTo(final String value) {
            this.addCriterion("user_desc <>", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescGreaterThan(final String value) {
            this.addCriterion("user_desc >", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescGreaterThanOrEqualTo(final String value) {
            this.addCriterion("user_desc >=", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescLessThan(final String value) {
            this.addCriterion("user_desc <", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescLessThanOrEqualTo(final String value) {
            this.addCriterion("user_desc <=", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescLike(final String value) {
            this.addCriterion("user_desc like", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescNotLike(final String value) {
            this.addCriterion("user_desc not like", value, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescIn(final List<String> values) {
            this.addCriterion("user_desc in", values, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescNotIn(final List<String> values) {
            this.addCriterion("user_desc not in", values, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescBetween(final String value1, final String value2) {
            this.addCriterion("user_desc between", value1, value2, "userDesc");
            return (Criteria) this;
        }

        public Criteria andUserDescNotBetween(final String value1, final String value2) {
            this.addCriterion("user_desc not between", value1, value2, "userDesc");
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

        public Criteria andLastLoginTimeIsNull() {
            this.addCriterion("last_login_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            this.addCriterion("last_login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(final Date value) {
            this.addCriterion("last_login_time =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(final Date value) {
            this.addCriterion("last_login_time <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(final Date value) {
            this.addCriterion("last_login_time >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(final Date value) {
            this.addCriterion("last_login_time >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(final Date value) {
            this.addCriterion("last_login_time <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(final Date value) {
            this.addCriterion("last_login_time <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(final List<Date> values) {
            this.addCriterion("last_login_time in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(final List<Date> values) {
            this.addCriterion("last_login_time not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(final Date value1, final Date value2) {
            this.addCriterion("last_login_time between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(final Date value1, final Date value2) {
            this.addCriterion("last_login_time not between", value1, value2, "lastLoginTime");
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