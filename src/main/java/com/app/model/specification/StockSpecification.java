package com.app.model.specification;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.Stock;
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
public class StockSpecification implements Specification<Stock> {

	private static final long serialVersionUID = -6496286854861568392L;

	private final String searchKey;

	@Override
	public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new LinkedList<Predicate>();

		if (!StringUtils.isNullOrEmpty(searchKey)) {
			String wrapSearch = CommonUtil.getWrapSearchLikeFull(searchKey);
			Predicate preSearchCode = criteriaBuilder.like(root.get("code"), wrapSearch);
			Predicate preSearchName = criteriaBuilder.like(root.get("name"), wrapSearch);
			Predicate preSearchKey = criteriaBuilder.or(preSearchCode, preSearchName);
			predicates.add(preSearchKey);
		}

		// default sort
		Order order = criteriaBuilder.desc(root.get("createdDate"));
		query.orderBy(order);

		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

}
