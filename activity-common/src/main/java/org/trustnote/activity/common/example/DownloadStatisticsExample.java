package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.List;

public class DownloadStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DownloadStatisticsExample() {
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

        public Criteria andAndroidSumIsNull() {
            addCriterion("android_sum is null");
            return (Criteria) this;
        }

        public Criteria andAndroidSumIsNotNull() {
            addCriterion("android_sum is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidSumEqualTo(Integer value) {
            addCriterion("android_sum =", value, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumNotEqualTo(Integer value) {
            addCriterion("android_sum <>", value, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumGreaterThan(Integer value) {
            addCriterion("android_sum >", value, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("android_sum >=", value, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumLessThan(Integer value) {
            addCriterion("android_sum <", value, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumLessThanOrEqualTo(Integer value) {
            addCriterion("android_sum <=", value, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumIn(List<Integer> values) {
            addCriterion("android_sum in", values, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumNotIn(List<Integer> values) {
            addCriterion("android_sum not in", values, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumBetween(Integer value1, Integer value2) {
            addCriterion("android_sum between", value1, value2, "androidSum");
            return (Criteria) this;
        }

        public Criteria andAndroidSumNotBetween(Integer value1, Integer value2) {
            addCriterion("android_sum not between", value1, value2, "androidSum");
            return (Criteria) this;
        }

        public Criteria andIosSumIsNull() {
            addCriterion("ios_sum is null");
            return (Criteria) this;
        }

        public Criteria andIosSumIsNotNull() {
            addCriterion("ios_sum is not null");
            return (Criteria) this;
        }

        public Criteria andIosSumEqualTo(Integer value) {
            addCriterion("ios_sum =", value, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumNotEqualTo(Integer value) {
            addCriterion("ios_sum <>", value, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumGreaterThan(Integer value) {
            addCriterion("ios_sum >", value, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ios_sum >=", value, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumLessThan(Integer value) {
            addCriterion("ios_sum <", value, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumLessThanOrEqualTo(Integer value) {
            addCriterion("ios_sum <=", value, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumIn(List<Integer> values) {
            addCriterion("ios_sum in", values, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumNotIn(List<Integer> values) {
            addCriterion("ios_sum not in", values, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumBetween(Integer value1, Integer value2) {
            addCriterion("ios_sum between", value1, value2, "iosSum");
            return (Criteria) this;
        }

        public Criteria andIosSumNotBetween(Integer value1, Integer value2) {
            addCriterion("ios_sum not between", value1, value2, "iosSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumIsNull() {
            addCriterion("windows_sum is null");
            return (Criteria) this;
        }

        public Criteria andWindowsSumIsNotNull() {
            addCriterion("windows_sum is not null");
            return (Criteria) this;
        }

        public Criteria andWindowsSumEqualTo(Integer value) {
            addCriterion("windows_sum =", value, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumNotEqualTo(Integer value) {
            addCriterion("windows_sum <>", value, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumGreaterThan(Integer value) {
            addCriterion("windows_sum >", value, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("windows_sum >=", value, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumLessThan(Integer value) {
            addCriterion("windows_sum <", value, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumLessThanOrEqualTo(Integer value) {
            addCriterion("windows_sum <=", value, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumIn(List<Integer> values) {
            addCriterion("windows_sum in", values, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumNotIn(List<Integer> values) {
            addCriterion("windows_sum not in", values, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumBetween(Integer value1, Integer value2) {
            addCriterion("windows_sum between", value1, value2, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andWindowsSumNotBetween(Integer value1, Integer value2) {
            addCriterion("windows_sum not between", value1, value2, "windowsSum");
            return (Criteria) this;
        }

        public Criteria andMacSumIsNull() {
            addCriterion("mac_sum is null");
            return (Criteria) this;
        }

        public Criteria andMacSumIsNotNull() {
            addCriterion("mac_sum is not null");
            return (Criteria) this;
        }

        public Criteria andMacSumEqualTo(Integer value) {
            addCriterion("mac_sum =", value, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumNotEqualTo(Integer value) {
            addCriterion("mac_sum <>", value, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumGreaterThan(Integer value) {
            addCriterion("mac_sum >", value, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("mac_sum >=", value, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumLessThan(Integer value) {
            addCriterion("mac_sum <", value, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumLessThanOrEqualTo(Integer value) {
            addCriterion("mac_sum <=", value, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumIn(List<Integer> values) {
            addCriterion("mac_sum in", values, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumNotIn(List<Integer> values) {
            addCriterion("mac_sum not in", values, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumBetween(Integer value1, Integer value2) {
            addCriterion("mac_sum between", value1, value2, "macSum");
            return (Criteria) this;
        }

        public Criteria andMacSumNotBetween(Integer value1, Integer value2) {
            addCriterion("mac_sum not between", value1, value2, "macSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumIsNull() {
            addCriterion("linux_sum is null");
            return (Criteria) this;
        }

        public Criteria andLinuxSumIsNotNull() {
            addCriterion("linux_sum is not null");
            return (Criteria) this;
        }

        public Criteria andLinuxSumEqualTo(Integer value) {
            addCriterion("linux_sum =", value, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumNotEqualTo(Integer value) {
            addCriterion("linux_sum <>", value, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumGreaterThan(Integer value) {
            addCriterion("linux_sum >", value, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("linux_sum >=", value, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumLessThan(Integer value) {
            addCriterion("linux_sum <", value, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumLessThanOrEqualTo(Integer value) {
            addCriterion("linux_sum <=", value, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumIn(List<Integer> values) {
            addCriterion("linux_sum in", values, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumNotIn(List<Integer> values) {
            addCriterion("linux_sum not in", values, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumBetween(Integer value1, Integer value2) {
            addCriterion("linux_sum between", value1, value2, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andLinuxSumNotBetween(Integer value1, Integer value2) {
            addCriterion("linux_sum not between", value1, value2, "linuxSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumIsNull() {
            addCriterion("github_sum is null");
            return (Criteria) this;
        }

        public Criteria andGithubSumIsNotNull() {
            addCriterion("github_sum is not null");
            return (Criteria) this;
        }

        public Criteria andGithubSumEqualTo(Integer value) {
            addCriterion("github_sum =", value, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumNotEqualTo(Integer value) {
            addCriterion("github_sum <>", value, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumGreaterThan(Integer value) {
            addCriterion("github_sum >", value, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("github_sum >=", value, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumLessThan(Integer value) {
            addCriterion("github_sum <", value, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumLessThanOrEqualTo(Integer value) {
            addCriterion("github_sum <=", value, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumIn(List<Integer> values) {
            addCriterion("github_sum in", values, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumNotIn(List<Integer> values) {
            addCriterion("github_sum not in", values, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumBetween(Integer value1, Integer value2) {
            addCriterion("github_sum between", value1, value2, "githubSum");
            return (Criteria) this;
        }

        public Criteria andGithubSumNotBetween(Integer value1, Integer value2) {
            addCriterion("github_sum not between", value1, value2, "githubSum");
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