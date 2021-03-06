package com.trelloclone.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trelloclone.demo.models.List;

public interface ListRepository extends JpaRepository<List, Long>{
	   public java.util.List<List> findByPositionGreaterThanEqual(Integer position);
	   public java.util.List<List> findByTitleContaining(String searchTerm);
}
