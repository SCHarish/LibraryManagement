//package com.harish.library.repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.hibernate.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.harish.library.model.Tag;
//
//@Repository
//public abstract class TagRepositoryImpl implements TagRepository{
//
//	@Autowired
//	private EntityManager em;
//
//	@Override
//	public List<Tag> findISBNByTagName(String name) {
////		CriteriaBuilder cb = em.getCriteriaBuilder();
////	    CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
////
////	    Root<Tag> tag = cq.from(Tag.class);
////	    List<Predicate> predicates = new ArrayList<>();
////	    
////	    if (name != null) {
////	        predicates.add(cb.equal(tag.get("name"), name));
////	    }
//////	    if (title != null) {
//////	        predicates.add(cb.like(book.get("title"), "%" + title + "%"));
//////	    }
////	    cq.where(predicates.toArray(new Predicate[0]));
//	   // Query query = session.createQuery("SELECT t from Text t JOIN t.projects");
//	    return em.createQuery(
//	    	    "SELECT BOOK_ISBN FROM BOOK_TAGS c WHERE c.name LIKE :name")
//	    	    .setParameter("custName", name)
//	    	    .setMaxResults(10)
//	    	    .getResultList();
//	}
//	
////	public List<Tag> findTagsByName(String name){
////		Session session = sessionFactory.getCurrentSession();
////		  session.beginTransaction();
////		  Query query = session.createQuery("from Event ev where ev.organizer = :organizer");
////		  query.setCacheable(true);
////		  query.setEntity("organizer", organizer);
////		  List result = query.list();
//////		  session.getTransaction().commit();
////
////	}
//}
