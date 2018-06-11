package org.trustnote.activity.common.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MediaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MediaExample() {
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

        public Criteria andCnTitleIsNull() {
            addCriterion("cn_title is null");
            return (Criteria) this;
        }

        public Criteria andCnTitleIsNotNull() {
            addCriterion("cn_title is not null");
            return (Criteria) this;
        }

        public Criteria andCnTitleEqualTo(String value) {
            addCriterion("cn_title =", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleNotEqualTo(String value) {
            addCriterion("cn_title <>", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleGreaterThan(String value) {
            addCriterion("cn_title >", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleGreaterThanOrEqualTo(String value) {
            addCriterion("cn_title >=", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleLessThan(String value) {
            addCriterion("cn_title <", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleLessThanOrEqualTo(String value) {
            addCriterion("cn_title <=", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleLike(String value) {
            addCriterion("cn_title like", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleNotLike(String value) {
            addCriterion("cn_title not like", value, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleIn(List<String> values) {
            addCriterion("cn_title in", values, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleNotIn(List<String> values) {
            addCriterion("cn_title not in", values, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleBetween(String value1, String value2) {
            addCriterion("cn_title between", value1, value2, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andCnTitleNotBetween(String value1, String value2) {
            addCriterion("cn_title not between", value1, value2, "cnTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleIsNull() {
            addCriterion("en_title is null");
            return (Criteria) this;
        }

        public Criteria andEnTitleIsNotNull() {
            addCriterion("en_title is not null");
            return (Criteria) this;
        }

        public Criteria andEnTitleEqualTo(String value) {
            addCriterion("en_title =", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleNotEqualTo(String value) {
            addCriterion("en_title <>", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleGreaterThan(String value) {
            addCriterion("en_title >", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleGreaterThanOrEqualTo(String value) {
            addCriterion("en_title >=", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleLessThan(String value) {
            addCriterion("en_title <", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleLessThanOrEqualTo(String value) {
            addCriterion("en_title <=", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleLike(String value) {
            addCriterion("en_title like", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleNotLike(String value) {
            addCriterion("en_title not like", value, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleIn(List<String> values) {
            addCriterion("en_title in", values, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleNotIn(List<String> values) {
            addCriterion("en_title not in", values, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleBetween(String value1, String value2) {
            addCriterion("en_title between", value1, value2, "enTitle");
            return (Criteria) this;
        }

        public Criteria andEnTitleNotBetween(String value1, String value2) {
            addCriterion("en_title not between", value1, value2, "enTitle");
            return (Criteria) this;
        }

        public Criteria andCnLinkIsNull() {
            addCriterion("cn_link is null");
            return (Criteria) this;
        }

        public Criteria andCnLinkIsNotNull() {
            addCriterion("cn_link is not null");
            return (Criteria) this;
        }

        public Criteria andCnLinkEqualTo(String value) {
            addCriterion("cn_link =", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkNotEqualTo(String value) {
            addCriterion("cn_link <>", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkGreaterThan(String value) {
            addCriterion("cn_link >", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkGreaterThanOrEqualTo(String value) {
            addCriterion("cn_link >=", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkLessThan(String value) {
            addCriterion("cn_link <", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkLessThanOrEqualTo(String value) {
            addCriterion("cn_link <=", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkLike(String value) {
            addCriterion("cn_link like", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkNotLike(String value) {
            addCriterion("cn_link not like", value, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkIn(List<String> values) {
            addCriterion("cn_link in", values, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkNotIn(List<String> values) {
            addCriterion("cn_link not in", values, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkBetween(String value1, String value2) {
            addCriterion("cn_link between", value1, value2, "cnLink");
            return (Criteria) this;
        }

        public Criteria andCnLinkNotBetween(String value1, String value2) {
            addCriterion("cn_link not between", value1, value2, "cnLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkIsNull() {
            addCriterion("en_link is null");
            return (Criteria) this;
        }

        public Criteria andEnLinkIsNotNull() {
            addCriterion("en_link is not null");
            return (Criteria) this;
        }

        public Criteria andEnLinkEqualTo(String value) {
            addCriterion("en_link =", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkNotEqualTo(String value) {
            addCriterion("en_link <>", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkGreaterThan(String value) {
            addCriterion("en_link >", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkGreaterThanOrEqualTo(String value) {
            addCriterion("en_link >=", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkLessThan(String value) {
            addCriterion("en_link <", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkLessThanOrEqualTo(String value) {
            addCriterion("en_link <=", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkLike(String value) {
            addCriterion("en_link like", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkNotLike(String value) {
            addCriterion("en_link not like", value, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkIn(List<String> values) {
            addCriterion("en_link in", values, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkNotIn(List<String> values) {
            addCriterion("en_link not in", values, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkBetween(String value1, String value2) {
            addCriterion("en_link between", value1, value2, "enLink");
            return (Criteria) this;
        }

        public Criteria andEnLinkNotBetween(String value1, String value2) {
            addCriterion("en_link not between", value1, value2, "enLink");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionIsNull() {
            addCriterion("cn_description is null");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionIsNotNull() {
            addCriterion("cn_description is not null");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionEqualTo(String value) {
            addCriterion("cn_description =", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionNotEqualTo(String value) {
            addCriterion("cn_description <>", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionGreaterThan(String value) {
            addCriterion("cn_description >", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("cn_description >=", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionLessThan(String value) {
            addCriterion("cn_description <", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionLessThanOrEqualTo(String value) {
            addCriterion("cn_description <=", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionLike(String value) {
            addCriterion("cn_description like", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionNotLike(String value) {
            addCriterion("cn_description not like", value, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionIn(List<String> values) {
            addCriterion("cn_description in", values, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionNotIn(List<String> values) {
            addCriterion("cn_description not in", values, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionBetween(String value1, String value2) {
            addCriterion("cn_description between", value1, value2, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andCnDescriptionNotBetween(String value1, String value2) {
            addCriterion("cn_description not between", value1, value2, "cnDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionIsNull() {
            addCriterion("en_description is null");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionIsNotNull() {
            addCriterion("en_description is not null");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionEqualTo(String value) {
            addCriterion("en_description =", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionNotEqualTo(String value) {
            addCriterion("en_description <>", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionGreaterThan(String value) {
            addCriterion("en_description >", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("en_description >=", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionLessThan(String value) {
            addCriterion("en_description <", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionLessThanOrEqualTo(String value) {
            addCriterion("en_description <=", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionLike(String value) {
            addCriterion("en_description like", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionNotLike(String value) {
            addCriterion("en_description not like", value, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionIn(List<String> values) {
            addCriterion("en_description in", values, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionNotIn(List<String> values) {
            addCriterion("en_description not in", values, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionBetween(String value1, String value2) {
            addCriterion("en_description between", value1, value2, "enDescription");
            return (Criteria) this;
        }

        public Criteria andEnDescriptionNotBetween(String value1, String value2) {
            addCriterion("en_description not between", value1, value2, "enDescription");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andQueueIsNull() {
            addCriterion("queue is null");
            return (Criteria) this;
        }

        public Criteria andQueueIsNotNull() {
            addCriterion("queue is not null");
            return (Criteria) this;
        }

        public Criteria andQueueEqualTo(Integer value) {
            addCriterion("queue =", value, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueNotEqualTo(Integer value) {
            addCriterion("queue <>", value, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueGreaterThan(Integer value) {
            addCriterion("queue >", value, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueGreaterThanOrEqualTo(Integer value) {
            addCriterion("queue >=", value, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueLessThan(Integer value) {
            addCriterion("queue <", value, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueLessThanOrEqualTo(Integer value) {
            addCriterion("queue <=", value, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueIn(List<Integer> values) {
            addCriterion("queue in", values, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueNotIn(List<Integer> values) {
            addCriterion("queue not in", values, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueBetween(Integer value1, Integer value2) {
            addCriterion("queue between", value1, value2, "queue");
            return (Criteria) this;
        }

        public Criteria andQueueNotBetween(Integer value1, Integer value2) {
            addCriterion("queue not between", value1, value2, "queue");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNull() {
            addCriterion("crt_time is null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNotNull() {
            addCriterion("crt_time is not null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeEqualTo(Date value) {
            addCriterion("crt_time =", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotEqualTo(Date value) {
            addCriterion("crt_time <>", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThan(Date value) {
            addCriterion("crt_time >", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("crt_time >=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThan(Date value) {
            addCriterion("crt_time <", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThanOrEqualTo(Date value) {
            addCriterion("crt_time <=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIn(List<Date> values) {
            addCriterion("crt_time in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotIn(List<Date> values) {
            addCriterion("crt_time not in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeBetween(Date value1, Date value2) {
            addCriterion("crt_time between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotBetween(Date value1, Date value2) {
            addCriterion("crt_time not between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNull() {
            addCriterion("upt_time is null");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNotNull() {
            addCriterion("upt_time is not null");
            return (Criteria) this;
        }

        public Criteria andUptTimeEqualTo(Date value) {
            addCriterion("upt_time =", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotEqualTo(Date value) {
            addCriterion("upt_time <>", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThan(Date value) {
            addCriterion("upt_time >", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upt_time >=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThan(Date value) {
            addCriterion("upt_time <", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThanOrEqualTo(Date value) {
            addCriterion("upt_time <=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeIn(List<Date> values) {
            addCriterion("upt_time in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotIn(List<Date> values) {
            addCriterion("upt_time not in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeBetween(Date value1, Date value2) {
            addCriterion("upt_time between", value1, value2, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotBetween(Date value1, Date value2) {
            addCriterion("upt_time not between", value1, value2, "uptTime");
            return (Criteria) this;
        }

        public Criteria andCrtByIsNull() {
            addCriterion("crt_by is null");
            return (Criteria) this;
        }

        public Criteria andCrtByIsNotNull() {
            addCriterion("crt_by is not null");
            return (Criteria) this;
        }

        public Criteria andCrtByEqualTo(Integer value) {
            addCriterion("crt_by =", value, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByNotEqualTo(Integer value) {
            addCriterion("crt_by <>", value, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByGreaterThan(Integer value) {
            addCriterion("crt_by >", value, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByGreaterThanOrEqualTo(Integer value) {
            addCriterion("crt_by >=", value, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByLessThan(Integer value) {
            addCriterion("crt_by <", value, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByLessThanOrEqualTo(Integer value) {
            addCriterion("crt_by <=", value, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByIn(List<Integer> values) {
            addCriterion("crt_by in", values, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByNotIn(List<Integer> values) {
            addCriterion("crt_by not in", values, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByBetween(Integer value1, Integer value2) {
            addCriterion("crt_by between", value1, value2, "crtBy");
            return (Criteria) this;
        }

        public Criteria andCrtByNotBetween(Integer value1, Integer value2) {
            addCriterion("crt_by not between", value1, value2, "crtBy");
            return (Criteria) this;
        }

        public Criteria andUptByIsNull() {
            addCriterion("upt_by is null");
            return (Criteria) this;
        }

        public Criteria andUptByIsNotNull() {
            addCriterion("upt_by is not null");
            return (Criteria) this;
        }

        public Criteria andUptByEqualTo(Integer value) {
            addCriterion("upt_by =", value, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByNotEqualTo(Integer value) {
            addCriterion("upt_by <>", value, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByGreaterThan(Integer value) {
            addCriterion("upt_by >", value, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByGreaterThanOrEqualTo(Integer value) {
            addCriterion("upt_by >=", value, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByLessThan(Integer value) {
            addCriterion("upt_by <", value, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByLessThanOrEqualTo(Integer value) {
            addCriterion("upt_by <=", value, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByIn(List<Integer> values) {
            addCriterion("upt_by in", values, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByNotIn(List<Integer> values) {
            addCriterion("upt_by not in", values, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByBetween(Integer value1, Integer value2) {
            addCriterion("upt_by between", value1, value2, "uptBy");
            return (Criteria) this;
        }

        public Criteria andUptByNotBetween(Integer value1, Integer value2) {
            addCriterion("upt_by not between", value1, value2, "uptBy");
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