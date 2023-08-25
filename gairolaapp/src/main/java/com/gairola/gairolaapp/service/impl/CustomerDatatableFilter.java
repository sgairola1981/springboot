package com.gairola.gairolaapp.service.impl;

import java.util.ArrayList;

import com.gairola.gairolaapp.entity.Customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomerDatatableFilter implements org.springframework.data.jpa.domain.Specification<Customer> {

    String userQuery;

    public CustomerDatatableFilter(String queryString) {
        this.userQuery = queryString;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (userQuery != null && userQuery != "") {
            predicates.add(criteriaBuilder.like(root.get("firstName"), '%' + userQuery + '%'));
            predicates.add(criteriaBuilder.like(root.get("lastName"), '%' + userQuery + '%'));
            predicates.add(criteriaBuilder.like(root.get("city"), '%' + userQuery + '%'));
            predicates.add(criteriaBuilder.like(root.get("emailAddress"), '%' + userQuery + '%'));
            predicates.add(criteriaBuilder.like(root.get("phoneNumber"), '%' + userQuery + '%'));
            predicates.add(criteriaBuilder.like(root.get("country"), '%' + userQuery + '%'));
        }

        return (!predicates.isEmpty() ? criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]))
                : null);
    }
}