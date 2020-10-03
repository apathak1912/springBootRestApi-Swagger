package com.restapi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.restapi.dtos.StudentDetailsDTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentInfoSpecification implements Specification<StudentEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentDetailsDTO studentDetailsDTO;

	@Override
	public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(!StringUtils.isEmpty(studentDetailsDTO.getName())) {
		predicates.add(criteriaBuilder.like(root.get("name"), "%"+studentDetailsDTO.getName()+"%"));
		}if(!StringUtils.isEmpty(studentDetailsDTO.getAddress())) {
		predicates.add(criteriaBuilder.like(root.get("Address"), "%"+studentDetailsDTO.getAddress()+"%"));
		}if(!StringUtils.isEmpty(studentDetailsDTO.getStd())) {
			predicates.add(criteriaBuilder.like(root.get("std"), "%"+studentDetailsDTO.getStd()+"%"));	
		}
		query.orderBy(criteriaBuilder.desc(root.get("id")));
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
}
