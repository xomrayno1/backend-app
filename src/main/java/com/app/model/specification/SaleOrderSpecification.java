package com.app.model.specification;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.SaleOrder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author tamnc
 *
 */
@Getter
@RequiredArgsConstructor
public class SaleOrderSpecification implements Specification<SaleOrder> {

	private static final long serialVersionUID = -6496286854861568392L;

	private final Date dateFrom;
	private final Date dateTo;

	@Override
	public Predicate toPredicate(Root<SaleOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new LinkedList<Predicate>();

		 
		// default sort
		Order order = criteriaBuilder.desc(root.get("createdDate"));
		query.orderBy(order);

		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

}
