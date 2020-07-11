package com.trelloclone.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trelloclone.demo.models.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
	public java.util.List<Checklist> findByCardId(Long cardId);
    public Long deleteByCardId(Long cardId);
}
