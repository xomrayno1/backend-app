package com.app.model.specification;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.Product;
import com.app.model.enumtype.Status;
import com.app.utils.CommonUtil;
import com.app.utils.StringUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProductSpecification implements Specification<Product>{

	private static final long serialVersionUID = 5133547074809300807L;
	
	private final String searchKey;
	private final Long supplierId;
	private final Long categoryId;

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new LinkedList<Predicate>();
		
		if(!StringUtils.isNullOrEmpty(searchKey)) {
			String wrapSearch = CommonUtil.getWrapSearchLikeFull(searchKey);
			Predicate preSearchCode = criteriaBuilder.like(root.get("productCode"), wrapSearch);
			Predicate preSearchName = criteriaBuilder.like(root.get("productName"), wrapSearch);
			Predicate preSearchKey = criteriaBuilder.or(preSearchCode, preSearchName);
			predicates.add(preSearchKey);
		}
		
		if(supplierId != null) {
			Predicate supplierPredicate = criteriaBuilder.equal(root.get("supplierId"), supplierId);
			predicates.add(supplierPredicate);
 		}
		
		if(categoryId != null) {
			Predicate categoryPredicate = criteriaBuilder.equal(root.get("categoryId"), categoryId);
			predicates.add(categoryPredicate);
 		}

		Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), Status.ACTIVE.getValue());
		predicates.add(statusPredicate);
		
		//default sort
		Order order = criteriaBuilder.desc(root.get("createdDate"));
		query.orderBy(order);
 		
		return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
	}

}
