package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("startTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("startTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("startTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("startTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("startTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("startTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("startTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("startTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("startTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("startTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andInviterNumIsNull() {
            addCriterion("Inviter_num is null");
            return (Criteria) this;
        }

        public Criteria andInviterNumIsNotNull() {
            addCriterion("Inviter_num is not null");
            return (Criteria) this;
        }

        public Criteria andInviterNumEqualTo(Integer value) {
            addCriterion("Inviter_num =", value, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumNotEqualTo(Integer value) {
            addCriterion("Inviter_num <>", value, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumGreaterThan(Integer value) {
            addCriterion("Inviter_num >", value, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("Inviter_num >=", value, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumLessThan(Integer value) {
            addCriterion("Inviter_num <", value, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumLessThanOrEqualTo(Integer value) {
            addCriterion("Inviter_num <=", value, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumIn(List<Integer> values) {
            addCriterion("Inviter_num in", values, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumNotIn(List<Integer> values) {
            addCriterion("Inviter_num not in", values, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumBetween(Integer value1, Integer value2) {
            addCriterion("Inviter_num between", value1, value2, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviterNumNotBetween(Integer value1, Integer value2) {
            addCriterion("Inviter_num not between", value1, value2, "inviterNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumIsNull() {
            addCriterion("invitee_num is null");
            return (Criteria) this;
        }

        public Criteria andInviteeNumIsNotNull() {
            addCriterion("invitee_num is not null");
            return (Criteria) this;
        }

        public Criteria andInviteeNumEqualTo(Integer value) {
            addCriterion("invitee_num =", value, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumNotEqualTo(Integer value) {
            addCriterion("invitee_num <>", value, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumGreaterThan(Integer value) {
            addCriterion("invitee_num >", value, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("invitee_num >=", value, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumLessThan(Integer value) {
            addCriterion("invitee_num <", value, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumLessThanOrEqualTo(Integer value) {
            addCriterion("invitee_num <=", value, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumIn(List<Integer> values) {
            addCriterion("invitee_num in", values, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumNotIn(List<Integer> values) {
            addCriterion("invitee_num not in", values, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumBetween(Integer value1, Integer value2) {
            addCriterion("invitee_num between", value1, value2, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andInviteeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("invitee_num not between", value1, value2, "inviteeNum");
            return (Criteria) this;
        }

        public Criteria andEnlinkIsNull() {
            addCriterion("enLink is null");
            return (Criteria) this;
        }

        public Criteria andEnlinkIsNotNull() {
            addCriterion("enLink is not null");
            return (Criteria) this;
        }

        public Criteria andEnlinkEqualTo(String value) {
            addCriterion("enLink =", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkNotEqualTo(String value) {
            addCriterion("enLink <>", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkGreaterThan(String value) {
            addCriterion("enLink >", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkGreaterThanOrEqualTo(String value) {
            addCriterion("enLink >=", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkLessThan(String value) {
            addCriterion("enLink <", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkLessThanOrEqualTo(String value) {
            addCriterion("enLink <=", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkLike(String value) {
            addCriterion("enLink like", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkNotLike(String value) {
            addCriterion("enLink not like", value, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkIn(List<String> values) {
            addCriterion("enLink in", values, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkNotIn(List<String> values) {
            addCriterion("enLink not in", values, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkBetween(String value1, String value2) {
            addCriterion("enLink between", value1, value2, "enlink");
            return (Criteria) this;
        }

        public Criteria andEnlinkNotBetween(String value1, String value2) {
            addCriterion("enLink not between", value1, value2, "enlink");
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