package com.harish.library.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;


import com.harish.library.model.Tag;

public abstract class TagRepositoryImpl implements TagRepository{

	@Autowired
	private EntityManager em;

	public Tag findTagByName(String name) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);

	    Root<Tag> tag = cq.from(Tag.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (name != null) {
	        predicates.add(cb.equal(tag.get("name"), name));
	    }
//	    if (title != null) {
//	        predicates.add(cb.like(book.get("title"), "%" + title + "%"));
//	    }
	    cq.where(predicates.toArray(new Predicate[0]));

	    return em.createQuery(cq).getResultList().get(0);
	}
}
