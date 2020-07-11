package com.trelloclone.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class List extends Main {
	 @Id
	 @GeneratedValue (strategy = GenerationType.AUTO)
	 private Long id;
	 private String title;
     private Integer position;
	 private Integer status=1;
	 
	 @OneToMany(
			 cascade = CascadeType.REMOVE,
			 fetch = FetchType.LAZY,
			 mappedBy = "list"
			 )
	 @OrderBy("position asc")
	 private Set<Card> cards = new HashSet<>();
	 public List() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Set<Card> getCards() {
		return cards;
	}
	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	 
	 
}
