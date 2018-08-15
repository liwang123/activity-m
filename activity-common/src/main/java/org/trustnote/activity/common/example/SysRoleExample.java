package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysRoleExample() {
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

        public Criteria andRoleNameIsNull() {
            this.addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            this.addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(final String value) {
            this.addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(final String value) {
            this.addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(final String value) {
            this.addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(final String value) {
            this.addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(final String value) {
            this.addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(final String value) {
            this.addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(final String value) {
            this.addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(final String value) {
            this.addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(final List<String> values) {
            this.addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(final List<String> values) {
            this.addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(final String value1, final String value2) {
            this.addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(final String value1, final String value2) {
            this.addCriterion("role_name not between", value1, value2, "roleName");
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

        public Criteria andRoleDescIsNull() {
            this.addCriterion("role_desc is null");
            return (Criteria) this;
        }

        public Criteria andRoleDescIsNotNull() {
            this.addCriterion("role_desc is not null");
            return (Criteria) this;
        }

        public Criteria andRoleDescEqualTo(final String value) {
            this.addCriterion("role_desc =", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotEqualTo(final String value) {
            this.addCriterion("role_desc <>", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescGreaterThan(final String value) {
            this.addCriterion("role_desc >", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescGreaterThanOrEqualTo(final String value) {
            this.addCriterion("role_desc >=", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescLessThan(final String value) {
            this.addCriterion("role_desc <", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescLessThanOrEqualTo(final String value) {
            this.addCriterion("role_desc <=", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescLike(final String value) {
            this.addCriterion("role_desc like", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotLike(final String value) {
            this.addCriterion("role_desc not like", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescIn(final List<String> values) {
            this.addCriterion("role_desc in", values, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotIn(final List<String> values) {
            this.addCriterion("role_desc not in", values, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescBetween(final String value1, final String value2) {
            this.addCriterion("role_desc between", value1, value2, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotBetween(final String value1, final String value2) {
            this.addCriterion("role_desc not between", value1, value2, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andCrtIdIsNull() {
            this.addCriterion("crt_id is null");
            return (Criteria) this;
        }

        public Criteria andCrtIdIsNotNull() {
            this.addCriterion("crt_id is not null");
            return (Criteria) this;
        }

        public Criteria andCrtIdEqualTo(final Integer value) {
            this.addCriterion("crt_id =", value, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdNotEqualTo(final Integer value) {
            this.addCriterion("crt_id <>", value, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdGreaterThan(final Integer value) {
            this.addCriterion("crt_id >", value, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("crt_id >=", value, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdLessThan(final Integer value) {
            this.addCriterion("crt_id <", value, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdLessThanOrEqualTo(final Integer value) {
            this.addCriterion("crt_id <=", value, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdIn(final List<Integer> values) {
            this.addCriterion("crt_id in", values, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdNotIn(final List<Integer> values) {
            this.addCriterion("crt_id not in", values, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdBetween(final Integer value1, final Integer value2) {
            this.addCriterion("crt_id between", value1, value2, "crtId");
            return (Criteria) this;
        }

        public Criteria andCrtIdNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("crt_id not between", value1, value2, "crtId");
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

        public Criteria andUptIdIsNull() {
            this.addCriterion("upt_id is null");
            return (Criteria) this;
        }

        public Criteria andUptIdIsNotNull() {
            this.addCriterion("upt_id is not null");
            return (Criteria) this;
        }

        public Criteria andUptIdEqualTo(final Integer value) {
            this.addCriterion("upt_id =", value, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdNotEqualTo(final Integer value) {
            this.addCriterion("upt_id <>", value, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdGreaterThan(final Integer value) {
            this.addCriterion("upt_id >", value, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("upt_id >=", value, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdLessThan(final Integer value) {
            this.addCriterion("upt_id <", value, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdLessThanOrEqualTo(final Integer value) {
            this.addCriterion("upt_id <=", value, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdIn(final List<Integer> values) {
            this.addCriterion("upt_id in", values, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdNotIn(final List<Integer> values) {
            this.addCriterion("upt_id not in", values, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdBetween(final Integer value1, final Integer value2) {
            this.addCriterion("upt_id between", value1, value2, "uptId");
            return (Criteria) this;
        }

        public Criteria andUptIdNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("upt_id not between", value1, value2, "uptId");
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