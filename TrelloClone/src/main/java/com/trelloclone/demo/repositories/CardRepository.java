package com.trelloclone.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trelloclone.demo.models.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
