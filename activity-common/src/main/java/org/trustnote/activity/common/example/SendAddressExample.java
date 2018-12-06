package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendAddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SendAddressExample() {
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

        public Criteria andAddressIsNull() {
            this.addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            this.addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(final String value) {
            this.addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(final String value) {
            this.addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(final String value) {
            this.addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(final String value) {
            this.addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(final String value) {
            this.addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(final String value) {
            this.addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(final String value) {
            this.addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(final String value) {
            this.addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(final List<String> values) {
            this.addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(final List<String> values) {
            this.addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(final String value1, final String value2) {
            this.addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(final String value1, final String value2) {
            this.addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            this.addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            this.addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(final Integer value) {
            this.addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(final Integer value) {
            this.addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(final Integer value) {
            this.addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(final Integer value) {
            this.addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(final Integer value) {
            this.addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(final List<Integer> values) {
            this.addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(final List<Integer> values) {
            this.addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(final Integer value1, final Integer value2) {
            this.addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCountNumberIsNull() {
            this.addCriterion("count_number is null");
            return (Criteria) this;
        }

        public Criteria andCountNumberIsNotNull() {
            this.addCriterion("count_number is not null");
            return (Criteria) this;
        }

        public Criteria andCountNumberEqualTo(final Integer value) {
            this.addCriterion("count_number =", value, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberNotEqualTo(final Integer value) {
            this.addCriterion("count_number <>", value, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberGreaterThan(final Integer value) {
            this.addCriterion("count_number >", value, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("count_number >=", value, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberLessThan(final Integer value) {
            this.addCriterion("count_number <", value, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberLessThanOrEqualTo(final Integer value) {
            this.addCriterion("count_number <=", value, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberIn(final List<Integer> values) {
            this.addCriterion("count_number in", values, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberNotIn(final List<Integer> values) {
            this.addCriterion("count_number not in", values, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberBetween(final Integer value1, final Integer value2) {
            this.addCriterion("count_number between", value1, value2, "countNumber");
            return (Criteria) this;
        }

        public Criteria andCountNumberNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("count_number not between", value1, value2, "countNumber");
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