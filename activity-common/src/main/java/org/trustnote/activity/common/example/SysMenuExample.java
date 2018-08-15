package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.List;

public class SysMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysMenuExample() {
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

        public Criteria andMenuNameIsNull() {
            this.addCriterion("menu_name is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            this.addCriterion("menu_name is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(final String value) {
            this.addCriterion("menu_name =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(final String value) {
            this.addCriterion("menu_name <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(final String value) {
            this.addCriterion("menu_name >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(final String value) {
            this.addCriterion("menu_name >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(final String value) {
            this.addCriterion("menu_name <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(final String value) {
            this.addCriterion("menu_name <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(final String value) {
            this.addCriterion("menu_name like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(final String value) {
            this.addCriterion("menu_name not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(final List<String> values) {
            this.addCriterion("menu_name in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(final List<String> values) {
            this.addCriterion("menu_name not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(final String value1, final String value2) {
            this.addCriterion("menu_name between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(final String value1, final String value2) {
            this.addCriterion("menu_name not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuShortIsNull() {
            this.addCriterion("menu_short is null");
            return (Criteria) this;
        }

        public Criteria andMenuShortIsNotNull() {
            this.addCriterion("menu_short is not null");
            return (Criteria) this;
        }

        public Criteria andMenuShortEqualTo(final String value) {
            this.addCriterion("menu_short =", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortNotEqualTo(final String value) {
            this.addCriterion("menu_short <>", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortGreaterThan(final String value) {
            this.addCriterion("menu_short >", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortGreaterThanOrEqualTo(final String value) {
            this.addCriterion("menu_short >=", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortLessThan(final String value) {
            this.addCriterion("menu_short <", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortLessThanOrEqualTo(final String value) {
            this.addCriterion("menu_short <=", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortLike(final String value) {
            this.addCriterion("menu_short like", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortNotLike(final String value) {
            this.addCriterion("menu_short not like", value, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortIn(final List<String> values) {
            this.addCriterion("menu_short in", values, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortNotIn(final List<String> values) {
            this.addCriterion("menu_short not in", values, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortBetween(final String value1, final String value2) {
            this.addCriterion("menu_short between", value1, value2, "menuShort");
            return (Criteria) this;
        }

        public Criteria andMenuShortNotBetween(final String value1, final String value2) {
            this.addCriterion("menu_short not between", value1, value2, "menuShort");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            this.addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            this.addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(final Integer value) {
            this.addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(final Integer value) {
            this.addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(final Integer value) {
            this.addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(final Integer value) {
            this.addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(final Integer value) {
            this.addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(final Integer value) {
            this.addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(final List<Integer> values) {
            this.addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(final List<Integer> values) {
            this.addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(final Integer value1, final Integer value2) {
            this.addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(final Integer value1, final Integer value2) {
            this.addCriterion("pid not between", value1, value2, "pid");
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