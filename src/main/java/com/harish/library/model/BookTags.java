//package com.harish.library.model;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//
//@Entity
//@Table(name = "book_tags")
//public class BookTags {
//	@Id
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name = "book_isbn", referencedColumnName = "bookISBN")
//	private String bookISBN;
//	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name = "tag_id", referencedColumnName = "tagId")
//	private Long tagId;
//
//	public String getBookISBN() {
//		return bookISBN;
//	}
//
//	public void setBookISBN(String bookISBN) {
//		this.bookISBN = bookISBN;
//	}
//
//	public Long getTagId() {
//		return tagId;
//	}
//
//	public void setTagId(Long tagId) {
//		this.tagId = tagId;
//	}
//}
