package com.vbtn.taskunite.specification;

import com.vbtn.taskunite.domain.TaskCategory;
import com.vbtn.taskunite.domain.TaskCategory_;
import com.vbtn.taskunite.domain.Tasker;
import com.vbtn.taskunite.domain.Tasker_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TaskerSpecification implements Specification<Tasker> {

    private SearchCriteria criteria;

    public TaskerSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Tasker> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                    root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("order")){
           if (criteria.getValue().equals("ASC")){
               return criteriaQuery.orderBy(builder.asc(root.get(criteria.getKey()))).getRestriction();
           }else if (criteria.getValue().equals("DESC")){
               return criteriaQuery.orderBy(builder.desc(root.get(criteria.getKey()))).getRestriction();
           }
        }
        return null;
    }

    public static Specification<Tasker> getTaskersByTaskCategory(Long  taskCategoryId){
        return new Specification<Tasker>() {
            @Override
            public Predicate toPredicate(Root<Tasker> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                SetJoin<Tasker, TaskCategory> taskerTaskCategoryListJoin =  root.join(Tasker_.taskCategories);
                Predicate equalPredicate = criteriaBuilder.equal(taskerTaskCategoryListJoin.get(TaskCategory_.ID), taskCategoryId);
                criteriaQuery.distinct(true);
                return equalPredicate;
            }
        };
    }

}
