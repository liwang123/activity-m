package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransactionRecordExample() {
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

        public Criteria andTrustnoteAddressIsNull() {
            addCriterion("trustnote_address is null");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressIsNotNull() {
            addCriterion("trustnote_address is not null");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressEqualTo(String value) {
            addCriterion("trustnote_address =", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressNotEqualTo(String value) {
            addCriterion("trustnote_address <>", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressGreaterThan(String value) {
            addCriterion("trustnote_address >", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressGreaterThanOrEqualTo(String value) {
            addCriterion("trustnote_address >=", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressLessThan(String value) {
            addCriterion("trustnote_address <", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressLessThanOrEqualTo(String value) {
            addCriterion("trustnote_address <=", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressLike(String value) {
            addCriterion("trustnote_address like", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressNotLike(String value) {
            addCriterion("trustnote_address not like", value, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressIn(List<String> values) {
            addCriterion("trustnote_address in", values, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressNotIn(List<String> values) {
            addCriterion("trustnote_address not in", values, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressBetween(String value1, String value2) {
            addCriterion("trustnote_address between", value1, value2, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andTrustnoteAddressNotBetween(String value1, String value2) {
            addCriterion("trustnote_address not between", value1, value2, "trustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andBugCountIsNull() {
            addCriterion("bug_count is null");
            return (Criteria) this;
        }

        public Criteria andBugCountIsNotNull() {
            addCriterion("bug_count is not null");
            return (Criteria) this;
        }

        public Criteria andBugCountEqualTo(Integer value) {
            addCriterion("bug_count =", value, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountNotEqualTo(Integer value) {
            addCriterion("bug_count <>", value, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountGreaterThan(Integer value) {
            addCriterion("bug_count >", value, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bug_count >=", value, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountLessThan(Integer value) {
            addCriterion("bug_count <", value, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountLessThanOrEqualTo(Integer value) {
            addCriterion("bug_count <=", value, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountIn(List<Integer> values) {
            addCriterion("bug_count in", values, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountNotIn(List<Integer> values) {
            addCriterion("bug_count not in", values, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountBetween(Integer value1, Integer value2) {
            addCriterion("bug_count between", value1, value2, "bugCount");
            return (Criteria) this;
        }

        public Criteria andBugCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bug_count not between", value1, value2, "bugCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountIsNull() {
            addCriterion("handsel_count is null");
            return (Criteria) this;
        }

        public Criteria andHandselCountIsNotNull() {
            addCriterion("handsel_count is not null");
            return (Criteria) this;
        }

        public Criteria andHandselCountEqualTo(Integer value) {
            addCriterion("handsel_count =", value, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountNotEqualTo(Integer value) {
            addCriterion("handsel_count <>", value, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountGreaterThan(Integer value) {
            addCriterion("handsel_count >", value, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("handsel_count >=", value, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountLessThan(Integer value) {
            addCriterion("handsel_count <", value, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountLessThanOrEqualTo(Integer value) {
            addCriterion("handsel_count <=", value, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountIn(List<Integer> values) {
            addCriterion("handsel_count in", values, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountNotIn(List<Integer> values) {
            addCriterion("handsel_count not in", values, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountBetween(Integer value1, Integer value2) {
            addCriterion("handsel_count between", value1, value2, "handselCount");
            return (Criteria) this;
        }

        public Criteria andHandselCountNotBetween(Integer value1, Integer value2) {
            addCriterion("handsel_count not between", value1, value2, "handselCount");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressIsNull() {
            addCriterion("invite_trustnote_address is null");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressIsNotNull() {
            addCriterion("invite_trustnote_address is not null");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressEqualTo(String value) {
            addCriterion("invite_trustnote_address =", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressNotEqualTo(String value) {
            addCriterion("invite_trustnote_address <>", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressGreaterThan(String value) {
            addCriterion("invite_trustnote_address >", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressGreaterThanOrEqualTo(String value) {
            addCriterion("invite_trustnote_address >=", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressLessThan(String value) {
            addCriterion("invite_trustnote_address <", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressLessThanOrEqualTo(String value) {
            addCriterion("invite_trustnote_address <=", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressLike(String value) {
            addCriterion("invite_trustnote_address like", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressNotLike(String value) {
            addCriterion("invite_trustnote_address not like", value, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressIn(List<String> values) {
            addCriterion("invite_trustnote_address in", values, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressNotIn(List<String> values) {
            addCriterion("invite_trustnote_address not in", values, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressBetween(String value1, String value2) {
            addCriterion("invite_trustnote_address between", value1, value2, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteTrustnoteAddressNotBetween(String value1, String value2) {
            addCriterion("invite_trustnote_address not between", value1, value2, "inviteTrustnoteAddress");
            return (Criteria) this;
        }

        public Criteria andInviteCode(String value) {
            addCriterion("invite_code = ", value, "inviteCode");
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