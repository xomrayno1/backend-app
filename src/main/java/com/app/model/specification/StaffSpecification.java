package com.app.model.specification;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.Staff;
import com.app.utils.CommonUtil;
import com.app.utils.StringUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author tamnc
 *
 */
@Getter
@RequiredArgsConstructor
public class StaffSpecification implements Specification<Staff> {

	private static final long serialVersionUID = -2618222493930052487L;

	private final String searchKey;
	private final String phone;

	@Override
	public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new LinkedList<Predicate>();

		if (!StringUtils.isNullOrEmpty(searchKey)) {
			String wrapSearch = CommonUtil.getWrapSearchLikeFull(searchKey);
			Predicate preSearchCode = criteriaBuilder.like(root.get("staffCode"), wrapSearch);
			Predicate preSearchName = criteriaBuilder.like(root.get("staffName"), wrapSearch);
			Predicate preSearchKey = criteriaBuilder.or(preSearchCode, preSearchName);
			predicates.add(preSearchKey);
		}

		if (!StringUtils.isNullOrEmpty(phone)) {
			Predicate phonePredicate = criteriaBuilder.equal(root.get("phone"), phone);
			predicates.add(phonePredicate);
		}

		// default sort
		Order order = criteriaBuilder.desc(root.get("createdDate"));
		query.orderBy(order);

		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

}
